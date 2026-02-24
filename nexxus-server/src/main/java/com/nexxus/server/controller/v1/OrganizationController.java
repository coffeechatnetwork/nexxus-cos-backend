package com.nexxus.server.controller.v1;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.OrgApi;
import com.nexxus.cos.api.dto.CreateOrganizationRequest;
import com.nexxus.cos.api.dto.ListOrganizationRequest;
import com.nexxus.cos.api.dto.OrganizationDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
