package ru.hellforge.refcollector.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.h2.util.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hellforge.refcollector.dto.*;
import ru.hellforge.refcollector.model.ExportProperties;
import ru.hellforge.refcollector.service.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static ru.hellforge.refcollector.util.BaseOperationService.collectionNotEmpty;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final ObjectMapper objectMapper;
    private final ReferenceService referenceService;
    private final EnvironmentService environmentService;
    private final RelationService baseRelationService;
    private final AccumulatesResponseService accumulatesResponseService;
    private final String EXPORT_DATE_FORMAT = "yyyy-MM-dd HH-mm-ss";
    private final String EXPORT_DELIMITER = " ";
    private final String FILE_FORMAT = ".txt";
    private final TagService tagService;

    @Transactional
    public void exportDumpToFile(ExportProperties properties) throws IOException {
        JsonDataDto jsonData = accumulatesResponseService.getExportDataDto(properties);
        String json = objectMapper.writeValueAsString(jsonData);

        try (FileWriter writer = new FileWriter(computeFileName(properties))) {
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error: check destination path");
        }
    }

    @Override
    public JsonDataDto importDataFromFile(JSONObject source) throws IOException {
        JsonDataDto jsonDataDto = importJsonFromFile(source);
        return updateStorageData(jsonDataDto);
    }

    private JsonDataDto importJsonFromFile(JSONObject json) throws IOException {
        return objectMapper.readValue(json.toString(), JsonDataDto.class);
    }

    private JsonDataDto updateStorageData(JsonDataDto jsonData) {
        List<ReferenceImportDto> savedReference = referenceService.importReference(jsonData.getReferences());
        List<TagImportDto> savedTags = tagService.importTag(jsonData.getTags());
        List<EnvironmentImportDto> savedEnvironment = environmentService.importEnvironment(jsonData.getEnvironments());
        List<RelationImportDto> savedBaseRelation = baseRelationService.importRelation(jsonData.getRelations());

        if (collectionNotEmpty(jsonData.getReferences()))
            savedReference = referenceService.importReference(jsonData.getReferences());
        if (collectionNotEmpty(jsonData.getTags()))
            savedTags = tagService.importTag(jsonData.getTags());
        if (collectionNotEmpty(jsonData.getEnvironments()))
            savedEnvironment = environmentService.importEnvironment(jsonData.getEnvironments());
        if (collectionNotEmpty(jsonData.getRelations()))
            savedBaseRelation = baseRelationService.importRelation(jsonData.getRelations());

        return JsonDataDto.builder()
                .references(savedReference)
                .tags(savedTags)
                .environments(savedEnvironment)
                .relations(savedBaseRelation)
                .build();
    }

    private String computeFileName(ExportProperties properties) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern(EXPORT_DATE_FORMAT));
        String formattedDate = EXPORT_DELIMITER.concat(EXPORT_DELIMITER)
                .concat(date);

        return properties.getDestination()
                .concat(properties.getFileName())
                .concat(properties.getAppendDate().equals(TRUE) ? formattedDate : EMPTY)
                .concat(FILE_FORMAT);
    }

}