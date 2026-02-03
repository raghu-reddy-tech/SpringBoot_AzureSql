package com.cloudshop.controller;

import com.cloudshop.service.AzureBlobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

    private final AzureBlobService blobService;

    public ImageController(AzureBlobService blobService) {
        this.blobService = blobService;
    }

    @GetMapping("/show-images")
    public String showImages(Model model) {
        model.addAttribute("imageUrls", blobService.listImageUrls());
        return "images"; // images.html
    }
}

