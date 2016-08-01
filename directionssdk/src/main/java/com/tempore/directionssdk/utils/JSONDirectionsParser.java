package com.tempore.directionssdk.utils;/*
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

import com.tempore.directionssdk.http.response.DirectionsResponse;
import com.tempore.directionssdk.http.response.responsemodel.Bounds;
import com.tempore.directionssdk.http.response.responsemodel.Distance;
import com.tempore.directionssdk.http.response.responsemodel.Duration;
import com.tempore.directionssdk.http.response.responsemodel.GeocodedWaypoints;
import com.tempore.directionssdk.http.response.responsemodel.LatLng;
import com.tempore.directionssdk.http.response.responsemodel.Leg;
import com.tempore.directionssdk.http.response.responsemodel.OverviewPolyline;
import com.tempore.directionssdk.http.response.responsemodel.Polyline;
import com.tempore.directionssdk.http.response.responsemodel.Route;
import com.tempore.directionssdk.http.response.responsemodel.Step;
import com.tempore.directionssdk.http.response.responsemodel.Time;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONDirectionsParser {

    public static DirectionsResponse getDirectionsResponse(String json){
        DirectionsResponse directionsResponse = new DirectionsResponse();
        try {
            JSONObject object = new JSONObject(json);
            directionsResponse.setStatus(object.getString("status"));
            if(!directionsResponse.getStatus().equals("OK"))
                directionsResponse.setErrorMessage((object.has("error_message")) ? object.getString("error_message") : "");
            else{
                JSONArray geocoded_waypoints = object.getJSONArray("geocoded_waypoints");
                directionsResponse.setGeocodedWaypoints(getGeocodedWaypoints(geocoded_waypoints));
                directionsResponse.setRoutes(getRoutes(object.getJSONArray("routes")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return directionsResponse;
    }

    private static List<GeocodedWaypoints> getGeocodedWaypoints(JSONArray geocoded_waypoints) throws JSONException {
        List<GeocodedWaypoints> geocodedWaypointsList = new ArrayList<>();
        for(int i = 0; i < geocoded_waypoints.length(); i++){
            JSONObject waypointTemp = geocoded_waypoints.getJSONObject(i);
            GeocodedWaypoints geocodedWaypoints = new GeocodedWaypoints();
            geocodedWaypoints.setGeocoderStatus((waypointTemp.has("geocoder_status")) ? waypointTemp.getString("geocoder_status") : "");
            geocodedWaypoints.setPlaceID((waypointTemp.has("place_id")) ? waypointTemp.getString("place_id") : "");
            JSONArray typesArray = waypointTemp.getJSONArray("types");
            List<String> types = new ArrayList<>();
            for(int j = 0; j < typesArray.length(); j++){
                types.add(typesArray.get(j).toString());
            }
            geocodedWaypoints.setTypes(types);
            geocodedWaypointsList.add(geocodedWaypoints);
        }
        return geocodedWaypointsList;
    }

    private static List<Route> getRoutes(JSONArray routes_array) throws JSONException {
        List<Route> routes = new ArrayList<>();
        for(int i = 0; i < routes_array.length(); i++){
            Route route = new Route();
            JSONObject routeTemp = routes_array.getJSONObject(i);

            //START BOUNDS

            JSONObject northeastObject = routeTemp.getJSONObject("bounds").getJSONObject("northeast");
            JSONObject southwestObject = routeTemp.getJSONObject("bounds").getJSONObject("southwest");
            Bounds bounds = new Bounds();
            LatLng northeast = new LatLng();
            LatLng southwest = new LatLng();
            northeast.setLatitude(northeastObject.optDouble("lat"));
            northeast.setLatitude(northeastObject.optDouble("lng"));
            southwest.setLatitude(southwestObject.optDouble("lat"));
            southwest.setLatitude(southwestObject.optDouble("lng"));
            bounds.setSouthwest(southwest);
            bounds.setNortheast(northeast);
            route.setBounds(bounds);

            //FINISH BOUNDS



            //START LEGS

            JSONArray legsArray = routeTemp.getJSONArray("legs");
            List<Leg> legs = new ArrayList<>();
            for(int j = 0; j < legsArray.length(); j++){
                Leg leg = new Leg();
                JSONObject legsTemp = legsArray.getJSONObject(j);

                //Arrival Time
                if(legsTemp.has("arrival_time")) {
                    JSONObject arrivalObject = legsTemp.getJSONObject("arrival_time");
                    Time arrivalTime = new Time();
                    arrivalTime.setText(getString("text", arrivalObject));
                    arrivalTime.setTimeZone(getString("time_zone", arrivalObject));
                    arrivalTime.setValue(getLong("value", arrivalObject));
                    leg.setArrivalTime(arrivalTime);
                }

                //Departure Time
                if(legsTemp.has("departure_time")) {
                    JSONObject departureObject = legsTemp.getJSONObject("departure_time");
                    Time departureTime = new Time();
                    departureTime.setText(getString("text", departureObject));
                    departureTime.setTimeZone(getString("time_zone", departureObject));
                    departureTime.setValue(getLong("value", departureObject));
                    leg.setDepartureTime(departureTime);
                }

                //Distance
                JSONObject distanceObject = legsTemp.getJSONObject("distance");
                Distance distance = new Distance();
                distance.setText(getString("text", distanceObject));
                distance.setValue(getDouble("value", distanceObject));
                leg.setDistance(distance);

                //Duration
                JSONObject durationObject = legsTemp.getJSONObject("duration");
                Duration duration = new Duration();
                duration.setText(getString("text", durationObject));
                duration.setValue(getDouble("value", durationObject));
                leg.setDuration(duration);

                //End Address
                leg.setEndAddress(getString("end_address", legsTemp));

                //End Location
                JSONObject endLocationObject = legsTemp.getJSONObject("end_location");
                LatLng endLocation = new LatLng();
                endLocation.setLatitude(getDouble("lat", endLocationObject));
                endLocation.setLatitude(getDouble("lng", endLocationObject));
                leg.setEndLocation(endLocation);

                //Start Address
                leg.setStartAddress(getString("start_address", legsTemp));

                //Start Location
                JSONObject startLocationObject = legsTemp.getJSONObject("start_location");
                LatLng startLocation = new LatLng();
                startLocation.setLatitude(getDouble("lat", startLocationObject));
                startLocation.setLatitude(getDouble("lng", startLocationObject));
                leg.setStartLocation(startLocation);

                //Steps
                leg.setSteps(getSteps(legsTemp.getJSONArray("steps")));

                legs.add(leg);

                route.setLegs(legs);
            }

            //OverviewPolyline
            JSONObject overviewPolylineObject = routeTemp.getJSONObject("overview_polyline");
            OverviewPolyline overviewPolyline = new OverviewPolyline();
            overviewPolyline.setPolyline(overviewPolylineObject.getString("points"));
            route.setOverviewPolyline(overviewPolyline);

            //Summary
            route.setSummary(routeTemp.getString("summary"));

            //Warnings
            JSONArray warningsArray = routeTemp.getJSONArray("warnings");
            List<String> warnings = new ArrayList<>();
            for(int j = 0; j < warningsArray.length(); j++){
                warnings.add(warningsArray.get(j).toString());
            }
            route.setWarnings(warnings);

            //Waypoint order
            JSONArray waypointOrderArray = routeTemp.getJSONArray("waypoint_order");
            List<Integer> waypointOrder = new ArrayList<>();
            for(int j = 0; j < waypointOrderArray.length(); j++){
                waypointOrder.add(waypointOrderArray.getInt(j));
            }
            route.setWaypointOrder(waypointOrder);
            routes.add(route);

            //FINISH LEGS
        }
        return routes;
    }

    private static List<Step> getSteps(JSONArray stepsArray) throws JSONException {
        List<Step> steps = new ArrayList<>();
        for(int i = 0; i < stepsArray.length(); i++){
            Step step = new Step();
            JSONObject stepTemp = stepsArray.getJSONObject(i);

            //Distance
            JSONObject distanceObject = stepTemp.getJSONObject("distance");
            Distance distance = new Distance();
            distance.setText(getString("text", distanceObject));
            distance.setValue(getDouble("value", distanceObject));
            step.setDistance(distance);

            //Duration
            JSONObject durationObject = stepTemp.getJSONObject("duration");
            Duration duration = new Duration();
            duration.setText(getString("text", durationObject));
            duration.setValue(getDouble("value", durationObject));
            step.setDuration(duration);

            //End Location
            JSONObject endLocationObject = stepTemp.getJSONObject("end_location");
            LatLng endLocation = new LatLng();
            endLocation.setLatitude(getDouble("lat", endLocationObject));
            endLocation.setLatitude(getDouble("lng", endLocationObject));
            step.setEndLocation(endLocation);

            //HTML INSTRUCTIONS
            step.setHtmlInstruction(getString("html_instructions", stepTemp));

            //HTML INSTRUCTIONS
            step.setInstruction(getString("html_instructions", stepTemp));

            //Polyline
            JSONObject polylineObject = stepTemp.getJSONObject("polyline");
            Polyline polyline = new Polyline();
            polyline.setPoints(getString("points", polylineObject));
            step.setPolyline(polyline);

            //Start Location
            JSONObject startLocationObject = stepTemp.getJSONObject("start_location");
            LatLng startLocation = new LatLng();
            startLocation.setLatitude(getDouble("lat", startLocationObject));
            startLocation.setLatitude(getDouble("lng", startLocationObject));
            step.setStartLocation(startLocation);

            //Substeps
            if(stepTemp.has("steps"))
                step.setSubSteps(getSteps(stepTemp.getJSONArray("steps")));

            steps.add(step);
        }
        return steps;
    }

    private static String getString(String name, JSONObject object) throws JSONException {
        return object.has(name) ? object.getString(name) : "";
    }

    private static double getDouble(String name, JSONObject object) throws JSONException {
        return object.has(name) ? object.optDouble(name) : 0;
    }

    private static long getLong(String name, JSONObject object) throws JSONException {
        return object.has(name) ? object.optLong(name) : 0;
    }
}
