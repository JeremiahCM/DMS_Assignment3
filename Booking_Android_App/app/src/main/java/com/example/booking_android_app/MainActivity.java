package com.example.booking_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Called when the user taps the Lookup pets button
    {
        EditText editStudent = (EditText) findViewById(R.id.student_edit);
        String theStudent = editStudent.getText().toString();

        //URL that will connect to the web service
        EditText editUrl = (EditText) findViewById(R.id.url_edit);
        String bookingURL = editUrl.getText().toString();
        // ensure url ends with / as the owner name will be appended
        if (!bookingURL.endsWith("/"))
        {
            bookingURL = bookingURL + "/";
        }

        TextView bookingView = (TextView) findViewById(R.id.student_view);
        StudyRoomBookingLookup task = new StudyRoomBookingLookup(bookingView);
        task.execute(bookingURL + theStudent);
    }

}
