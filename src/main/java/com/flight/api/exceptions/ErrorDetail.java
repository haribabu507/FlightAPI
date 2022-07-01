package com.flight.api.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetail {
    private final String name;
    private final String code;
    private final String message;
}
