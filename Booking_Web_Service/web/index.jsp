<%-- A simple JSP to direct user to some RESTful queries
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Booking RESTful Web Service</title>
   </head>
   <body>
      <h1>Booking RESTful Web Service</h1>
      Demonstration of the Booking Web Service using JAX-RS
      <p/>
      <a href="<%= response.encodeURL(request.getContextPath())%>/web_service/bookings">Get all bookings</a>
      <p/>
      <a href="<%= response.encodeURL(request.getContextPath())%>/web_service/bookings/Trisha">Get bookings of Trisha</a>
      <p/>
      <a href="<%= response.encodeURL(request.getContextPath())%>/web_service/students">Get all students</a>
      <p/>
      <a href="<%= response.encodeURL(request.getContextPath())%>/web_service/students/Jeremiah">Get bookings of Jeremiah</a>
      <p/>
      <form action="<%= response.encodeURL(request.getContextPath())%>/web_service/students" method="POST">
         <p>
            Student Name:
            <input type="text" name="student"/>
         </p>
         <p>
            Booking Name:
            <input type="text" name="booking"/>
         </p>
         <p>
            Room Name:
            <input type="text" name="room"/>
         </p>
         <p>
            Booking date:
            <input type="text" name="date"/>
         </p>
        <p>
            Booking time:
            <input type="text" name="time"/>
         </p>
         <input type="submit" value="Add Booking"/>
      </form>
      <p/>
      <a href="/PetRestfulService_2/test-resbean.html">
         Use the NetBeans RESTful web service tester
      </a>
   </body>
</html>
