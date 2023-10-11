package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.JsonDataDto;
import ru.hellforge.refcollector.model.ExportProperties;

import java.io.IOException;

public interface DataService {
    void saveJsonToFile(Object object, ExportProperties properties) throws IOException;

    void importDataFromFile(String source) throws IOException;
}
