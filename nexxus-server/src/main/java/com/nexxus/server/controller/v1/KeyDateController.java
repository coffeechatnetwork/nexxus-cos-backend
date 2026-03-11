package com.nexxus.server.controller.v1;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.KeyDateApi;
import com.nexxus.cos.api.dto.keydate.CreateKeyDateRequest;
import com.nexxus.cos.api.dto.keydate.EditKeyDateRequest;
import com.nexxus.cos.api.dto.keydate.KeyDateDto;
import com.nexxus.cos.api.dto.keydate.KeyDateListItem;
import com.nexxus.cos.api.dto.keydate.ListKeyDateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/projects/{projectId}/key-dates")
@RequiredArgsConstructor
public class KeyDateController {

    private final KeyDateApi keyDateApi;

    @PostMapping("")
    public KeyDateDto createKeyDate(@PathVariable Long projectId, @RequestBody @Valid CreateKeyDateRequest req) {
        return keyDateApi.createKeyDate(projectId, req);
    }

    @GetMapping("/{displayId}")
    public KeyDateDto detail(@PathVariable Long projectId, @PathVariable String displayId) {
        return keyDateApi.getByDisplayId(projectId, displayId);
    }

    @PostMapping("/{displayId}/edit")
    public KeyDateDto edit(@PathVariable Long projectId, @PathVariable String displayId, @RequestBody @Valid EditKeyDateRequest req) {
        return keyDateApi.edit(projectId, displayId, req);
    }

    @PostMapping("/list")
    public PageResult<KeyDateListItem> list(@PathVariable Long projectId, @RequestBody @Valid ListKeyDateRequest req) {
        return keyDateApi.list(projectId, req.getPage(), req.getPageSize(),
                req.getStartDate(), req.getEndDate());
    }
}
