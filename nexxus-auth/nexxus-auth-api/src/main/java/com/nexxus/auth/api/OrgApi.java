package com.nexxus.auth.api;

import com.nexxus.auth.api.dto.CreateOrganizationRequest;
import com.nexxus.auth.api.dto.OrganizationDto;
import com.nexxus.common.PageResult;

public interface OrgApi {
    OrganizationDto createOrganization(CreateOrganizationRequest req);

    PageResult<OrganizationDto> listOrganization(Long page, Long pageSize);

    OrganizationDto getOrganizationByDisplayId(String displayId);
}
