/*
 * Singleton EJB that holds the list of current students
 */
package web_service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class StudentsBean
{
    private List<Student> students; // list of all students

    @PostConstruct
    public void initialiseStudentCollection()
    {
        // convenience list to avoid using any database
        students = new ArrayList<>();
        addStudent(1, "fpn871", "Jeremiah", "Martinez");
        addStudent(2, "fpn871", "Trisha", "Tan");
        addStudent(3, "fpn871", "Some", "Person");
        addStudent(4, "fpn871", "Another", "One");
        addStudent(5, "fpn871", "More", "People");
    }

    public void addStudent(int s_id, String password, String fName, String lName)
    {
        students.add(new Student(s_id, password, fName, lName));
    }

    public boolean removeStudent(String fName, String lName)
    {
        for (Student student : students)
        {
            if (student.getFname().equals(fName)
                && student.getLname().equals(lName))
            {
                students.remove(student);
                return true;
            }
        }
        return false; // student not found in collection
    }

    public Collection<Student> getStudents()
    {
        return students;
    }
}