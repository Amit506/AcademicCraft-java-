package com.example.Auth.ResponseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseTokeResponse extends BaseResponse {
    String accessToken;
    String refreshToken;
}
