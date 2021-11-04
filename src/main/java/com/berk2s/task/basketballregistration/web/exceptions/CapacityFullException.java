package com.berk2s.task.basketballregistration.web.exceptions;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class CapacityFullException extends RuntimeException implements GraphQLError {

    private final String message;

    public CapacityFullException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        // TODO: It might be different error type?
        return ErrorType.ValidationError;
    }
}
