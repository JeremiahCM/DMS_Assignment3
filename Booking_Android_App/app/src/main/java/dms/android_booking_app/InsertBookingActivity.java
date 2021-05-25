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

public class InsertBookingActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_activity);
    }

    /** Called when the user taps the Lookup bookings button */
    public void insertBooking(View view)
    {
        EditText editStudent = (EditText) findViewById(R.id.insert_student_edit);
        EditText editBooking = (EditText) findViewById(R.id.insert_booking_edit);
        EditText editRoom = (EditText) findViewById(R.id.insert_room_edit);
        EditText editDate = (EditText) findViewById(R.id.insert_date_edit);
        EditText editTime = (EditText) findViewById(R.id.insert_time_edit);
        String studentName = editStudent.getText().toString();
        String bookingName = editBooking.getText().toString();
        String roomName = editRoom.getText().toString();
        String date = editDate.getText().toString();
        String time = editTime.getText().toString();
        EditText editUrl = (EditText) findViewById(R.id.url_edit);
        String bookingUrl = editUrl.getText().toString();
        // ensure url ends with / as the owner name will be appended
        if (!bookingUrl.endsWith("/"))
            bookingUrl = bookingUrl + "/";
        TextView bookingsView = (TextView) findViewById(R.id.bookings_view);
        RestfulInsertTask task = new RestfulInsertTask(bookingsView, studentName, bookingName, roomName, date, time);
        task.execute(bookingUrl+studentName+"/"+bookingName+"/"+roomName+"/"+date+"/"+time);
    }
}