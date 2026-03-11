package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.dto.keydate.CreateKeyDateRequest;
import com.nexxus.cos.api.dto.keydate.EditKeyDateRequest;
import com.nexxus.cos.api.dto.keydate.KeyDateDto;
import com.nexxus.cos.api.dto.keydate.KeyDateListItem;

import java.time.Instant;

public interface KeyDateApi {
    KeyDateDto createKeyDate(Long projectId, CreateKeyDateRequest req);

    KeyDateDto getByDisplayId(Long projectId, String displayId);

    KeyDateDto edit(Long projectId, String displayId, EditKeyDateRequest req);

    PageResult<KeyDateListItem> list(Long projectId, Long page, Long pageSize, Instant startDate, Instant endDate);
}
