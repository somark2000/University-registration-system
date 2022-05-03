package lab3.controller;

import junit.framework.TestCase;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.view.CourseView;

import java.util.ArrayList;
import java.util.List;

public class CourseControllerTest extends TestCase {
    List<Course> emptyCourses1 = new ArrayList<>();
    List<Course> emptyCourses2 = new ArrayList<>();

    List<Student> emptyStudent1 = new ArrayList<>();
    List<Student> emptyStudent2 = new ArrayList<>();

    List<Course> emptyEnrolled1 = new ArrayList<>();
    List<Course> emptyEnrolled2 = new ArrayList<>();

    Teacher modoi = new Teacher("Modoi", "Gabriel", emptyCourses1,1);
    Teacher brigitte = new Teacher("Lisei", "Brigitte", emptyCourses2,2);

    Course algebra = new Course((long) 1,"Algebra", modoi, 3,emptyStudent1 , 5);
    Course analysis = new Course((long) 2, "Brigitte", brigitte, 1, emptyStudent2, 6);

    Student naomi = new Student("Toth", "Naomi", 1,0,emptyEnrolled1);
    Student cristina = new Student ("Toth", "Cristina", 2, 0, emptyEnrolled2);

    List<Student> listStud = new ArrayList<>();
    StudentRepository studRepo = new StudentRepository(listStud);

    List<Course> courses = new ArrayList<>();
    CourseRepository courseRepo = new CourseRepository(courses);

    CourseView view = new CourseView();
    CourseController controller = new CourseController(courseRepo, view);


    public void testAddCourse() {
        assertEquals(0, controller.getCourseRepo().getCourses().size());
        controller.addCourse(algebra);
        controller.addCourse(analysis);
        assertEquals(2, controller.getCourseRepo().getCourses().size());
    }

    public void testDeleteCourse() {
        assertEquals(0, controller.getCourseRepo().getCourses().size());
        controller.addCourse(algebra);
        controller.addCourse(analysis);
        assertEquals(2, controller.getCourseRepo().getCourses().size());
        controller.deleteCourse(2);
        assertEquals(1, controller.getCourseRepo().getCourses().size());
        assertSame(controller.getCourseRepo().findOne((long) 1), algebra);
        assertNull(controller.getCourseRepo().findOne((long) 2));
    }

    public void testUpdateCourse() {
        controller.addCourse(algebra);
        controller.addCourse(analysis);
        algebra.addStudent(naomi);
        algebra.addStudent(cristina);
        algebra.setName("ALGEBRA");
        controller.updateCourse(algebra);
        assertTrue(controller.getCourseRepo().findOne((long) 1).getStudentsEnrolled().size()==2);
        assertSame("ALGEBRA", controller.getCourseRepo().findOne((long) 1).getName());
    }

    public void testCoursesWithAvailablePlace() {
        controller.addCourse(algebra);
        controller.addCourse(analysis);
        algebra.addStudent(naomi);
        algebra.addStudent(cristina);
        analysis.addStudent(naomi);
        assertEquals(1, controller.CoursesWithAvailablePlace().size());
        assertTrue(controller.CoursesWithAvailablePlace().contains(algebra));
    }

    public void testAddStudentToGivenCourse() {
        controller.addCourse(algebra);
        controller.addCourse(analysis);
        controller.addStudentToGivenCourse(naomi,1);
        controller.addStudentToGivenCourse(naomi,2);
        assertTrue(controller.getCourseRepo().findOne((long) 1).getStudentsEnrolled().contains(naomi));
        assertFalse(controller.getCourseRepo().findOne((long) 1).getStudentsEnrolled().contains(cristina));
        assertTrue(controller.getCourseRepo().findOne((long) 2).getStudentsEnrolled().contains(naomi));
    }

    public void testStudentsEnrolledToGivenCourse() {
        controller.addCourse(algebra);
        controller.addCourse(analysis);
        controller.addStudentToGivenCourse(naomi,1);
        controller.addStudentToGivenCourse(naomi,2);
        controller.addStudentToGivenCourse(cristina,1);
        assertEquals(2, controller.studentsEnrolledToGivenCourse(1).size());
        assertEquals(1, controller.studentsEnrolledToGivenCourse(2).size());
    }

}