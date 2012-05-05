package com.voyzala.controller;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.datastore.KeyFactory;
import com.voyzala.model.domain.Card;
import com.voyzala.model.domain.Game;
import com.voyzala.model.domain.Turn;
import com.voyzala.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Main Controller for all REST operations
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
        private final String uploadUrl;

        private NewGameDTO(Game game, Card card, String uploadUrl) {
            this.game = game;
            this.card = card;
            this.uploadUrl = uploadUrl;
        }

        public Game getGame() {
            return game;
        }

        public Card getCard() {
            return card;
        }

        public String getUploadUrl() {
            return uploadUrl;
        }
    }

    private final GameService gameService;

    private final BlobstoreService blobstoreService;

    @Inject
    public RestController(final GameService gameService,
                          final BlobstoreService blobstoreService) {
        this.gameService = gameService;
        this.blobstoreService = blobstoreService;
    }

    @ResponseBody
    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public List<Game> getGameList(@RequestParam final String userId) {
        return gameService.getCurrentGames(Long.parseLong(userId));
    }

    @ResponseBody
    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public NewGameDTO startGame(@RequestParam final String userId,
                                @RequestParam final String friendId) {

        final Game game = gameService.createNewGame(Long.parseLong(userId), Long.parseLong(friendId));
        final Card card = gameService.startRound(game.getKey());
        final String uploadUrl = this.blobstoreService.createUploadUrl("/submitTurn");

        return new NewGameDTO(game, card, uploadUrl);
    }

    @ResponseBody
    @RequestMapping(value = "/submitTurn", method = RequestMethod.POST)
    public void submitTurn(@RequestParam final String turnKey,
                           final HttpServletRequest req) {

        final Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);

        gameService.submitTurn(KeyFactory.stringToKey(turnKey), blobs.get("voiceData").get(0));
    }

    @ResponseBody
    @RequestMapping(value = "/game/{gameKey}", method = RequestMethod.GET)
    public Turn guessRound(@PathVariable("gameKey") final String gameKey) {
        return gameService.guessRound(KeyFactory.stringToKey(gameKey));
    }

    @ResponseBody
    @RequestMapping(value = "/guess/{turnKey}", method = RequestMethod.GET)
    public Card submitGuess(@PathVariable("turnKey") final String turnKey,
                            @RequestParam final String guess) {
        return gameService.submitGuess(KeyFactory.stringToKey(turnKey), guess);
    }

}
