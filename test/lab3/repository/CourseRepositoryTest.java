package lab3.repository;

import junit.framework.TestCase;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryTest extends TestCase {
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

    List<Course> courses = new ArrayList<>();
    CourseRepository courseRepo = new CourseRepository(courses);

    public void testFindOne() {
        courseRepo.save(algebra);
        courseRepo.save(analysis);
        algebra.addStudent(naomi);
        algebra.addStudent(cristina);
        assertEquals(courseRepo.findOne((long) 1),algebra);
        assertEquals(courseRepo.findOne((long) 2),analysis);

    }

    public void testFindAll() {
        courseRepo.save(algebra);
        courseRepo.save(analysis);
        algebra.addStudent(naomi);
        algebra.addStudent(cristina);
        assertSame(((List<Course>)courseRepo.findAll()).size(), 2);
    }

    public void testSave() {
        courseRepo.save(algebra);
        courseRepo.save(analysis);
        algebra.addStudent(naomi);
        algebra.addStudent(cristina);
        assertEquals(2, courseRepo.findOne((long) 1).getStudentsEnrolled().size());
        assertEquals(0, courseRepo.findOne((long) 2).getStudentsEnrolled().size());
    }

    public void testDelete() {
        courseRepo.save(algebra);
        courseRepo.save(analysis);
        courseRepo.delete((long) 1);
        assertNull(courseRepo.findOne((long) 1));
    }

    public void testUpdate() {
        courseRepo.save(algebra);
        courseRepo.save(analysis);
        assertEquals(0, courseRepo.findOne((long) 1).getStudentsEnrolled().size());
        algebra.addStudent(naomi);
        algebra.addStudent(cristina);
        assertSame(2,courseRepo.findOne((long) 1).getStudentsEnrolled().size());
        algebra.setCredits(10);
        algebra.setName("Algebra");
        algebra.setMaxEnrollment(160);
        courseRepo.update(algebra);
        assertEquals(10, courseRepo.findOne((long) 1).getCredits());
        assertSame("Algebra", courseRepo.findOne((long) 1).getName());
        assertEquals(160, courseRepo.findOne((long) 1).getMaxEnrollment());
    }
}