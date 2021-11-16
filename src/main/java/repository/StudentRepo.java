package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Student;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StudentRepo extends InMemoryRepo<Student> implements IFileRepo<Student>{
    public StudentRepo(){
        super();
    }
    private String filename;
    /**
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity - (e.g id does not exist).
     */
    @Override
    public Student update(Student entity){
        for(Student s: repo_list) {
            if (s.equals(entity)){
                s.setStudentID(entity.getStudentID());
                s.setEnrolledCourses(entity.getEnrolledCourses());
                s.setTotalCredits(entity.getTotalCredits());
                return null;
            }
        }
        return entity;
    }

    /**
     *
     * @param id is the id of a student
     * @return the student with that specific id
     */
    public Student findByID(long id){
        for(Student t:repo_list)
        {
            if(t.getStudentID() == id)
                return t;
        }
        return null;
    }

    /**
     * stores to students.json the entities from the repository list
     */
    @Override
    public void storeToFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filename), repo_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads the students from the students.json file
     * @param c the list of students from the file
     */
    @Override
    public void loadFromFile(Class<Student[]> c) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Student> aux= Arrays.asList(objectMapper.readValue(new File(filename),c));
            for(Student elem:aux)
                repo_list.add(elem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param _filename is the name of the file where we save the students
     */
    @Override
    public void setFile(String _filename) {
        filename=_filename;
    }
}
