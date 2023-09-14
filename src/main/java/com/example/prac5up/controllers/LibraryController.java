package com.example.prac5up.controllers;

import com.example.prac5up.models.Library;
import com.example.prac5up.repos.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/libraries")
public class LibraryController {

    @Autowired
    private LibraryRepository libraryRepository;

    @GetMapping("")
    public String getAllLibraries(Model model) {
        List<Library> libraries = libraryRepository.findAll();
        model.addAttribute("libraries", libraries);
        return "libraries/list";
    }

    @GetMapping("/create")
    public String showLibraryForm(Model model) {
        model.addAttribute("library", new Library());
        return "libraries/create";
    }

    @PostMapping("/create")
    public String createLibrary(@Valid @ModelAttribute("library") Library library) {
        libraryRepository.save(library);
        return "redirect:/libraries";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid library ID: " + id));
        model.addAttribute("library", library);
        return "libraries/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateLibrary(@PathVariable Long id, @Valid Library library) {
        library.setId(id);
        libraryRepository.save(library);
        return "redirect:/libraries";
    }

    @GetMapping("/{id}/delete")
    public String deleteLibrary(@PathVariable Long id) {
        libraryRepository.deleteById(id);
        return "redirect:/libraries";
    }
}
