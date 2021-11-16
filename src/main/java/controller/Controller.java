package controller;

import exceptions.ObjectNotFoundException;
import model.Course;
import model.Student;
import model.Teacher;
import repository.CourseRepo;
import repository.StudentRepo;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Classname Controller
 * Date: 25.10.2021
 */
public class Controller {
    private final StudentRepo srepo;
    private final CourseRepo crepo;

    /**
     * Class constructor.
     *
     * @param srepo is a students repository
     * @param crepo is a courses repository
     */
    public Controller(StudentRepo srepo, CourseRepo crepo) {
        this.srepo = srepo;
        this.crepo = crepo;
    }

    public boolean addCourse (String name, String firstName, String lastName , int maxEnrollment, int credits) {
        Teacher t = new Teacher(firstName, lastName, new ArrayList<>());
        Course c = new Course(name, t, maxEnrollment, new ArrayList<>(), credits);
        this.crepo.save(c);
        t.addCourse(c);
        this.crepo.storeToFile();
        return true;
    }

    public boolean addStudent (String firstName, String lastName, long id){
        Student s = new Student(firstName, lastName, id, new ArrayList<>());
        this.srepo.save(s);
        this.srepo.storeToFile();
        return true;
    }

    /**
     * @param id is the id of a student
     * @param name is the name of a course
     * @return true if the student s was enrolled to the course s
     */
    public boolean register(long id, String name) throws ObjectNotFoundException {
        if(this.srepo.findByID(id)==null || this.crepo.findByName(name)==null)
            throw new ObjectNotFoundException("Object was not found in repository");
        Student s = this.srepo.findByID(id);
        Course c = this.crepo.findByName(name);
        if (c.getStudentsEnrolled().size() == c.getMaxEnrollment()) {
            System.out.println("Maximum number of students reached");
            return false;
        } else if (s.getTotalCredits() + c.getCredit() > 30) {
            System.out.println("Maximum number of credits reached");
            return false;
        } else {
            ArrayList<Course> newcourselist = (ArrayList<Course>) s.getEnrolledCourses();
            newcourselist.add(c);
            s.setEnrolledCourses(newcourselist);
            s.setTotalCredits(s.getTotalCredits() + c.getCredit());
            ArrayList<Student> newstudlist = (ArrayList<Student>) c.getStudentsEnrolled();
            newstudlist.add(s);
            c.setStudentsEnrolled(newstudlist);
            this.crepo.storeToFile();
            this.srepo.storeToFile();
            return true;
        }
    }

    /**
     * @return the list of courses with available places and display the courses and number of places
     */
    public ArrayList<Course> retrieveCourses() {
        ArrayList<Course> newlist = new ArrayList<>();
        for (Course c : crepo.findAll()) {
            if (c.getStudentsEnrolled().size() < c.getMaxEnrollment()) {
                newlist.add(c);
                System.out.println(c + " " + (c.getMaxEnrollment() - c.getStudentsEnrolled().size()));
            }
        }
        return newlist;
    }

    /**
     * @param name is the name of a course
     * @return the list of students enrolled for course c
     */
    public ArrayList<Student> retrieveStudents(String name) throws ObjectNotFoundException {
        if(this.crepo.findByName(name)==null)
            throw new ObjectNotFoundException("Object was not found in repository");
        Course c = this.crepo.findByName(name);
        return (ArrayList<Student>) crepo.findOne(c).getStudentsEnrolled();
    }

    /**
     * @return all courses
     */
    public ArrayList<Course> getAllCourses() {
        return (ArrayList<Course>) crepo.findAll();
    }

    /**
     * Function updateCredits modifies the no of credits of a course and it also updates the totalCredits of
     * the students that are enrolled
     *
     * @param name is a course
     * @param newcredits is the new number of credits
     */
    public void updateCredits(String name, int newcredits) throws ObjectNotFoundException {
        if(this.crepo.findByName(name)==null)
            throw new ObjectNotFoundException("Object was not found in repository");
        Course c = this.crepo.findByName(name);
        int diff = c.getCredit() - newcredits;
        crepo.findOne(c).setCredit(newcredits);
        for (Student s : c.getStudentsEnrolled()) {
            s.setTotalCredits(s.getTotalCredits() - diff);
        }
        this.crepo.storeToFile();
        this.srepo.storeToFile();
    }

    /**
     * Function deleteTeacher sets the teacher for course c to null and removes the course from all enrolled students
     *
     * @param name is the name of the course
     */
    public void deleteTeacher(String name) throws ObjectNotFoundException {
        if(this.crepo.findByName(name)==null)
            throw new ObjectNotFoundException("Object was not found in repository");
        Course c = this.crepo.findByName(name);
        c.getTeacher().getCourses().remove(c);
        c.setTeacher(null);
        for (Student s : c.getStudentsEnrolled()) {
            ArrayList<Course> newcourselist = (ArrayList<Course>) s.getEnrolledCourses();
            newcourselist.remove(c);
            s.setEnrolledCourses(newcourselist);
        }
        this.crepo.storeToFile();
    }

    /**
     * @return list of students sorted by last name
     */
    public ArrayList<Student> sortStudentsByName()
    {
        ArrayList<Student> newlist = (ArrayList<Student>) srepo.findAll();
        newlist.sort(new Student.NameSorter());
        return newlist;
    }

    /**
     * @return list of courses sorted by name
     */
    public ArrayList<Course> sortCoursesByName()
    {
        ArrayList<Course> newlist = (ArrayList<Course>) crepo.findAll();
        newlist.sort(new Course.NameSorter());
        return newlist;
    }

    /**
     * Function filterStudentsByCredits filters the list of students using the byCredits predicate
     * @return a list of students who have less than 15 credits
     */
    public ArrayList<Student> filterStudentsByCredits() {
        Predicate<Student> byCredits = student -> student.getTotalCredits() < 15;
        ArrayList<Student> newlist = (ArrayList<Student>) srepo.findAll();
        return (ArrayList<Student>) newlist.stream().filter(byCredits).collect(Collectors.toList());
    }

    /**
     * Function filterCoursesByCredit filters the list of courses using the byCredits predicate
     * @return a list of courses with less than x credits
     */
    public ArrayList<Course> filterCoursesByCredit(int credits) {
        Predicate<Course> byCredits = course -> course.getCredit() < credits;
        ArrayList<Course> newlist = (ArrayList<Course>) crepo.findAll();
        return (ArrayList<Course>) newlist.stream().filter(byCredits).collect(Collectors.toList());
    }

}

