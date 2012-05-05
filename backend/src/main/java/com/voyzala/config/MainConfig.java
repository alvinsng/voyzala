package com.voyzala.config;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Main configuration class for the application.
 *
 * @author Geoff Chandler
 */
@Configuration
public class MainConfig {

    @Bean
    public BlobstoreService blobstoreService() {
        return BlobstoreServiceFactory.getBlobstoreService();
    }

    @Bean
    public DatastoreService datastore() {
        return DatastoreServiceFactory.getDatastoreService();
    }

}
