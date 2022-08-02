package com.gmarquezp.web.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/archivos")
public class ArchivoController {


    @Value("${app.upload.files}")
    private String pathUploadFiles;

    @PostMapping("/subir")
    // MultipartFile // Especifica que sera un archivo binario
    public String subir(@RequestParam("archivo") MultipartFile archivo,
                        Model model) {

        String originalFilename = archivo.getOriginalFilename();
        System.out.println("originalFilename = " + originalFilename);
        originalFilename = originalFilename.replace(" ", "_");
        System.out.println("originalFilename replace = " + originalFilename);
        String extensionArchivo = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        System.out.println("extensionArchivo = " + extensionArchivo);

        // Generando el archivo binario
        File archivoFile = new File(this.pathUploadFiles + originalFilename);
        try {
            // Moviendo al directorio generado
            archivo.transferTo(archivoFile);
        } catch (IOException e) {
            System.out.println("Error al subir el archivo:=\t" + e.getMessage());
            throw new RuntimeException(e);
        }

        model.addAttribute("originalFilename", originalFilename);
        model.addAttribute("absolutePath", archivoFile.getAbsolutePath());
        model.addAttribute("size", archivo.getSize());
        model.addAttribute("extensionArchivo", extensionArchivo);

        return "archivos/index";
    }

}
