package com.voyzala.model.dao;

import com.google.appengine.api.datastore.*;
import com.voyzala.model.domain.Card;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;

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
    //TODO: Make this not suck horribly
    public Card getRandomCard() {

        final Query q1 = new Query(super.getDataStoreKind());
        final PreparedQuery pq = super.getDatastore().prepare(q1);
        List<Entity> entities = pq.asList(FetchOptions.Builder.withDefaults());

        if (entities.size() < 1) return null;

        int size = entities.size();
        int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
        int i = 0;
        for (Entity obj : entities) {
            if (i == item)
                return super.entityToObject(obj);
            i = i + 1;
        }
        return null;
    }
}
