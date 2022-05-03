package lab3.controller;
import junit.framework.TestCase;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.view.CourseView;
import lab3.view.StudentView;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystemTest extends TestCase{
    List<Course> emptyCourses1 = new ArrayList<>();
    List<Course> emptyCourses2 = new ArrayList<>();

    List<Student> emptyStudent1 = new ArrayList<>();
    List<Student> emptyStudent2 = new ArrayList<>();

    List<Course> emptyEnrolled1 = new ArrayList<>();
    List<Course> emptyEnrolled2 = new ArrayList<>();

    Teacher modoi = new Teacher("Modoi", "Gabriel", emptyCourses1,1);
    Teacher brigitte = new Teacher("Lisei", "Brigitte", emptyCourses2,2);

    Course algebra = new Course((long) 1,"Algebra", modoi, 3,emptyStudent1 , 20);
    Course analysis = new Course((long) 2, "Brigitte", brigitte, 1, emptyStudent2, 6);

    Student naomi = new Student("Toth", "Naomi", 1,0,emptyEnrolled1);
    Student cristina = new Student ("Toth", "Cristina", 2, 0, emptyEnrolled2);

    List<Student> listStud = new ArrayList<>();
    StudentRepository studRepo = new StudentRepository(listStud);

    List<Course> courses = new ArrayList<>();
    CourseRepository courseRepo = new CourseRepository(courses);

    CourseView view = new CourseView();
    CourseController controller = new CourseController(courseRepo, view);
    StudentView studView = new StudentView();
    StudentController studCont = new StudentController(studRepo, studView);
    RegistrationSystem registrationSystem=new RegistrationSystem(controller,studCont);

    public void testregister()
    {
        registrationSystem.studentController.addStudent(naomi);
        registrationSystem.courseController.addCourse(algebra);
        assertTrue(registrationSystem.register(algebra,naomi));
        registrationSystem.register(algebra,naomi);
        assertTrue(registrationSystem.register(algebra,naomi)==false);
    }

    public void testretiveCoursesWithFreePlaces()
    {
        registrationSystem.courseController.addCourse(algebra);
        registrationSystem.courseController.addCourse(analysis);
        assertEquals(2,registrationSystem.retiveCoursesWithFreePlaces().size());
        registrationSystem.register(analysis,naomi);
        assertEquals(1,registrationSystem.retiveCoursesWithFreePlaces().size());
    }

    public void testretiveStudentsEnrolledForACourse()
    {
        registrationSystem.studentController.addStudent(naomi);
        registrationSystem.courseController.addCourse(algebra);
        registrationSystem.courseController.addCourse(analysis);
        assertEquals(0,registrationSystem.retiveStudentsEnrolledForACourse(algebra).size());
        registrationSystem.register(algebra,naomi);
        System.out.println(registrationSystem.retiveStudentsEnrolledForACourse(algebra).size());
        System.out.println(registrationSystem.courseController.courseRepo.findOne(algebra.getId()).getStudentsEnrolled().size());
        assertEquals(1,registrationSystem.retiveStudentsEnrolledForACourse(analysis).size());
    }

    public void testgetAllCourses()
    {
        assertEquals(0,registrationSystem.getAllCourses().size());
        registrationSystem.studentController.addStudent(naomi);
        registrationSystem.courseController.addCourse(algebra);
        assertEquals(2,registrationSystem.getAllCourses().size());
    }
}
