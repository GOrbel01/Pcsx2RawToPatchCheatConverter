package com.geo.conv.controller;

import com.geo.conv.FileConverter;
import com.geo.conv.mode.Mode;
import com.geo.conv.model.PatchFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class ConverterController {

    @RequestMapping("/convert")
    public PatchFile getConvertedPatchFile(@RequestParam("mode") String mode, @RequestParam("file") String file) {
        return null;
    }

    private PatchFile convertFile(Mode mode, File file) throws IOException {
        FileConverter fileConverter = new FileConverter();

        try {
            return fileConverter.convertFile(file, mode);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
