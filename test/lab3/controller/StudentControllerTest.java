package lab3.controller;

import junit.framework.TestCase;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.StudentRepository;
import lab3.view.StudentView;

import java.util.ArrayList;
import java.util.List;

public class StudentControllerTest extends TestCase {

    List<Course> emptyCourses1 = new ArrayList<>();
    List<Course> emptyCourses2 = new ArrayList<>();

    List<Student> emptyStudent1 = new ArrayList<>();
    List<Student> emptyStudent2 = new ArrayList<>();

    List<Course> emptyEnrolled1 = new ArrayList<>();
    List<Course> emptyEnrolled2 = new ArrayList<>();

    Teacher modoi = new Teacher("Modoi", "Gabriel", emptyCourses1,2);
    Teacher brigitte = new Teacher("Lisei", "Brigitte", emptyCourses2,1);

    Course algebra = new Course((long) 1,"Algebra", modoi, 120,emptyStudent1 , 5);
    Course analysis = new Course((long) 2, "Brigitte", brigitte, 100, emptyStudent2, 6);

    Student naomi = new Student("Toth", "Naomi", 1,0,emptyEnrolled1);
    Student cristina = new Student ("Toth", "Cristina", 2, 0, emptyEnrolled2);

    StudentView studView = new StudentView();
    List<Student> listStud = new ArrayList<>();
    StudentRepository studRepo = new StudentRepository(listStud);

    StudentController studCont = new StudentController(studRepo, studView);

    public void testAddStudent() {
        studCont.addStudent(naomi);
        studCont.addStudent(cristina);
        assertEquals(2, ((List<Student>) studCont.getStudRepo().findAll()).size());
    }

    public void testDeleteStudent() {
        studCont.addStudent(naomi);
        studCont.addStudent(cristina);
        studCont.deleteStudent(1);
        assertEquals(1, ((List<Student>) studCont.getStudRepo().findAll()).size());
    }

    public void testAddCourseToGivenStudent() {
        studCont.addStudent(naomi);
        studCont.addStudent(cristina);

        studCont.addCourseToGivenStudent(analysis,1); //Naomi analysis
        studCont.addCourseToGivenStudent(algebra,2); //Cristina analysis

        assertTrue(studCont.getStudRepo().findOne((long) 1).getEnrolledCourses().contains(analysis));
        assertTrue(studCont.getStudRepo().findOne((long) 2).getEnrolledCourses().contains(algebra));
        assertFalse(studCont.getStudRepo().findOne((long) 1).getEnrolledCourses().contains(algebra));
    }

    public void testDropCourse() {
        studCont.addStudent(naomi);
        studCont.addStudent(cristina);

        studCont.addCourseToGivenStudent(analysis,1); //Naomi analysis
        studCont.addCourseToGivenStudent(algebra,2); //Cristina analysis

        assertTrue(studCont.getStudRepo().findOne((long) 1).getEnrolledCourses().contains(analysis));
        assertTrue(studCont.getStudRepo().findOne((long) 2).getEnrolledCourses().contains(algebra));

        studCont.dropCourse(algebra);
        assertFalse(studCont.getStudRepo().findOne((long) 2).getEnrolledCourses().contains(algebra));
    }

}