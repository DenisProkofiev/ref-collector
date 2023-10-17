package ru.hellforge.refcollector.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    private final BaseRelationService baseRelationService;
    private final AccumulatesResponseService accumulatesResponseService;
    private final String EXPORT_DATE_FORMAT = "yyyy-MM-dd_HH-MM";
    private final String EXPORT_DELIMITER = "_";
    private TagService tagService;

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
        List<ReferenceImportDto> savedReference = null;
        List<TagImportDto> savedTags = null;
        List<EnvironmentImportDto> savedEnvironment = null;
        List<BaseRelationImportDto> savedBaseRelation = null;

        if (!isEmpty(jsonData.getReferences()))
            savedReference = referenceService.importReference(jsonData.getReferences());
        if (!isEmpty(jsonData.getTags()))
            savedTags = tagService.importTag(jsonData.getTags());
        if(!isEmpty(jsonData.getEnvironments()))
            savedEnvironment = environmentService.importEnvironment(jsonData.getEnvironments());
//        if(!isEmpty(jsonData.getBaseRelations()))
//            savedBaseRelation = baseRelationService.importBaseRelation(jsonData.getBaseRelations());

        return JsonDataDto.builder()
                .references(savedReference)
                .tags(savedTags)
                .environments(savedEnvironment)
                .baseRelations(savedBaseRelation)
                .build();
    }

    private String computeFileName(ExportProperties properties) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern(EXPORT_DATE_FORMAT));

        return properties.getDestination()
                .concat(properties.getFileName())
                .concat(EXPORT_DELIMITER)
                .concat(EXPORT_DELIMITER)
                .concat(properties.getAppendDate().equals(TRUE) ? date : EMPTY)
                .concat(".txt");
    }

}