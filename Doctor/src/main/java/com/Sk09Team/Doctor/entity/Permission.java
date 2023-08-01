package com.Sk09Team.Doctor.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    DOCTOR_READ("admin:read"),
    DOCTOR_UPDATE("admin:update"),
    DOCTOR_CREATE("admin:create"),
    DOCTOR_DELETE("admin:delete"),

    ;

    @Getter
    private final String permission;
}
