package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hellforge.refcollector.dto.TagFilter;
import ru.hellforge.refcollector.service.TagService;

/**
 * TagController.
 *
 * @author dprokofev
 */
@Controller
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

  private final TagService tagService;

  @GetMapping
  public String getAllTag(@RequestParam(required = false) String filter, Model model) {
    model.addAttribute("tags", tagService.getAllTag(TagFilter.builder().name(filter).build()));
    return "tag/tags";
  }

  @GetMapping("/delete/{id}")
  public String deleteReference(@PathVariable(name = "id") Long id) {
    tagService.deleteById(id);
    return "redirect:/tag/tags";
  }

}