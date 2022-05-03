package lab3.controller;

import lab3.model.Course;
import lab3.model.Student;

import java.util.List;

public class RegistrationSystem {
    CourseController courseController;
    StudentController studentController;

    public RegistrationSystem(CourseController courseController, StudentController studentController) {
        this.courseController = courseController;
        this.studentController = studentController;
    }


    public StudentController getStudentController() {
        return studentController;
    }

    /**
     *
     * @param course is a course
     * @param student is a student
     * @return true if the registration was successful
     */
    public boolean register(Course course, Student student)
    {
        if(student.getTotalCredits()+course.getCredits()<=30)
        {
            if(course.getMaxEnrollment()-course.getStudentsEnrolled().size()>0)
            {
                //the student has the possibility to enrole himself to the given course
                this.studentController.addCourseToGivenStudent(course,student.getStudentId());
                this.courseController.addStudentToGivenCourse(student,course.getId());
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return a list of courses
     */
    public List<Course> retiveCoursesWithFreePlaces()
    {
        return this.courseController.CoursesWithAvailablePlace();
    }

    /**
     *
     * @param course is a course
     * @return a list of students
     */
    public List<Student> retiveStudentsEnrolledForACourse(Course course)
    {
        return  this.courseController.studentsEnrolledToGivenCourse(course.getId());
    }

    /**
     *
     * @return a list of courses
     */
    public List<Course> getAllCourses()
    {
        return this.courseController.getCourseRepo().getCourses();
    }

}
