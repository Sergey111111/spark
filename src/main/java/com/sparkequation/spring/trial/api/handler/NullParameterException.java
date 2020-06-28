package com.sparkequation.spring.trial.api.handler;

import static com.sparkequation.spring.trial.api.handler.ErrorCodes.BAD_PARAMETER;

public class NullParameterException extends ServiceException {

    private static final String MESSAGE = "Parameter %s must not be null";

    public NullParameterException(String name) {
        super(String.format(MESSAGE, name), BAD_PARAMETER);
    }
}
