package lab3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import lab3.controller.CourseController;
import lab3.controller.StudentController;
import lab3.controller.TeacherController;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepo;
import lab3.view.CourseView;
import lab3.view.StudentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class where program starts.
 */
public class StartApp extends Application {

    private StudentController studentController;
    private CourseController courseController;
    private TeacherController teacherController;

    /**
     * Start point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * this is the core of the application
     * we have here all the windows needed
     * the main function will "call" this start function in order to be able to run the javafx programme
     * @param stage is the main stage
     */
    @Override
    public void start(Stage stage) throws Exception {

        // initialize all the elements (controllers)
        this.initialize();

        //window components
        stage.setTitle("Welcome to the new registration platform for UBB");
        GridPane layer = new GridPane();
        layer.setPadding(new Insets(10));
        layer.setHgap(8);
        layer.setVgap(6);
        layer.setAlignment(Pos.CENTER);

        Text title=new Text("Please select the category you want!");

        Button loginButtonTeacher = new Button();
        loginButtonTeacher.setText("Teacher");

        Button loginButtonStudent = new Button();
        loginButtonStudent.setText("Student");

        layer.add(title,0,0);
        layer.add(loginButtonTeacher,0,3);
        layer.add(loginButtonStudent,0,4);

        Scene studentOrTeacherPage = new Scene(layer, 300, 200);
        stage.setScene(studentOrTeacherPage);
        stage.show();

        StudentBox studentBox = new StudentBox(studentController, courseController);
        TeacherBox teacherBox = new TeacherBox(this.teacherController);
        studentBox.setTeacherBox(teacherBox);

        loginButtonStudent.setOnAction(event -> {
            studentBox.display();
        });

        loginButtonTeacher.setOnAction(event -> {
            teacherBox.display();
        });
    }

    /**
     * we will initialize all the needed elements
     * we will create all the repositories, controllers, students, courses and teachers
     */
    public void initialize() {
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

        Student naomi = new Student("Toth", "Naomi", 1,0,new ArrayList<>());
        Student cristina = new Student ("Toth", "Cristina", 2, 0, new ArrayList<>());
        Student cristinataflan = new Student ("Taflan", "Cristina", 3, 0, new ArrayList<>());
        Student teo = new Student ("Oltean", "Teo", 4, 0, new ArrayList<>());
        Student szintia = new Student ("Nagy", "Szintia", 5, 0, new ArrayList<>());

        List<Student> listStud = new ArrayList<>();
        listStud.add(naomi);
        listStud.add(cristina);
        listStud.add(cristinataflan);
        listStud.add(teo);
        listStud.add(szintia);

        StudentRepository studRepo = new StudentRepository(listStud);
        this.studentController = new StudentController(studRepo, new StudentView());

        List<Course> courses = new ArrayList<>();
        courses.add(algebra);
        courses.add(analysis);

        CourseRepository courseRepo = new CourseRepository(courses);
        this.courseController = new CourseController(courseRepo, new CourseView());

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(modoi);
        teachers.add(brigitte);

        TeacherRepo teacherRepo = new TeacherRepo(teachers);
        this.teacherController = new TeacherController(teacherRepo);

        this.teacherController.addCourse(algebra,1);
        this.teacherController.addCourse(analysis,2);

        this.courseController.addStudentToGivenCourse(naomi,1);
        this.courseController.addStudentToGivenCourse(naomi,2);
        this.courseController.addStudentToGivenCourse(cristina,1);



        this.studentController.addCourseToGivenStudent(algebra,1);
        this.studentController.addCourseToGivenStudent(algebra,2);
        this.studentController.addCourseToGivenStudent(analysis,1);



    }
}
