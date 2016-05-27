package io.egen.weighttracker;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by priyeshkumarwani on 5/25/16.
 */

@Entity
public class Alert {

    @Id
    @Indexed(unique = true)
    @Property("id")
    protected ObjectId id;

    @Property("message")
    String message;

    @Property
    String timeStamp;

    @Property
    int weightAboveBelowThreshold;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTimeStamp() {
        return  this.timeStamp;
    }

    public void setWeightAboveBelowThreshold(int weight) {
        this.weightAboveBelowThreshold = weight;
    }

    public int getWeightAboveBelowThreshold() {
        return  this.weightAboveBelowThreshold;
    }

}
