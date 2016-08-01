package com.tempore.directionssdk.model;/*
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

import android.support.annotation.NonNull;
import android.util.Log;

import com.tempore.directionssdk.http.HttpUtils;
import com.tempore.directionssdk.utils.DirectionsParams;
import com.tempore.directionssdk.utils.exceptions.DirectionsRuleException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.tempore.directionssdk.utils.Validate.notNull;

public class DirectionsRequest {

    //Output type
    private String output;

    //Required Parameters
    private Location origin;
    private Location destination;
    private boolean optimize;

    //Opcional Parameters
    private String mode;
    private boolean alternatives;
    private String avoid;
    private String language;
    private String units;
    private double arrivalTime;
    private double departureTime;
    private String trafficModel;
    private String transitMode;
    private String transitRoutingPreference;
    private List<Waypoint> waypoints = new ArrayList<>();
    public String url;

    private DirectionsRequest(Builder builder) {
        output = builder.output;
        origin = builder.origin;
        destination = builder.destination;
        mode = builder.mode;
        alternatives = builder.alternatives;
        avoid = builder.avoid;
        language = builder.language;
        units = builder.units;
        arrivalTime = builder.arrivalTime;
        departureTime = builder.departureTime;
        trafficModel = builder.trafficModel;
        transitMode = builder.transitMode;
        transitRoutingPreference = builder.transitRoutingPreference;
        waypoints = builder.waypoints;
        optimize = builder.optimize;
        url = HttpUtils.buildRequestUrl(this);
    }

    public boolean isOptimize() {
        return optimize;
    }

    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }

    public String getUrl() {
        return url;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isAlternatives() {
        return alternatives;
    }

    public void setAlternatives(boolean alternatives) {
        this.alternatives = alternatives;
    }

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(double departureTime) {
        this.departureTime = departureTime;
    }

    public String getTrafficModel() {
        return trafficModel;
    }

    public void setTrafficModel(String trafficModel) {
        this.trafficModel = trafficModel;
    }

    public String getTransitMode() {
        return transitMode;
    }

    public void setTransitMode(String transitMode) {
        this.transitMode = transitMode;
    }

    public String getTransitRoutingPreference() {
        return transitRoutingPreference;
    }

    public void setTransitRoutingPreference(String transitRoutingPreference) {
        this.transitRoutingPreference = transitRoutingPreference;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    /**
     * {@code DirectionsRequest} builder static inner class.
     */
    public static final class Builder {
        private String output = DirectionsParams.OUTPUT_JSON;
        private Location origin;
        private Location destination;
        private String mode;
        private boolean alternatives;
        private String avoid;
        private String language;
        private String units;
        private double arrivalTime = DirectionsParams.DEFAULT_DOUBLE;
        private double departureTime = DirectionsParams.DEFAULT_DOUBLE;
        private String trafficModel;
        private String transitMode;
        private String transitRoutingPreference;
        private Waypoint waypoint;
        private List<Waypoint> waypoints = new ArrayList<>();
        private boolean optimize;

        private Builder() {
        }

        /**
         * Sets the {@code output} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code output} to set
         * @return a reference to this Builder
         */
        public Builder withOutput(String val) {
            notNull(val, "Output");
            //output = val;
            return this;
        }

        /**
         * Sets the {@code origin} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code origin} to set
         * @return a reference to this Builder
         */
        public Builder withOrigin(Location val) {
            notNull(val, "Origin");
            origin = val;
            return this;
        }

        /**
         * Sets the {@code destination} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code destination} to set
         * @return a reference to this Builder
         */
        public Builder withDestination(Location val) {
            notNull(val, "Destination");
            destination = val;
            return this;
        }

        /**
         * Sets the {@code mode} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code mode} to set
         * @return a reference to this Builder
         */
        public Builder withMode(String val) {
            notNull(val, "Mode");
            mode = val;
            return this;
        }

        /**
         * Sets the {@code alternatives} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code alternatives} to set
         * @return a reference to this Builder
         */
        public Builder withAlternatives(boolean val) {
            notNull(val, "Alernatives");
            alternatives = val;
            return this;
        }

        /**
         * Sets the {@code avoid} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code avoid} to set
         * @return a reference to this Builder
         */
        public Builder withAvoid(String val) {
            notNull(val, "Avoid");
            avoid = val;
            return this;
        }

        /**
         * Sets the {@code language} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code language} to set
         * @return a reference to this Builder
         */
        public Builder withLanguage(String val) {
            notNull(val, "Language");
            language = val;
            return this;
        }

        /**
         * Sets the {@code optimize} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code optimize} to set
         * @return a reference to this Builder
         */
        public Builder optimize(boolean val) {
            optimize = val;
            return this;
        }

        /**
         * Sets the {@code units} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code units} to set
         * @return a reference to this Builder
         */
        public Builder withUnits(String val) {
            notNull(val, "Units");
            units = val;
            return this;
        }

        /**
         * Sets the {@code arrivalTime} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code arrivalTime} to set
         * @return a reference to this Builder
         */
        public Builder withArrivalTime(double val) {
            notNull(val, "Time");
            arrivalTime = val;
            return this;
        }

        /**
         * Sets the {@code departureTime} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code departureTime} to set
         * @return a reference to this Builder
         */
        public Builder withDepartureTime(double val) {
            notNull(val, "DepartureTime");
            departureTime = val;
            return this;
        }

        /**
         * Sets the {@code trafficModel} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code trafficModel} to set
         * @return a reference to this Builder
         */
        public Builder withTrafficModel(String val) {
            notNull(val, "TrafficModel");
            trafficModel = val;
            return this;
        }

        /**
         * Sets the {@code transitMode} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code transitMode} to set
         * @return a reference to this Builder
         */
        public Builder withTransitMode(String val) {
            notNull(val, "TransitMode");
            transitMode = val;
            return this;
        }

        /**
         * Sets the {@code transitRoutingPreference} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code transitRoutingPreference} to set
         * @return a reference to this Builder
         */
        public Builder withTransitRoutingPreference(String val) {
            notNull(val, "TransitRoutingPreference");
            transitRoutingPreference = val;
            return this;
        }

        /**
         * Sets the {@code waypoint} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code waypoint} to set
         * @return a reference to this Builder
         */
        public Builder withWaypoint(Waypoint val) {
            notNull(val, "Waypoint");
            Log.i("Waypoint","Agregado");
            waypoints.add(val);
            return this;
        }

        /**
         * Returns a {@code DirectionsRequest} built from the parameters previously set.
         *
         * @return a {@code DirectionsRequest} built with parameters of this {@code DirectionsRequest.Builder}
         */
        public DirectionsRequest build() {
            return new DirectionsRequest(this);
        }
    }
}
