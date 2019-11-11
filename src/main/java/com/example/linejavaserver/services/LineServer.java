package com.example.linejavaserver.services;

import java.io.InputStream;
import java.util.*;


public class LineServer {
    private static LineServer instance = null;
    private List<Long> indexes = new ArrayList<>();
    private LineServer() {
        long curIndex = 0;
        indexes.add(curIndex);
        InputStream inputStream = this.getClass().getResourceAsStream("/file.txt");
        Scanner sc = new Scanner(inputStream, "UTF-8");
        while (sc.hasNextLine()) {
            curIndex += sc.nextLine().length() + 2; // length of next line and '/n'
            indexes.add(curIndex);
        }
        sc.close();
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