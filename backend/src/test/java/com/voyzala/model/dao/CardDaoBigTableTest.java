package com.voyzala.model.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.voyzala.model.domain.Card;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * description
 * <p/>
 * <small>
 * Originally created by gcc on 5/5/12 at 12:07 AM
 * </small>
 *
 * @author Geoffrey Chandler, geoffc@gmail.com
 */
public class CardDaoBigTableTest {

    private final LocalServiceTestHelper helper;

    private final CardDao cardDao;

    {
        helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        cardDao = new CardDaoBigTable(datastore);
    }

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void testGetRandomCard() throws Exception {

        Card c1 = new Card();
        c1.setWord("shoe");
        c1.setPipeDelimitedForbiddenWords("feet|toes|boot|socks");
        cardDao.save(c1);

        Card c2 = new Card();
        c2.setWord("one");
        c2.setPipeDelimitedForbiddenWords("two|three|four|five");
        cardDao.save(c2);

        Card c3 = new Card();
        c3.setWord("a");
        c3.setPipeDelimitedForbiddenWords("b|c|d|e");
        cardDao.save(c3);

        Card fetched = cardDao.getRandomCard();
        System.out.println("card: " + fetched);

        Assert.assertNotNull(fetched);
    }

}
