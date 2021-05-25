/**
 * Activity for looking up bookings
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

public class LookupBookingsActivity extends AppCompatActivity implements View.OnClickListener
{
    public Button lookupButton;
    public Button insertButton;
    public Button deleteButton;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookup_activity);
        lookupButton = findViewById(R.id.lookup_nav_button);
        insertButton = findViewById(R.id.insert_nav_button);
        deleteButton = findViewById(R.id.delete_nav_button);
        lookupButton.setOnClickListener(this);
        insertButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    /** Called when the user taps the Lookup bookings button */
    public void lookupBookings(View view)
    {
        EditText editStudent = (EditText) findViewById(R.id.lookup_student_edit);
        EditText editUrl = (EditText) findViewById(R.id.url_edit);
        String bookingStudent = editStudent.getText().toString();
        String bookingUrl = editUrl.getText().toString();

        if (!bookingUrl.endsWith("/"))
            bookingUrl = bookingUrl + "/";

        TextView bookingsView = (TextView) findViewById(R.id.bookings_view);
        RestfulLookupTask task = new RestfulLookupTask(bookingsView);
        task.execute(bookingUrl+bookingStudent);
    }

    /** Button action listener for activity navigation */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert_nav_button:
                Intent insertIntent = new Intent(LookupBookingsActivity.this, InsertBookingActivity.class);
                startActivity(insertIntent);
                break;
            case R.id.delete_nav_button:
                Intent deleteIntent = new Intent(LookupBookingsActivity.this, DeleteBookingActivity.class);
                startActivity(deleteIntent);
                break;
        }
    }
}