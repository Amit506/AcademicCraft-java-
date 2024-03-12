package com.example.Auth.ResponseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignUpResponse extends BaseTokeResponse {

    Long userId;

}
