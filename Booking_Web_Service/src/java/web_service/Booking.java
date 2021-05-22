/*
 * JavaBean class that represents a Booking
 */

package web_service;

public class Booking
{
    private String bookingName;
    private String studentName;
    private String roomName;
    private String date;
    private String time;

    public Booking()
    {
    }
    
    public Booking(String bookingName, String studentName, String roomName, String date, String time)
    {
        setBookingName(bookingName);
        setStudentName(studentName);
        setRoomName(roomName);
        setDate(date);
        setTime(time);
    }
    
    //Setter for booking name
    public void setBookingName(String bookingName)
    {
        this.bookingName = bookingName;
    }
    
    //Getter for booking name
    public String getBookingName()
    {
        return bookingName;
    }
    
    //Setter for student name
    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    //Getter for student name
    public String getStudentName()
    {
        return studentName;
    }
    
    //Setter for room name
    public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }

    //Getter for room name
    public String getRoomName()
    {
        return roomName;
    }
    
    //Setter for date
    public void setDate(String date)
    {
        this.date = date;
    }
    
    //Getter for date
    public String getDate()
    {
        return date;
    }
    
    //Setter for time
    public void setTime(String time)
    {
        this.time = time;
    }
    
    //Getter for time
    public String getTime()
    {
        return time;
    }
    
   /*
     #SERIALIZABLE
    */
    public String getXMLString()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<booking>");
        buffer.append("<bookingName>").append(getBookingName()).append("</bookingName>");
        buffer.append("<studentName>").append(getStudentName()).append("</studentName>");
        buffer.append("<roomName>").append(getRoomName()).append("</roomName>");
        buffer.append("<date>").append(getDate()).append("</date>");
        buffer.append("<time>").append(getTime()).append("</time>");
        buffer.append("</booking>");
        return buffer.toString();
    }
}