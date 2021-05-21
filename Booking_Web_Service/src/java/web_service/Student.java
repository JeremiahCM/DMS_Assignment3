/*
 * JavaBean class that represents a Student
 */

package web_service;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Define class Entity to be used for dgn1399_students table in database
@Entity
@Table(name = "dgn1399_students")
public class Student implements Serializable
{
    /*Entity will have an automatically generated ID, incremented one above the
    previous student ID generated*/
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int s_id;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String fName;
    @Column(name = "last_name")
    private String lName;

    public Student()
    {
    }

    //Setter for first name
    public void setFname(String fName)
    {
        this.fName = fName;
    }

    //Getter for first name
    public String getFname()
    {
        return fName;
    }
    
    //Setter for password
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    //Getter for password
    public String getPassword()
    {
        return password;
    }

    //Setter for last name
    public void setLname(String lName)
    {
        this.lName = lName;
    }

    //Getter for last name
    public String getLname()
    {
        return lName;
    }

    //Setter for student ID
    public void setStuID(int e_id)
    {
        this.s_id = e_id;
    }

    //Getter for student ID
    public int getStuID()
    {
        return s_id;
    }
}