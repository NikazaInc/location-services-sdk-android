package nikaza.io.sampleapp;

import android.app.Application;

import io.nikaza.sdk.NBeaconScanner;


public class MyApplication extends Application
{

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the NBeacon Scanner
        NBeaconScanner.initialize(NBeaconScanner.newInitializer(this).build());
    }
}
