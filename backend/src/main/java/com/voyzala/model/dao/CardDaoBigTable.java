package com.voyzala.model.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.voyzala.model.domain.Card;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

/**
 * description
 * <p/>
 * <small>
 * Originally created by gcc on 5/4/12 at 10:01 PM
 * </small>
 *
 * @author Geoffrey Chandler, geoffc@gmail.com
 */
@Repository("cardDao")
public class CardDaoBigTable extends BaseDaoBigTable<Card> implements CardDao {

    @Inject
    public CardDaoBigTable(DatastoreService datastore) {
        super(datastore, Card.class);
    }

    @Override
    public Card getRandomCard() {

        final double randomNumber = Math.random();

        final Query q1 = new Query(super.getDataStoreKind());
        q1.addFilter("randomSeed", Query.FilterOperator.GREATER_THAN_OR_EQUAL, randomNumber);
        q1.addSort("randomSeed");
        final PreparedQuery pq = super.getDatastore().prepare(q1);

        Entity e = pq.asSingleEntity();

        return e == null ? null : super.entityToObject(e);
    }
}
