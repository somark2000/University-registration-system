package lab3.view;
import lab3.model.Course;
import lab3.model.Student;

public class CourseView implements View<Course>{

    @Override
    public void printDetails(Course entity) {
        System.out.println("Name: " + entity.getName());
        System.out.println("Teacher: " + entity.getTeacher());
        System.out.println("MaxEnrollment: " + entity.getMaxEnrollment());
        System.out.println("Credits: " + entity.getCredits());
        System.out.println("Students enrolled: ");
        for (Student s:entity.getStudentsEnrolled()){
            System.out.println("ID: " + s.getStudentId());
            System.out.println("First Name: " + s.getFirstName());
            System.out.println("Last Name: " + s.getLastName());
        }
    }
}
