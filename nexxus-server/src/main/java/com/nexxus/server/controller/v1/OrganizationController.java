package com.nexxus.server.controller.v1;

import com.nexxus.auth.api.OrgApi;
import com.nexxus.auth.api.dto.CreateOrganizationRequest;
import com.nexxus.auth.api.dto.ListOrganizationRequest;
import com.nexxus.auth.api.dto.OrganizationDto;
import com.nexxus.common.PageResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/organizations")
public class OrganizationController {

    private final OrgApi orgApi;

    @PostMapping("")
    public OrganizationDto createOrganization(@RequestBody @Valid CreateOrganizationRequest req) {
        return orgApi.createOrganization(req);
    }

    @GetMapping("/{displayId}")
    public OrganizationDto getOrganizationByDisplayId(@PathVariable String displayId) {
        return orgApi.getOrganizationByDisplayId(displayId);
    }

    @PostMapping("/list")
    public PageResult<OrganizationDto> listOrganizationByCursor(
            @RequestBody @Valid ListOrganizationRequest req) {
        return orgApi.listOrganization(req.getPage(), req.getPageSize());
    }
}
