package com.onlineshopping.project2restapi.exception;

public class DuplicateSupplierNameException extends RuntimeException {
    public DuplicateSupplierNameException( String message ){ super(message); }
}
