/*
 * JavaBean class that represents a Student
 */

package web_service;

public class Student
{
    private int s_id;
    private String password;
    private String fName;
    private String lName;

    public Student()
    {
    }
    
    public Student(int s_id, String password, String fName, String lName)
    {
        setStuID(s_id);
        setPassword(password);
        setFname(fName);
        setLname(lName);
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
    
    public String getXMLString()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<student>");
        buffer.append("<s_id>").append(getStuID()).append("</s_id>");
        buffer.append("<password>").append(getPassword()).append("</password>");
        buffer.append("<fName>").append(getFname()).append("</fName>");
        buffer.append("<lName>").append(getLname()).append("</lName>");
        buffer.append("</student>");
        return buffer.toString();
    }
}