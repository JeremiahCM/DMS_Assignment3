/*
 * JAX-RS class overridden to specifify the application path for
 * RESTful services
 */
package web_service;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("studyroombooking")
public class ApplicationConfig extends Application {
    
    @Override
   public Set<Class<?>> getClasses()
   {
      Set<Class<?>> resources = new java.util.HashSet<>();
      addRestResourceClasses(resources);
      return resources;
   }

   private void addRestResourceClasses(Set<Class<?>> resources)
   {
      resources.add(web_service.StudentResource.class);
      resources.add(web_service.RoomResource.class);
   } 
    
}
