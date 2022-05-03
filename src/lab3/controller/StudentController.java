package lab3.controller;

import lab3.model.Course;
import lab3.model.Student;
import lab3.repository.StudentRepository;
import lab3.view.StudentView;

import java.util.List;

public class StudentController {
    StudentRepository studRepo;
    StudentView studView;

    public StudentController(StudentRepository studRepo, StudentView studView) {
        this.studRepo = studRepo;
        this.studView = studView;
    }

    public StudentRepository getStudRepo() {
        return studRepo;
    }

    public void addStudent(Student s) {
        studRepo.save(s);
    }

    public void deleteStudent(long id) {
        studRepo.delete(id);
    }

    /**
     *
     * @param c the course
     * @param id the id of the student, who would like to be enrolled to a course
     */
    public void addCourseToGivenStudent(Course c, long id){
        Student stud = studRepo.findOne(id);
        if (stud==null) {
            return; // there is no such student
        }

        // STREAM
        ((List<Student>)this.studRepo.findAll()).stream()
                .filter(s -> s.getStudentId()==id && !s.getEnrolledCourses().contains(c))
                .forEach(s -> {
                    s.addToCourse(c);
                    s.addCredits(c.getCredits());
                } );

    }

    /**
     * if the teacher drops a course, the students list with all the courses should be updated (the deleted course will be deleted from the students list aswell)
     * @param c the given course
     */
    public void dropCourse(Course c) {
        // STREAM
        ((List<Student>)this.studRepo.findAll()).stream()
                .filter(s -> s.getEnrolledCourses().contains(c))
                .forEach(s -> {
                    List<Course> newList = s.getEnrolledCourses();
                    newList.remove(c);
                    s.setEnrolledCourses(newList);
                    s.setTotalCredits(s.getTotalCredits()-c.getCredits());
                });


    }


    public long returnId(String firstName,String lastName)
    {

        long id=-1;
        for(Student t:this.studRepo.findAll())
        {
            if(t.getFirstName().equals(firstName) && t.getLastName().equals(lastName))
            {
                id=t.getStudentId();
            }
        }
        System.out.println(id);
        return id;


    }

}