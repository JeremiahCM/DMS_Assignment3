/**
 * An Aysnc class that accepts a URL as a string and makes a background
 * HTTP request to that URL. In the (presumed XML) response it searches
 * for any <pet> tag and collected the string within the <petName> and
 * within the <petSpecies> into a list, which it uses to populate the
 * TextView passed to its constructor
 * @see MainActivity.java
 */
package dms.booking_android_app;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestfulLookupTask extends AsyncTask<String, Void, String>
{
    private TextView bookingsView;

    public RestfulLookupTask(TextView bookingsView)
    {
        this.bookingsView = bookingsView;
    }

    protected String doInBackground(String... params)
    {
        if (params.length == 0)
        {
            return "No URL provided";
        }
        try
        {
            URL bookingUrl = new URL(params[0]);
            HttpURLConnection conn
                = (HttpURLConnection) bookingUrl.openConnection();
            conn.setReadTimeout(3000); // 3000ms
            conn.setConnectTimeout(3000); // 3000ms
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                BufferedReader br = new BufferedReader
                    (new InputStreamReader(conn.getInputStream()));
                StringBuilder xmlResponse = new StringBuilder();
                String line = br.readLine();
                while (line != null)
                {
                    xmlResponse.append(line);
                    line = br.readLine();
                }
                br.close();
                conn.disconnect();
                if (xmlResponse.length()==0)
                    return "Empty response";
                // simplistic parse of the XML string to extract each
                // booking name and species
                StringBuilder bookingsList = new StringBuilder();
                int bookingIndex = xmlResponse.indexOf("<booking>");
                while (bookingIndex >= 0)
                {
                    int bookingIDStartIndex
                            = xmlResponse.indexOf("<bookingID>", bookingIndex) + 3;
                    int bookingIDEndIndex
                            = xmlResponse.indexOf("</", bookingIDStartIndex);
                    String bookingID = (bookingIDEndIndex > bookingIDStartIndex) ?
                            xmlResponse.substring(bookingIDStartIndex,
                                    bookingIDEndIndex) : "No booking ID";

                    int bookedStartIndex
                            = xmlResponse.indexOf("<booked>", bookingIndex) + 6;
                    int bookedEndIndex
                            = xmlResponse.indexOf("</", bookedStartIndex);
                    String booked = (bookedEndIndex > bookedStartIndex) ?
                            xmlResponse.substring(bookedStartIndex,
                                    bookedEndIndex) : "No name";

                    int studentStartIndex
                            = xmlResponse.indexOf("<studentName>", bookingIndex) + 9;
                    int studentEndIndex
                            = xmlResponse.indexOf("</", studentStartIndex);
                    String studentName = (studentEndIndex > studentStartIndex) ?
                            xmlResponse.substring(studentStartIndex,
                                    studentEndIndex) : "No name";

                    int roomStartIndex
                            = xmlResponse.indexOf("<roomName>", bookingIndex) + 12;
                    int roomEndIndex
                            = xmlResponse.indexOf("</", roomStartIndex);
                    String roomName = (roomEndIndex > roomStartIndex) ?
                            xmlResponse.substring(roomStartIndex,
                                    roomEndIndex) : "No name";

                    int dateStartIndex
                            = xmlResponse.indexOf("<date>", bookingIndex) + 15;
                    int dateEndIndex
                            = xmlResponse.indexOf("</", dateStartIndex);
                    String date = (dateEndIndex > dateStartIndex) ?
                        xmlResponse.substring(dateStartIndex,
                                dateEndIndex) : "No date";

                    int timeStartIndex
                            = xmlResponse.indexOf("<date>", bookingIndex) + 18;
                    int timeEndIndex
                            = xmlResponse.indexOf("</", timeStartIndex);
                    String timeSlot = (timeEndIndex > timeStartIndex) ?
                            xmlResponse.substring(timeStartIndex,
                                    timeEndIndex) : "No date";

                    bookingsList.append("bookingID: ").append(bookingID)
                        .append(", booked: ").append(booked)
                        .append(", studentName: ").append(studentName)
                        .append(", roomName: ").append(roomName)
                        .append(", date: ").append(date)
                        .append(", timeSlot: ").append(timeSlot)
                        .append("\n");
                    bookingIndex = xmlResponse.indexOf("<booking>", bookingIndex+1);
                }
                return bookingsList.toString();
            }
            else
                return "HTTP Response code " + responseCode;
        }
        catch (MalformedURLException e)
        {
            Log.e("RestfulLookupTask", "Malformed URL: " + e);
            e.printStackTrace();
        }
        catch (IOException e)
        {
            Log.e("RestfulLookupTask", "IOException: " + e);
            e.printStackTrace();
        }
        return "Error during HTTP request to url " + params[0];
    }

    protected void onPostExecute(String workerResult)
    {
        bookingsView.setText(workerResult);
    }
}