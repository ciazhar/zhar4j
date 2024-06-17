package com.ciazhar.postgres.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private String error;
    private Object data;
    private Long count;

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }


    public ApiResponse(String message, Object data, Long count) {
        this.message = message;
        this.data = data;
        this.count = count;
    }

}
