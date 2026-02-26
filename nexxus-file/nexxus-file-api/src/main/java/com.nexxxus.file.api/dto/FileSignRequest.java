package com.nexxxus.file.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.net.URL;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FileSignRequest implements Serializable {
    private URL orginalUrl;
}
