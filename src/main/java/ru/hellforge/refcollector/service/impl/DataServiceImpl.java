package ru.hellforge.refcollector.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.service.DataService;

import java.io.FileWriter;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final ObjectMapper objectMapper;

    public void saveJsonToFile(Object object, String destination) throws IOException {
        String json = objectMapper.writeValueAsString(object);

        try (FileWriter writer = new FileWriter(destination)) {
            writer.write(json);
            writer.flush();
        } catch (IOException ignored) {

        }
    }

}