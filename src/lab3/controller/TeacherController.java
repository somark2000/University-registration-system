package lab3.controller;

import lab3.model.Course;
import lab3.model.Teacher;
import lab3.repository.TeacherRepo;


public class TeacherController {
    TeacherRepo teacherRepo;

    public TeacherController(TeacherRepo teachers) {
        this.teacherRepo = teachers;
    }

    public TeacherRepo getTeachers() {
        return teacherRepo;
    }

    /**
     * adds a new course to the given teacher
     * @param c is an object of type Course
     * @param id is a teachers id
     */
    public void addCourse(Course c,long id){
        // STREAM
        teacherRepo.getTeachers().stream()
                .filter(t -> t.getTeacherID()==id)
                .forEach(t -> t.addCourse(c));


    }


    public long returnId(String firstName,String lastName)
    {
        long id=-1;
        for(Teacher t:this.teacherRepo.findAll())
        {
            if(t.getFirstName().equals(firstName) && t.getLastName().equals(lastName))
            {
                id=t.getTeacherID();
            }
        }
        System.out.println(id);
        return id;
    }
}
