package nikaza.io.sampleapp;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.nikaza.sdk.NBeaconScanner;
import io.nikaza.sdk.NikazaBeaconListener;
import io.nikaza.sdk.models.GeofenceTriggerData;
import io.nikaza.sdk.models.ScanControlModel;
import io.nikaza.sdk.models.placesDetailsModel.BssidsResponse;
import io.nikaza.sdk.models.placesDetailsModel.PlacesResponse;

public class MainActivity extends AppCompatActivity implements NikazaBeaconListener{

    private List<String> tagList;
    private HashMap<String,String> customParam = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagList = new ArrayList<>();
        customParam.put("test1","value1");
        customParam.put("test2","value2");

        NBeaconScanner.getInstance().setCallback(this);

        if (PermissionCheck.locationAccessAvailable(MainActivity.this))
        {
            NBeaconScanner.getInstance().startScanning(tagList,customParam);
        }
        else
        {
            PermissionCheck.requestLocPerm(MainActivity.this);
        }

    }

    @Override
    public void didFind_beacon(ScanControlModel scanControlModel) {

    }

    @Override
    public void didFind_beaconURL(ScanControlModel scanControlModel) {

    }

    @Override
    public void didUpdate_beacon(ScanControlModel scanControlModel) {

    }

    @Override
    public void didLost_Beacon(ScanControlModel scanControlModel) {

    }

    @Override
    public void didGetLocationMetadata_nikaza(JSONObject jsonObject) {

    }

    @Override
    public void didUpdateStatus_framework(String s) {

    }

    @Override
    public void didEnter_geofence(PlacesResponse placesResponse) {

    }

    @Override
    public void didExit_geofence(GeofenceTriggerData geofenceTriggerData) {

    }

    @Override
    public void didEnter_place(PlacesResponse placesResponse) {

    }

    @Override
    public void didExit_place(GeofenceTriggerData geofenceTriggerData) {

    }

    @Override
    public void didWifi_Find(BssidsResponse bssidsResponse) {

    }

    @Override
    public void didWifi_updates(List<String> list) {

    }

    @Override
    public void didWifi_Lost(BssidsResponse bssidsResponse) {

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case Constants.REQUSET_LOCATION:
                if (grantResults.length > 0)
                {
                    boolean accessLocation = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(accessLocation)
                    {

                        NBeaconScanner.getInstance().startScanning(tagList, customParam);

                    }


                }
                break;
        }
    }


}
