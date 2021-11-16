package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Date: 21.10.2021
 */
@JsonIgnoreProperties({"studentsEnrolled"})
public class Course {
    private String name;
    private Teacher teacher;
    private int maxEnrollment;
    private List<Student> studentsEnrolled;
    private int credit;

    /**
     * Class constructor.
     * @param name is the name of the course
     * @param teacher is the name of the teacher assigned to the course
     * @param maxEnrollment is the no of maximum students
     * @param studentsEnrolled is the list of enrolled students
     * @param credit is the no of credits for this course
     */
    public Course(String name, Teacher teacher, int maxEnrollment, List<Student> studentsEnrolled, int credit) {
        this.name = name;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = studentsEnrolled;
        this.credit = credit;
        ArrayList<Course> newlist = (ArrayList<Course>) teacher.getCourses();
        newlist.add(this);
        teacher.setCourses(newlist);
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Teacher getTeacher() {return teacher;}
    public void setTeacher(Teacher teacher) {this.teacher = teacher;}

    public int getMaxEnrollment() {return maxEnrollment;}
    public void setMaxEnrollment(int maxEnrollment) {this.maxEnrollment = maxEnrollment;}

    public List<Student> getStudentsEnrolled() {return studentsEnrolled;}
    public void setStudentsEnrolled(List<Student> studentsEnrolled) {this.studentsEnrolled = studentsEnrolled;}

    public int getCredit() {return credit;}
    public void setCredit(int credit) {this.credit = credit;}

    /**
     *
     * @param o object to be compared
     * @return boolean value resulted by comparing the names of two courses
     * (c1=c2 if names are the same)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * @return a string containing the name of the course and maximum no of students
     */
    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", maxEnrollment=" + maxEnrollment +
                '}';
    }

    /**
     * Class NameSorter implements Comparator interface and defines the comparison method
     * for Course (lexicographically by Name)
     */
    public static class NameSorter implements Comparator<Course> {
        @Override
        public int compare(Course c1, Course c2){
            return c1.getName().compareToIgnoreCase(c2.getName());
        }
    }

}

