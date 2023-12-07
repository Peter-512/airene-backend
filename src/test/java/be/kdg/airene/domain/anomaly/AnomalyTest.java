package be.kdg.airene.domain.anomaly;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AnomalyTest {

    @Test
    void anomalyDetected() {
        UUID uuid = UUID.randomUUID();
        Timestamp ts =  Timestamp.valueOf(LocalDateTime.now());
        Anomaly anomaly = Anomaly.anomalyDetected(uuid, ts);


        /** Timestamp is a sql timestamp which is infrastructure aware, better use localdatetime
            made the constructor package private for testing purposes.
            Constructor gave a null pointer... just needed a test to make sure I have an artifact to publish
         */

        assertEquals(new Anomaly(uuid, ts), anomaly);



    }
}