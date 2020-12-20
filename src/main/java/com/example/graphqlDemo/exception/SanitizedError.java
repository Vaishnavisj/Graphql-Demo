package com.example.graphqlDemo.exception;

import graphql.ExceptionWhileDataFetching;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SanitizedError extends ExceptionWhileDataFetching {
	
	public SanitizedError(ExceptionWhileDataFetching inner) {
        super(null, inner.getException(), null);
    }

    @Override
    @JsonIgnore
    public Throwable getException() {
        return super.getException();
    }

}
