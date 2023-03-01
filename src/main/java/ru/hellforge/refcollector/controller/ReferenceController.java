package ru.hellforge.refcollector.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilter;
import ru.hellforge.refcollector.service.ReferenceService;

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

  @GetMapping
  public String getReference(@RequestParam(required = false) String filter, Model model) {
    List<ReferenceDto> references = referenceService.getAllReference(ReferenceFilter.builder().tags(filter).build());
    model.addAttribute("references", references);
    return "reference/collection";
  }

  @GetMapping("/filter")
  public String filterReference(@ModelAttribute("filter") ReferenceFilter filter) {
    return "reference/filter";
  }

  @GetMapping("/new")
  public String newReference(@ModelAttribute("reference") ReferenceDto reference) {
    return "reference/new";
  }

  @PostMapping
  public String saveReference(@ModelAttribute ReferenceDto referenceDto, Model model) {
    referenceService.saveReference(referenceDto);
    return "redirect:/reference";
  }

  @GetMapping("/delete/{id}")
  public String deleteReference(@PathVariable(name = "id") Long id) {
    referenceService.deleteById(id);
    return "redirect:/reference";
  }

  @PostMapping("/update/{id}")
  public String updateReference(@PathVariable(name = "id") Long id, @RequestBody ReferenceDto referenceDto, Model model) {
    referenceService.saveReference(referenceDto);
    return "redirect:/reference";
  }

}
