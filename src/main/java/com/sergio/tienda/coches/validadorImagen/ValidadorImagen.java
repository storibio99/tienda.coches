package com.sergio.tienda.coches.validadorImagen;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;



public class ValidadorImagen {

    private static final long MAX_BYTES = 20L * 1024 * 1024; // 20 MB
    private static final Set<String> PERMITIDOS = Set.of("image/jpeg", "image/png", "image/webp");
    private static final Tika TIKA = new Tika();

    /** Devuelve null si es válida (o si no se subió archivo), o el mensaje de error. */
    public static String validar(MultipartFile f) {
        if (f == null || f.isEmpty()) return null;

        if (f.getSize() > MAX_BYTES)
            return "La imagen no puede superar los 20 MB";

        try (InputStream in = f.getInputStream()) {
            String tipo = TIKA.detect(in);
            if (!PERMITIDOS.contains(tipo))
                return "Formato no permitido. Solo JPG, PNG o WEBP";
        } catch (IOException e) {
            return "No se pudo leer la imagen";
        }
        return null;
    }
}