import io.egen.weighttracker.MongoDBDatastore;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import static org.junit.Assert.assertNotNull;

/**
 * Created by priyeshkumarwani on 5/27/16.
 */
public class MongoDBDatastoreTest {

    private Datastore dataStore;

    @Test
    public void testDatastoreInstance() {
        assertNotNull(MongoDBDatastore.getInstance());
    }


}
