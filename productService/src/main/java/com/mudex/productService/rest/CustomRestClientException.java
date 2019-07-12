package com.mudex.productService.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class CustomRestClientException extends HttpClientErrorException {

    public CustomRestClientException(String msg, HttpStatus status) {
        super(status,msg);
    }

}
