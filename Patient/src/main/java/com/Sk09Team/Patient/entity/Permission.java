package com.Sk09Team.Patient.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    PATIENT_READ("patient:read"),
    PATIENT_UPDATE("patient:update"),
    PATIENT_CREATE("patient:create"),
    PATIENT_DELETE("patient:delete"),

    ;

    @Getter
    private final String permission;
}
