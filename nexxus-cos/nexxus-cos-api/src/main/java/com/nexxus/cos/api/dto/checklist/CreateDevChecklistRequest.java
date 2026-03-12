package com.nexxus.cos.api.dto.checklist;

import com.nexxus.common.enums.cos.checklist.DevChecklistCategory;
import com.nexxus.common.vo.Attachment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(max = 64)
    private String title;
    private String description;
    @NotNull
    private DevChecklistCategory category;
    private List<Attachment> attachments;
}
