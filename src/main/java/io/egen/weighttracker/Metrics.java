package io.egen.weighttracker;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.Date;

/**
 * Created by priyeshkumarwani on 5/25/16.
 */

@Entity
public class Metrics {
    @Id
    @Indexed(unique = true)
    @Property("id")
    protected ObjectId id;

    @Property("value")
    private int value;

    @Property("timeStamp")
    private String timeStamp;

    public String getTimestamp() {
        return  this.timeStamp;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setTimestamp(String date) {
        this.timeStamp = date;
    }

    public int getValue() {
        return this.value;
    }

}
