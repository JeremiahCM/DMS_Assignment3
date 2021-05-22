/**
 * An Aysnc class that accepts a URL as a string and makes a background
 * HTTP request to that URL. In the (presumed XML) response it searches
 * for any <student> tag and collected the string within the <theStudent>
 * into a list, which it uses to populate the
 * TextView passed to its constructor
 * @see MainActivity.java
 */

package com.example.booking_android_app;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StudyRoomBookingLookup extends AsyncTask<String, Void, String> {

    private TextView bookingView;

    //Initialised constructor
    public StudyRoomBookingLookup(TextView bookingView)
    {
        this.bookingView = bookingView;
    }

    protected String doInBackground(String... params) {

        if(params.length == 0)
        {
            return "No URL provided";
        }

        try
        {
            URL bookingURL = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) bookingURL.openConnection();
            conn.setReadTimeout(3000);
            conn.setConnectTimeout(3000);
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                BufferedReader br = new BufferedReader
                        (new InputStreamReader(conn.getInputStream()));
                StringBuilder xmlResponse = new StringBuilder();
                String line = br.readLine();
                while(line != null)
                {
                    xmlResponse.append(line);
                    line = br.readLine();
                }
                br.close();
                conn.disconnect();

                if(xmlResponse.length()==0)
                {
                    return "Empty response";
                }

                StringBuilder student = new StringBuilder();
                int studentIndex = xmlResponse.indexOf("<student>");
                while(studentIndex >= 0)
                {
                    int nameStartIndex =
                            xmlResponse.indexOf("<s_Name>", studentIndex) + 9;
                    int nameEndIndex =
                            xmlResponse.indexOf("</", nameStartIndex);
                    String studentName = (nameEndIndex > nameStartIndex) ?
                            xmlResponse.substring(nameStartIndex, nameEndIndex) : "No name";

                    student.append("Name: ").append(studentName);
                }
                return student.toString();
            }
            else
            {
                return "HTTP Response code " + responseCode;
            }
        } catch (MalformedURLException e) {
            Log.e("StudyRoomBookingLookup", "Malformed URL:" +e);
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("StudyRoomBookingLookup", "IOException:" +e);
            e.printStackTrace();
        }
        return "Error during HTTP request to url " + params[0];
    }
}
