/*
 * JAX_RS RESTful resource class for GET requests on bookings
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
@Path("/bookings")
public class BookingsResource
{
    @EJB
    private BookingsBean bookingsBean;
    @Context
    private UriInfo context;
    private static final char QUOTE = '\"';

    /**
     * Creates a new instance of BookingsResource
     */
    public BookingsResource()
    {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getAllBookings()
    {
       StringBuilder buffer = new StringBuilder();
       buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
       buffer.append("<bookings uri=").append(QUOTE).append(
          context.getAbsolutePath()).append(QUOTE).append(">");
       Collection<Booking> allBookings = bookingsBean.getBookings(); 
       for (Booking booking : allBookings)
       {
          buffer.append(booking.getXMLString());
       }
       buffer.append("</bookings>");
       return buffer.toString();
    }

   @GET
   @Produces(MediaType.APPLICATION_XML)
   @Path("{student}")
   public String getBookingsForStudent(@PathParam("student") String studentName)
   {
      StringBuilder buffer = new StringBuilder();
      buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      buffer.append("<bookings uri=").append(QUOTE).append(
         context.getAbsolutePath()).append(QUOTE).append(">");
      Collection<Booking> studentsBookings = bookingsBean.getBookingsForStudent(studentName); 
      for (Booking booking : studentsBookings)
      {
         buffer.append(booking.getXMLString());
      }
      buffer.append("</bookings>");
      return buffer.toString();
   }
}
