package com.power.exception;

import lombok.Data;

/**
 * @author Powerveil
 * @Date 2024/1/21 10:45
 */
@Data
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8087887878787878787L;

    private String id;

    public UserNotFoundException(String id) {
        super("user not found");
        this.id = id;
    }
}
