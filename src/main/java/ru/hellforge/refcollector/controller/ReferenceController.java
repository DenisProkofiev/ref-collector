package ru.hellforge.refcollector.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hellforge.refcollector.dto.*;
import ru.hellforge.refcollector.service.*;

/**
 * ReferenceController.
 *
 * @author dprokofev
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/reference")
public class ReferenceController {

  private final ReferenceService referenceService;
  private final TagService tagService;
  private final ReferenceTagRelationService referenceTagRelationService;
  private final EnvironmentService environmentService;
  private final AccumulatesResponseService accumulatesResponseService;

  @GetMapping
  public String getReference(ReferenceFilterDto referenceFilterDto, Model model) {
    //ReferenceFilterDto referenceFilterDto = isEmpty(tagsIdList) ? null : ReferenceFilterDto.builder().tagsIdList(tagsIdList).build();

    List<TagDto> tags = tagService.getAllTag(TagFilter.builder().build());
    //List<ReferenceTagRelationDto> relations = referenceTagRelationService.getAllReferenceTagRelation();
    List<ReferenceResponseDto> references = accumulatesResponseService.getReferenceResponse(referenceFilterDto);
    List<EnvironmentDto> environments = environmentService.getAllEnvironment();

    model.addAttribute("environments", environments);
    model.addAttribute("references", references);
    model.addAttribute("tags", tags);

    return "Index";
  }

}