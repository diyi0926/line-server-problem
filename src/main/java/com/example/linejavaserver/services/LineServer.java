package com.example.linejavaserver.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.springframework.util.ResourceUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LineServer {
    private static LineServer instance = null;
    private List<Long> indexes = new ArrayList<>();
    private LineServer() {
        try{
            long curIndex = 0;
            indexes.add(curIndex);
            InputStream inputStream = new ClassPathResource("file.txt").getInputStream();
            Scanner sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                curIndex += sc.nextLine().length() + 2; // length of next line and '/n'
                indexes.add(curIndex);
            }
            sc.close();
        } catch(IOException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        
    }
    public static LineServer getInstance() {
        if (instance == null) instance = new LineServer();
        return instance;
    }
    
    public List<Long> getIndexes() {
        return this.indexes;
    }

    public void setIndexes(List<Long> indexes) {
        this.indexes = indexes;
    }


}