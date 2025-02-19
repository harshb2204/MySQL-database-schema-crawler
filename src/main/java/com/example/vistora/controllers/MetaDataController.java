package com.example.vistora.controllers;


import com.example.vistora.models.TableMetaData;
import com.example.vistora.services.MetaDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metadata")
public class MetaDataController {

    private final MetaDataService metadataService;



    public MetaDataController(MetaDataService metadataService) {
        this.metadataService = metadataService;
    }

    @GetMapping("/tables")
    public List<TableMetaData> getTables() {
        return metadataService.getDatabaseMetadata();
    }
}
