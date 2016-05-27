package io.egen.weighttracker;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * Created by priyeshkumarwani on 5/26/16.
 */

public class MongoDBDatastore {
    String dbname = "weight-tracker-db";
    MongoClient mongodb;
    private static MongoDBDatastore datastoreInstance = null;
    Datastore datastore;
    private MongoDBDatastore() {
        this.configure();
    }

    private void configure() {

        try {
            Morphia morphia = new Morphia();
            mongodb = new MongoClient();
            datastore = morphia.createDatastore(mongodb, dbname);
            datastore.delete(Metrics.class, dbname);
            morphia.mapPackage("io.egen.weighttracker");
        }
        catch(Exception exception) {
            System.out.println("Exception occurred while configuring the data store"+ exception.getMessage());
        }

    }
    public static Datastore getInstance() {
        if(datastoreInstance == null) {
            datastoreInstance = new MongoDBDatastore();

        }
        return datastoreInstance.datastore;
    }


}
