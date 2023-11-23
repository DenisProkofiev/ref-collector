package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hellforge.refcollector.dto.*;
import ru.hellforge.refcollector.service.AccumulatesResponseService;
import ru.hellforge.refcollector.service.EnvironmentService;
import ru.hellforge.refcollector.service.TagService;

import java.util.List;

/**
 * ReferenceController.
 *
 * @author dprokofev
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/reference")
public class ReferenceController {
    private final TagService tagService;
    private final EnvironmentService environmentService;
    private final AccumulatesResponseService accumulatesResponseService;

    @GetMapping
    public String getReference(ReferenceFilterDto referenceFilterDto, Model model) {
        List<TagDto> tags = tagService.getAllTag(TagFilter.builder().build());
        List<ReferenceResponseDto> references = accumulatesResponseService.getReferenceResponse(referenceFilterDto);
        List<EnvironmentDto> environments = environmentService.getAllEnvironment();

        model.addAttribute("environments", environments);
        model.addAttribute("references", references);
        model.addAttribute("tags", tags);

        return "Index";
    }

}