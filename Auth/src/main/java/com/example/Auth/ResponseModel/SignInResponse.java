package com.example.Auth.ResponseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignInResponse extends BaseTokeResponse {
    Long userId;

}
