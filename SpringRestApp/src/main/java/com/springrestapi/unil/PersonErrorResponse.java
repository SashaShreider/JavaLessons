package com.springrestapi.unil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PersonErrorResponse {
    private String message;
    private long timestamp;
}
