package lab3.repository;

import junit.framework.TestCase;
import lab3.controller.StudentController;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.view.StudentView;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryTest extends TestCase {

    List<Course> emptyCourses1 = new ArrayList<>();
    List<Course> emptyCourses2 = new ArrayList<>();

    List<Student> emptyStudent1 = new ArrayList<>();
    List<Student> emptyStudent2 = new ArrayList<>();

    List<Course> emptyEnrolled1 = new ArrayList<>();
    List<Course> emptyEnrolled2 = new ArrayList<>();

    Teacher modoi = new Teacher("Modoi", "Gabriel", emptyCourses1,1);
    Teacher brigitte = new Teacher("Lisei", "Brigitte", emptyCourses2,2);

    Course algebra = new Course((long) 1,"Algebra", modoi, 120,emptyStudent1 , 5);
    Course analysis = new Course((long) 2, "Brigitte", brigitte, 100, emptyStudent2, 6);

    Student naomi = new Student("Toth", "Naomi", 1,0,emptyEnrolled1);
    Student cristina = new Student ("Toth", "Cristina", 2, 0, emptyEnrolled2);

    List<Student> listStud = new ArrayList<>();
    StudentRepository studRepo = new StudentRepository(listStud);

    public void testFindOne() {
        studRepo.save(naomi);
        studRepo.save(cristina);
        assertEquals(studRepo.findOne((long) 1),naomi);
        assertNull(studRepo.findOne((long) 3));
    }

    public void testFindAll() {
        List<Student> all = (List<Student>) studRepo.findAll();
        assertEquals(all.size(),0);

        studRepo.save(naomi);
        studRepo.save(cristina);
        assertEquals(((List<Student>) studRepo.findAll()).size(),2);
    }

    public void testSave() {
        studRepo.save(naomi);
        assertEquals(studRepo.findOne((long) 1),naomi);
        assertEquals(studRepo.save(naomi), naomi);
    }

    public void testDelete() {
        assertNull(studRepo.delete((long) 1));
        studRepo.save(naomi);
        studRepo.save(cristina);
        studRepo.delete((long) 1);
        assertNull(studRepo.findOne((long) 1));
    }

    public void testUpdate() {
        studRepo.save(naomi);
        studRepo.save(cristina);
        cristina.addToCourse(algebra);
        cristina.addToCourse(analysis);
        cristina.addCredits(20);
        studRepo.update(cristina);
        assertEquals(studRepo.findOne((long) 2).getEnrolledCourses().size(),2);
        assertEquals(studRepo.findOne((long) 2).getTotalCredits(),20);
    }
}