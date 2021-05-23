package dms.android_booking_app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LookUpBookingsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Lookup bookings button */
    public void lookupBookings(View view)
    {
        EditText editStudent = (EditText) findViewById(R.id.student_edit);
        String bookingStudent = editStudent.getText().toString();

        EditText editUrl = (EditText) findViewById(R.id.url_edit);
        String bookingUrl = editUrl.getText().toString();
        // ensure url ends with / as the owner name will be appended
        if (!bookingUrl.endsWith("/"))
            bookingUrl = bookingUrl + "/";

        TextView bookingsView = (TextView) findViewById(R.id.bookings_view);
        RestfulLookupTask task = new RestfulLookupTask(bookingsView);
        task.execute(bookingUrl+bookingStudent);
    }
}
