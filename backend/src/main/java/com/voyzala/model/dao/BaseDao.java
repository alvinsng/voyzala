package com.voyzala.model.dao;

import com.google.appengine.api.datastore.Key;

public interface BaseDao<T> {

    T fetchByKey(Key key);

    T fetchByKey(String stringKey);

    void delete(final Key key);

    void delete(final String key);

    void save(T e);

}
