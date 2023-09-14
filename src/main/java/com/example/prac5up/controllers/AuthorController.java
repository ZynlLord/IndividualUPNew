package com.example.prac5up.controllers;


import com.example.prac5up.models.Author;
import com.example.prac5up.repos.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("")
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors/list";
    }

    @GetMapping("/create")
    public String showAuthorForm(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "authors/create";
    }

    @PostMapping("/create")
    public String createAuthor(@Valid Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author ID: " + id));
        model.addAttribute("author", author);
        return "authors/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateAuthor(@PathVariable Long id, @Valid Author author) {
        author.setId(id);
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }
}
