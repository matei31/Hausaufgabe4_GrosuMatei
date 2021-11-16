package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Course;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CourseRepo extends InMemoryRepo<Course> implements IFileRepo<Course>{
    public CourseRepo(){
        super();
    }
    protected String filename;
    /**
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity - (e.g id does not exist).
     */
    @Override
    public Course update(Course entity) {
        for(Course c:repo_list) {
            if (c.equals(entity)){
                c.setCredit(entity.getCredit());
                c.setMaxEnrollment(entity.getMaxEnrollment());
                c.setTeacher(entity.getTeacher());
                c.setStudentsEnrolled(entity.getStudentsEnrolled());
                return null;
            }
        }
        return entity;
    }

    /**
     *
     * @param name is the name of the course we are searching for
     * @return the course with that name
     */
    public Course findByName(String name){
        for(Course t:repo_list)
        {
            if(Objects.equals(t.getName(), name))
                return t;
        }
        return null;
    }

    /**
     * stores to courses.json the entities from the repository list
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
     * loads the course from the courses.json file
     * @param c the list of courses from the file
     */
    @Override
    public void loadFromFile(Class<Course[]> c){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Course> aux= Arrays.asList(objectMapper.readValue(new File(filename),c));
            repo_list.addAll(aux);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param _filename is the name of the file where we save the courses
     */
    @Override
    public void setFile(String _filename) {
        filename=_filename;
    }

}


