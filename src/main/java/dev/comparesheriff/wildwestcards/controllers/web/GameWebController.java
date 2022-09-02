package dev.comparesheriff.wildwestcards.controllers.web;

import dev.comparesheriff.wildwestcards.models.Game;
import dev.comparesheriff.wildwestcards.models.Player;
import dev.comparesheriff.wildwestcards.services.GameService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class GameWebController extends AbstractWebController {

    final GameService gameService;

    public GameWebController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/game")
    public String gameOverview(@RequestParam(name = "id") final String id, @ModelAttribute("model") ModelMap model, HttpServletRequest request) {
        String playerHandle = ((String) request.getSession().getAttribute("playerHandle"));
        final Game gameById = gameService.getGameById(id);
        if (gameById == null) {
            model.addAttribute("errorMsg", "Couldn't find a game with that id");
            return getPage("error", model);
        }
        Optional<Player> playerWithHandle = gameById.getPlayerWithHandle(playerHandle);
        if (playerWithHandle.isEmpty()) {
            model.addAttribute("errorMsg", "Couldn't find your player in the game");
            return getPage("error", model);
        }
        model.addAttribute("player", playerWithHandle.get());
        model.addAttribute("game", gameById);
        return getPage("game-overview", model);
    }

    @GetMapping("/game/join")
    public String gameJoin(@RequestParam(name = "id") final String id, @RequestParam(name = "errorMsg", required = false) final String errorMsg, @ModelAttribute("model") ModelMap model, final HttpServletRequest request) {
        String playerHandle = ((String) request.getSession().getAttribute("playerHandle"));
        final Game gameById = gameService.getGameById(id);
        if (gameById == null || StringUtils.isNotBlank(errorMsg)) {
            model.addAttribute("errorMsg", StringUtils.defaultIfBlank(errorMsg, "Couldn't find a game with that id"));
            return getPage("error", model);
        }
        model.addAttribute("game", gameById);
        Optional<Player> playerWithHandle = gameById.getPlayerWithHandle(playerHandle);
        if(StringUtils.isNotBlank(playerHandle) && playerWithHandle.isPresent()){
            model.addAttribute("player", playerWithHandle.get());
            return getPage("game-overview", model);
        }
        return getPage("game-join", model);
    }

    @GetMapping("/game/start")
    public String gameStart(@RequestParam(name = "id") final String id, @ModelAttribute("model") ModelMap model, final HttpServletRequest request) {
        String playerHandle = ((String) request.getSession().getAttribute("playerHandle"));
        final Game game = gameService.getGameById(id);
        if (game == null || StringUtils.isBlank(playerHandle)) {
            model.addAttribute("errorMsg", "Couldn't find a game with that id and your user handle");
            return getPage("error", model);
        }
        Optional<Player> playerWithHandle = game.getPlayerWithHandle(playerHandle);
        if(playerWithHandle.isPresent() && playerWithHandle.get().isHost() && game.canStart()){
            model.addAttribute("player", playerWithHandle.get());
            game.start();
            return getPage("game-session", model);
        }
        model.addAttribute("errorMsg", "It seems the game can not start yet");
        return getPage("error", model);
    }

}
