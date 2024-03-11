package com.example.Auth.ResponseModel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse {
    private  String message;
    private  int statusCode;
}
