package com.tempore.directionssdk.http;/*
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

import android.os.AsyncTask;
import android.util.Log;

import com.tempore.directionssdk.utils.DirectionsParams;
import com.tempore.directionssdk.utils.JSONDirectionsParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

public class GetTask extends AsyncTask<String, String, String> {

    private String mRestUrl;
    private DirectionRequestCallback mCallback;

    public GetTask(String restUrl, DirectionRequestCallback callback){
        this.mRestUrl = restUrl;
        this.mCallback = callback;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder json = new StringBuilder();
        try {
            URL url = new URL(mRestUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
            urlConnection.connect();

            InputStream in = urlConnection.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = r.readLine()) != null) {
                json.append(line);
            }
            urlConnection.disconnect();
        }catch (Exception e){
            Log.e("Error","" +  e.getMessage());
            return DirectionsParams.DEFAULT_STRING;
        }
        return json.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.onRequestFinish(JSONDirectionsParser.getDirectionsResponse(result));
        super.onPostExecute(result);
    }
}
