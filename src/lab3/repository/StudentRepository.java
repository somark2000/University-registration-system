package lab3.repository;
import lab3.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository implements ICrudRepository<Student>{
    List<Student> students;

    public StudentRepository(List<Student> students) {
        this.students = students;
    }

    /**
     *
     * @param id -the id of the entity to be returned id must not be null
     * @return the student with the given id if it exists, 0 otherwise
     */
    @Override
    public Student findOne(Long id) {
        for (Student s:students) {
            if (s.getStudentId()==id)
                return s;
        }
        return null;
    }

    /**
     *
     * @return all the entities
     */
    @Override
    public Iterable<Student> findAll() {
        return students;
    }

    /**
     *
     * @param entity entity must be not null
     * adds the given student to the list of students in this repo
     * @return null- if the given entity is saved otherwise returns the entity (id already exists)
     */
    @Override
    public Student save(Student entity) {
        if (this.findOne(entity.getStudentId())!=null) {
            // there is already an entity with this id in the list
            return entity;
        }
        students.add(entity);
        return null;
    }

    /**
     *
     * @param id id must be not null
     * deletes the student with the given id
     * @return the deleted student if it exists, otherwise return 0
     */
    @Override
    public Student delete(Long id) {
        this.students = students.stream()
                .filter(s->s.getStudentId()!=id)
                .collect(Collectors.toList());
        return this.findOne(id);
    }

    /**
     *
     * @param entity entity must not be null
     * @return 0 if the given entity was updated, otherwise return the entity
     * first of all we delete the entity with the given id, than add the new entity to our repository (list with all the students)
     */
    @Override
    public Student update(Student entity) {
        if (this.findOne(entity.getStudentId())!=null)
        {
            this.delete(entity.getStudentId());
            this.save(entity);
            return null;
        }
        return entity;
    }
}