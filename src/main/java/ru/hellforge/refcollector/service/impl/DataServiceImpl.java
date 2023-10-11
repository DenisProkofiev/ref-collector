package ru.hellforge.refcollector.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.JsonDataDto;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceImportDto;
import ru.hellforge.refcollector.model.ExportProperties;
import ru.hellforge.refcollector.service.AccumulatesResponseService;
import ru.hellforge.refcollector.service.DataService;
import ru.hellforge.refcollector.service.ReferenceService;
import ru.hellforge.refcollector.service.TagService;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final ObjectMapper objectMapper;
    private final ReferenceService referenceService;
    private final TagService tagService;
    private final AccumulatesResponseService accumulatesResponseService;


    public void saveJsonToFile(ExportProperties properties) throws IOException {
        JsonDataDto jsonData = accumulatesResponseService.getExportDataDto();
        String json = objectMapper.writeValueAsString(jsonData);

        try (FileWriter writer = new FileWriter(properties.getDestination(), true)) {
            writer.write(json);
            writer.flush();
        } catch (IOException ignored) {

        }
    }

    @Override
    public void importDataFromFile(String source) throws IOException {
        JsonDataDto jsonDataDto = importJsonFromFile(source);
        updateRowsInDataBase(jsonDataDto);
    }

    private JsonDataDto importJsonFromFile(String source) throws IOException {
        Path sourcePath = Paths.get(source);
        String jsonString = new String(Files.readAllBytes(sourcePath));

        return objectMapper.readValue(jsonString, JsonDataDto.class);
    }

    private void updateRowsInDataBase(JsonDataDto jsonData) {
        List<ReferenceImportDto> referenceDtoList = jsonData.getReferences();


    }

    private List<String> compareImportDataWithStorageData(JsonDataDto jsonData) {
        List<String> dataBaseUrls = referenceService.getAllReference(null).stream()
                .map(ReferenceDto::getUrl)
                .collect(toList());

        List<String> importDataUrls = jsonData.getReferences().stream()
                .map(ReferenceImportDto::getUrl)
                .collect(toList());

        List<ReferenceImportDto> referenceImportDtos = jsonData.getReferences().stream()
                .filter(row -> compareTwoList(row.getUrl(), dataBaseUrls))
                .collect(toList());


        referenceService.importReferenceList(referenceImportDtos);
        return null;
    }

    private Boolean compareTwoList(String importDataRow, List<String> storageData) {
        return !storageData.contains(importDataRow);
    }

}