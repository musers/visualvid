package com.ae.visuavid.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ApiRuntimeException extends AbstractThrowableProblem {
    private static final long serialVersionUID = 1L;

    public ApiRuntimeException() {
        super();
    }

    public ApiRuntimeException(String detailErrorMsg) {
        super(ErrorConstants.API_EXCEPTION, "Internal Server Error", Status.INTERNAL_SERVER_ERROR, detailErrorMsg);
    }
}
