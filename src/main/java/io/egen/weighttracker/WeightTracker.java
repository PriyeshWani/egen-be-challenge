package io.egen.weighttracker;

/**
 * Created by priyeshkumarwani on 5/25/16.
 */

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.data.geo.Metric;

import java.net.UnknownHostException;
import java.util.Date;

@SpringBootApplication
@EnableAutoConfiguration
public class WeightTracker {

    public static void main(String[] args) throws UnknownHostException, MongoException {
        SpringApplication.run(WeightTracker.class, args);
    }
}
