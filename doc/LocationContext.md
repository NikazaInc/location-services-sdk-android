## Location Context

### Contents

1. [Introduction](#introduction)

2. [Quickstart](#quickstart)

3. [How it works](#how-it-works)

4. [Examples](#examples)

5. [Support](#support)

### Introduction

Nikaza Context Hub backend has a collection of location and context information associated with these locations. The server call to retrieve location context and associated location metadata will be triggered when the a Geofence, Beacon or WiFi  event occurs. Developers can subscribe to context metadata via callbacks.

### Quickstart

1. Integrate the latest version of the Nikaza Location Services Framework into your app
2. Add a Geofence, Beacon or WiFi in Nikaza Context Hub.
3. Listen to receive the following event

 * `didGetLocationMetadata_nikaza` 

Learn more about [SDK implementation](SDK.md).

### How it works

* User adds a Geofence, Beacon or WiFi in Nikaza context hub. Email us at support@nikaza.io if you do not have access to Nikaza context hub. 
* Location meta-data (such as venue name, zone name, campaign URLs) is attached to the Geofence, Beacon or WiFi. 
* When the SDK finds a matching Geofence, Beacon or WiFi the user is notified and its metadata is retrieved.

### Examples

To do something when location metadata response is received from the Nikaza server, use [Location Context](#location-context).

```java
	@Override
    public void didGetLocationMetadata_nikaza(ResponseBody responseBody) {
        if(null != responseBody)
        Log.e(TAG,"Here are the location details : "+responseBody.String());

    }
```

### Support

Questions? We're here to help.

Email us at [support@nikaza.io](mailto:support@nikaza.io) and we’ll help you sort it out.
