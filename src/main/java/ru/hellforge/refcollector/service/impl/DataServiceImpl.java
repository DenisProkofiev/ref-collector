package ru.hellforge.refcollector.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hellforge.refcollector.dto.*;
import ru.hellforge.refcollector.model.ExportProperties;
import ru.hellforge.refcollector.service.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.springframework.util.CollectionUtils.isEmpty;

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
    public JsonDataDto importDataFromFile(String source) throws IOException {
        JsonDataDto jsonDataDto = importJsonFromFile(source);
        return updateStorageData(jsonDataDto);
    }

    private JsonDataDto importJsonFromFile(String source) throws IOException {
        Path sourcePath = Paths.get(source);
        String jsonString = new String(Files.readAllBytes(sourcePath));

        return objectMapper.readValue(jsonString, JsonDataDto.class);
    }

    private JsonDataDto updateStorageData(JsonDataDto jsonData) {
        List<ReferenceImportDto> savedReference = referenceService.importReference(jsonData.getReferences());
        List<TagImportDto> savedTags = tagService.importTag(jsonData.getTags());
        List<EnvironmentImportDto> savedEnvironment = environmentService.importEnvironment(jsonData.getEnvironments());
        List<RelationImportDto> savedBaseRelation = baseRelationService.importRelation(jsonData.getRelations());

        System.out.println(savedTags);
        System.out.println(savedEnvironment);
        System.out.println(savedBaseRelation);
//        if (!isEmpty(jsonData.getReferences()))
//            savedReference = referenceService.importReference(jsonData.getReferences());
//        if (!isEmpty(jsonData.getTags()))
//            savedTags = tagService.importTag(jsonData.getTags());
//        if (!isEmpty(jsonData.getEnvironments()))
//            savedEnvironment = environmentService.importEnvironment(jsonData.getEnvironments());
//        if (!isEmpty(jsonData.getRelations()))
//            savedBaseRelation = baseRelationService.importRelation(jsonData.getRelations());

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
                .concat(".txt");
    }

}