package lab3.repository;

import lab3.model.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherRepo implements ICrudRepository<Teacher>{
    List<Teacher> teachers;

    public TeacherRepo(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * @param id -the id of the entity to be returned id must not be null
     * @return the entity with the specified id or null - if there is no entity with the given id
     */
    @Override
    public Teacher findOne(Long id) {
        for (Teacher t:teachers) {
            if(t.getTeacherID()==id)
                return t;
        }
        return null;
    }

    /**
     * @return all entities
     */
    @Override
    public Iterable<Teacher> findAll() {
        return teachers;
    }

    /**
     * @param entity entity must be not null
     * adds the teacher to the repo
     * @return null- if the given entity is saved otherwise returns the entity (id already exists)
     */
    @Override
    public Teacher save(Teacher entity) {
        if(this.findOne(entity.getTeacherID())!=null)
            return entity;
        teachers.add(entity);
        return null;
    }

    /**
     * removes the entity with the specified id
     * @param id id must be not null
     * @return the removed entity or null if there is no entity with the given id
     */
    @Override
    public Teacher delete(Long id) {
        this.teachers = teachers.stream()
                .filter(t -> t.getTeacherID()!=id)
                .collect(Collectors.toList());

        return this.findOne(id);
    }

    /**
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity - (e.g id does not exist).
     */
    @Override
    public Teacher update(Teacher entity) {
        if(this.findOne(entity.getTeacherID())!=null)
        {
            this.delete(entity.getTeacherID());
            this.save(entity);
            return null;
        }
        return entity;
    }
}
