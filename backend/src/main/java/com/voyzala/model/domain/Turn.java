package com.voyzala.model.domain;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.voyzala.model.Id;

/**
 * description
 * <p/>
 * <small>
 * Originally created by gcc on 5/4/12 at 9:55 PM
 * </small>
 *
 * @author Geoffrey Chandler, geoffc@gmail.com
 */
public class Turn {

    @Id
    private Key key;

    private BlobKey voiceDataKey;

    private Key gameKey;

    private Key cardKey;

    public String getStringKey() {
        return KeyFactory.keyToString(key);
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public BlobKey getVoiceDataKey() {
        return voiceDataKey;
    }

    public void setVoiceDataKey(BlobKey voiceDataKey) {
        this.voiceDataKey = voiceDataKey;
    }

    public Key getGameKey() {
        return gameKey;
    }

    public void setGameKey(Key gameKey) {
        this.gameKey = gameKey;
    }

    public Key getCardKey() {
        return cardKey;
    }

    public void setCardKey(Key cardKey) {
        this.cardKey = cardKey;
    }

}
