/**
 * Activity for deleting existing bookings
 * in the RESTful web service.
 */
package dms.android_booking_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteBookingActivity extends AppCompatActivity implements View.OnClickListener
{
    public Button lookupButton;
    public Button insertButton;
    public Button deleteButton;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_activity);
        lookupButton = findViewById(R.id.lookup_nav_button);
        insertButton = findViewById(R.id.insert_nav_button);
        deleteButton = findViewById(R.id.delete_nav_button);
        lookupButton.setOnClickListener(this);
        insertButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    /** Called when the user taps the Delete bookings button */
    public void deleteBooking(View view)
    {
        EditText editStudent = (EditText) findViewById(R.id.delete_student_edit);
        EditText editBooking = (EditText) findViewById(R.id.delete_booking_edit);
        EditText editUrl = (EditText) findViewById(R.id.url_edit);
        String studentName = editStudent.getText().toString();
        String bookingName = editBooking.getText().toString();
        String bookingUrl = editUrl.getText().toString();

        if (!bookingUrl.endsWith("/"))
            bookingUrl = bookingUrl + "/";

        TextView bookingsView = (TextView) findViewById(R.id.bookings_view);
        RestfulDeleteTask task = new RestfulDeleteTask(bookingsView, studentName, bookingName);
        task.execute(bookingUrl+studentName+"/"+bookingName);
    }

    /** Button action listener for activity navigation */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lookup_nav_button:
                Intent lookupIntent = new Intent(DeleteBookingActivity.this, LookupBookingsActivity.class);
                startActivity(lookupIntent);
                break;
            case R.id.insert_nav_button:
                Intent insertIntent = new Intent(DeleteBookingActivity.this, InsertBookingActivity.class);
                startActivity(insertIntent);
                break;
        }
    }
}