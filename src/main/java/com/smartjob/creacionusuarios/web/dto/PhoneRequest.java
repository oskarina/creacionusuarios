package com.smartjob.creacionusuarios.web.dto;

public record PhoneRequest(
        String number,
        String cityCode,
        String countryCode
) {
}
