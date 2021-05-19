/*
 * JavaBean class that represents a Booking
 */

package web_service;

public class Booking
{
    private int b_id;
    private boolean booked;
    private int s_id;
    private int r_id;
    private String time_slot;

    public Booking()
    {
    }
    
    public Booking(int b_id, boolean booked, int s_id, int r_id, String time_slot)
    {
        setBookingID(b_id);
        setBooked(booked);
        setStudentID(s_id);
        setRoomID(r_id);
        setTimeSlot(time_slot);
    }
    
    //Setter for booking ID
    public void setBookingID(int b_id)
    {
        this.b_id = b_id;
    }
    
    public int getBookingID()
    {
        return b_id;
    }
    
    //Setter for booked status
    public void setBooked(boolean booked)
    {
        this.booked = booked;
    }
    
    //Getter for booked status
    public boolean getBooked()
    {
        return booked;
    }
    
    //Setter for student ID
    public void setStudentID(int e_id)
    {
        this.s_id = e_id;
    }

    //Getter for student ID
    public int getStudentID()
    {
        return s_id;
    }
    
    //Setter for room ID
    public void setRoomID(int r_id)
    {
        this.r_id = r_id;
    }

    //Getter for room ID
    public int getRoomID()
    {
        return r_id;
    }
    
    //Setter for time slot
    public void setTimeSlot(String time_slot)
    {
        this.time_slot = time_slot;
    }
    
    //Getter for time slot
    public String getTimeSlot()
    {
        return time_slot;
    }
    
    public String getXMLString()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<booking>");
        buffer.append("<b_id>").append(getBookingID()).append("</b_id>");
        buffer.append("<booked>").append(getBooked()).append("</booked>");
        buffer.append("<s_id>").append(getStudentID()).append("</s_id>");
        buffer.append("<r_id>").append(getRoomID()).append("</r_id>");
        buffer.append("<time_slot>").append(getTimeSlot()).append("</time_slot>");
        buffer.append("</booking>");
        return buffer.toString();
    }
}