package lab3.model;

import java.util.List;

public class Student extends Person{
    long studentId;
    int totalCredits;
    List<Course> enrolledCourses;

    public Student(String firstName, String lastName, long studentId, int totalCredits, List<Course> enrolledCourses) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
        this.enrolledCourses = enrolledCourses;
    }

    /**
     * adds a given course to the student
     * it will send a notification to the course, when a new student is enrolled to the course
     * @param c is an object of type Course
     */
    public void addToCourse(Course c) {
        enrolledCourses.add(c);
        this.notifyCourse(c);
    }


    public void addCredits(int credit) {
        totalCredits+=credit;
    }

    public long getStudentId() {
        return studentId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public void notifyCourse(Course c) {
        c.notifyTeacher(this);
    }
}
