import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Form extends Application {

    ObservableList<String> list = FXCollections.observableArrayList();
    ArrayList<StudentClass> studentsList = new ArrayList<>();

    int currentIndex = 0;

    TextField studentNumberTextField = new TextField();
    TextField programTextField = new TextField();
    TextField firstNameTextField = new TextField();
    TextField lastNameTextField = new TextField();
    TextField emailTextField = new TextField();

    Button btnBackward = new Button("<");
    Button btnForward = new Button(">");
    Button btnSaveData = new Button("Save");
    Button btnLoadData = new Button("Load Data");

    @Override
    public void start(Stage stage) {

        BorderPane pane = new BorderPane();

        VBox vBoxForTitle = new VBox();
        Text textTitle = new Text("Student Information");
        textTitle.setFont(Font.font("Arial", 28));
        vBoxForTitle.getChildren().add(textTitle);
        vBoxForTitle.setAlignment(Pos.CENTER);
        vBoxForTitle.setPadding(new Insets(15));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(25, 0, 20, 0));

        Label studentLabel = new Label("Student# :");
        gridPane.add(studentLabel, 0, 0);
        GridPane.setHalignment(studentLabel, HPos.RIGHT);
        gridPane.add(studentNumberTextField, 1, 0);

        Label programLabel = new Label("Program :");
        gridPane.add(programLabel, 2, 0);
        GridPane.setHalignment(programLabel, HPos.RIGHT);
        gridPane.add(programTextField, 3, 0);

        Label firstNameLabel = new Label("First Name :");
        gridPane.add(firstNameLabel, 0, 1);
        GridPane.setHalignment(firstNameLabel, HPos.RIGHT);
        gridPane.add(firstNameTextField, 1, 1);

        Label lastNameLabel = new Label("Last Name :");
        gridPane.add(lastNameLabel, 2, 1);
        GridPane.setHalignment(lastNameLabel, HPos.RIGHT);
        gridPane.add(lastNameTextField, 3, 1);

        Label emailLabel = new Label("Email :");
        gridPane.add(emailLabel, 0, 2);
        GridPane.setHalignment(emailLabel, HPos.RIGHT);
        gridPane.add(emailTextField, 1, 2);

        Label coursesLabel = new Label("Courses :");
        gridPane.add(coursesLabel, 0, 3);
        GridPane.setHalignment(coursesLabel, HPos.RIGHT);
        GridPane.setValignment(coursesLabel, VPos.TOP);

        ListView<String> listView = new ListView<String>();
        listView.setItems(list);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setMaxHeight(100);
        listView.setMaxWidth(150);

        gridPane.add(listView, 1, 3);

        HBox vBoxForCheckBox = new HBox();
        CheckBox checkBox1 = new CheckBox("Save with Course Listing");
        vBoxForCheckBox.getChildren().add(checkBox1);
        gridPane.add(vBoxForCheckBox, 2, 3, 2, 2);

        HBox hBoxForButton = new HBox(10);

        btnLoadData.setOnAction(actionEvent -> {
            loadFileData();
        });

        btnBackward.setDisable(true);
        btnBackward.setOnAction(actionEvent -> {
            if (currentIndex > 0) {
                currentIndex--;
                displayData();
            }
        });

        btnForward.setDisable(true);
        btnForward.setOnAction(actionEvent -> {
            if (currentIndex < studentsList.size()) {
                currentIndex++;
                displayData();
            }
        });

        btnSaveData.setOnAction(actionEvent -> {
            if (studentsList.get(currentIndex) != null) {
                if (checkBox1.isSelected()) {
                    saveToFile(new StudentClass(Integer.parseInt(studentNumberTextField.getText()), programTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), new ArrayList<>(listView.getSelectionModel().getSelectedItems())));
                } else {
                    saveToFile(new StudentClass(Integer.parseInt(studentNumberTextField.getText()), programTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText()));
                }

                studentsList.get(currentIndex).setSaved(!studentsList.get(currentIndex).getSaved());
                btnSaveData.setDisable(studentsList.get(currentIndex).getSaved());
            }
        });

        hBoxForButton.getChildren().addAll(btnLoadData, btnBackward, btnForward, btnSaveData);
        hBoxForButton.setPadding(new Insets(20));
        hBoxForButton.setAlignment(Pos.CENTER);

        pane.setTop(vBoxForTitle);
        pane.setCenter(gridPane);
        pane.setBottom(hBoxForButton);

        Scene scene = new Scene(pane, 600, 400);
        stage.setTitle("Student Information Form");
        stage.setScene(scene);
        stage.show();
    }

    private void loadFileData() {

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/data.studat"))) {
            while (true) {
                studentsList.add((StudentClass) objectInputStream.readObject());
            }
        } catch (EOFException ex) {
            System.out.println("File scan completed!");
        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println("File Read Failed");
        }

        currentIndex = 0;
        displayData();
        btnLoadData.setDisable(true);
    }

    private void displayData() {

        if (studentsList.size() > currentIndex) {

            studentNumberTextField.setText(String.valueOf(studentsList.get(currentIndex).getStudentId()));
            programTextField.setText(String.valueOf(studentsList.get(currentIndex).getProgram()));
            firstNameTextField.setText(String.valueOf(studentsList.get(currentIndex).getFirstName()));
            lastNameTextField.setText(String.valueOf(studentsList.get(currentIndex).getLastName()));
            emailTextField.setText(String.valueOf(studentsList.get(currentIndex).getEmail()));

            list.clear();
            list.addAll(studentsList.get(currentIndex).getCourse());

            btnBackward.setDisable(currentIndex == 0);
            btnForward.setDisable(studentsList.size() - 1 == currentIndex);
            btnSaveData.setDisable(studentsList.get(currentIndex).getSaved());

        }
    }

    private void saveToFile(StudentClass studentClass) {
        try (RandomAccessFile inoutFile = new RandomAccessFile("src/output.dat", "rw")) {
            inoutFile.seek(inoutFile.length());
            inoutFile.writeBytes(studentClass.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
