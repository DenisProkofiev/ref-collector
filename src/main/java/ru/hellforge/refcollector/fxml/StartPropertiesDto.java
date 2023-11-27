package ru.hellforge.refcollector.fxml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
public class StartPropertiesDto {
    private String applicationHost;
    private String dataBaseLocation;
}