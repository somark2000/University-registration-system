package lab3.model;

import java.util.List;

public class Teacher extends Person {
    List<Course> courses;
    long teacherID;

    public void addCourse(Course c) {
        courses.add(c);
    }

    public Teacher(String firstName, String lastName, List<Course> courses, long teacherID) {
        super(firstName, lastName);
        this.courses = courses;
        this.teacherID = teacherID;
    }

    public long getTeacherID() {
        return teacherID;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * gets a message every time a student is enrolled to his/her class
     */
    public void update() {
        System.out.println("Hello " + this.getFirstName() + ", a new student has enrolled to your course!");
    }

}
