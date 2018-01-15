## Beacons

### Contents

1. [Introduction](#introduction)

2. [Quickstart](#quickstart)

3. [How it works](#how-it-works)

4. [Examples](#examples)

5. [Support](#support)

### Introduction

The Nikaza Location Services Framework scans for beacons (iBeacon and Eddystone). Beacons provide higher accuracy than geofences and can be used to identify custom geographical location, such as a coffee shop, an auto dealership or a retail outlet and specific zones such as entrance or cash counter within the geographical location.

### Quickstart

1. Integrate the latest version of the Nikaza Location Services Framework into your app to start scanning beacons.
2. Add a beacon in Nikaza Context Hub.
3. Listen to receive the following beacon events:

 * `didFind_beacon` 
 * `didFind_beaconURL`
 * `didUpdate_beacon` 
 * `didLost_Beacon`

Learn more about [SDK implementation](SDK.md).

### How it works

* User adds a beacon in Nikaza context hub. Email us at support@nikaza.io if you do not have access to Nikaza context hub.
* Location meta-data (such as venue name, zone name, campaign URLs) is attached to the beacon.
* When the SDK finds a matching beacon the user is notified and its metadata is retrieved.


### Examples

For example, to get a callback when a beacon event occurs, use [Beacons](Beacons.md).

```java

  // When a new beacon is found, a `didFind_beacon:` call gets triggered.
  @Override
    public void didFind_beacon(ScanControlModel beacon) {
        Log.e(TAG," Find "+beacon.getBeaconId()+" rssi : "+beacon.getBeaconRssi());
    }

  //  If a beacon is broadcasting URLs, `didFind_beaconURL:` is triggered.
  @Override
    public void didFind_beaconURL(ScanControlModel beacon) {
        Log.e(TAG," Find URL "+beacon.getBeaconId()+" rssi : "+beacon.getBeaconRssi());
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
    
```

### Support

Questions? We're here to help.

Email us at [support@nikaza.io](mailto:support@nikaza.io) and we’ll help you sort it out.
