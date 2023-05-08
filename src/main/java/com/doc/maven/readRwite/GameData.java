package com.doc.maven.readRwite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameData {
    private List<Game> games;

    public GameData() {
        games = new ArrayList<>();
    }

    // Méthode pour extraire les informations du fichier inputData.txt
    public void loadGameData() {
        try {
            File inputFile = new File("inputData.txt");
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");

                String title = tokens[0].trim();
                int releaseYear = Integer.parseInt(tokens[1].trim());
                String developer = tokens[2].trim();

                Game game = new Game(title, releaseYear, developer);
                games.add(game);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier inputData.txt n'a pas été trouvé.");
        }
    }

    public List<Game> getGames() {
        return games;
    }
 // Méthode pour écrire les données dans un fichier
    public void writeGameData(List<Game> games) {
        try {
            PrintWriter writer = new PrintWriter(new File("outputData.txt"));

            for (Game game : games) {
                writer.println(game.getTitle() + "," + game.getReleaseYear() + "," + game.getDeveloper());
            }

            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : le fichier outputData.txt n'a pas pu être créé.");
        }
    }
}

