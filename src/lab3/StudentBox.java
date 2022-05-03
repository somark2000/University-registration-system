package lab3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lab3.controller.CourseController;
import lab3.controller.StudentController;
import lab3.model.Course;
import lab3.model.Student;
import javafx.scene.control.Label;


public class StudentBox {
    private Student stud;
    private final CourseController courseController;

    private final StudentController studentController;
    private TeacherBox teacherBox; // this is the observer
    private int state=0; // when the state 0 is, there is no change in the list of courses of the student

    public StudentBox(StudentController studCont, CourseController courseController) {
        this.studentController = studCont;
        this.courseController = courseController;
    }

    public void setTeacherBox(TeacherBox teacherBox) {
        this.teacherBox = teacherBox;
    }

    public void display() {
        Stage window = new Stage();

        window.setTitle("Hello Student");
        window.setMinWidth(250);

        GridPane layer = new GridPane();
        layer.setPadding(new Insets(10));
        layer.setHgap(8);
        layer.setVgap(6);
        layer.setAlignment(Pos.CENTER);

        Text title=new Text("Please log in first!");
        Text fn = new Text("First name: ");
        Text ln = new Text("Last name: ");

        TextField fnameinput=new TextField();
        fnameinput.setPromptText("enter your first name here");
        TextField lnameinput=new TextField();
        lnameinput.setPromptText("enter your last name here");

        Button loginbtn = new Button();
        loginbtn.setText("Log in");
        layer.add(title,0,0);
        layer.add(fn,0,1);
        layer.add(ln,0,2);
        layer.add(fnameinput,1,1);
        layer.add(lnameinput,1,2);
        layer.add(loginbtn,0,3);

        loginbtn.setOnAction(event -> {
            String fname=fnameinput.getText();
            String lname=lnameinput.getText();
            long id= studentController.returnId(fname,lname);
            this.stud = studentController.getStudRepo().findOne(id);
            if(stud==null) {
                AlertBox.dispaly("ERROR", "No such student!");
            }
            else
            {
                changeTheScene(window);
            }
        });

        Scene loginpage = new Scene(layer, 300, 200);
        window.setScene(loginpage);
        window.show();
    }

    public void changeTheScene(Stage originalStage) {
        GridPane layer2 = new GridPane();


        // create all the labels
        Label name = new Label();
        name.setText("Welcome back " + this.stud.getFirstName() + " " + this.stud.getLastName());

        Text creditsText = new Text();
        creditsText.setText("Credits: ");
        Label credits = new Label();

        Text newId = new Text();
        newId.setText("New course id: ");
        TextField newCourseId = new TextField();
        newCourseId.setPromptText("enter the id of the course");

        // add course to the student
        Button enrollToCourse = new Button();
        enrollToCourse.setText("New Course");

        enrollToCourse.setOnAction(event -> {
            int idCourse = Integer.parseInt(newCourseId.getText());
            newCourseId.clear();
            if (!this.studentController.getStudRepo().findOne(stud.getStudentId()).getEnrolledCourses().contains(this.courseController.getCourseRepo().findOne((long) idCourse))) {
                Text course=new Text(idCourse + "  " + this.courseController.getCourseRepo().findOne((long) idCourse).getName() + "  " + this.courseController.getCourseRepo().findOne((long) idCourse).getTeacher().getFirstName()+"  " + this.courseController.getCourseRepo().findOne((long) idCourse).getTeacher().getLastName());
                int line = stud.getEnrolledCourses().size();
                layer2.add(course,1,line+5);
                this.state = 1;
                this.studentController.addCourseToGivenStudent(this.courseController.getCourseRepo().findOne((long) idCourse),stud.getStudentId());
                this.courseController.addStudentToGivenCourse(stud,idCourse);
            }


            if (this.state==1 && this.courseController.getCourseRepo().findOne((long) idCourse).getTeacher()==teacherBox.getTeacher()) {
                this.notifyTeacher();
                this.state=0;
            }
        });


        // see all credits
        Button seeAllCredits = new Button();
        seeAllCredits.setText("Credits");

        seeAllCredits.setOnAction(event -> credits.setText(String.valueOf(this.stud.getTotalCredits())));


        layer2.add(name,0,0);
        layer2.add(creditsText,0,1);
        layer2.add(credits,1,1);
        layer2.add(newId, 0,2);
        layer2.add(newCourseId,1,2);
        layer2.add(seeAllCredits, 0,3);
        layer2.add(enrollToCourse,1,3);


        Text allCourses = new Text();
        allCourses.setText("All of the courses: ");
        layer2.add(allCourses,0,4);

        int i=5;
        for (Course c:this.courseController.getCourseRepo().findAll()) {
            Text course=new Text(c.getId() + "  " + c.getName() + "  " + c.getTeacher().getFirstName()+"  " + c.getTeacher().getLastName());
            layer2.add(course,0,i);

            i++;
        }

        Text myCourses = new Text();
        myCourses.setText("My courses: ");
        layer2.add(myCourses,1,4);

        int j=5;
        for (Course c:this.studentController.getStudRepo().findOne(stud.getStudentId()).getEnrolledCourses()) {
            Text course=new Text(c.getId() + "  " + c.getName() + "  " + c.getTeacher().getFirstName()+"  " + c.getTeacher().getLastName());
            layer2.add(course,1,j);

            j++;
        }

        ScrollPane bg=new ScrollPane();
        bg.setContent(layer2);
        Scene page = new Scene(bg, 300, 200);
        originalStage.setScene(page);
        originalStage.show();
    }

    public void notifyTeacher(){
        this.teacherBox.update();
    }
}
