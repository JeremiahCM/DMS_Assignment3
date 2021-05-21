/*
 * JavaBean class that represents a Student
 */

package web_service;

public class Student {
    private String s_ID;
    private String s_Name; 
    
    public Student()
    {
    }
    
    public Student(String s_ID, String s_Name)
    {
        setStudentID(s_ID);
        setStudentName(s_Name);
    }
    
    public String getStudentID()
   {
      return s_ID;
   }

   public void setStudentID(String s_ID)
   {
      this.s_ID = s_ID;
   }

   public String getStudentName()
   {
      return s_Name;
   }

   public void setStudentName(String s_Name)
   {
      this.s_Name = s_Name;
   }
   
   public String getXMLString()
   {
      StringBuilder buffer = new StringBuilder();
      buffer.append("<student>");
      buffer.append("<s_ID>").append(getStudentID()).append("</s_ID>");
      buffer.append("<s_Name>").append(getStudentName()).append("</s_Name>");
      buffer.append("</student>");
      return buffer.toString();
   }
}