package com.voyzala.model.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.voyzala.model.domain.Turn;
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
@Repository("turnDao")
public class TurnDaoBigTable extends BaseDaoBigTable<Turn> implements TurnDao {

    @Inject
    public TurnDaoBigTable(DatastoreService datastore) {
        super(datastore, Turn.class);
    }
}
