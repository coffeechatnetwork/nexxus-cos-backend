package com.nexxus.server.controller.v1;

import com.nexxus.auth.api.OrgApi;
import com.nexxus.auth.api.dto.CreateOrganizationRequest;
import com.nexxus.auth.api.dto.OrganizationDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/organizations")
public class OrganizationController {

    private final OrgApi orgApi;

    @PostMapping("")
    public OrganizationDto createOrganization(@RequestBody @Valid CreateOrganizationRequest req) {
        return orgApi.createOrganization(req);
    }
}
