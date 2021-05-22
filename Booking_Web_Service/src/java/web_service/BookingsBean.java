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
        addBooking("CISE Sprint Review", "Jeremiah", "WZ305", "Jun 14", "10:00 - 11:00am");
        addBooking("RnD Meeting", "Trisha", "WS201", "Jun 16", "2:00pm - 3:00pm");
        addBooking("Final Presentation", "John", "WA404", "Jun 13",  "1:00 - 2:00pm");
        addBooking("Project Work", "Jeremiah", "WZ305", "Jun 16", "1:00 - 3:00pm");
    }

    public void addBooking(String bookingName, String studentName, String roomName, String date, String time)
    {
        bookings.add(new Booking(bookingName, studentName, roomName, date, time));
    }

    public boolean removeBooking(String bookingName, String studentName)
    {
        for (Booking booking : bookings)
        {
            if (booking.getBookingName().equals(bookingName)
                && booking.getStudentName().equals(studentName))
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
    
    public Booking getBooking(String bookingName)
    {
        for (Booking booking : bookings)
        {
            if(booking.getBookingName().equals(bookingName))
                return booking;
        }
        
        return null;
    }
   
    public Collection<Booking> getBookingsForStudent(String studentName)
    {
        Collection<Booking> studentBookings = new ArrayList<Booking>();
        for (Booking booking : bookings)
        {
          if(booking.getStudentName().equals(studentName))
             studentBookings.add(booking);
       }
       return studentBookings;
    }

    public Collection<String> getStudents()
    {
        Collection<String> students = new ArrayList<String>();
        for (Booking booking : bookings)
        {
            if(!students.contains(booking.getStudentName()))
                students.add(booking.getStudentName());
        }
        return students;
    }

    public Collection<Booking> getBookingsForRoom(String roomName)
    {
        Collection<Booking> roomBookings = new ArrayList<Booking>();
        for (Booking booking : bookings)
        {
            if(booking.getRoomName().equals(roomName))
                roomBookings.add(booking);
        }
        return roomBookings;
    }
    
    public Collection<String> getRooms()
    {
        Collection<String> rooms = new ArrayList<String>();
        for (Booking booking : bookings)
        {
            if(!rooms.contains(booking.getRoomName()))
                rooms.add(booking.getRoomName());
        }
        return rooms;
    }
}