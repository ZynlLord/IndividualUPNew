package com.example.prac5up.controllers;


import com.example.prac5up.models.Address;
import com.example.prac5up.repos.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("")
    public String getAllAddresses(Model model) {
        model.addAttribute("addresses", addressRepository.findAll());
        return "addresses/list";
    }

    @GetMapping("/create")
    public String showAddressForm(Model model) {
        model.addAttribute("address", new Address());
        return "addresses/create";
    }

    @PostMapping("/create")
    public String createAddress(@Valid Address address) {
        addressRepository.save(address);
        return "redirect:/addresses";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid address ID: " + id));
        model.addAttribute("address", address);
        return "addresses/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateAddress(@PathVariable Long id, @Valid Address address) {
        address.setId(id);
        addressRepository.save(address);
        return "redirect:/addresses";
    }

    @GetMapping("/{id}/delete")
    public String deleteAddress(@PathVariable Long id) {
        addressRepository.deleteById(id);
        return "redirect:/addresses";
    }
}