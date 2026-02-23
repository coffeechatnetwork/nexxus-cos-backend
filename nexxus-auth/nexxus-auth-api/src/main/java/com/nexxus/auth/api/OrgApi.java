package com.nexxus.auth.api;

import com.nexxus.auth.api.dto.CreateOrganizationRequest;
import com.nexxus.auth.api.dto.OrganizationDto;

public interface OrgApi {
    OrganizationDto createOrganization(CreateOrganizationRequest req);
}
