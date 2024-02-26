package ru.hellforge.refcollector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * ReferenceDto.
 *
 * @author dprokofev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("objectCode")
    private UUID objectCode;
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("description")
    private String description;
    @JsonProperty("createDate")
    private LocalDate createDate;
    @JsonProperty("tags")
    private List<UUID> tagObjectCodeList;
    @JsonProperty("environmentId")
    private Long environmentId;
    @JsonProperty("environmentObjectCode")
    private UUID environmentObjectCode;
}