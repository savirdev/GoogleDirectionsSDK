package com.tempore.directionssdk.http.response.responsemodel;/*
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

import android.text.Html;
import android.text.Spanned;

import java.util.List;

public class Step {
    private Distance distance;
    private Duration duration;
    private LatLng endLocation;
    private LatLng startLocation;
    private String htmlInstruction;
    private String instruction;
    private Polyline polyline;
    private String travelMode;
    private String maneuver;
    private List<Step> subSteps;
    private TransitDetails transitDetails;

    public TransitDetails getTransitDetails() {
        return transitDetails;
    }

    public void setTransitDetails(TransitDetails transitDetails) {
        this.transitDetails = transitDetails;
    }

    public List<Step> getSubSteps() {
        return subSteps;
    }

    public void setSubSteps(List<Step> subSteps) {
        this.subSteps = subSteps;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        Spanned result;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(instruction,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(instruction);
        }
        this.instruction = result.toString();
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LatLng getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LatLng endLocation) {
        this.endLocation = endLocation;
    }

    public LatLng getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LatLng startLocation) {
        this.startLocation = startLocation;
    }

    public String getHtmlInstruction() {
        return htmlInstruction;
    }

    public void setHtmlInstruction(String htmlInstruction) {
        this.htmlInstruction = htmlInstruction;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public String getManeuver() {
        return maneuver;
    }

    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }
}
