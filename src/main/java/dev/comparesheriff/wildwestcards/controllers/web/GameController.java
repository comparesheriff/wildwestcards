package dev.comparesheriff.wildwestcards.controllers.web;

import dev.comparesheriff.wildwestcards.models.Game;
import dev.comparesheriff.wildwestcards.services.GameService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController extends AbstractWebController {

    final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/game")
    public String gameOverview(@RequestParam(name = "id") final String id, @ModelAttribute("model") ModelMap model) {
        final Game gameById = gameService.getGameById(id);
        if (gameById == null) {
            model.addAttribute("errorMsg", "Couldn't find a game with that id");
            return getPage("error", model);
        }
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
        if(StringUtils.isNotBlank(playerHandle) && gameById.hasPlayerWithHandle(playerHandle)){
            return getPage("game-overview", model);
        }
        return getPage("game-join", model);
    }

}
