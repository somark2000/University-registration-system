package lab3.view;
import lab3.model.Course;
import lab3.model.Student;

public class StudentView implements View<Student>{

    @Override
    public void printDetails(Student entity) {
        System.out.println("ID: " + entity.getStudentId());
        System.out.println("First Name: " + entity.getFirstName());
        System.out.println("Last Name: " + entity.getLastName());
        System.out.println("Total credits: " + entity.getTotalCredits());
        System.out.println("Courses: ");
        for (Course c:entity.getEnrolledCourses()){
            System.out.println("Course name: " + c.getName() + " Teacher: " + c.getTeacher());
        }
    }
}
