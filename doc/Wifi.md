## WiFi

### Contents

1. [Introduction](#introduction)

2. [Quickstart](#quickstart)

3. [How it works](#how-it-works)

4. [Examples](#examples)

5. [Support](#support)

### Introduction

The Nikaza Location Services Framework scans for WiFi access points. WiFi provides better accuracy than geofences and can be used to identify custom geographical location, such as a coffee shop, an auto dealership or a retail outlet and specific zones such as entrance or cash counter within the geographical location.

### Quickstart

1. Integrate the latest version of the Nikaza Location Services Framework into your app to start scanning beacons.
2. Add a WiFi AP in Nikaza Context Hub.
3. Listen to receive the following WiFi events:

 * `didWifi_Find` 
 * `didWifi_updates`
 * `didWifi_Lost`
 
Learn more about [SDK implementation](SDK.md).

### How it works

* User creates a WiFi beacon in Nikaza context hub. Email us at [support@nikaza.io](mailto:support@nikaza.io) if you do not have access to Nikaza context hub. 
* Location meta-data (such as venue name, zone name, campaign URLs) is attached to the WiFi beacon.
* When the SDK finds a matching SSID/BSSID, location metadata is retrieved from Nikaza backend.

### Examples

For example, to get a callback when a wifi beacon event occurs, use [Wifi](Wifi.md).

```java
  // When a new wifi ssid/bssid is found, a `didWifi_Find:` call gets triggered.
  @Override
    public void didWifi_Find(BssidsResponse bssidsResponse) {
        Log.e(TAG,"\n Wifi Find SSID : "+ bssidsResponse.getSsid());
    }

  // When an existing wifi is updated (RSSI), a `didWifi_updates:` call gets triggered.
    @Override
    public void didWifi_updates(List<String> scanBssidList) {
        Log.e(TAG,"\n Wifi Updates SSID : "+ scanBssidList);
    }

    // When an existing wifi is no longer seen, a didWifi_Lost:’ call gets triggered.
    @Override
    public void didWifi_Lost(BssidsResponse bssidsResponse) {

        Log.e(TAG,"\n Wifi Lost SSID : "+ bssidsResponse.getSsid());
    }

    
```

### Support

Questions? We're here to help.

Email us at [support@nikaza.io](mailto:support@nikaza.io) and we’ll help you sort it out.
