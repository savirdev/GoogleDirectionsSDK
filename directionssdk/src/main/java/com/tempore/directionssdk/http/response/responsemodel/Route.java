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

import java.util.List;

public class Route {
    private Bounds bounds;
    private String copyrights;
    private List<Leg> legs;
    private OverviewPolyline overviewPolyline;
    private String summary;
    private List<String> warnings;
    private List<Integer> waypointOrder;
    private Fare fare;

    public List<Integer> getWaypointOrder() {
        return waypointOrder;
    }

    public void setWaypointOrder(List<Integer> waypointOrder) {
        this.waypointOrder = waypointOrder;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }


    public OverviewPolyline getOverviewPolyline() {
        return overviewPolyline;
    }

    public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }
}
