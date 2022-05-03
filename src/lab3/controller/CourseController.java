package lab3.controller;

import lab3.model.Course;
import lab3.model.Student;
import lab3.repository.CourseRepository;
import lab3.view.CourseView;

import java.util.ArrayList;
import java.util.List;

public class CourseController {
    CourseRepository courseRepo;
    CourseView courseView;

    public CourseRepository getCourseRepo() {
        return courseRepo;
    }

    /**
     * @param c a given course
     * add the given course to the repository
     */
    public void addCourse(Course c) {
        courseRepo.save(c);
    }

    /**
     * delete course from the repository
     * @param id the id of the course which will be deleted
     */
    public void deleteCourse(long id) {
        courseRepo.delete(id);
    }


    public void updateCourse(Course c) {
        courseRepo.update(c);
    }

    public CourseController(CourseRepository courseRepo, CourseView courseView) {
        this.courseRepo = courseRepo;
        this.courseView = courseView;
    }

    /**
     *
     * @return the courses, which have available places -> they are not full
     */
    public List<Course> CoursesWithAvailablePlace() {
        List<Course> myList = new ArrayList<>();
        for (Course c:courseRepo.findAll()){
            int numberEnrolled = c.getStudentsEnrolled().size();
            if (numberEnrolled < c.getMaxEnrollment())
                myList.add(c);
        }
        return myList;
    }

    /**
     *
     * @param stud the student, which will be enrolled to the given course
     * @param id the id of the course
     */
    public void addStudentToGivenCourse(Student stud, long id) {
        Course c = this.courseRepo.findOne(id);
        // STREAM
        if (c != null) {
            ((List<Course>)courseRepo.findAll()).stream()
                    .filter(course -> course.getId()==id)
                    .forEach(course -> {
                        List<Student> listOfStudents=c.getStudentsEnrolled();
                        listOfStudents.add(stud);
                    });

        }
    }

    /**
     *
     * @param course_id the id for the given course
     * @return a list of student, who are enrolled to this given class/course
     */
    public List<Student> studentsEnrolledToGivenCourse(long course_id) {
        // STREAM

        return ((List<Course>)courseRepo.findAll()).stream()
                .filter(c -> c.getId()==course_id)
                .findAny()
                .get().getStudentsEnrolled();
    }

}