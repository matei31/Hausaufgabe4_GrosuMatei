package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Date: 21.10.2021
 */
@JsonIgnoreProperties({"courses"})
public class Teacher extends Person {
    private List<Course> courses;

    /**
     * Class constructor.
     * @param firstName is the name of the teacher
     * @param lastName is the last name of the teacher
     */
    public Teacher(String firstName, String lastName, List<Course> courses) {
        super(firstName, lastName); this.courses = courses;
    }

    public List<Course> getCourses() {return courses;}
    public void setCourses(List<Course> courses) {this.courses = courses;}

    public void addCourse(Course c){
        List<Course> newlist = this.getCourses();
        newlist.add(c);
        this.setCourses(newlist);
    }
}

