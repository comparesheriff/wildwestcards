package dev.comparesheriff.wildwestcards.controllers.web;

import dev.comparesheriff.wildwestcards.models.Game;
import dev.comparesheriff.wildwestcards.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class IndexController extends AbstractWebController{
    private final Logger LOG = LoggerFactory.getLogger(IndexController.class);
    private final GameService gameService;

    public IndexController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * GET - Content Page via PathVariable
     *
     * @param cpage Path of the Content Page
     * @param model model for stuff
     * @return the requested page via {@link #getPage(String, ModelMap)}
     */
    @GetMapping(value = "/c/{cpage}")
    public String getContentPage(@PathVariable("cpage") String cpage, @ModelAttribute("model") ModelMap model) {
        return getPage("c-" + cpage, model);
    }

    /**
     * GET - HomePage
     *
     * @param model model to add attributes to
     * @return the home page via {@link #getPage(String, ModelMap)}
     */
    @GetMapping(value = "/")
    public String getHomePage(@ModelAttribute("model") ModelMap model) {
        List<Game> games = gameService.getGames();
        model.addAttribute("games", games);
        return getPage("home-page", model);
    }

}
