package com.tempore.directionssdk.model;

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

import com.tempore.directionssdk.http.response.responsemodel.LatLng;

public class Geolocation {
    private String direction;
    private String placeId;
    private LatLng latLng;

    public Geolocation(){}

    private Geolocation(Builder builder) {
        setDirection(builder.direction);
        setPlaceId(builder.placeId);
        latLng = builder.latLng;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }


    /**
     * {@code Geolocation} builder static inner class.
     */
    public static final class Builder {
        private String direction;
        private String placeId;
        private LatLng latLng;

        private Builder() {
        }

        /**
         * Sets the {@code direction} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code direction} to set
         * @return a reference to this Builder
         */
        public Builder withDirection(String val) {
            direction = val;
            return this;
        }

        /**
         * Sets the {@code placeId} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code placeId} to set
         * @return a reference to this Builder
         */
        public Builder withPlaceId(String val) {
            placeId = val;
            return this;
        }

        /**
         * Sets the {@code latLng} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code latLng} to set
         * @return a reference to this Builder
         */
        public Builder withCoordinates(LatLng val) {
            latLng = val;
            return this;
        }

        /**
         * Returns a {@code Geolocation} built from the parameters previously set.
         *
         * @return a {@code Geolocation} built with parameters of this {@code Geolocation.Builder}
         */
        public Geolocation build() {
            return new Geolocation(this);
        }
    }
}
