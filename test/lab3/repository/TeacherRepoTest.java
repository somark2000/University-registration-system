package lab3.repository;

import junit.framework.TestCase;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;

import java.util.ArrayList;
import java.util.List;


public class TeacherRepoTest extends TestCase {
    List<Course> emptyCourses1 = new ArrayList<>();
    List<Course> emptyCourses2 = new ArrayList<>();

    List<Student> emptyStudent1 = new ArrayList<>();
    List<Student> emptyStudent2 = new ArrayList<>();

    Teacher modoi = new Teacher("Modoi", "Gabriel", emptyCourses1,1);
    Teacher brigitte = new Teacher("Lisei", "Brigitte", emptyCourses2,2);

    Course algebra = new Course((long) 1,"Algebra", modoi, 120,emptyStudent1 , 5);
    Course analysis = new Course((long) 2, "Brigitte", brigitte, 100, emptyStudent2, 6);

    List<Teacher> teachers=new ArrayList<>();
    TeacherRepo teacherRepo=new TeacherRepo(teachers);

    public void testFindOne() {
        teacherRepo.save(modoi);
        teacherRepo.save(brigitte);
        assertEquals(teacherRepo.findOne((long) 1),modoi);
        assertEquals(teacherRepo.findOne((long) 2),brigitte);
        assertNull(teacherRepo.findOne((long) 3));
    }

    public void testFindAll() {
        teacherRepo.save(modoi);
        teacherRepo.save(brigitte);
        assertSame(((List<Teacher>)teacherRepo.findAll()).size(), 2);
    }

    public void testSave() {
        teacherRepo.save(modoi);
        teacherRepo.save(brigitte);
        modoi.addCourse(algebra);
        modoi.addCourse(analysis);
        assertEquals(2, teacherRepo.findOne((long) 1).getCourses().size());
        assertEquals(0, teacherRepo.findOne((long) 2).getCourses().size());
    }

    public void testDelete() {
        teacherRepo.save(modoi);
        teacherRepo.save(brigitte);
        teacherRepo.delete((long) 1);
        assertNull(teacherRepo.findOne((long) 1));
    }

    public void testUpdate() {
        teacherRepo.save(modoi);
        teacherRepo.save(brigitte);
        assertEquals(0, teacherRepo.findOne((long) 1).getCourses().size());
        modoi.addCourse(algebra);
        modoi.addCourse(analysis);
        assertSame(2,teacherRepo.findOne((long) 1).getCourses().size());
        brigitte.setFirstName("Algebra");
        brigitte.setLastName("Analysis");
        teacherRepo.update(brigitte);
        assertSame("Algebra", teacherRepo.findOne((long) 2).getFirstName());
        assertEquals("Analysis", teacherRepo.findOne((long) 2).getLastName());
    }

}
