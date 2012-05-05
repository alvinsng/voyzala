package com.voyzala.service;

import com.voyzala.model.domain.Card;
import com.voyzala.model.domain.Game;
import com.voyzala.model.domain.Turn;

import java.util.List;

/**
 * description
 * <p/>
 * <small>
 * Originally created by gcc on 5/4/12 at 10:03 PM
 * </small>
 *
 * @author Geoffrey Chandler, geoffc@gmail.com
 */
public interface GameService {

    List<Game> getCurrentGames(Long userId);

    Card startRound(Long myUserId, Long friendUserId);

    void submitTurn(Turn turn);

    Turn guessRound(Long gameId);

    Boolean submitGuess(String guessText, Long turnId);

}
