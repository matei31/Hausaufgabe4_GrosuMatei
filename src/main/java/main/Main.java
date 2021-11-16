package main;

import controller.Controller;
import exceptions.ObjectNotFoundException;
import model.Course;
import model.Student;
import model.Teacher;
import repository.CourseRepo;
import repository.StudentRepo;
import view.ConsoleUI;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws ObjectNotFoundException {
        Student s1 = new Student("Ion", "Ionescu", 1234, new ArrayList<>());
        Teacher t1 = new Teacher("Maria", "Pop", new ArrayList<>());
        Course c1 = new Course("BD", t1, 6, new ArrayList<>(), 12);
        Course c2 = new Course("LP", t1, 1, new ArrayList<>(), 20);
        StudentRepo srepo = new StudentRepo();
        srepo.setFile("students.JSON");
        srepo.save(s1);
        srepo.storeToFile();
        CourseRepo crepo = new CourseRepo();
        crepo.setFile("courses.JSON");
        crepo.save(c1);
        crepo.save(c2);
        crepo.storeToFile();
        Controller control = new Controller(srepo, crepo);
        ConsoleUI ui = new ConsoleUI(control);
        ui.display();
    }
}

