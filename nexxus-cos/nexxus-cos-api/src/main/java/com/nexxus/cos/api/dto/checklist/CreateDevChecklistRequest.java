package com.nexxus.cos.api.dto.checklist;

import com.nexxus.common.enums.cos.checklist.DevChecklistCategory;
import com.nexxus.common.vo.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDevChecklistRequest implements Serializable {
    private Long projectId;
    private String title;
    private String description;
    private DevChecklistCategory category;
    private List<Attachment> attachments;
}
