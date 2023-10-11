package ru.hellforge.refcollector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonDataDto {
    private List<ReferenceImportDto> references;
    private List<TagImportDto> tags;
    private List<EnvironmentImportDto> environments;
    private List<BaseRelationImportDto> baseRelations;
}
