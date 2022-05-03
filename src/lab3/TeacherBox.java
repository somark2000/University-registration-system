package lab3;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lab3.controller.TeacherController;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;

public class TeacherBox {
    private Teacher t;
    private final TeacherController teacherController;

    private final Stage window = new Stage();


    public Teacher getTeacher() {
        return t;
    }

    public void update() {
        /*Text notification = new Text("Hello Professor! You have a new student :) ");
        GridPane layer2 = new GridPane();
        layer2.setPadding(new Insets(10));
        layer2.setHgap(8);
        layer2.setVgap(6);
        layer2.setAlignment(Pos.CENTER);

        // create all the labels
        Label name = new Label();
        name.setText("Welcome back " + this.t.getFirstName() + " " + this.t.getLastName());


        // see all students enrolled
        Button seeAllStudents = new Button();
        seeAllStudents.setText("View all the students");

        seeAllStudents.setOnAction(event -> {
            int i;
            int j=0;
            for (Course c:t.getCourses()) {
                i=2;
                Text course = new Text(c.getId()+"  " + c.getName() );
                layer2.add(course,j,i);
                i++;
                for (Student s:c.getStudentsEnrolled())
                {
                    Text student=new Text(s.getStudentId() + "  " + s.getFirstName() + "  " + s.getLastName());
                    layer2.add(student,j,i);
                    i++;
                }
                j++;
            }
        });


        layer2.add(name,0,0);

        Text allStudents = new Text();
        allStudents.setText("All of the courses: ");
        layer2.add(allStudents,0,1);

        layer2.add(seeAllStudents,0,5);
        layer2.add(notification, 0,6);
        Scene page = new Scene(layer2, 300, 200);
        window.setScene(page);
        window.show();*/


        Text notification = new Text("Hello Professor! You have a new student :) ");
        GridPane layer2 = new GridPane();
        layer2.setPadding(new Insets(10));
        layer2.setHgap(8);
        layer2.setVgap(6);
        layer2.setAlignment(Pos.CENTER);

        // create all the labels
        Label name = new Label();
        name.setText("Welcome back " + this.t.getFirstName() + " " + this.t.getLastName());


        // see all students enrolled
        Button seeAllStudents = new Button();
        seeAllStudents.setText("View all the students");

        seeAllStudents.setOnAction(event -> {
            int i;
            int j=0;
            for (Course c:t.getCourses()) {
                i=4;
                Text course = new Text(c.getId()+"  " + c.getName() );
                layer2.add(course,j,i);
                i++;
                for (Student s:c.getStudentsEnrolled())
                {
                    Text student=new Text(s.getStudentId() + "  " + s.getFirstName() + "  " + s.getLastName());
                    layer2.add(student,j,i);
                    i++;
                }
                j++;
            }
        });


        layer2.add(name,0,0);
        layer2.add(notification,0,1);

        Text allStudents = new Text();
        allStudents.setText("All of the courses: ");
        layer2.add(allStudents,0,2);

        layer2.add(seeAllStudents,0,3);

        ScrollPane bg=new ScrollPane();
        bg.setContent(layer2);
        Scene page = new Scene(bg, 300, 200);
        window.setScene(page);
        window.show();

    }

    public TeacherBox(TeacherController teacherController) {
        this.teacherController = teacherController;
    }

    public void display() {
        window.setTitle("Hello Teacher");
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
            long id= teacherController.returnId(fname,lname);
            this.t = teacherController.getTeachers().findOne( id);
            if(t==null) {
                AlertBox.dispaly("ERROR", "No such teacher!");
            }
            else
            {
                changeTheScene();
            }
        });

        Scene loginpage = new Scene(layer, 300, 200);
        window.setScene(loginpage);
        window.show();
    }

    public void changeTheScene() {
        GridPane layer2 = new GridPane();
        layer2.setPadding(new Insets(10));
        layer2.setHgap(8);
        layer2.setVgap(6);
        layer2.setAlignment(Pos.CENTER);

        // create all the labels
        Label name = new Label();
        name.setText("Welcome back " + this.t.getFirstName() + " " + this.t.getLastName());


        // see all students enrolled
        Button seeAllStudents = new Button();
        seeAllStudents.setText("View all the students");

        seeAllStudents.setOnAction(event -> {
            int i;
            int j=0;
            for (Course c:t.getCourses()) {
                i=4;
                Text course = new Text(c.getId()+"  " + c.getName() );
                layer2.add(course,j,i);
                i++;
                for (Student s:c.getStudentsEnrolled())
                {
                    Text student=new Text(s.getStudentId() + "  " + s.getFirstName() + "  " + s.getLastName());
                    layer2.add(student,j,i);
                    i++;
                }
                j++;
            }
        });


        layer2.add(name,0,0);

        Text allStudents = new Text();
        allStudents.setText("All of the courses: ");
        layer2.add(allStudents,0,2);

        layer2.add(seeAllStudents,0,3);

        ScrollPane bg=new ScrollPane();
        bg.setContent(layer2);
        Scene page = new Scene(bg, 300, 200);
        window.setScene(page);
        window.show();
    }
}
