package com.voyzala.model.dao;

import com.google.appengine.api.datastore.*;
import com.voyzala.model.Id;
import com.voyzala.model.Parent;
import com.voyzala.model.Transient;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDaoBigTable<T> implements BaseDao<T> {

    private final DatastoreService datastore;
    private final Class<? extends T> entityClass;

    public BaseDaoBigTable(final DatastoreService datastore,
                           final Class<? extends T> entityClass) {
        this.datastore = datastore;
        this.entityClass = entityClass;
    }

    protected DatastoreService getDatastore() {
        return datastore;
    }

    protected String getDataStoreKind() {
        return entityClass.getSimpleName();
    }

    protected T entityToObject(final Entity e) {
        T o;
        try {
            o = this.entityClass.newInstance();
            for (final Field field : this.entityClass.getDeclaredFields()) {
                field.setAccessible(true);
                final Object value;
                if (field.getAnnotation(Id.class) != null) {
                    value = e.getKey();
                } else {
                    value = e.getProperty(field.getName());
                }
                if (value != null) field.set(o, value);
            }
        } catch (InstantiationException ignore) {
            throw new RuntimeException(
                    "There was no default constructor to call for " + this.entityClass.getName());
        } catch (IllegalAccessException ignore) {
            throw new RuntimeException(
                    "The default constructor was not accessible for " + this.entityClass.getName());
        }
        return o;
    }

    protected List<T> entitiesToObjects(final List<Entity> entities) {
        final List<T> results = new ArrayList<T>(entities.size());
        for (final Entity e : entities) {
            results.add(entityToObject(e));
        }
        return results;
    }

    public T fetchByKey(final Key entityKey) {
        T o = null;
        try {
            final Entity e = datastore.get(entityKey);
            o = entityToObject(e);
        } catch (EntityNotFoundException ignore) {
        }
        return o;
    }

    @Override
    public T fetchByKey(final String stringKey) {
        return fetchByKey(KeyFactory.stringToKey(stringKey));
    }

    @Override
    public void save(T obj) {

        Key oldKey = null;
        Field keyField = null;
        Key parentKey = null;
        final Field[] declaredFields = obj.getClass().getDeclaredFields();
        final Map<String, Object> map = new HashMap<String, Object>(declaredFields.length);
        for (final Field field : declaredFields) {
            try {
                field.setAccessible(true);
                if (field.getAnnotation(Id.class) != null) {
                    keyField = field;
                    oldKey = (Key) field.get(obj);
                }
                if (field.getAnnotation(Parent.class) != null) parentKey = (Key) field.get(obj);
                if (field.getAnnotation(Transient.class) == null
                        && field.getAnnotation(Parent.class) == null
                        && field.getAnnotation(Id.class) == null) {
                    map.put(field.getName(), field.get(obj));
                }
            } catch (IllegalAccessException ignore) {
            }
        }

        final Entity e;
        if (oldKey != null) {
            e = new Entity(oldKey);
        } else {
            if (parentKey != null) {
                e = new Entity(this.getDataStoreKind(), parentKey);
            } else {
                e = new Entity(this.getDataStoreKind());
            }
        }
        for (final String property : map.keySet()) {
            e.setProperty(property, map.get(property));
        }
        this.datastore.put(e);
        if (keyField != null) {
            try {
                keyField.set(obj, e.getKey());
            } catch (IllegalAccessException ignore) {
            }
        }
    }

    @Override
    public void delete(final Key key) {
        datastore.delete(key);
    }

    @Override
    public void delete(final String key) {
        delete(KeyFactory.stringToKey(key));
    }

}
