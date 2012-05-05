package com.voyzala.controller;

import com.voyzala.model.domain.Card;
import com.voyzala.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * description
 * <p/>
 * <small>
 * Originally created by gcc on 5/5/12 at 12:14 AM
 * </small>
 *
 * @author Geoffrey Chandler, geoffc@gmail.com
 */
@Controller
public class AdminController {

    private final GameService gameService;

    @Inject
    public AdminController(GameService gameService) {
        this.gameService = gameService;
    }

    @ResponseBody
    @RequestMapping(value = "/createCard", method = RequestMethod.POST)
    public Card startGame(@RequestParam String word, @RequestParam String forbiddenWords) {
        return gameService.createNewCard(word, forbiddenWords);
    }

}
