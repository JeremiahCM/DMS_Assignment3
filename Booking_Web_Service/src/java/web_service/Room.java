/*
 * JavaBean class that represents a Room
 */
package web_service;

public class Room {
    private int r_id;
    private String room_name;
    private String room_location;
    
    public Room()
    {
    }
    
    public Room(int r_id, String room_name, String room_location)
   {
      setRoomID(r_id);
      setName(room_name);
      setLocation(room_location);
   }
    
    public void setName(String roomName)
    {
        this.room_name = room_name;
    }
    
    public String getName()
    {
        return this.room_name;
    }
    
    public void setLocation(String roomLocation)
    {
        this.room_location = room_location;
    }
    
    public String getLocation()
    {
        return this.room_location;
    }
    
    public void setRoomID(int r_id)
    {
        this.r_id = r_id;
    }
    
    public int getRoomID()
    {
        return this.r_id;
    }
    
    public String getXMLString()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<room>");
        buffer.append("<r_id>").append(getRoomID()).append("</r_id>");
        buffer.append("<room_name>").append(getName()).append("</room_name>");
        buffer.append("<room_location>").append(getLocation()).append("</room_location>");
        buffer.append("</room>");
        return buffer.toString();
    }
}
