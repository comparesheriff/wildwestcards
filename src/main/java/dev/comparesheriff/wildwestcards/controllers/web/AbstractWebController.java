package dev.comparesheriff.wildwestcards.controllers.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

abstract public class AbstractWebController {
    protected Logger LOG = LoggerFactory.getLogger(AbstractWebController.class);

    /**
     * Return the index page and embed the requested page in it via model attribute
     *
     * @param ftlpage the requested ftl
     * @param model   the model to add attributes to
     * @return the index page with the requested page embedded in it
     */
    public String getPage(String ftlpage, ModelMap model) {
        model.addAttribute("page", ftlpage);
        LOG.debug("Requested page '{}'", ftlpage);
        return "index";
    }
}
