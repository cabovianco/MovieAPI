package com.cabovianco.movieapi.exception;

public class NullParameterException extends Exception {

    public NullParameterException() {
        super("Params can not be null");
    }

}
