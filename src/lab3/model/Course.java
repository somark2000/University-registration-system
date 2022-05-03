package lab3.model;

import java.util.List;

public class Course {
    Long id;
    String name;
    Teacher teacher;
    int maxEnrollment;
    List<Student> studentsEnrolled;
    int credits;

    public void addStudent(Student s) {
        studentsEnrolled.add(s);
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course(Long id, String name, Teacher teacher, int maxEnrollment, List<Student> studentsEnrolled, int credits) {
        this.id=id;
        this.name = name;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = studentsEnrolled;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }


    /**
     * sends a notification to the teacher, when a student is enrolled to his/her course
     * @param s is the student, which was enrolled to the course
     */
    public void notifyTeacher(Student s) {
        this.teacher.update();
    }
}
