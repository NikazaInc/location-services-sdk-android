## SDK

### Contents

1. [Introduction](#introduction)

2. [Authentication](#authentication)

3. [Android](#android)
   * [Download the framework](#download-the-framework)
   * [Integrate the framework](#integrate-the-framework)
   	  - [Service](#service)
      - [Application](#Application)
      - [Activity](#activity)
      - [Fragment](#fragment)
      
   * [Integrate SDK into app](#integrate-sdk-into-app)
      - [Start scanning](#start-location-services)
      - [Stop scanning](#stop-location-services)
      - [Enable places](#subscribe-to-location-categories-or-location-names-using-tags)
      - [Register for callbacks](#register-for-callbacks)
      - [Sample implementation](#sample-implementation)
      - [Submit to App Store](#submit-to-app-store)
    * [License](#license)  
4. [Support](#support)

### Introduction
Nikaza serves as a bridge between the physical and digital worlds. Integrate the Nikaza Location Services Framework into your Android apps to start tracking users and generating events. The SDK allows you to add location context and tracking to your apps with only a few lines of code.

You can use the Nikaza Location Services Framework to track the user's location in the foreground or background.

Most of the geofencing process happens on Nikaza server-side. This allows Nikaza geofencing to be more powerful than native Android geofencing with cross-platform support for unlimited geofences.

### Authentication

Authenticate using your unique API keys:

1. Contact [support@nikaza.io](mailto:support@nikaza.io) to obtain your unique API key.
2. Add the API key to the framework as mentioned below.

### Android

#### Download the framework

Nikaza Location Services Framework can be downloaded from [here](../nikaza-sdk-1.0.15.aar)


#### Integrate the framework

1. Download the nikaza framework - .AAR file.
2. Create new module dependency, File - New - New Module - Import .JAR/.AAR pacakge.
3. Open project structure from the studio and set the dependency for Nikaza SDK. 
4. Add the following dependencies to your build.gradle
```
    compile 'com.google.android.gms:play-services-location:11.0.2'

```
5. Add Nikaza API Key in Android Manifest file under the ```application``` section.

    ```
    <meta-data
        android:name="io.nikaza.API_KEY"
        android:value="Your API Key"/>
    ```

 6. Add location permissions (fine and coarse) in manifest file.
 
    ```
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    ```
    
##### Service

If you want to use Nikaza Location Service as a android service.

```java
public class MyService extends Service implements NikazaBeaconListener
{
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the NikazaBeacon Scanner
        NBeaconScanner.initialize(NBeaconScanner.newInitializer(this).build());
        NBeaconScanner.getInstance().setCallback(this);
    }
}
```

##### Application

To use Nikaza Location Service in an Activity or in Fragment, the first step is to initialize the library in the application class.

```java
public class MyApplication extends Application
{

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the NikazaBeacon Scanner
        NBeaconScanner.initialize(NBeaconScanner.newInitializer(this).build());
    }
}
```


##### Activity

To use Nikaza Location Service in the Android Activity.

```java
@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity implements NikazaBeaconListener{

    private static final String TAG = "MAIN ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the NikazaBeacon Callbacks
        NBeaconScanner.getInstance().setCallback(this);
    }
}    

```

##### Fragment

To use Nikaza Location Service in the Android Fragment.

```java
public class MainFragment extends Fragment implements NikazaBeaconListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting the NikazaBeacon Callbacks
        NBeaconScanner.getInstance().setCallback(this);
    }

```


#### Integrate SDK into app

##### Start Location Services

To Start Scanning.

```java

NBeaconScanner.getInstance().startScanning(tagList,customParam);

```

By default, Nikaza Location Service scans for Beacons (iBeacon and EddyStone). User will be prompted to give the permission for accessing the location details. The parameters passed is tagList and customParam, tagList can be in List<String> and customParam in HashMap<String,String> format.

##### Stop Location Services

To Stop Scanning

```java

 NBeaconScanner.getInstance().stopScanning();

```

##### Subscribe to location categories or location names using tags

```java

List<String> tagList = new ArrayList<>();
tagList.add("airport");

```
Nikaza Location Services Framework communicates with Nikaza server to determine if a user is at a place or a specific business category.

Learn more about [Places tags](Places.md).

##### Advanced framework configuration

Take these optional steps to add or modify the Nikaza Location Services Framework settings. These variables needs to be set before calling NBeaconScanner.getInstance().startScanning().
 
* If `NikazaConstants.API_INVOCATION_RSSI_DIFFERENCE` is set to 30 then Nikaza API calls are triggered only if the RSSI changes by at least 30dBm once a beacon is found. Default 20.
* `NikazaConstants.LOST_TIMEOUT` defines the amount of time in milliseconds to wait (after a beacon is lost) before trigerring an exit event to Nikaza backend. Default 10000.
* If `NikazaConstants.POWER_SAVING_MODE = false` GPS polling is more frequent. Note that this will drain the battery faster. Default true.
* If `NikazaConstants.AutoWiFiON = true` the SDK will automatically turn on the devices' WiFi. Default false.
* If `NikazaConstants.wifiBeacon = true` the SDK will detect nearby Nikaza WiFi beacons. This functionality will work even when bluetooth is off on the phone.


##### Register for callbacks

To listern for callbacks, implement ```NikazaBeaconListener``` in your main class.

Below are the optional delegates or callbacks:

```
didFind_beacon
didUpdate_beacon
didLost_Beacon
didGetLocationMetadata_nikaza
didEnter_geofence
didExit_geofence
didEnter_place
didExit_place
didWifi_Find
didWifi_updates
didWifi_Lost
didUpdateStatus_framework
```

##### Sample implementation

```java

  // When a new beacon is found, a `didFind_beacon:` call gets triggered.
  @Override
    public void didFind_beacon(ScanControlModel beacon) {
        Log.e(TAG," Find "+beacon.getBeaconId()+" rssi : "+beacon.getBeaconRssi());
    }

  // When an existing beacon is updated (RSSI change), a `didUpdate_beacon:` call gets triggered.
  @Override
    public void didUpdate_beacon(ScanControlModel beacon) {
        Log.e(TAG," Update "+beacon.getBeaconId()+" rssi : "+beacon.getBeaconRssi());
    }

  // When an existing beacon is no longer seen (exited from beacon region), a ‘didLose_beacon:’ call gets triggered.
   @Override
    public void didLost_Beacon(ScanControlModel controlModel) {
        Log.e(TAG,"Lost Beacon "+controlModel.getBeaconId());
    }

  // When entered into a geofence, a ‘didEnter_geofence:’ call gets triggered
   @Override
    public void didEnter_geofence(PlacesResponse oPlacesResponse) {
        Log.e(TAG,"Place ID : "+oPlacesResponse.getPlaceId());

    }  

  //When exited from a geofence, a ‘didExit_geofence:’ call gets triggered
    @Override
    public void didExit_geofence(GeofenceTriggerData oGeofenceTriggerData) {
        Log.e(TAG,"Exited Geofence");
    }
  
  // When entered into a place, a ‘didEnter_place:’ call gets triggered
    @Override
    public void didEnter_place(PlacesResponse oPlacesResponse) {
        Log.e(TAG,"Place ID : "+oPlacesResponse.getPlaceId());
    }

  // When exited from a place, a ‘didExit_place:’ call gets triggered
     @Override
    public void didExit_place(GeofenceTriggerData oGeofenceTriggerData) {
        Log.e(TAG,"Exited Place");

    }

  // If location metadata response is received from Nikaza server, `didGetLocationMetadata_nikaza:` call gets triggered.
   @Override
    public void didGetLocationMetadata_nikaza(ResponseBody responseBody) {
  
        Log.e(TAG,"Response :  "+responseBody.String());
      
    }

  // When a new wifi module is found, a `didWifi_Find:` call gets triggered.
   @Override
    public void didWifi_Find(BssidsResponse bssidsResponse) {
        Log.e(TAG,"\n Wifi Find SSID : "+ bssidsResponse.getSsid());
    }
    
  // When an existing wifi module is updated, a `didWifi_updates:` call gets triggered.
   @Override
    public void didWifi_updates(List<String> scanBssidList) {
        Log.e(TAG,"\n Wifi Updates SSID : "+ scanBssidList);
    }  

  // When an existing wifi module is no longer seen, a didWifi_Lost:’ call gets triggered.
    @Override
    public void didWifi_Lost(BssidsResponse bssidsResponse) {
        Log.e(TAG,"\n Wifi Lost SSID : "+ bssidsResponse.getSsid());
    }
  // To receive SDK logs
    @Override
    public void didUpdateStatus_framework(String string) {
      setText(getLogTxt(),"\n\n" + string);
    }
```
  

### Support

Questions? We're here to help.

Email us at [support@nikaza.io](mailto:support@nikaza.io) and we’ll help you sort it out.
