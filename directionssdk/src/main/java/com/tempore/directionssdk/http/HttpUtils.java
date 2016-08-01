package com.tempore.directionssdk.http;

/*
 * Copyright (c) 2016 Tempore, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import android.util.Log;

import com.tempore.directionssdk.DirectionsSdk;
import com.tempore.directionssdk.model.DirectionsRequest;
import com.tempore.directionssdk.model.Geolocation;
import com.tempore.directionssdk.model.Location;
import com.tempore.directionssdk.utils.DirectionsParams;
import com.tempore.directionssdk.utils.Validate;

public class HttpUtils {
    private static final String SEPARATOR = "&";
    public static String buildRequestUrl(DirectionsRequest request){
        String urlApi = "https://maps.googleapis.com/maps/api/directions/";
        String output = request.getOutput() + "?";
        String originString = "origin=" + request.getOrigin().getLatitude() + "," + request.getOrigin().getLongitude();
        String destinationString = "destination=" + request.getDestination().getLatitude() + "," + request.getDestination().getLongitude();
        urlApi = urlApi.concat(output + originString + SEPARATOR + destinationString);
        if (request.getMode() != null)
            urlApi = urlApi.concat(SEPARATOR + "mode=" + request.getMode());
        if (request.isAlternatives())
            urlApi = urlApi.concat(SEPARATOR + "alternatives=" + request.isAlternatives());
        if (request.getAvoid() != null)
            urlApi = urlApi.concat(SEPARATOR + "avoid=" + request.getAvoid());
        if (request.getLanguage() != null)
            urlApi = urlApi.concat(SEPARATOR + "language=" + request.getLanguage());
        if (request.getUnits() != null)
            urlApi = urlApi.concat(SEPARATOR + "units=" + request.getUnits());
        if (request.getArrivalTime() != DirectionsParams.DEFAULT_DOUBLE)
            urlApi = urlApi.concat(SEPARATOR + "arrival_time=" + request.getArrivalTime());
        if (request.getDepartureTime() != DirectionsParams.DEFAULT_DOUBLE)
            urlApi = urlApi.concat(SEPARATOR + "departure_time=" + request.getDepartureTime());
        if (request.getTrafficModel() != null)
            urlApi = urlApi.concat(SEPARATOR + "traffic_model=" + request.getTrafficModel());
        if (request.getTransitMode() != null)
            urlApi = urlApi.concat(SEPARATOR + "transit_mode=" + request.getTransitMode());
        if (request.getTransitRoutingPreference() != null)
            urlApi = urlApi.concat(SEPARATOR + "transit_routing_preference=" + request.getTransitRoutingPreference());
        String waypoints_params = DirectionsParams.DEFAULT_STRING;
        if (request.getWaypoints().size() > 0){
            for(int i = 0; i < request.getWaypoints().size(); i++){
                Location location = request.getWaypoints().get(i).getLocation();
                if(request.getWaypoints().get(i).isAddToRoute())
                    waypoints_params = waypoints_params.concat("via:");
                if(location.getLatitude() != 0 && location.getAddress() != null)
                    waypoints_params = waypoints_params.concat(location.getPlaceID().getPlaceID());
                else if(location.getLatitude() != 0 && location.getPlaceID() != null)
                    waypoints_params = waypoints_params.concat(location.getAddress());
                else
                    waypoints_params = waypoints_params.concat(location.getLatitude() + "," + location.getLongitude());
                if(i < request.getWaypoints().size() - 1) {
                    waypoints_params = waypoints_params.concat("|");
                }
            }
            if(request.isOptimize())
                waypoints_params = "optimize:true|".concat(waypoints_params);
            urlApi = urlApi.concat(SEPARATOR + "waypoints=" + waypoints_params);
        }
        if (DirectionsSdk.getApiKey() != null)
            urlApi = urlApi.concat(SEPARATOR + "key=".concat(DirectionsSdk.getApiKey()));
        else
            throw new NullPointerException("Api key not specified. You must call DirectionsSdk.init() first");
        Log.i("url", urlApi);
        return urlApi;
    }
}
