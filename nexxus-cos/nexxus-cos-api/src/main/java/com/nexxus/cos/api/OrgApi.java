package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.dto.CreateOrganizationRequest;
import com.nexxus.cos.api.dto.OrganizationDto;

public interface OrgApi {
    OrganizationDto createOrganization(CreateOrganizationRequest req);

    PageResult<OrganizationDto> listOrganization(Long page, Long pageSize);

    OrganizationDto getOrganizationByDisplayId(String displayId);

    OrganizationDto getOrganizationById(Long orgId);
}
