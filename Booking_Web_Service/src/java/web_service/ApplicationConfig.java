/*
 * JAX-RS class overridden to specifify the
 *  application path for RESTful services
 */
package web_service;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("web_service")
public class ApplicationConfig extends Application
{

   @Override
   public Set<Class<?>> getClasses()
   {
      Set<Class<?>> resources = new java.util.HashSet<>();
      addRestResourceClasses(resources);
      return resources;
   }

   /**
    * Do not modify addRestResourceClasses() method.
    * It is automatically populated with
    * all resources defined in the project.
    * If required, comment out calling this method in getClasses().
    */
   private void addRestResourceClasses(Set<Class<?>> resources)
   {
      resources.add(web_service.BookingsResource.class);
      resources.add(web_service.StudentsResource.class);
   } 
}
