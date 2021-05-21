/*
 * JavaBean class that represents a Room
 */

package web_service;

public class Room {
    
    private String r_ID;
    private String r_Location;
    private boolean r_Availability;
    //private String r_TImeSlot; - add later once app works as 
    //this function is more complex 
   
    //Default constructor
    public Room()
    {
    }
    
    //Constructor to initialize instance variables
    public Room(String r_ID, String r_Location, boolean r_Availability)
    {
        setRoomID(r_ID);
        setRoomLocation(r_Location);
        setRoomAvailability(true);
        //setRoomTimeslot(r_Timeslot); 
    }
    
   public String getRoomID()
   {
      return r_ID;
   }

   public void setRoomID(String r_ID)
   {
      this.r_ID = r_ID;
   }

   public String getRoomLocation()
   {
      return r_Location;
   }

   public void setRoomLocation(String r_Location)
   {
      this.r_Location = r_Location;
   }
   
   public boolean getRoomAvailability()
   {
      return r_Availability;
   }

   public void setRoomAvailability(boolean r_Availability)
   {
      this.r_Availability = r_Availability;
   }
   
   /*
   public String getRoomTimeslot()
   {
      return r_TImeSlot;
   }

   public void setRoomTimeslot(String r_TImeSlot)
   {
      this.r_TImeSlot = r_TImeSlot;
   }
*/
   
   public String getXMLString()
   {
      StringBuilder buffer = new StringBuilder();
      buffer.append("<room>");
      buffer.append("<r_ID>").append(getRoomID()).append("</r_ID>");
      buffer.append("<r_Location>").append(getRoomLocation()).append("</r_Location>");
      buffer.append("<r_Availability>").append(getRoomAvailability()).append("</r_Availability>");
      buffer.append("</room>");
      return buffer.toString();
   }
}
