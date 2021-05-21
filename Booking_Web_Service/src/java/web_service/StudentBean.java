/*
 * Singleton EJB that holds the list of Students
 */

package web_service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class StudentBean {
    
    private List<Student> students;

    @PostConstruct
    public void initialiseStudents() {
      
        students = new ArrayList<>();
        addStudent("dsf4787", "Trisha Tan");
        addStudent("dgn1394", "Jeremiah Martinez");
        
    }

    public void addStudent(String student_ID, String student_Name) {
      students.add(new Student(student_ID, student_Name)); 
    }

    public Student getTheStudent(String student_Name) {
        
        Student theStudent = new Student(); 
        for(Student stdnt : students)
        {
            if(stdnt.getStudentName().equals(theStudent))
            {
                theStudent = stdnt; 
            }
        }
        return theStudent; 
    }

    public boolean removeStudent(String student_ID, String student_Name) {
        
        for(Student stdnt : students)
        {
            if(stdnt.getStudentID().equals(student_ID) 
                && stdnt.getStudentName().equals(student_Name))
            {
                students.remove(stdnt);
                return true;
            }
        }
        return false;
    }
}
