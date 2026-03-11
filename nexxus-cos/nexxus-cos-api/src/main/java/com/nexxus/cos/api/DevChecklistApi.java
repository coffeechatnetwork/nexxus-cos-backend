package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.checklist.DevChecklistCategory;
import com.nexxus.cos.api.dto.checklist.CreateDevChecklistRequest;
import com.nexxus.cos.api.dto.checklist.DevChecklistDto;
import com.nexxus.cos.api.dto.checklist.DevChecklistListItem;
import com.nexxus.cos.api.dto.checklist.DevChecklistSummaryDto;
import com.nexxus.cos.api.dto.checklist.DevChecklistSummaryRequest;
import com.nexxus.cos.api.dto.checklist.EditDevChecklistRequest;

public interface DevChecklistApi {
    DevChecklistDto createDevChecklist(Long projectId, CreateDevChecklistRequest req);

    DevChecklistDto getByDisplayId(Long projectId, String displayId);

    DevChecklistDto edit(Long projectId, String displayId, EditDevChecklistRequest req);

    PageResult<DevChecklistListItem> listDevChecklists(Long projectId,
                                                       Long page,
                                                       Long pageSize,
                                                       DevChecklistCategory category);

    DevChecklistSummaryDto summary(Long projectId, DevChecklistSummaryRequest req);
}
