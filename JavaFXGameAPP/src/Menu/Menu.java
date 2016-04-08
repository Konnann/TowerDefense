package Menu;

import game.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Optional;
import game.Game;


public class Menu extends Application{

    public Stage window;
    public Scene sceneMenu,sceneGame,sceneCredits;
    public StackPane root,gamePane,creditsPane;
    public Image background,gameBackground;
    public ImageView viewBackground,viewGameBackground;
    Button gameButton, exitButton,creditsButton,returnInMenuGame,returnInMenuCredits;
    VBox buttonContainer;



    @Override
    public void start(Stage primaryStage) throws Exception {
        //Window close option and style
        window = primaryStage;
        window.setTitle("My JavaFX Game");
       // window.initStyle(StageStyle.TRANSPARENT);
        window.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });

        //Creating scene
        root = new StackPane();
        gamePane = new StackPane();
        creditsPane = new StackPane();

        sceneMenu = new Scene(root,1280,720);
        sceneGame = new Scene(gamePane,1280,720);
        sceneCredits = new Scene(creditsPane,1280,720);

        //Making Button Container
        buttonContainer = new VBox(60);
        buttonContainer.setAlignment(Pos.CENTER);


        //Making Buttons
        gameButton=new Button("PLAY");
        gameButton.setOnAction(e->ButtonClicked(e));
        creditsButton = new Button("CREDITS");
        creditsButton.setOnAction(e->ButtonClicked(e));
        exitButton = new Button("EXIT");
        exitButton.setOnAction(e -> closeProgram());
        returnInMenuCredits = new Button("Return In Menu");
        returnInMenuCredits.setOnAction(e->ButtonClicked(e));
        returnInMenuGame = new Button("Return In Menu");
        returnInMenuGame.setOnAction(e->ButtonClicked(e));


        //Adding buttons in Button Container
        buttonContainer.getChildren().addAll(gameButton,creditsButton,exitButton);

        //Adding Background
        background = new Image("background.jpg",1280,720,true,false,true);
        viewBackground = new ImageView(background);
        gameBackground = new Image("gameBackgroundImage.png",1280,720,true,false,true);
        viewGameBackground = new ImageView(gameBackground);

        //Drawing shape
        Canvas behindButtons = new Canvas(600,900);
        GraphicsContext gc = behindButtons.getGraphicsContext2D();
        drawShape(gc);




        //Gather Everything
        root.getChildren().add(viewBackground);
        root.getChildren().add(behindButtons);
        root.getChildren().add(buttonContainer);
        gamePane.getChildren().add(viewGameBackground);
        gamePane.getChildren().add(returnInMenuGame);
        creditsPane.getChildren().add(returnInMenuCredits);

        //Show Scene
        window.setScene(sceneMenu);
        window.show();
    }

    private void drawShape(GraphicsContext gc){
     gc.setFill(Color.rgb(127,107,208));
        gc.fillRoundRect(200, 300, 200, 300, 20, 20);

    }

    private void closeProgram() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Do you really want to EXIT ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()==ButtonType.OK){
            window.close();

            //Also stop the thread of the game
            try {
                stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            alert.close();
        }
    }
    private void ButtonClicked(ActionEvent e){
        if (e.getSource()==gameButton){
            Game game = new Game();
            game.start();
        }else if (e.getSource()==creditsButton){
            window.setScene(sceneCredits);
        }else if (e.getSource()==returnInMenuGame||e.getSource()==returnInMenuCredits){
            window.setScene(sceneMenu);
        }
    }

    public static void menu(String[] args) {
        launch(args);
    }

}
