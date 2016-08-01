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

public class Waypoint {
    private Location location;
    private boolean addToRoute;

    private Waypoint(Builder builder) {
        location = builder.location;
        addToRoute = builder.addToRoute;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isAddToRoute() {
        return addToRoute;
    }

    public void setAddToRoute(boolean addToRoute) {
        this.addToRoute = addToRoute;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    /**
     * {@code Waypoint} builder static inner class.
     */
    public static final class Builder {
        private Location location;
        private boolean addToRoute;

        private Builder() {
        }

        /**
         * Sets the {@code location} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code location} to set
         * @return a reference to this Builder
         */
        public Builder withLocation(Location val) {
            location = val;
            return this;
        }

        /**
         * Sets the {@code addToRoute} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code addToRoute} to set
         * @return a reference to this Builder
         */
        public Builder withAddToRoute(boolean val) {
            addToRoute = val;
            return this;
        }

        /**
         * Returns a {@code Waypoint} built from the parameters previously set.
         *
         * @return a {@code Waypoint} built with parameters of this {@code Waypoint.Builder}
         */
        public Waypoint build() {
            return new Waypoint(this);
        }
    }
}