package com.voyzala.service;

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
    public GameServiceImpl(GameDao gameDao, TurnDao turnDao, CardDao cardDao) {
        this.gameDao = gameDao;
        this.turnDao = turnDao;
        this.cardDao = cardDao;
    }

    @Override
    public List<Game> getCurrentGames(Long userId) {
        //TODO: Implement
        return null;
    }

    @Override
    public Card startRound(Long myUserId, Long friendUserId) {

        Card card = new Card();
        card.setWord("shoe");
        card.setPipeDelimitedForbiddenWords("feet|toes|boot|socks");

        return card;
    }

    @Override
    public void submitTurn(Turn turn) {
        //TODO: Implement

    }

    @Override
    public Turn guessRound(Key gameId) {
        //TODO: Implement
        return null;
    }

    @Override
    public Boolean submitGuess(String guessText, Key turnId) {
        //TODO: Implement
        return null;
    }
}
