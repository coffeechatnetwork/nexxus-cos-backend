package com.nexxus.cos.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectRequest implements Serializable {
    @NotBlank
    @Size(max = 64)
    private String name;
    @Size(max = 32)
    private String slug;
    @Size(min = 1, max = 2048)
    @URL
    private String logoUrl;
    private List<@NotBlank @URL String> imageUrls;
}
