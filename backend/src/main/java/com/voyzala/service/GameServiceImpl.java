package com.voyzala.service;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.voyzala.model.dao.CardDao;
import com.voyzala.model.dao.GameDao;
import com.voyzala.model.dao.TurnDao;
import com.voyzala.model.domain.Card;
import com.voyzala.model.domain.Game;
import com.voyzala.model.domain.Turn;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * description
 * <p/>
 * <small>
 * Originally created by gcc on 5/4/12 at 10:08 PM
 * </small>
 *
 * @author Geoffrey Chandler, geoffc@gmail.com
 */
@Service("gameService")
public class GameServiceImpl implements GameService {

    private final GameDao gameDao;

    private final TurnDao turnDao;

    private final CardDao cardDao;

    @Inject
    public GameServiceImpl(final GameDao gameDao, final TurnDao turnDao, final CardDao cardDao) {
        this.gameDao = gameDao;
        this.turnDao = turnDao;
        this.cardDao = cardDao;
    }

    @Override
    public List<Game> getCurrentGames(final Long userId) {
        return gameDao.getCurrentGamesForUser(userId);
    }

    @Override
    public Game createNewGame(final Long userId, final Long friendId) {

        final Game game = new Game();
        game.setPlayerOne(userId);
        game.setPlayerTwo(friendId);
        game.setPlayerOneScore(0);
        game.setPlayerTwoScore(0);
        game.setCurrentTurnCount(0);

        gameDao.save(game);
        return game;
    }

    @Override
    public Card startRound(final Key gameKey) {
        final Card card = cardDao.getRandomCard();
        Turn turn = new Turn();
        turn.setGameKey(gameKey);
        turn.setCardKey(card.getKey());
        turnDao.save(turn);
        return card;
    }

    @Override
    public void submitTurn(final Key turnKey, final BlobKey voiceDataKey) {
        final Turn turn = turnDao.fetchByKey(turnKey);
        final Game game = gameDao.fetchByKey(turn.getGameKey());
        turn.setVoiceDataKey(voiceDataKey);
        turnDao.save(turn);
        game.setCurrentTurnCount(game.getCurrentTurnCount() + 1);
        game.setCurrentTurnKey(turn.getKey());
        gameDao.save(game);
    }

    @Override
    public Turn guessRound(final Key gameKey) {
        final Game game = gameDao.fetchByKey(gameKey);
        return turnDao.fetchByKey(game.getCurrentTurnKey());
    }

    @Override
    public Boolean submitGuess(String guessText, Key turnKey) {
        //TODO: Implement
        return null;
    }

    @Override
    public Card createNewCard(final String word, final String forbiddenWords) {
        final Card card = new Card();
        card.setWord(word);
        card.setForbiddenWords(forbiddenWords);
        cardDao.save(card);
        return card;
    }
}
