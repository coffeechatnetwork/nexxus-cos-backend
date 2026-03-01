package com.nexxus.cos.service.api;

import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.enums.cos.deliverable.DeliverableStatus;
import com.nexxus.cos.api.DeliverableApi;
import com.nexxus.cos.api.dto.deliverable.CreateDeliverableRequest;
import com.nexxus.cos.api.dto.deliverable.DeliverableDto;
import com.nexxus.cos.api.dto.deliverable.EditDeliverableRequest;
import com.nexxus.cos.service.api.converter.DeliverableConverter;
import com.nexxus.cos.service.entity.DeliverableEntity;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.DeliverableService;
import com.nexxus.cos.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliverableApiImpl implements DeliverableApi {

    private final UserService userService;
    private final DeliverableService deliverableService;
    private final DeliverableConverter deliverableConverter;

    @Override
    public DeliverableDto createDeliverable(CreateDeliverableRequest req) {
        // check the duplicate
        DeliverableEntity deliverableEntity = deliverableService.getByTitle(req.getTitle());
        if (deliverableEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("deliverable already exist"));
        }

        // check the assignee
        UUID assigneeId = req.getAssignee();
        UserEntity assignee = userService.getByAccountId(assigneeId);
        if (assignee == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("assignee not found."));
        }

        DeliverableEntity newDeliverable = DeliverableEntity.builder()
                .displayId(UUID.randomUUID().toString())
                .title(req.getTitle())
                .shortDesc(req.getShortDesc())
                .longDesc(req.getLongDesc())
                .assignee(req.getAssignee())
                .deadline(req.getDeadline())
                .status(DeliverableStatus.NOT_YET_STARTED)
                .attachments(req.getAttachments())
                .build();
        deliverableService.save(newDeliverable);
        return deliverableConverter.toDeliverableDto(newDeliverable);
    }

    @Override
    public DeliverableDto getByDisplayId(String displayId) {
        DeliverableEntity deliverableEntity = deliverableService.getByDisplayId(displayId);
        if (deliverableEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("deliverable not found"));
        }
        return deliverableConverter.toDeliverableDto(deliverableEntity);
    }

    @Override
    public DeliverableDto edit(String displayId, EditDeliverableRequest req) {
        DeliverableEntity deliverableEntity = deliverableService.getByDisplayId(displayId);
        if (deliverableEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("deliverable not found"));
        }
        deliverableEntity.setAttachments(req.getAttachments());
        deliverableEntity.setParticipants(req.getParticipants());
        deliverableEntity.setStatus(req.getStatus());
        deliverableService.updateById(deliverableEntity);
        return deliverableConverter.toDeliverableDto(deliverableEntity);
    }
}
