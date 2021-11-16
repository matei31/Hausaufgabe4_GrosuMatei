package controller;

import exceptions.ObjectNotFoundException;
import model.Course;
import model.Student;
import model.Teacher;
import repository.CourseRepo;
import repository.StudentRepo;


import java.util.ArrayList;

//import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

   /* @Test
    void register() throws ObjectNotFoundException {
        Student s1 = new Student("Ion", "Ionescu", 1234, new ArrayList<>());
        Teacher t1 = new Teacher("Maria", "Pop", new ArrayList<>());
        Course c1 = new Course("BD", t1, 6, new ArrayList<>(), 12);
        Course c2 = new Course("LP", t1, 1, new ArrayList<>(), 20);
        StudentRepo srepo = new StudentRepo();
        CourseRepo crepo = new CourseRepo();
        Controller control = new Controller(srepo, crepo);
        srepo.save(s1);
        crepo.save(c1);
        crepo.save(c2);
        assertTrue(control.register(s1.getStudentID(), c1.getName()));
        assertFalse(control.register(s1.getStudentID(), c2.getName()));
    }

    @Test
    void retrieveCourses() {
        Student s1 = new Student("Ion", "Ionescu", 1234, new ArrayList<>());
        Teacher t1 = new Teacher("Maria", "Pop", new ArrayList<>());
        Course c1 = new Course("BD", t1, 6, new ArrayList<>(), 12);
        Course c2 = new Course("LP", t1, 1, new ArrayList<>(), 20);
        StudentRepo srepo = new StudentRepo();
        CourseRepo crepo = new CourseRepo();
        Controller control = new Controller(srepo, crepo);
        srepo.save(s1);
        crepo.save(c1);
        crepo.save(c2);
        assertEquals(control.retrieveCourses().get(0).getName(), "BD");
        assertEquals(control.retrieveCourses().get(1).getCredit(), 20);
    }

    @Test
    void retrieveStudents() throws ObjectNotFoundException {
        Student s1 = new Student("Ion", "Ionescu", 1234, new ArrayList<>());
        Student s2 = new Student("Mihai", "Popescu", 5678, new ArrayList<>());
        Teacher t1 = new Teacher("Maria", "Pop", new ArrayList<>());
        Course c1 = new Course("BD", t1, 6, new ArrayList<>(), 12);
        Course c2 = new Course("WS", t1, 1, new ArrayList<>(), 6);
        StudentRepo srepo = new StudentRepo();
        CourseRepo crepo = new CourseRepo();
        srepo.save(s1);
        srepo.save(s2);
        crepo.save(c1);
        crepo.save(c2);
        Controller control = new Controller(srepo, crepo);
        control.register(s1.getStudentID(), c1.getName());
        control.register(s2.getStudentID(), c1.getName());
        control.register(s2.getStudentID(), c2.getName());
        assertEquals(control.retrieveStudents(c1.getName()).size(), 2);
        assertEquals(control.retrieveStudents(c2.getName()).size(), 1);

    }

    @Test
    void getAllCourses() {
        Student s1 = new Student("Ion", "Ionescu", 1234, new ArrayList<>());
        Teacher t1 = new Teacher("Maria", "Pop", new ArrayList<>());
        Course c1 = new Course("BD", t1, 6, new ArrayList<>(), 12);
        Course c2 = new Course("WS", t1, 1, new ArrayList<>(), 6);
        Course c3 = new Course("LP", t1, 1, new ArrayList<>(), 20);
        StudentRepo srepo = new StudentRepo();
        CourseRepo crepo = new CourseRepo();
        srepo.save(s1);
        crepo.save(c1);
        crepo.save(c2);
        crepo.save(c3);
        Controller control = new Controller(srepo, crepo);
        assertEquals(control.getAllCourses().size(), 3);
        assertEquals(control.getAllCourses().get(1).getName(), "WS");
    }

    @Test
    void updateCredits() throws ObjectNotFoundException{
        Student s1 = new Student("Ion", "Ionescu", 1234, new ArrayList<>());
        Teacher t1 = new Teacher("Maria", "Pop", new ArrayList<>());
        Course c1 = new Course("BD", t1, 6, new ArrayList<>(), 12);
        StudentRepo srepo = new StudentRepo();
        CourseRepo crepo = new CourseRepo();
        srepo.save(s1);
        crepo.save(c1);
        Controller control = new Controller(srepo, crepo);
        control.register(s1.getStudentID(),c1.getName());
        control.updateCredits(c1.getName(), 6);
        assertEquals(s1.getTotalCredits(), 6);
    }

    @Test
    void deleteTeacher() throws ObjectNotFoundException {
        Student s1 = new Student("Ion", "Ionescu", 1234, new ArrayList<>());
        Teacher t1 = new Teacher("Maria", "Pop", new ArrayList<>());
        Course c1 = new Course("BD", t1, 6, new ArrayList<>(), 12);
        StudentRepo srepo = new StudentRepo();
        CourseRepo crepo = new CourseRepo();
        srepo.save(s1);
        crepo.save(c1);
        Controller control = new Controller(srepo, crepo);
        control.deleteTeacher(c1.getName());
        assertEquals(s1.getEnrolledCourses().size(), 0);
    }*/
}