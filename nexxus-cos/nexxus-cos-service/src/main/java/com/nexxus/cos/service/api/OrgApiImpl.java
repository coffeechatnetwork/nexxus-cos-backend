package com.nexxus.cos.service.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.auth.OrganizationStatus;
import com.nexxus.cos.api.OrgApi;
import com.nexxus.cos.api.dto.CreateOrganizationRequest;
import com.nexxus.cos.api.dto.OrganizationDto;
import com.nexxus.cos.service.entity.OrganizationEntity;
import com.nexxus.cos.service.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrgApiImpl implements OrgApi {

    private final OrganizationService organizationService;

    @Override
    public OrganizationDto createOrganization(CreateOrganizationRequest req) {
        String orgCode = req.getCode();
        OrganizationEntity organizationEntity = organizationService.getByCode(orgCode);
        if (organizationEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("organization already exist"));
        }
        String displayId = UUID.randomUUID().toString();
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

    @Override
    public PageResult<OrganizationDto> listOrganization(Long page, Long pageSize) {
        Page<OrganizationEntity> entityPage = organizationService.listOrganizations(page, pageSize);

        List<OrganizationDto> dtoList = entityPage.getRecords().stream()
                .map(entity -> OrganizationDto.builder()
                        .displayId(entity.getDisplayId())
                        .name(entity.getName())
                        .code(entity.getCode())
                        .status(entity.getStatus())
                        .build())
                .collect(Collectors.toList());

        return PageResult.<OrganizationDto>builder()
                .records(dtoList)
                .total(entityPage.getTotal())
                .pageSize(entityPage.getSize())
                .page(entityPage.getCurrent())
                .build();
    }

    @Override
    public OrganizationDto getOrganizationByDisplayId(String displayId) {
        OrganizationEntity organizationEntity = organizationService.getByDisplayId(displayId);
        if (organizationEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("organization not found"));
        }

        return OrganizationDto.builder()
                .displayId(organizationEntity.getDisplayId())
                .name(organizationEntity.getName())
                .code(organizationEntity.getCode())
                .status(organizationEntity.getStatus())
                .build();
    }

    @Override
    public OrganizationDto getOrganizationById(Long orgId) {
        OrganizationEntity organizationEntity = organizationService.getById(orgId);
        if (organizationEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("organization not found"));
        }
        return OrganizationDto.builder()
                .displayId(organizationEntity.getDisplayId())
                .name(organizationEntity.getName())
                .code(organizationEntity.getCode())
                .status(organizationEntity.getStatus())
                .build();
    }
}
