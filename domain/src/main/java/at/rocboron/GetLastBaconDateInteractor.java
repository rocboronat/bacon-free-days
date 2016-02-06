package at.rocboron;

import java.util.Date;

import at.rocboron.datastore.BaconDatastore;

public class GetLastBaconDateInteractor {

    BaconDatastore datastore;

    public GetLastBaconDateInteractor(BaconDatastore datastore) {
        this.datastore = datastore;
    }

    public Date getLastBaconDate() {
        Date lastBaconDate = datastore.getLastBaconDate();
        if (lastBaconDate == null) {
            return new Date();
        } else {
            return lastBaconDate;
        }
    }
}
