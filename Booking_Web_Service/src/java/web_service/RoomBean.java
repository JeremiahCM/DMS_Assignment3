/*
 * Singleton EJB that holds the list of Rooms
 */

package web_service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class RoomBean {
    
    private List<Room> rooms;

    @PostConstruct
    public void initialiseRooms() {
        
        rooms = new ArrayList<>();
        addRoom("WAL444", "WA Library", true);
        addRoom("WA326", "WA Lvl 3", true);
        addRoom("WZ570", "WZ Lvl 5", true);
        addRoom("WZ391", "WZ Lvl 3", true);
       
    }

    public void addRoom(String room_ID, String room_Location, boolean room_Availability) {
       rooms.add(new Room(room_ID, room_Location, room_Availability)); 
    }

    public Collection<Room> getRooms() {
        return rooms; 
    }

    public Collection<Room> getBookedStudyRooms() {
        Collection<Room> bookedRooms = new ArrayList<Room>();
        for(Room room : rooms)
        {
            if(room.getRoomAvailability() == false) //observe functionality 
            {
                bookedRooms.add(room); 
            }
        }
        return bookedRooms; 
    }   
    
    //Collections for Fav Bookings

}
