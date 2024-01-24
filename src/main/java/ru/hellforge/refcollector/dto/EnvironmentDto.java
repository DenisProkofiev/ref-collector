package ru.hellforge.refcollector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("objectCode")
    private String objectCode;
    @JsonProperty("name")
    private String name;
}