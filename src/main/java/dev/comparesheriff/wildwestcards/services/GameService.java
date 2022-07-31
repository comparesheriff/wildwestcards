package dev.comparesheriff.wildwestcards.services;

import dev.comparesheriff.wildwestcards.models.Game;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private final List<Game> games = new ArrayList<>();

    public void addGame(Game game) {
        if (game != null) {
            games.add(game);
        }
    }

    public List<Game> getGames() {
        return games;
    }

    public Game getGameById(String id) {
        if(StringUtils.isNotBlank(id)){
            for (Game game : games) {
                if(game.getId().equals(id)){
                    return game;
                }
            }
        }
        return null;
    }
}
