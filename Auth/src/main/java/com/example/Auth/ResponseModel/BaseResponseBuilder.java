package com.example.Auth.ResponseModel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponseBuilder {
    private  String message;
    private  int statusCode;
}
