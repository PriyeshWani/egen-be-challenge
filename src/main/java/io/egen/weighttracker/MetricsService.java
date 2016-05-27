package io.egen.weighttracker;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by priyeshkumarwani on 5/26/16.
 */
@Controller
@RestController
public class MetricsService {
    @Autowired
    AlertMessageService alertMessageService;
    MongoDBDatastore mongodbdatastore;
    Datastore ds = MongoDBDatastore.getInstance();
    int WEIGHT_LIMIT = 1000;
    int BASE_WEIGHT = Integer.MIN_VALUE;

    /**
     *
     * @param data This argument is the payload received in the post request
     * @return This method returs success if the entry is succesfully entered in the database
     */
    @RequestMapping(value = "/postWeight", method = RequestMethod.POST)
    public HttpStatus create(@RequestBody Map<String, String> data) {
        if(data!=null) {
            Metrics metrics = new Metrics();
            metrics.setTimestamp(data.get("timeStamp"));
            int weight = Integer.parseInt(data.get("value"));
            if(weight<=0 || weight > WEIGHT_LIMIT) {
                return HttpStatus.BAD_REQUEST;
            }
            else {
                metrics.setValue(weight);
                ds.save(metrics);
                checkForWeightThreshold(data.get("timeStamp"),weight);
                return HttpStatus.OK;
            }
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    /**
     *
     * @return This method returs all the metrics stored in the database
     */
    @RequestMapping("/retrieveAllMetrics")
    public ResponseEntity<List<Metrics>> getAllMetrics() {
        List<Metrics> metrics = ds.createQuery(Metrics.class).asList();
        return new ResponseEntity<List<Metrics>>(metrics, HttpStatus.OK);
    }

    /**
     *
     * @param from this argument accepts the from date in the range
     * @param to this argumnet accepts to date in the range
     * @return this method returns the list of metrics stored in the databased in specified timerange
     */
    @RequestMapping(value = "/retrieveMetricsInRange")
    public ResponseEntity<List<Metrics>> getAllMetricsInRange(@RequestParam String from, @RequestParam String to) {
        Query<Metrics> query = ds.createQuery(Metrics.class);
        query.and(query.criteria("timeStamp").greaterThanOrEq(from),
                query.criteria("timeStamp").lessThanOrEq(to));
        return new ResponseEntity<List<Metrics>>(query.asList(),HttpStatus.OK);
    }


    private void checkForWeightThreshold(String timeStamp, int weight) {
        if (BASE_WEIGHT == Integer.MIN_VALUE) {
            BASE_WEIGHT = weight;
        }
        else {
            if (weight > (BASE_WEIGHT + (BASE_WEIGHT * 10)/100)) {
                //createAlert()
                int weightDiff = (weight - (BASE_WEIGHT + (BASE_WEIGHT * 10)/100));
                String message = "Weight exceeded the higher threshold weight by: "+weightDiff;
                alertMessageService.addAlertMessage(timeStamp, message, weightDiff);
            }
            else if (weight < (BASE_WEIGHT - (BASE_WEIGHT * 10)/100)) {
                //createAlert()
                int weightDiff = ((BASE_WEIGHT - (BASE_WEIGHT * 10)/100) - weight);
                String message = "Weight reduced below threshold by: "+weightDiff;
                alertMessageService.addAlertMessage(timeStamp, message,
                        (weight - (BASE_WEIGHT - (BASE_WEIGHT * 10)/100)));
            }
        }
    }
}
