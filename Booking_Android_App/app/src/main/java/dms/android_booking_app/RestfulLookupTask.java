/**
 * An Aysnc class that accepts a URL as a string and makes a background
 * HTTP request to that URL.
 */
package dms.android_booking_app;

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

                StringBuilder bookingsList = new StringBuilder();
                int bookingIndex = xmlResponse.indexOf("<booking>");
                while (bookingIndex >= 0)
                {
                    int bookingStartIndex
                            = xmlResponse.indexOf("<bookingName>", bookingIndex) + 13;
                    int bookingEndIndex
                            = xmlResponse.indexOf("</", bookingStartIndex);
                    String bookingName = (bookingEndIndex > bookingStartIndex) ?
                            xmlResponse.substring(bookingStartIndex,
                                    bookingEndIndex) : "No booking name";
                    int studentStartIndex = xmlResponse.indexOf
                            ("<studentName>", bookingEndIndex) + 12;
                    int studentEndIndex = xmlResponse.indexOf
                            ("</", studentStartIndex);
                    String studentName
                            = (studentEndIndex > studentStartIndex) ?
                            xmlResponse.substring(studentStartIndex,
                                    studentEndIndex) : "No student name";
                    int roomStartIndex = xmlResponse.indexOf
                            ("<roomName>", studentEndIndex) + 9;
                    int roomEndIndex = xmlResponse.indexOf
                            ("</", roomStartIndex);
                    String roomName
                            = (roomEndIndex > roomStartIndex) ?
                            xmlResponse.substring(roomStartIndex,
                                    roomEndIndex) : "No room name";
                    int dateStartIndex = xmlResponse.indexOf
                            ("<date>", roomEndIndex) + 5;
                    int dateEndIndex = xmlResponse.indexOf
                            ("</", dateStartIndex);
                    String date
                            = (dateEndIndex > dateStartIndex) ?
                            xmlResponse.substring(dateStartIndex,
                                    dateEndIndex) : "No date";
                    int timeStartIndex = xmlResponse.indexOf
                            ("<time>", dateEndIndex) + 5;
                    int timeEndIndex = xmlResponse.indexOf
                            ("</", timeStartIndex);
                    String time
                            = (timeEndIndex > timeStartIndex) ?
                            xmlResponse.substring(timeStartIndex,
                                    timeEndIndex) : "No time";
                    bookingsList.append("booking: ").append(bookingName)
                            .append(", student: ").append(studentName)
                            .append(", room: ").append(roomName)
                            .append(", date: ").append(date)
                            .append(", time: ").append(time)
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