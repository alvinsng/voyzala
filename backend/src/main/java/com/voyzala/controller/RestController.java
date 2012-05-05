package com.voyzala.controller;

import com.voyzala.model.domain.Card;
import com.voyzala.model.domain.Game;
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
 * Originally created by gcc on 5/4/12 at 10:18 PM
 * </small>
 *
 * @author Geoffrey Chandler, geoffc@gmail.com
 */
@Controller
public class RestController {

    private static class NewGameDTO {
        private final Game game;
        private final Card card;

        private NewGameDTO(Game game, Card card) {
            this.game = game;
            this.card = card;
        }

        public Game getGame() {
            return game;
        }

        public Card getCard() {
            return card;
        }
    }

    private final GameService gameService;

    @Inject
    public RestController(GameService gameService) {
        this.gameService = gameService;
    }

    @ResponseBody
    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public NewGameDTO startGame(@RequestParam String userId, @RequestParam String friendId) {
        final Game game = gameService.createNewGame(Long.parseLong(userId), Long.parseLong(friendId));
        final Card card = gameService.startRound(game.getKey());
        return new NewGameDTO(game, card);
    }

}
