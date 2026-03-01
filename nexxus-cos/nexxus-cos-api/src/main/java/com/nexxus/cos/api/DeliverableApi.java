package com.nexxus.cos.api;

import com.nexxus.cos.api.dto.deliverable.CreateDeliverableRequest;
import com.nexxus.cos.api.dto.deliverable.DeliverableDto;
import com.nexxus.cos.api.dto.deliverable.EditDeliverableRequest;

public interface DeliverableApi {
    DeliverableDto createDeliverable(CreateDeliverableRequest req);

    DeliverableDto getByDisplayId(String displayId);

    DeliverableDto edit(String displayId, EditDeliverableRequest req);
}
