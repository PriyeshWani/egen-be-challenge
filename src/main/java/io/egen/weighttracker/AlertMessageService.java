package io.egen.weighttracker;

import org.apache.catalina.connector.Response;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by priyeshkumarwani on 5/26/16.
 */

@Service
@RestController
public class AlertMessageService {


    Datastore ds = MongoDBDatastore.getInstance();
    /**
     *
     * @return This method returns all the alert messages stored in the database
     */
    @RequestMapping(value = "/retrieveAllAlertMessages", method = RequestMethod.GET)
    public ResponseEntity<List<Alert>> retrieveAlertMessages() {
        return new ResponseEntity<List<Alert>>(ds.createQuery(Alert.class).asList(), HttpStatus.OK);
    }

    /**
     *
     * @param from this parameter specifies the start date of the range
     * @param to this paramter specifies the end date of the range
     * @return This method returns the alert messages in the specified time range
     */
    @RequestMapping(value = "/retrieveAlertMessagesInRange")
    public ResponseEntity<List<Alert>> retrieveAlertMessagesInRange(@RequestParam("from") String from,
                                                                    @RequestParam("to") String to) {
        if(from != null && to != null) {
            Query<Alert> query = ds.createQuery(Alert.class);
            query.and(query.criteria("timeStamp").greaterThanOrEq(from),
                    query.criteria("timeStamp").lessThanOrEq(to));
            return  new ResponseEntity<List<Alert>>(query.asList(),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<List<Alert>>(HttpStatus.BAD_REQUEST);
        }

    }

    public void addAlertMessage(String timeStamp, String message, int weightDiff) {
        Alert alert = new Alert();
        alert.setMessage(message);
        alert.setTimeStamp(timeStamp);
        alert.setWeightAboveBelowThreshold(weightDiff);
        ds.save(alert);
    }
}
