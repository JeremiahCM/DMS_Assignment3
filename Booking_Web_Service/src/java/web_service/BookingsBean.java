/*
 * Singleton EJB that holds the list of current bookings
 */
package web_service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class BookingsBean
{
    private List<Booking> bookings; // list of all bookings

    @PostConstruct
    public void initialiseBookingCollection()
    {
        // convenience list to avoid using any database
        bookings = new ArrayList<>();
        addBooking(1, true, 3, 2, "10:00 - 11:00am");
        addBooking(2, false, 0, 0, null);
        addBooking(3, true, 1, 5, "1:00 - 2:00pm");
    }

    public void addBooking(int b_id, boolean booked, int s_id, int r_id, String time_slot)
    {
        bookings.add(new Booking(b_id, booked, s_id, r_id, time_slot));
    }

    public boolean removeBooking(int b_id)
    {
        for (Booking booking : bookings)
        {
            if (booking.getBookingID() == b_id)
            {
                bookings.remove(booking);
                return true;
            }
        }
        return false; // booking not found in collection
    }

    public Collection<Booking> getBookings()
    {
        return bookings;
    }
}