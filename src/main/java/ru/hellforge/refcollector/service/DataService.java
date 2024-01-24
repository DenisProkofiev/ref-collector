package ru.hellforge.refcollector.service;

import org.h2.util.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import ru.hellforge.refcollector.dto.JsonDataDto;
import ru.hellforge.refcollector.model.ExportProperties;

import java.io.IOException;

public interface DataService {
    void exportDumpToFile(ExportProperties properties) throws IOException;

    JsonDataDto importDataFromFile(JSONObject json) throws IOException;
}
