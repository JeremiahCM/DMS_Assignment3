/**
 * An Aysnc Task that accepts a URL as a string and makes
 * a background PUT request to that URL.
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

public class RestfulInsertTask extends AsyncTask<String, Void, String>
{
    private TextView bookingsView;
    private String studentName, bookingName, roomName, date, time;

    public RestfulInsertTask(TextView bookingsView, String studentName, String bookingName,
                                String roomName, String date, String time)
    {
        this.bookingsView = bookingsView;
        this.studentName = studentName;
        this.bookingName = bookingName;
        this.roomName = roomName;
        this.date = date;
        this.time = time;
    }

    protected String doInBackground(String... params)
    {
        if (params.length == 0)
            return "No URL provided";
        try
        {
            URL bookingUrl = new URL(params[0]);
            HttpURLConnection conn
                    = (HttpURLConnection) bookingUrl.openConnection();
            conn.setReadTimeout(3000); //3 seconds
            conn.setConnectTimeout(3000); //3 seconds
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "text/plain");
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT)
                return "Successful insert";
            else
                return "HTTP Response code " + responseCode;
        }
        catch (MalformedURLException e)
        {
            Log.e("RestfulInsertTask", "Malformed URL: " + e);
            e.printStackTrace();
        }
        catch (IOException e)
        {
            Log.e("RestfulInsertTask", "IOException: " + e);
            e.printStackTrace();
        }
        return "Error during HTTP PUT request to url " + params[0];
    }

    protected void onPostExecute(String workerResult)
    {
        bookingsView.setText(workerResult);
    }
}