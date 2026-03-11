package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.dto.deliverable.CreateDeliverableRequest;
import com.nexxus.cos.api.dto.deliverable.DeliverableDashboardDto;
import com.nexxus.cos.api.dto.deliverable.DeliverableDashboardRequest;
import com.nexxus.cos.api.dto.deliverable.DeliverableDto;
import com.nexxus.cos.api.dto.deliverable.DeliverableListItem;
import com.nexxus.cos.api.dto.deliverable.EditDeliverableRequest;

public interface DeliverableApi {
    DeliverableDto createDeliverable(Long projectId, CreateDeliverableRequest req);

    DeliverableDto getByDisplayId(Long projectId, String displayId);

    DeliverableDto edit(Long projectId, String displayId, EditDeliverableRequest req);

    PageResult<DeliverableListItem> list(Long projectId, Long page, Long pageSize);

    DeliverableDashboardDto dashboard(Long projectId, DeliverableDashboardRequest req);
}
