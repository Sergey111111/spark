package com.sparkequation.spring.trial.api.handler;

import static com.sparkequation.spring.trial.api.handler.ErrorCodes.BAD_PARAMETER;

public class ValidationException  extends ServiceException {

    private static final String MESSAGE = "Incorrect value: %s";

    public ValidationException(String value) {
        super(String.format(MESSAGE, value), BAD_PARAMETER);
    }
}
