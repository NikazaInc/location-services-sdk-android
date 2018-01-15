## Places

### Contents

1. [Introduction](#introduction)

2. [Quickstart](#quickstart)

3. [Examples](#examples)

4. [Support](#support)

### Introduction

The Nikaza Places database has millions of pre-defined locations that are categorized based on the location type which can be subscribed to.

Each place has a category, sub-category and location name. Example: Arts & Entertainment, Movie Theatre, Acme Cinema.

### Quickstart

1. Integrate the latest version of the Nikaza Location Services Framework into your app to know when a user visits a place.
2. Subscribe to location categories or location names using tags
3. Listen to entry and exit events via following callbacks

 * `didEnter_place()` 
 * `didExit_place()`

Learn more about [SDK implementation](SDK.md).
Nikaza supports thousands of Tags. View the [full list of Tags](CSV/TagList.csv).

### Examples

For example, to get a callback when user has entered into a geofence or exited from a geofence, use [Places](Places.md).

```java
	@Override
    public void didEnter_place(PlacesResponse oPlacesResponse) {

        //User is at this place
    }

    @Override
    public void didExit_place(GeofenceTriggerData oGeofenceTriggerData) {

        //User exited this place
    }

```

### Support

Questions? We're here to help.

Email us at [support@nikaza.io](mailto:support@nikaza.io) and we’ll help you sort it out.
