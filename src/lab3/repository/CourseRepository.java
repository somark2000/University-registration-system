package lab3.repository;
import lab3.model.Course;

import java.util.List;

public class CourseRepository implements ICrudRepository<Course>{
    List<Course> courses;

    public CourseRepository(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    /**
     * @param id -the id of the entity to be returned id must not be null
     * @return the entity with the specified id or null - if there is no entity with the given id
     */
    @Override
    public Course findOne(Long id) {
        for (Course c:courses) {
            if (c.getId()==id)
                return c;
        }
        return null;
    }

    /**
     * @return all entities
     */
    @Override
    public Iterable<Course> findAll() {
        return courses;
    }

    /**
     * @param entity entity must be not null
     * @return null- if the given entity is saved otherwise returns the entity (id already exists)
     */
    @Override
    public Course save(Course entity) {
        if (this.findOne(entity.getId())!=null) {
            return entity;
        }
        else
        {
            courses.add(entity);
            return null;
        }
    }

    /**
     * removes the entity with the specified id
     * @param id id must be not null
     * @return the removed entity or null if there is no entity with the given id
     */
    @Override
    public Course delete(Long id) {
        Course course = this.findOne(id);
        if (course==null)
            return null;
        else
        {
            courses.remove(course);
            return course;
        }
    }

    /**
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity - (e.g id does not exist).
     */
    @Override
    public Course update(Course entity) {
        Course course = this.findOne(entity.getId());
        if (course==null) {
            return entity;
        }
        else
        {
            this.delete(entity.getId());
            this.save(entity);
            return null;
        }
    }

}
