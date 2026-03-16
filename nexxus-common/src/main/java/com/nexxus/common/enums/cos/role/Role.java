package com.nexxus.common.enums.cos.role;

import com.nexxus.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role implements BaseEnum {
    ADMIN("ADMIN", "Super Admin"),
    CCS("CCS", "Senior Client Contact"),
    EV("EV", "External Viewer"),
    PM("PM", "Project Manager"),
    CCA("CCA", "Active Client Contact"),
    PCE("PCE", "External Project Consultant"),
    PCI("PCI", "Internal Project Consultant"),
    CM("CM", "Client Manager"),
    ;


    private final String value;
    private final String desc;
}
