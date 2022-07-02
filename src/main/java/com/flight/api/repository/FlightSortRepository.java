package com.flight.api.repository;

import com.flight.api.entities.FlightData;
import com.flight.api.model.FilterInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class FlightSortRepository {

    private final EntityManager entityManager;

    public FlightSortRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * TO get the list of ordered flight details based on the provided filters
     * @param info
     * @return
     */
    public List<FlightData> sortedFlightInfo(FilterInfo info) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlightData> flightDataCriteriaQuery = cb.createQuery(FlightData.class);
        Root<FlightData> flightDataRoot = flightDataCriteriaQuery.from(FlightData.class);

        Predicate predicates = getPredicates(info, flightDataRoot, cb);
        flightDataCriteriaQuery.where(predicates);

        List<Order> orders = new ArrayList<>();
        if ("asc".equalsIgnoreCase(info.getSortingOrder())) {
            orders.add(cb.asc(flightDataRoot.<Date>get("depatureTime")));
            orders.add(cb.asc(flightDataRoot.<Date>get("arrivalTime")));
            orders.add(cb.asc(flightDataRoot.<Integer>get("price")));
        } else if ("desc".equalsIgnoreCase(info.getSortingOrder())) {
            orders.add(cb.desc(flightDataRoot.<Date>get("depatureTime")));
            orders.add(cb.desc(flightDataRoot.<Date>get("arrivalTime")));
            orders.add(cb.desc(flightDataRoot.<Integer>get("price")));
        }

        flightDataCriteriaQuery.orderBy(orders);
        TypedQuery<FlightData> typedQuery = entityManager.createQuery(flightDataCriteriaQuery);
        return  typedQuery.getResultList();

    }

    /**
     * Preparing the list of different predicates with the table columns
     * @param info
     * @param root
     * @return
     */
    private Predicate getPredicates(FilterInfo info, Root<FlightData> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(info.getDepatureTime())){
            predicates.add(cb.greaterThanOrEqualTo(root.get("depatureTime"), info.getDepatureTime()));
        }
        if (Objects.nonNull(info.getArrivalTime())) {
            predicates.add(cb.lessThanOrEqualTo(root.get("arrivalTime"), info.getArrivalTime()));
        }
        if (Objects.nonNull(info.getPrice())) {
            predicates.add(cb.equal(root.<Integer>get("price"), info.getPrice()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
