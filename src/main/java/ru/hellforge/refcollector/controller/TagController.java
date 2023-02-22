package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.hellforge.refcollector.dto.TagDto;
import ru.hellforge.refcollector.service.TagService;

/**
 * TagController.
 *
 * @author dprokofev
 */
@Controller
@RequiredArgsConstructor
public class TagController {

  private final TagService tagService;

  @GetMapping("/tag")
  public String getAllTag(Model model) {
    model.addAttribute("tags", tagService.getAllTag());
    return "tag/collection";
  }

  @GetMapping("tag/new")
  public String newTag(@ModelAttribute("tag") TagDto tagDto, Model model) {
    return "tag/new";
  }

  @PostMapping("/tag")
  public String saveTag(@ModelAttribute TagDto tagDto, Model model) {
    tagService.saveTag(tagDto);
    return "redirect: tag";
  }

}