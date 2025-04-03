package com.example.desafioprocessoseletivoseplagapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadFotoController {

    @GetMapping("/upload-fotos")
    public String showUploadPage(Model model) {
        // Se necessário, adicione atributos ao model, por exemplo, pessoaId
        model.addAttribute("pessoaId", 1);
        return "uploadFoto"; // Retorna o nome do template (upload.html)
    }
}
