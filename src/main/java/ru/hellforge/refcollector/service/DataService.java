package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.JsonDataDto;
import ru.hellforge.refcollector.model.ExportProperties;

import java.io.IOException;

public interface DataService {
    void exportDumpToFile(ExportProperties properties) throws IOException;

    JsonDataDto importDataFromFile(String source) throws IOException;
}
