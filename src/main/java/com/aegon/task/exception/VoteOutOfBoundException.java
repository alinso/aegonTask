package com.aegon.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VoteOutOfBoundException extends RuntimeException {
    public VoteOutOfBoundException() {
        super("Value of the vote is out its bouds (1-10) ");
    }
}
