package com.nexxus.cos.service.api;

import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.checklist.DevChecklistCategory;
import com.nexxus.cos.api.DevChecklistApi;
import com.nexxus.cos.api.dto.checklist.CreateDevChecklistRequest;
import com.nexxus.cos.api.dto.checklist.DevChecklistDto;
import com.nexxus.cos.api.dto.checklist.DevChecklistListItem;
import com.nexxus.cos.api.dto.checklist.DevChecklistSummaryDto;
import com.nexxus.cos.api.dto.checklist.DevChecklistSummaryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DevChecklistApiImpl implements DevChecklistApi {
    @Override
    public DevChecklistDto createDevChecklist(CreateDevChecklistRequest req) {
        return null;
    }

    @Override
    public DevChecklistDto getByDisplayId(String displayId) {
        return null;
    }

    @Override
    public PageResult<DevChecklistListItem> listDevChecklists(Long projectId, Long page, Long pageSize, DevChecklistCategory category) {
        return null;
    }

    @Override
    public DevChecklistSummaryDto summary(DevChecklistSummaryRequest req) {
        return null;
    }
}
