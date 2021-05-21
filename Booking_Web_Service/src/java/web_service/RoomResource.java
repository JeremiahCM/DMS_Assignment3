/*
 * JAX_RS RESTful resource class for various requests on rooms 
 *(Specify more later)
 */
package web_service;

import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Named
@Path(value = "/rooms")
public class RoomResource {
    
    @EJB
    private RoomBean roomsBean;
    @Context
    private UriInfo context;
    private static final char QUOTE = '"';

    
    //Creates a new instance 
    public RoomResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getallRooms() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        buffer.append("<rooms uri=").append(QUOTE).append(
         context.getAbsolutePath()).append(QUOTE).append(">");
        
        Collection<Room> allPets = roomsBean.getRooms(); 
        for (Room room : allPets)
        {
            buffer.append(room.getXMLString());
        }
        buffer.append("</rooms>");
        return buffer.toString();
    }
    
    //Method for booked rooms
    //Method for fav bookings
    //Method for cancelling 
    
}
