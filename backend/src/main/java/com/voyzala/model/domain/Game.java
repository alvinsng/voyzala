package com.voyzala.model.domain;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.voyzala.model.Id;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

/**
 * description
 * <p/>
 * <small>
 * Originally created by gcc on 5/4/12 at 9:55 PM
 * </small>
 *
 * @author Geoffrey Chandler, geoffc@gmail.com
 */
public class Game {

    @Id
    private Key key;

    private Long playerOne;

    private Long playerTwo;

    private Long playerOneScore;

    private Long playerTwoScore;

    private Date createdOn = new Date();

    private Long currentTurnCount;

    private Key currentTurnKey;

    public String getStringKey() {
        return KeyFactory.keyToString(key);
    }

    @JsonIgnore
    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Long getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Long playerOne) {
        this.playerOne = playerOne;
    }

    public Long getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Long playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Long getPlayerOneScore() {
        return playerOneScore;
    }

    public void setPlayerOneScore(Long playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public Long getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void setPlayerTwoScore(Long playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Long getCurrentTurnCount() {
        return currentTurnCount;
    }

    public void setCurrentTurnCount(Long currentTurnCount) {
        this.currentTurnCount = currentTurnCount;
    }

    public String getCurrentTurnStringKey() {
        return currentTurnKey != null ? KeyFactory.keyToString(currentTurnKey) : null;
    }

    @JsonIgnore
    public Key getCurrentTurnKey() {
        return currentTurnKey;
    }

    public void setCurrentTurnKey(Key currentTurnKey) {
        this.currentTurnKey = currentTurnKey;
    }
}
