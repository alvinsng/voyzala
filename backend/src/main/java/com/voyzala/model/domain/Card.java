package com.voyzala.model.domain;

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
public class Card {

    @Id
    private Key key;

    private String word;

    private String forbiddenWords;

    public String getStringKey() {
        return KeyFactory.keyToString(key);
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getForbiddenWords() {
        return forbiddenWords;
    }

    public void setForbiddenWords(String forbiddenWords) {
        this.forbiddenWords = forbiddenWords;
    }

    @Override
    public String toString() {
        return "Card{" +
                "key=" + key +
                ", word='" + word + '\'' +
                ", forbiddenWords='" + forbiddenWords + '\'' +
                '}';
    }
}
