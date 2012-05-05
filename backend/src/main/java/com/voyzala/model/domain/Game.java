package com.voyzala.model.domain;

import com.google.appengine.api.datastore.Key;
import com.voyzala.model.Id;

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

    private Integer playerOneScore;

    private Integer playerTwoScore;

    private Date createdOn;

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

    public Integer getPlayerOneScore() {
        return playerOneScore;
    }

    public void setPlayerOneScore(Integer playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public Integer getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void setPlayerTwoScore(Integer playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
