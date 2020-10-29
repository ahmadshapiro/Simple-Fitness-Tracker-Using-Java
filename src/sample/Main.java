package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class Main extends Application {
   public DecimalFormat deci = new DecimalFormat("#.###");
    Stage window;
    Scene scene;
    Scene scene2;
    Button addActivityButton;
    Button playButton;
    Swimming swimming = new Swimming();
    Running running = new Running();
    KickBoxing kickBoxing = new KickBoxing();
    StrengthTraining strengthTraining = new StrengthTraining();
    User ahmad = new User("Ahmad");
    TableView<Activity> ranks;
    ObservableList<Node> gridPaneNodes;
    Label resultsLabel;
    Label calroiesBurntLabel;
    Label heartBeatLabel;
    Button ranksButton;
    VBox results;
    Button backButton;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Fitness Tracker");
        //Making the activities Scene
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setHgap(10);
        pane.setVgap(8);
        addActivityButton = new Button("Add Activity"); //Add Activity Button
        addActivityButton.setOnAction(e ->
        {
            if (pane.getRowCount() == 10) {
                //setting maximum number of activites to 10


            } else {
                HBox layout = creatActivity();
                pane.addColumn(1, layout);
//                System.out.println(pane.getRowCount());
            }
            gridPaneNodes = pane.getChildren();


        });
        pane.addColumn(1, creatActivity());//insert First activity with add-button aligned
        gridPaneNodes = pane.getChildren();
        playButton = new Button("Play");
        playButton.setAlignment(Pos.CENTER);
        pane.add(addActivityButton, 5, 0);
        pane.setAlignment(Pos.CENTER);
        AnchorPane mainPane = new AnchorPane();
        mainPane.getChildren().add(pane);
        mainPane.setTopAnchor(pane, 20d);
        mainPane.setLeftAnchor(pane, 20d);
        mainPane.setRightAnchor(pane, 20d);
        BorderPane finalPane = new BorderPane();
        finalPane.setCenter(mainPane);
        finalPane.setBottom(playButton);
        finalPane.setAlignment(playButton, Pos.TOP_CENTER);
        finalPane.setMargin(playButton, new Insets(12, 12, 20, 12));
        finalPane.getStyleClass().add("background-pane");
        results=new VBox(10);
        resultsLabel=new Label("Results:-");
        calroiesBurntLabel=new Label("Total Calories Burnt: "+ahmad.getCalories()+" Calories");
        heartBeatLabel=new Label("Total Heart Rate: "+deci.format(ahmad.getHearBeat())+" bpm");
        ranksButton=new Button("Ranks");
        results.getChildren().addAll(resultsLabel,calroiesBurntLabel,heartBeatLabel,ranksButton);
        finalPane.setRight(results);
        results.setAlignment(Pos.BOTTOM_CENTER);
        finalPane.setMargin(results, new Insets(0, 0, 0, 0));
        finalPane.getRight().setVisible(false);

        playButton.setOnAction(e ->
        {
            ahmad.getActivitiesplayed().clear();
            ahmad.clearAll();
            swimming.clearAllData();
            running.clearAllData();
            kickBoxing.clearAllData();
            strengthTraining.clearAllData();
            for (int i = 0; i < gridPaneNodes.size(); i++) {
                if (i == 1)
                    continue;
                else {
                    HBox temp = (HBox) gridPaneNodes.get(i);
                    ComboBox<String> tempActname = (ComboBox) temp.getChildren().get(0);
                    String actName = tempActname.getSelectionModel().getSelectedItem();
                    Spinner<Integer> hours = (Spinner) temp.getChildren().get(1);
                    int hoursCount = hours.getValue();
                    Spinner<Integer> minutes = (Spinner) temp.getChildren().get(3);
                    int minutesCount = minutes.getValue();
                    //System.out.println(actName+" for "+hoursCount+" h and "+minutesCount+" minutes");
                    int finalminutes = (hoursCount * 60) + minutesCount;
                    try{
                    switch (actName) {

                        case "Swimming":
                            ahmad.play(swimming, finalminutes);
                            break;
                        case "Running":
                            ahmad.play(running, finalminutes);
                            break;
                        case "KickBoxing":
                            ahmad.play(kickBoxing, finalminutes);
                            break;
                        case "Strength Training":
                            ahmad.play(strengthTraining, finalminutes);
                            break;
                        default:
                            break;
                    }}
                    catch (NullPointerException e1)
                    {
                        if (i==0&&gridPaneNodes.size()==2||i==gridPaneNodes.size()-1)
                            break;
                        else
                            continue;
                    }

                }
            }

            calroiesBurntLabel.setText("Total Calories Burnt: "+ahmad.getCalories()+" Calories");
            heartBeatLabel.setText("Total Heart Rate: "+deci.format(ahmad.getHearBeat())+" bpm");
            results.getStyleClass().add("results-Label");
            finalPane.getRight().setVisible(true);
            //Scene 2:-
            //The Table view
            //Name Column
            TableColumn<Activity, String> nameColumn = new TableColumn<>("Activity");
            nameColumn.setMinWidth(200);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

            //Calories Column
            TableColumn<Activity, Integer> caloriesColumn = new TableColumn<>("Calories Burnt");
            caloriesColumn.setMinWidth(200);
            caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calroiesBurnedBy"));

            //Heart Beat Column
            TableColumn<Activity, Double> heartColumn = new TableColumn<>("Heart Rate Increase");
            heartColumn.setMinWidth(200);
            heartColumn.setCellValueFactory(new PropertyValueFactory<>("heartRateIncrease"));

            //Setting Table
            ranks=new TableView<>();
            ranks.setItems(ahmad.printRanks());
            ranks.getColumns().addAll(nameColumn,caloriesColumn,heartColumn);
            ranks.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//BACK button
            backButton=new Button("Back");
            BorderPane sceneBorderPane=new BorderPane();
            sceneBorderPane.setCenter(ranks);
            sceneBorderPane.setTop(backButton);
            backButton.setAlignment(Pos.TOP_LEFT);
            backButton.setOnAction(e1->
                    window.setScene(scene) );
            sceneBorderPane.getStyleClass().add("background-pane");
            scene2=new Scene(sceneBorderPane,1000,500);
            scene2.getStylesheets().add("Scene2.css");


        });
        ranksButton.setOnAction(e->
                window.setScene(scene2));

        scene = new Scene(finalPane, 1000, 500);
        scene.getStylesheets().add("Style.css");
        window.resizableProperty().set(false);
        window.setScene(scene);
        window.show();

    }

    public HBox creatActivity() {
//Function to creat activity--
        final Spinner<Integer> minutes = new Spinner<Integer>();
        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 59);
        minutes.setValueFactory(minuteValueFactory);
        minutes.setMaxWidth(70);
        final Spinner<Integer> hours = new Spinner<Integer>();
        SpinnerValueFactory<Integer> hoursValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        hours.setValueFactory(hoursValueFactory);
        hours.setMaxWidth(70);
        Label lhours = new Label("Hours");
        Label lminutes = new Label("Minutes");
        lhours.setLabelFor(hours);
        lminutes.setLabelFor(minutes);
        ComboBox<String> activities = new ComboBox<>();
        activities.setMinWidth(200);
        activities.setMaxWidth(200);
        activities.getItems().addAll("Swimming", "Running", "KickBoxing", "Strength Training");
        activities.setPromptText("Sport");
        HBox layout = new HBox(10);
        layout.getChildren().addAll(activities, hours, lhours, minutes, lminutes);
        return layout;
    }

    public static void main(String[] args) {
        launch(args);


    }
}
