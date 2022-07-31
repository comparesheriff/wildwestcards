package dev.comparesheriff.wildwestcards.controllers.rest;

import dev.comparesheriff.wildwestcards.models.AISpeed;
import dev.comparesheriff.wildwestcards.models.Game;
import dev.comparesheriff.wildwestcards.models.Player;
import dev.comparesheriff.wildwestcards.services.GameService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/.games")
public class GamesController {

    private final GameService gameService;

    public GamesController(final GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/all")
    public List<Game> getAllGames() {
        return gameService.getGames();
    }

    @GetMapping("/get")
    public Game getGameById(@RequestParam(name = "id") final String id) {
        return gameService.getGameById(id);
    }

    @PostMapping("/add")
    public void createGame(@RequestParam(name = "isPublic", required = false, defaultValue = "false") final boolean isPublic, @RequestParam(name = "aiSpeed", required = false, defaultValue = "INSTANT") final AISpeed aiSpeed, @RequestParam(name = "useExtension", required = false, defaultValue = "false") final boolean useExtension, @RequestParam(name = "handle") final String handle, HttpServletRequest request, HttpServletResponse response) {
        Player player = new Player(UUID.randomUUID().toString(), handle, true);
        Game game = new Game(UUID.randomUUID().toString(), useExtension, isPublic, player, aiSpeed);
        gameService.addGame(game);
        try {
            request.getSession().setAttribute("playerHandle", handle);
            response.sendRedirect(String.format("/game?id=%s", game.getId()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/join")
    public void joinGame(@RequestParam(name = "id") final String id, @RequestParam(name = "handle") final String handle, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final HttpSession session = request.getSession();
        final String playerHandle = (String) session.getAttribute("playerHandle");
        final Game game = gameService.getGameById(id);
        if (StringUtils.isNotBlank(playerHandle) && game.hasPlayerWithHandle(playerHandle)) {
            response.sendRedirect(String.format("/game?id=%s", game.getId()));
        }
        final Player player = new Player(UUID.randomUUID().toString(), handle, true);
        boolean couldAdd = game.addPlayer(player);
        if (!couldAdd) {
            response.sendRedirect(String.format("/game/join?id=%s&errorMsg=%s", game.getId(), "Could not add User, game is full or handle already exists"));
        } else {
            session.setAttribute("playerHandle", handle);
            response.sendRedirect(String.format("/game?id=%s", game.getId()));
        }
    }

}
