/*
 * JAX_RS RESTful resource class for various requests on students
 * (Specify more later)
 */
package web_service;

import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Named
@Path(value = "/students")
public class StudentResource {
    
    @EJB
    private StudentBean studentsBean;
    @Context
    private UriInfo context;
    private static final char QUOTE = '"';

    public StudentResource() 
    {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{student}")
    public String getTheStudent(@PathParam("student") String aStudent) 
    {
      StringBuilder buffer = new StringBuilder();
      buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      buffer.append("<students uri=").append(QUOTE).append(
         context.getAbsolutePath()).append(QUOTE).append(">");
      Student stdnt = studentsBean.getTheStudent(aStudent);
      buffer.append("</students>");
      return buffer.toString();
    }
    
    //Method for logging out 
}
