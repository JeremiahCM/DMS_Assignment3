/*
 * JAX_RS RESTful resource class for GET requests on rooms
 * @author Andrew Ensor
 */
package web_service;

import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Named // so that dependency injection can be used for the EJB
@Path("/rooms")
public class RoomsResource
{

   @EJB
   private RoomsBean roomsBean;
   @Context
   private UriInfo context;
   private static final char QUOTE = '\"';

   /**
    * Creates a new instance of RoomsResource
    */
   public RoomsResource()
   {
   }

   @GET
   @Produces(MediaType.APPLICATION_XML)
   public String getAllRooms()
   {
      StringBuilder buffer = new StringBuilder();
      buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      buffer.append("<rooms uri=").append(QUOTE).append(
         context.getAbsolutePath()).append(QUOTE).append(">");
      Collection<Room> allRooms = roomsBean.getRooms(); 
      for (Room room : allRooms)
      {
         buffer.append(room.getXMLString());
      }
      buffer.append("</rooms>");
      return buffer.toString();
   }
}
