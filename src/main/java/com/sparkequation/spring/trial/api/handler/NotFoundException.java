package com.sparkequation.spring.trial.api.handler;

import static com.sparkequation.spring.trial.api.handler.ErrorCodes.NOT_FOUND;

public class NotFoundException extends ServiceException {

    private static final String MESSAGE = "There's no product with id %d";

    public NotFoundException(Integer id) {
        super(String.format(MESSAGE, id), NOT_FOUND);
    }
}
