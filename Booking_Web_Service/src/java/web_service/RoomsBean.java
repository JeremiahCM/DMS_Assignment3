/*
 * Singleton EJB that holds the list of current rooms
 */
package web_service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class RoomsBean
{
    private List<Room> rooms; // list of all rooms

    @PostConstruct
    public void initialiseRoomCollection()
    {
        // convenience list to avoid using any database
        rooms = new ArrayList<>();
        addRoom(1, "WZ301", "WZ Building");
        addRoom(2, "WZ302", "WZ Building");
        addRoom(3, "WS202", "WS Building");
        addRoom(4, "WS205", "WS Building");
        addRoom(5, "WZ315", "WZ Building");
    }

    public void addRoom(int r_id, String room_name, String room_location)
    {
        rooms.add(new Room(r_id, room_name, room_location));
    }

    public boolean removeRoom(String room_name)
    {
        for (Room room : rooms)
        {
            if (room.getName().equals(room_name))
            {
                rooms.remove(room);
                return true;
            }
        }
        return false; // room not found in collection
    }

    public Collection<Room> getRooms()
    {
        return rooms;
    }
}
