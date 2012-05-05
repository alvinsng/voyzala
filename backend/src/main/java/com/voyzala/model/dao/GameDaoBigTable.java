package com.voyzala.model.dao;

import com.google.appengine.api.datastore.*;
import com.voyzala.model.domain.Game;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * description
 * <p/>
 * <small>
 * Originally created by gcc on 5/4/12 at 10:01 PM
 * </small>
 *
 * @author Geoffrey Chandler, geoffc@gmail.com
 */
@Repository("gameDao")
public class GameDaoBigTable extends BaseDaoBigTable<Game> implements GameDao {

    @Inject
    public GameDaoBigTable(DatastoreService datastore) {
        super(datastore, Game.class);
    }

    @Override
    public List<Game> getCurrentGamesForUser(Long userId) {
        final Query q1 = new Query(super.getDataStoreKind());
        q1.addFilter("playerOne", Query.FilterOperator.EQUAL, userId);
        final PreparedQuery pq1 = super.getDatastore().prepare(q1);
        final List<Entity> games = pq1.asList(FetchOptions.Builder.withDefaults());

        final Query q2 = new Query(super.getDataStoreKind());
        q2.addFilter("playerTwo", Query.FilterOperator.EQUAL, userId);
        final PreparedQuery pq2 = super.getDatastore().prepare(q2);
        final List<Entity> playerTwoGames = pq2.asList(FetchOptions.Builder.withDefaults());

        games.addAll(playerTwoGames);

        return super.entitiesToObjects(games);
    }
}
