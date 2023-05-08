package com.doc.maven.readRwite;

import java.util.List;


public class Main {
	
    public static void main(String[] args) {
        // Charger les données du fichier inputData.txt dans une liste de jeux
        List<Game> games = Game.loadGameData();

        // Écrire les données de la liste de jeux dans un nouveau fichier outputData.txt
        GameData gameData = new GameData();
        gameData.writeGameData(games);
    }
}

