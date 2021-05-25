/**
 * A Java Android application accessing the bookings
 * RESTful web service.
 */
package dms.android_booking_app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteBookingActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_activity);
    }

    /** Called when the user taps the Lookup bookings button */
    public void deleteBooking(View view)
    {
        EditText editStudent = (EditText) findViewById(R.id.delete_student_edit);
        EditText editBooking = (EditText) findViewById(R.id.delete_booking_edit);
        String studentName = editStudent.getText().toString();
        String bookingName = editBooking.getText().toString();
        EditText editUrl = (EditText) findViewById(R.id.url_edit);
        String bookingUrl = editUrl.getText().toString();
        // ensure url ends with / as the owner name will be appended
        if (!bookingUrl.endsWith("/"))
            bookingUrl = bookingUrl + "/";
        TextView bookingsView = (TextView) findViewById(R.id.bookings_view);
        RestfulDeleteTask task = new RestfulDeleteTask(bookingsView, studentName, bookingName);
        task.execute(bookingUrl+studentName+"/"+bookingName);
    }
}