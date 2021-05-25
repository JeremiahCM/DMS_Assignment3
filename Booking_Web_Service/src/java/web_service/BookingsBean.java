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
    private List<Booking> bookings; //list of all bookings

    @PostConstruct
    public void initialiseBookingCollection()
    {
        //initiating list instead of having a database
        bookings = new ArrayList<>();
        addBooking("CISE Sprint Review", "Jeremiah", "WZ305", "Jun 14", "10:00am - 11:00am");
        addBooking("RnD Client Meeting", "Trisha", "WS201", "Jun 17", "2:00pm - 3:00pm");
        addBooking("Final Presentation", "John", "WT702", "Jun 13",  "1:00pm - 2:00pm");
        addBooking("DMS Assignment 3", "Jeremiah", "WZ305", "Jun 18", "2:00pm - 4:00pm");
        addBooking("RnD Mentor Meeting", "Trisha", "WZ315", "Jun 17", "12:00pm - 2:00pm");
        addBooking("Project Work", "Olivia", "WZ701", "Jun 13", "10:00am - 4:00pm");
        addBooking("Web Development", "Zhang", "WZ315", "Jun 21", "9:00am - 11:00am");
        addBooking("AI Assignment 2", "Jeremiah", "WS601", "Jun 20", "4:00pm - 6:00pm");
        addBooking("DMS Assignment 1", "John", "WZ305", "Jun 21", "3:00pm - 5:00pm");
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
        return false;
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