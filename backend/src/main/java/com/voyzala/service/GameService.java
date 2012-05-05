package com.voyzala.service;

import com.google.appengine.api.datastore.Key;
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

    /**
     * Used to view all games that you have going on in the first screen.
     *
     * @param userId the userId looking for a game list
     * @return a list of the current games
     */
    List<Game> getCurrentGames(Long userId);

    /**
     * Creates a new game from choosing a friend and starts round 1 in the new game.
     *
     * @param userId   your user id
     * @param friendId the id of your opponent
     * @return Card the game card to play
     */
    Game createNewGame(Long userId, Long friendId);

    /**
     * Starts a new round in an existing game.
     *
     * @param gameId the id of the game that we want a new turn in
     * @return Card the game card to play
     */
    Card startRound(Key gameId);

    /*
    Submits your voice data and completes your trun.
     */
    void submitTurn(Turn turn);

    /**
     * Gives you the voice data to guess the word.
     *
     * @param gameId the key for the game
     * @return Turn the turn with the blob key for voice data
     */
    Turn guessRound(Key gameId);

    /**
     * Submits the guess.
     *
     * @param guessText the text that you guessed for the word
     * @param turnId    the id for the turn that you are playing.
     * @return Boolean true if you got it right, false if not
     */
    Boolean submitGuess(String guessText, Key turnId);

    /**
     * Admin method for adding cards to the data store
     *
     * @param word           the word that is to be guessed
     * @param forbiddenWords the words that should not be said
     * @return the new game card
     */
    Card createNewCard(String word, String forbiddenWords);
}
