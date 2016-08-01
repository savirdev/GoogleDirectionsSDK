package com.tempore.directionssdkexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tempore.directionssdk.DirectionsSdk;
import com.tempore.directionssdk.http.DirectionRequestCallback;
import com.tempore.directionssdk.http.response.DirectionsResponse;
import com.tempore.directionssdk.model.DirectionsRequest;
import com.tempore.directionssdk.model.Location;
import com.tempore.directionssdk.model.Waypoint;
import com.tempore.directionssdk.utils.DirectionsParams;

public class MainActivity extends AppCompatActivity implements DirectionRequestCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DirectionsSdk.init("AIzaSyDostQsPMUzNx6ODV8TnXTsQy82FwLifVc");
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

        DirectionsSdk.makeRequest(directionsRequest, this);
    }

    @Override
    public void onRequestFinish(DirectionsResponse json) {
        if(json.getErrorMessage() != null)
            Log.i("json_error", "" + json.getErrorMessage());
        Log.i("json", "" + json.getRoutes().get(0).getOverviewPolyline().getPolyline());
    }
}
