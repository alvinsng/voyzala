package com.voyzala.service;

import com.voyzala.model.domain.Card;
import com.voyzala.model.domain.Game;
import com.voyzala.model.domain.Turn;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Game> getCurrentGames(Long userId) {
        //TODO: Implement
        return null;
    }

    @Override
    public Card startRound(Long myUserId, Long friendUserId) {
        //TODO: Implement
        return null;
    }

    @Override
    public void submitTurn(Turn turn) {
        //TODO: Implement

    }

    @Override
    public Turn guessRound(Long gameId) {
        //TODO: Implement
        return null;
    }

    @Override
    public Boolean submitGuess(String guessText, Long turnId) {
        //TODO: Implement
        return null;
    }
}
