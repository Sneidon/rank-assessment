package com.rank.startup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, code = HttpStatus.I_AM_A_TEAPOT)
public class WagerGreaterThanBalanceException extends RuntimeException {
    public WagerGreaterThanBalanceException(String message) {
        super(message);
    }
}