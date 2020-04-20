package br.com.tqi.test.development.exception;

import javax.servlet.ServletException;

public class BusinessException extends ServletException {
    public BusinessException(String message) {
        super(message);
    }
}
