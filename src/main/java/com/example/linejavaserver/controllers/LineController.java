package com.example.linejavaserver.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.linejavaserver.services.LineServer;
@RestController
public class LineController {
    @GetMapping("/lines/{lineIndex}")
    public String findLine(
        @PathVariable("lineIndex") Integer lineIndex) {
            try{
                LineServer ls = LineServer.getInstance();
                List<Long> indexes = ls.getIndexes();
                File file = new ClassPathResource("file.txt").getFile();
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                raf.seek(indexes.get(lineIndex));
                byte[] bytes = new byte[(int)(indexes.get(lineIndex + 1) - indexes.get(lineIndex))];
                raf.read(bytes);
                raf.close();
                return new String(bytes);
            } catch(IOException e) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            } catch(IndexOutOfBoundsException e) {
                throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE);
            }
           

    }
}