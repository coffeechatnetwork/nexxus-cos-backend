package com.nexxus.cos.service.api;

import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.cos.api.KeyDateApi;
import com.nexxus.cos.api.dto.keydate.CreateKeyDateRequest;
import com.nexxus.cos.api.dto.keydate.EditKeyDateRequest;
import com.nexxus.cos.api.dto.keydate.KeyDateDto;
import com.nexxus.cos.api.dto.keydate.KeyDateListItem;
import com.nexxus.cos.service.api.converter.KeyDateConverter;
import com.nexxus.cos.service.entity.KeyDateEntity;
import com.nexxus.cos.service.service.KeyDateService;
import com.nexxus.cos.service.service.query.KeyDateQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeyDateApiImpl implements KeyDateApi {

    private final KeyDateService keyDateService;
    private final KeyDateConverter keyDateConverter;

    @Override
    public KeyDateDto createKeyDate(Long projectId, CreateKeyDateRequest req) {
        AccountInfo accountInfo = AccountInfoContext.get();
        KeyDateEntity keyDateEntity = keyDateService.getByProjectIdAndTitle(projectId, req.getTitle());
        if (keyDateEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("keyDate already exist"));
        }
        KeyDateEntity newKeyDate = KeyDateEntity.builder()
                .orgId(accountInfo.getOrgId())
                .projectId(projectId)
                .displayId(UUID.randomUUID().toString())
                .title(req.getTitle())
                .shortDesc(req.getShortDesc())
                .longDesc(req.getLongDesc())
                .category(req.getCategory())
                .referenceDate(req.getReferenceDate())
                .attachments(req.getAttachments())
                .build();
        keyDateService.save(newKeyDate);
        return keyDateConverter.toKeyDateDto(newKeyDate);
    }

    @Override
    public KeyDateDto getByDisplayId(Long projectId, String displayId) {
        KeyDateEntity keyDateEntity = keyDateService.getByDisplayId(displayId);
        if (keyDateEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("keyDate not found"));
        }
        return keyDateConverter.toKeyDateDto(keyDateEntity);
    }

    @Override
    public KeyDateDto edit(Long projectId, String displayId, EditKeyDateRequest req) {
        KeyDateEntity keyDateEntity = keyDateService.getByDisplayId(displayId);
        if (keyDateEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("keyDate not found"));
        }
        keyDateEntity.setAttachments(req.getAttachments());
        keyDateService.updateById(keyDateEntity);
        return keyDateConverter.toKeyDateDto(keyDateEntity);
    }

    @Override
    public PageResult<KeyDateListItem> list(Long projectId, Long page, Long pageSize, Instant startDate, Instant endDate) {
        KeyDateQuery query = KeyDateQuery.builder()
                .projectId(projectId)
                .page(page)
                .pageSize(pageSize)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        var entityPage = keyDateService.listKeyDates(query);

        List<KeyDateListItem> items = entityPage.getRecords().stream()
                .map(keyDateConverter::toKeyDateListItem)
                .collect(Collectors.toList());

        return PageResult.<KeyDateListItem>builder()
                .records(items)
                .total(entityPage.getTotal())
                .pageSize(entityPage.getSize())
                .page(entityPage.getCurrent())
                .build();
    }
}
