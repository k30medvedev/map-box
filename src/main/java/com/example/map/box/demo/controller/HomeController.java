package com.example.map.box.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.map.box.demo.model.Model;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final ServiceBox service;

    @GetMapping
    public String getCity(Model model) {
        return service.getCity(model.getRequest());
    }

}
