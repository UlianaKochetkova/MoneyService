package com.moneyservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse {
    String code;
    String message;

    public static BaseResponse buildOkResponse(){
        return new BaseResponse("S-200", "");
    }
}
