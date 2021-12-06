package com.eduardoprogramador.layout;

/*
* Copyright 2021. Eduardo Programador
*
* This class contains several methods for building full user interfaces,
* used for manipulating large GUI programs.
* It is built upon the JavaFX library
* and makes its usage easier and more efficient.
* This class only can be instantiated with the static method getInstance().
* Also, the class Skelet has a static class called Builder,
* which may be initialized with a default constructor for create UI objects
*
* All rights reserved.
*
* */
import com.eduardoprogramador.common.EngineFailHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class Skelet {

    /*
    * The default UI object.
    * Private variable
    * */
    private Stage stage;

    /*
    * Returns a new Skelet object
    * for invoking the common methods.
    * */
    public static Skelet getInstance() {
        return new Skelet();
    }

    /*
    * Adds a stage from another class.
    * */
    public void dumpIt(Stage stage) {
        this.stage = stage;
    }

    /*
    * Configures the stage, the window that the user sees
    * when he or she executes the program.
    *
    * Params:
    *
    * title: The software title
    * isMax: Boolean variable that informs if the windows is maximized.
    * opacity: The opacity of the current GUI software.
    *
    * */
    public void settings(String title, boolean isMax, float opacity) {
        this.stage.setTitle(title);
        this.stage.setMaximized(isMax);
        this.stage.setOpacity(opacity);
    }

    /*
    * Attaches the root Virtual Box (Vertical)
    * to the root of the stage.
    * */
    public void attach(VBox root) {
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
    }

    /*
    * Attaches the root Virtual Box (Vertical)
    * to the root of the stage.
    * Also, provides an image background
    * to the software, an online or offline one.
    * */
    public void attach(VBox root, boolean onlineImage, String url) throws EngineFailHandler {
        if(onlineImage) {

            Image image = new Image(url);
            if(image != null) {
                BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                root.setBackground(new Background(backgroundImage));
            } else {
                throw new EngineFailHandler("Problems when reading online image");
            }

        } else {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("icon.png");

            if(inputStream != null) {
                Image image = new Image(inputStream);
                BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                root.setBackground(new Background(backgroundImage));
            } else {
                throw new EngineFailHandler("Problems when reading offline image icon");
            }
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    /*
    * Add icon to the software based in an online image
    * */
    public void addIconOnline(String url) throws EngineFailHandler {
        Image image = new Image(url);
        if(image != null)
            stage.getIcons().add(image);
        else
            throw new EngineFailHandler("Problem when reading online image icon");
    }

    /*
    * Returns the image icon inside the jar file.
    * The image must be renamed to "icon.png"
    * */
    public void addIconOffline() throws EngineFailHandler {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("icon.png");
        if(inputStream != null) {
            stage.getIcons().add(new Image(inputStream));
        } else {
            throw new EngineFailHandler("Problems when reading offline image icon");
        }
    }


    /*
    * Makes the window visible to the user,
    * starting the GUI program
    * */
    public void appears() {
        this.stage.show();
    }

    /*
    * This static class of Skelet contain several methods
    * and properties to building UI objects like buttons, radio buttons, etc.
    * You must clear ready the documentation before you use it!
    * */
    public static class Builder  {

        /*
        * private variables.
        * these are the core of this package
        * */
        private VBox root;
        private ArrayList<Node> objects;
        private ArrayList<ToggleGroup> groups;
        public static final int Y = 0;
        public static final int X = 1;

        /*
        * Default constructor:
        * It does not require any param,
        * but it initializes the private objects.
        * */
        public Builder() {
            root = new VBox();
            objects = new ArrayList<>();
            groups = new ArrayList<>();
        }


        /*
        * Packages new objects to one another.
        *
        * Params:
        *
        * pos: it is the parent node that will hold the child objects.
        * objs: an int list containing the child nodes
        * type: the type of the parent node that was informed in the first param.
        *
        * the values of type must be one of the follow:
        *
        * 1 (Builder.Y) : A vertical Box (VBOX)
        * 2 (Builder.X) : An horizontal Box (HBOX)
        *
        * */
        public void packIt(int pos,int[] objs,int type) {

            ArrayList<Node> nodes = new ArrayList<>();

            for (int i = 0; i < objs.length; i++) {
                Node child = objects.get(objs[i]);
                nodes.add(child);
            }

            switch (type) {
                case X:
                    HBox hBox = (HBox) objects.get(pos);
                    for(Node nd : nodes) {
                        hBox.getChildren().add(nd);
                    }
                    objects.remove(pos);
                    objects.add(pos,hBox);
                    break;

                case Y:
                    VBox vBox = (VBox) objects.get(pos);
                    for(Node nd : nodes) {
                        vBox.getChildren().add(nd);
                    }
                    objects.remove(pos);
                    objects.add(pos,vBox);
                    break;

                default:
                    break;
            }
        }

        /*
        * Creates a new Text Field
        * and returns its position in the object array.
        * */
        public int edit() {
            objects.add(new TextField());
            return objects.size() - 1;
        }

        /*
        * Creates a new label
        * and returns its position in the object array.
        *
        * Params:
        *
        * txt: the text of the label
        * family: the font family. Eg.: Arial
        * size: the font size
        *
        * */
        public int text(String txt, String family, int size) {
            Label label = new Label(txt);
            label.setFont(new Font(family,size));
            objects.add(label);
            return objects.size() - 1;
        }

        /*
        * Creates a new empty ComboBox
        * and returns its position in the object array
        * */
        public int box() {
            objects.add(new ComboBox<>());
            return objects.size() - 1;
        }

        /*
        * Fills the previous ComboBox with string values
        *
        * Params:
        *
        * pos: the previous combobox position
        * elements: a list of the strings that will populate the object
        * fired: an interface that will trigger the selecting event
        *
        * */
        public void fillBox(int pos, String[] elements, Fired fired) {
            ComboBox comboBox = (ComboBox) objects.get(pos);
            for(String ele : elements) {
                comboBox.getItems().add(ele);
            }
            comboBox.getSelectionModel().select(0);
            comboBox.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                    String value = (String) newValue;
                    fired.select(pos,value);

                }
            });
            objects.remove(pos);
            objects.add(pos,comboBox);
        }

        /*
        * Adds a callback method to a previous combobox
        * without filling it with strings.
        *
         * Params:
         *
         * pos: the previous combobox position
         * elements: a list of the strings that will populate the object
         * fired: an interface that will trigger the selecting event
        * */
        public void fillBox(int pos,FillHandler handler) {

            if(handler.fillBox(pos)) {
                ComboBox comboBox = (ComboBox) objects.get(pos);
                comboBox.getSelectionModel().select(0);
                objects.remove(pos);
                objects.add(pos, comboBox);
            }
        }

        /*
        * Change the select element from a box element
        *
        * Params:
        *
        * box: the box element created
        * pos: the new posision of the selected element
        * */
        public void positionBox(int box, int pos) {
            ComboBox comboBox = (ComboBox) objects.get(box);
            comboBox.getSelectionModel().select(pos);
        }

        /*
        * Creates a new button and returns its position
        * in the array of node elements.
        *
        * Params:
        *
        * txt: The button text.
        * fired: An interface that will trigger the click event
        * */
        public int btn(String txt, Fired fired) {
            Button button = new Button(txt);
            int sz = objects.size() - 1;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    fired.click(sz);
                }
            });
            objects.add(button);
            return objects.size() - 1;
        }

        /*
        * Creates a textarea and returns its position
        * in the array of nodes
        * */
        public int area() {
            objects.add(new TextArea());
            return objects.size() - 1;
        }

        /*
        * Creates a DatePicker and returns
        * its position in the array of nodes
        * */
        public int date() {
            objects.add(new DatePicker());
            return objects.size() - 1;
        }

        /*
        * Creates a new radio button and
        * returns its position in the node array.
        *
        * params:
        *
        * label: the radio button text
        * */
        public int rb(String label) {
            objects.add(new RadioButton(label));
            return objects.size() - 1;
        }

        /*
        * Creates a new togglegroup that will hold
        * the child radiobutons, and returns its position
        * in the node array
        *
        * params:
        *
        * rbs: an int list of rbs objects
        * rbSelected: the rb position to be selected
        * fired: the interface that will trigger the select event
        * */
        public int rg(int[] rbs, int rbSelected,Fired fired) {

            ArrayList<RadioButton> buttons = new ArrayList<>();

            ToggleGroup toggleGroup = new ToggleGroup();
            for(int rb : rbs) {
                RadioButton radioButton = (RadioButton) objects.get(rb);
                toggleGroup.getToggles().add(radioButton);
                buttons.add(radioButton);

                if(rb == rbSelected)
                    radioButton.setSelected(true);

            }
            groups.add(toggleGroup);

            toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                    for (int i = 0; i < buttons.size(); i++) {

                        RadioButton rb = buttons.get(i);
                        if(newValue.equals(rb)) {
                            fired.choose(rbs[i],groups.size() - 1);
                        }
                    }
                }
            });

            return groups.size() - 1;
        }

        /*
        * Selects the given rb object
        * between the rbs inside a rg object
        * */
        public void positionRb(int pos) {
            RadioButton radioButton = (RadioButton) objects.get(pos);
            radioButton.setSelected(true);
        }


        /*
        * Creates a new horizontal box (Hbox) and returns its position
        * in the node array
        *
        * params:
        *
        * allignment: The Pos value tht will position the object
        * Eg.: Pos.Center
        *
        * spacing: the spacing that will be placed between the child
        * elements. The value is a number of type int.
        *
        * padding: the distance from the margin.
        * A new constructor of the class Insects must be instantiated.
        *
        * */
        public int X(Pos allignment, double spacing,Insets padding) {
            HBox hBox = new HBox();
            hBox.setAlignment(allignment);
            hBox.setSpacing(spacing);
            hBox.setPadding(padding);
            objects.add(hBox);
            return objects.size() - 1;
        }

        /*
         * Creates a new vertical lbox (Hbox) and returns its position
         * in the node array
         *
         * params:
         *
         * allignment: The Pos value tht will position the object
         * Eg.: Pos.Center
         *
         * spacing: the spacing that will be placed between the child
         * elements. The value is a number of type int.
         *
         * padding: the distance from the margin.
         * A new constructor of the class Insects must be instantiated.
         *
         * */
        public int Y(Pos allignment,double spacing, Insets padding) {
            VBox vBox = new VBox();
            vBox.setAlignment(allignment);
            vBox.setSpacing(spacing);
            vBox.setPadding(padding);
            objects.add(vBox);
            return objects.size() - 1;

        }

        /*
        * Returns the last node added
        * to the node array
        * */
        public Node cur() {
            return objects.get(objects.size() - 1);
        }

        /*
        * Returns the node of the node array
        * given the position provided by the user.
        *
        * param:
        *
        * pos: the position that the node
        * occupies inside the node array.
        *
        * */
        public Node curObj(int pos) {
            return objects.get(pos);
        }

        /*
        * Returns the date selected from the
        * date object.
        *
        * param:
        *
        * pos: the node position in its array.
        * */
        public String getBruteTime(int pos) {
            DatePicker datePicker = (DatePicker) curObj(pos);
            LocalDate localDate = datePicker.getValue();
            String brute = (localDate == null) ? "--" : localDate.toString();
            String[] pieces = brute.split("-");
            return pieces[0] + pieces[1] + pieces[2];
        }

        /*
        * Returns the text from an edit object.
        * The node position must be provided in the param
        * */
        public String txtFrom(int pos) {
            TextField textField = (TextField) objects.get(pos);
            return textField.getText();
        }

        /*
        * Put some text inside the edit object.
        *
        * params:
        *
        * pos: the position of the object in the node array.
        * value: the text to put in.
        * */
        public void  txtToEdit(int pos, String value) {
            TextField textField = (TextField) objects.get(pos);
            textField.setText(value);
        }

        /*
         * Put some text inside the area object.
         *
         * params:
         *
         * pos: the position of the object in the node array.
         * value: the text to put in.
         * */
        public void txtToArea(int pos, String value) {
            TextArea textArea = (TextArea) objects.get(pos);
            textArea.setText(value);
        }

        /*
         * Put some text inside the date object.
         *
         * params:
         *
         * pos: the position of the object in the node array.
         * value: the text to put in.
         * */
        public void txtToDate(int pos, String value) {
            DatePicker datePicker = (DatePicker) objects.get(pos);
            datePicker.getEditor().setText("");
            objects.remove(pos);
            objects.add(pos,datePicker);
        }

        /*
        * Appends new text onto the current text
        * of an area object.
        *
        * params:
        *
        * pos: the position of the area object in its node array.
        * value: the text to append to.
        * */
        public void joinArea(int pos, String value) {
            TextArea textArea = (TextArea) objects.get(pos);
            textArea.appendText(value);
        }
    }

}
