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

import android.graphics.Color;

import java.util.List;

public class Line {
    private String line;
    private String shortLine;
    private int color;
    private List<Agency> agencies;
    private String url;
    private String icon;
    private int textColor;
    private Vehicle vehicle;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getShortLine() {
        return shortLine;
    }

    public void setShortLine(String shortLine) {
        this.shortLine = shortLine;
    }

    public int getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = Color.parseColor(color);
    }

    public List<Agency> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<Agency> agencies) {
        this.agencies = agencies;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = Color.parseColor(textColor);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
