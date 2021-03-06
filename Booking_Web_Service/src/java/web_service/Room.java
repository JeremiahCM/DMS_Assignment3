/*
 * JPA Entity class that represents a Room, as a Java Bean
 * Entity has attribute for a room ID, room name and romm location
 * Implemented get and set methods to acccess and manage these attributes
 * @author Jeremiah Martinez: 18027693 | ?: ?
 * 
 * The files have used code learned from several stackoverflow threads, Telusko youtube channel and reused
 * from lab exercises, few bits of the following is adapted not copy and pasted, except for lab code we have used
*/
package web_service;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Define class Task to be used for dgn1399_etasks table in database
@Entity
@Table(name = "dgn1399_rooms")
public class Room implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int r_id;
    @Column(name = "room_name")
    private String roomName;
    @Column(name = "room_location")
    private String roomLocation;
    
    public Room()
    {
    }
    
    public void setRoom(String roomName)
    {
        this.roomName = roomName;
    }
    
    public String getRoom()
    {
        return this.roomName;
    }
    
    public void setLocation(String roomLocation)
    {
        this.roomLocation = roomLocation;
    }
    
    public String getLocation()
    {
        return this.roomLocation;
    }
    
    public void setRoomID(int r_id)
    {
        this.r_id = r_id;
    }
    
    public int getRoomID()
    {
        return this.r_id;
    }
}
