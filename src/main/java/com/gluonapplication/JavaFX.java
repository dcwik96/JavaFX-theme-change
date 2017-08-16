package com.gluonapplication;

import java.io.File;

import com.guigarage.flatterfx.FlatterFX;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JavaFX extends Application {

    private Stage stage;
    private Scene scene1, scene2;

    private Label label1;
    private Label label2;
    private Button button1;
    private Button showContentButton;
    private Button goBackButton;
    private Button openTxtButton;
    private Button closeAppButton;
    private Button checkSysInfoButton;

    private TextField textField;

    private String osName;

    @Override
    public void start(Stage stage) throws Exception {
        osName = checkSysInfo();
        this.stage = stage;
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        double width = visualBounds.getWidth();
        double height = visualBounds.getHeight();

        initLabelsAndFields();

        initButton1();
        initGoBackButton();
        initShowContentButton();
        initCloseAppButton();
        initOpenTxTButton();
        initCheckSystemButton();


        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, textField, button1, showContentButton, openTxtButton, checkSysInfoButton, closeAppButton);
        scene1 = new Scene(layout1, width, height);


        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(label2, goBackButton);
        scene2 = new Scene(layout2, width, height);


        this.stage.setScene(scene1);
        this.stage.setTitle("Elenx");
        this.stage.show();
        FlatterFX.style();

    }

    private void initCheckSystemButton() {
        checkSysInfoButton = new Button("Check system info");
        checkSysInfoButton.setOnAction(event -> {


            System.out.println(osName);
            label2.setText(osName);

            this.stage.setScene(scene2);
        });

    }

    private String checkSysInfo() {
        return System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch");
    }

    private void initOpenTxTButton() {
        openTxtButton = new Button("Open .txt");
        openTxtButton.setOnAction((e) ->{
        label2.setText("//TODO");
//            try {
//
//
//                BufferedReader readTxt = new BufferedReader(new FileReader(""));
//                String s = "", line = null;
//                while ((line = readTxt.readLine()) != null){
//                    s += line;
//                }
//
//
//            } catch (FileNotFoundException e1) {
//                e1.printStackTrace();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
            this.stage.setScene(scene2);
        });
    }

    private void initCloseAppButton() {
        closeAppButton = new Button("Close");
        closeAppButton.setOnAction((e)->{
            System.exit(0);
        });
    }

    private void initShowContentButton() {
        showContentButton = new Button("List content folder");
        showContentButton.setOnAction((e) ->{
            this.stage.setScene(scene2);

            String[] elements = new String[0];

            if (osName.toLowerCase().startsWith("windows")){
                elements = new File(System.getProperty("user.home"), "Desktop").list();
            }

            if (osName.toLowerCase().startsWith("linux")){
                elements = new File("/storage/emulated/0/Android").list();
            }

            String list = new String();
            for (String s : elements) {
                list += "\n" + s;
            }
            label2.setText(list);

        });
    }

    private void initButton1() {
        button1 = new Button("Button");
        button1.setOnAction(e -> this.stage.setScene(scene2));
        button1.setOnAction((e) -> {
            this.stage.setScene(scene2);
            label2.setText(textField.getText());

        });
    }

    private void initGoBackButton() {
        goBackButton = new Button("Go back");
        goBackButton.setOnAction(e -> this.stage.setScene(scene1));
    }


    public void initLabelsAndFields() throws Exception {

        label1 = new Label("Click the button");
        label2 = new Label();

        textField = new TextField();

    }

}
