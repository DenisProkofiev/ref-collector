package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.service.ReferenceService;

/**
 * ReferenceController.
 *
 * @author dprokofev
 */
@Controller
@RequiredArgsConstructor
public class ReferenceController {

  private final ReferenceService referenceService;

  @GetMapping("/reference")
  public String getAllReference(Model model) {
    model.addAttribute("references", referenceService.getAllReference());
    return "reference/collection";
  }

  @GetMapping("reference/new")
  public String newReference(@ModelAttribute("reference") ReferenceDto reference) {
    return "reference/new";
  }

  @PostMapping("/reference")
  public String saveReference(@ModelAttribute ReferenceDto referenceDto, Model model) {
    referenceService.saveReference(referenceDto);
    return "redirect:reference";
  }

}
