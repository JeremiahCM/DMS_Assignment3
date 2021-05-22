/*
 * JAX_RS RESTful resource class for GET requests on bookings
 * and PUT/DELETE requests to add/remove from the booking collection
 */
package web_service;

import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

@Named // so that dependency injection can be used for the EJB
@Path("/students")
public class StudentsResource
{

   @EJB
   private BookingsBean bookingsBean;
   @Context
   private UriInfo context;
   private static final char QUOTE = '\"';

   /**
    * Creates a new instance of StudentsResource
    */
   public StudentsResource()
   {
   }

   @GET
   @Produces(MediaType.APPLICATION_XML)
   public String getAllStudents()
   {
      StringBuilder buffer = new StringBuilder();
      buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      buffer.append("<students uri=").append(QUOTE).append(
         context.getAbsolutePath()).append(QUOTE).append(">");
      Collection<Booking> allStudents = bookingsBean.getBookings(); 
      for (Booking booking : allStudents)
      {
         buffer.append(booking.getXMLString());
      }
      buffer.append("</students>");
      return buffer.toString();
   }

   @GET
   @Produces(MediaType.APPLICATION_XML)
   @Path("{student}")
   public String getBookingsForStudent(@PathParam("student") String studentName)
   {
      StringBuilder buffer = new StringBuilder();
      buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      buffer.append("<students uri=").append(QUOTE).append(
         context.getAbsolutePath()).append(QUOTE).append(">");
      Collection<Booking> studentsBookings = bookingsBean.getBookingsForStudent(studentName); 
      for (Booking booking : studentsBookings)
      {
         buffer.append(booking.getXMLString());
      }
      buffer.append("</students>");
      return buffer.toString();
   }

   @GET
   @Produces(MediaType.APPLICATION_XML)
   @Path("{student}/{booking}")
   public String getDetailsForStudentBooking(@PathParam("student") String studentName,
      @PathParam("booking") String bookingName)
   {
      StringBuilder buffer = new StringBuilder();
      buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      buffer.append("<students uri=").append(QUOTE).append(
         context.getAbsolutePath()).append(QUOTE).append(">");
      Collection<Booking> studentsBookings = bookingsBean.getBookingsForStudent(studentName); 
      for (Booking booking : studentsBookings)
      {
         if (booking.getBookingName().equals(bookingName))
             buffer.append(booking.getXMLString());
      }
      buffer.append("</students>");
      return buffer.toString();
   }

   @PUT
   @Consumes(MediaType.TEXT_PLAIN)
   @Path("{student}/{booking}/{room}/{date}/{time}")
   public void addNewBooking(@PathParam("student") String studentName,
      @PathParam("booking") String bookingName,
      @PathParam("roomName") String roomName,
      @PathParam("date") String date,
      @PathParam("time") String time)
   {
      bookingsBean.addBooking(bookingName, studentName, roomName, date, time);
   }

   @DELETE
   @Produces(MediaType.TEXT_PLAIN)
   @Path("{student}/{booking}")
   public void removeBooking(@PathParam("student") String studentName,
      @PathParam("booking") String bookingName)
   {
      // note NetBeans Test Restful service apparent bug with DELETE
      bookingsBean.removeBooking(bookingName, studentName);
   }

   @POST // also allow post here so can use HTML forms to add bookings
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public void addNewBookingViaPost(
      MultivaluedMap<String, String> formParams)
   {
      String bookingName = formParams.getFirst("booking");
      String studentName = formParams.getFirst("student");
      String roomName = formParams.getFirst("room");
      String date = formParams.getFirst("date");
      String time = formParams.getFirst("time");
      bookingsBean.addBooking(bookingName, studentName, roomName, date, time);
   }
}