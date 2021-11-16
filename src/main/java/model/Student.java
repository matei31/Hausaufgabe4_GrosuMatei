package model;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Class Student is a subclass of Person;
 * Aside from first and last name, a student has an ID and a list of courses
 * Date: 25.10.2021
 */
public class Student extends Person {
    private long studentID;
    private int totalCredits;
    private List<Course> enrolledCourses;

    /**
     * Class constructor
     * @param firstName is the first name
     * @param lastName is the last name
     * @param studentID is the ID
     * @param enrolledCourses is a list of courses
     */
    public Student(String firstName, String lastName, long studentID, List<Course> enrolledCourses){
        super(firstName, lastName);
        this.studentID = studentID;
        this.enrolledCourses=enrolledCourses;
        for (Course c: this.enrolledCourses)
            totalCredits += c.getCredit();
    }

    public long getStudentID() {return studentID;}
    public void setStudentID(long studentID) {this.studentID = studentID;}

    public int getTotalCredits() {return totalCredits;}
    public void setTotalCredits(int totalCredits) {this.totalCredits = totalCredits;}

    public List<Course> getEnrolledCourses() {return enrolledCourses;}
    public void setEnrolledCourses(List<Course> enrolledCourses) {this.enrolledCourses = enrolledCourses;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return studentID == student.studentID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentID);
    }

    /**
     * Class NameSorter implements Comparator interface and defines the comparison method
     * for Student (lexicographically by lastName)
     */
    public static class NameSorter implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2){
            return s1.getlastName().compareToIgnoreCase(s2.getlastName());
        }
    }

}
