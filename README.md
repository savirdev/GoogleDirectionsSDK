# GoogleDirectionsSDK

An open source toolset for building applications that need the Google Directions API in Android.

## Request Example

The following code retrieve a DirectionResponse object that contains the information of the directions from a point to others.

```java
DirectionsSdk.init("yourApiKey");
        DirectionsRequest directionsRequest = DirectionsRequest.newBuilder()
                .withOrigin(new Location(19.558426, -99.147803))
                .withDestination(new Location(19.541447, -99.145569))
                .withMode(DirectionsParams.MODE_BICYCLING)
                .withUnits(DirectionsParams.UNIT_METRIC)
                .withLanguage(DirectionsParams.LANGUAGE_SPANISH)
                .withAlternatives(true)
                .withWaypoint(Waypoint.newBuilder()
                        .withLocation(new Location(19.502421, -99.169408))
                        .build())
                .withWaypoint(Waypoint.newBuilder()
                        .withLocation(new Location(19.534449, -99.122366))
                        .build())
                .withWaypoint(Waypoint.newBuilder()
                        .withLocation(new Location(19.543124, -99.180994))
                        .build())
                .optimize(true)
                .build();

        DirectionsSdk.makeRequest(directionsRequest, new DirectionRequestCallback() {
            @Override
            public void onRequestFinish(DirectionsResponse directionsResponse) {
                
            }
        });
```

Check the [Android Test App](https://github.com/savirdev/GoogleDirectionsSDK/tree/master/app) for an example and check the official documetnacion of Google Directions API.
