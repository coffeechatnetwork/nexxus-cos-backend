package com.nexxus.auth.service.api;

import com.nexxus.auth.api.OrgApi;
import com.nexxus.auth.api.dto.CreateOrganizationRequest;
import com.nexxus.auth.api.dto.OrganizationDto;
import com.nexxus.auth.service.entity.OrganizationEntity;
import com.nexxus.auth.service.service.OrganizationService;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.enums.auth.OrganizationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrgApiImpl implements OrgApi {

    private final OrganizationService organizationService;

    @Override
    public OrganizationDto createOrganization(CreateOrganizationRequest req) {
        String displayId = UUID.randomUUID().toString();
        OrganizationEntity organizationEntity = organizationService.getByDisplayId(displayId);
        if (organizationEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("organization already exist"));
        }
        OrganizationEntity newOrg = OrganizationEntity.builder()
                .displayId(displayId)
                .name(req.getName())
                .code(req.getCode())
                .status(OrganizationStatus.ACTIVE)
                .build();
        organizationService.save(newOrg);

        return OrganizationDto.builder()
                .displayId(newOrg.getDisplayId())
                .name(newOrg.getName())
                .code(newOrg.getCode())
                .status(newOrg.getStatus())
                .build();
    }
}
