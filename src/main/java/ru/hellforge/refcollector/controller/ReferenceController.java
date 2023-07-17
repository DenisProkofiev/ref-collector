package ru.hellforge.refcollector.controller;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hellforge.refcollector.dto.*;
import ru.hellforge.refcollector.service.AccumulatesResponseService;
import ru.hellforge.refcollector.service.ReferenceService;
import ru.hellforge.refcollector.service.ReferenceTagRelationService;
import ru.hellforge.refcollector.service.TagService;

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
  private final AccumulatesResponseService accumulatesResponseService;

  @GetMapping
  public String getReference(ReferenceFilterDto referenceFilterDto, Model model) {
    //ReferenceFilterDto referenceFilterDto = isEmpty(tagsIdList) ? null : ReferenceFilterDto.builder().tagsIdList(tagsIdList).build();

    List<TagDto> tags = tagService.getAllTag(TagFilter.builder().build());
    List<ReferenceTagRelationDto> relations = referenceTagRelationService.getAllReferenceTagRelation();
    List<ReferenceResponseDto> references = accumulatesResponseService.getReferenceResponse(referenceFilterDto);

    model.addAttribute("references", references);
    model.addAttribute("tags", tags);
    model.addAttribute("relations", relations);

    return "reference/collection";
  }

}