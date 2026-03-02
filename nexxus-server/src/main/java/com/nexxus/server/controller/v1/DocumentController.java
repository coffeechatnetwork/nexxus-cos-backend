package com.nexxus.server.controller.v1;

import com.nexxus.cos.api.dto.document.CreateDocumentFolderRequest;
import com.nexxus.cos.api.dto.document.DocumentFolderDto;
import com.nexxus.cos.api.dto.document.DocumentFolderListItem;
import com.nexxus.cos.api.dto.document.ListDocumentFolderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/documents")
@RequiredArgsConstructor
public class DocumentController {

    @PostMapping("/folders")
    public DocumentFolderDto createFolder(@RequestBody @Valid CreateDocumentFolderRequest req) {
        return null;
    }

    @PostMapping("/folders/list")
    public List<DocumentFolderListItem> listFolders(@RequestBody @Valid ListDocumentFolderRequest req) {
        return null;
    }
}
