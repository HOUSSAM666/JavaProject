package com.doc.maven.readRwite;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Game {
    private String title;
    private int releaseYear;
    private String developer;

    // Constructeur avec paramètres
    public Game(String title, int releaseYear, String developer) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.developer = developer;
    }

    // Constructeur sans paramètres
    public Game() {
        this.title = "";
        this.releaseYear = 0;
        this.developer = "";
    }

    // Accesseurs (getters)
    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDeveloper() {
        return developer;
    }

    // Mutateurs (setters)
    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    // Méthode play
    public void play() {
        System.out.println("Playing " + title + " developed by " + developer + " released in " + releaseYear);
    }

    // Redéfinition de la méthode toString
    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", developer='" + developer + '\'' +
                '}';
    }

    // Redéfinition de la méthode equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return releaseYear == game.releaseYear &&
                Objects.equals(title, game.title) &&
                Objects.equals(developer, game.developer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseYear, developer);
    }

    // Méthode pour charger les données du fichier
    
    public static List<Game> loadGameData() {
        GameData gameData = new GameData();
        gameData.loadGameData();
        return gameData.getGames();
    }

    // Méthode pour charger les données du fichier Excel
    
    public static List<Game> loadExcelData(String filePath) throws IOException {
        List<Game> games = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        // Skip the header row
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String title = row.getCell(0).getStringCellValue();
            int releaseYear = (int) row.getCell(1).getNumericCellValue();
            String developer = row.getCell(2).getStringCellValue();
            games.add(new Game(title, releaseYear, developer));
        }

        workbook.close();
        fileInputStream.close();

        return games;
    }
    
    public static void saveExcelData(List<Game> games, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Games");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Title");
        headerRow.createCell(1).setCellValue("Release Year");
        headerRow.createCell(2).setCellValue("Developer");

        // Create data rows
        int rowNum = 1;
        for (Game game : games) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(game.getTitle());
            row.createCell(1).setCellValue(game.getReleaseYear());
            row.createCell(2).setCellValue(game.getDeveloper());
        }

        // Write the workbook to the output file
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
    }
    
    
    	// 7.b
    
    public static List<Game> loadJsonData(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("inputDataJson.txt");
        List<Game> games = mapper.readValue(file, TypeFactory.defaultInstance().constructCollectionType(List.class, Game.class));
        return games;
    }
    
    	// 7.c
    
    public static void saveJsonData(List<Game> games) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("outputDateJson.txt");
        mapper.writeValue(file, games);
    }


    


}
