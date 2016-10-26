package com.Gui;

import com.Head.GameModel;
import com.Pieces.Piece;
import com.Utility.IObserver;
import com.Utility.Log;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Thinh-Laptop on 13.09.2016.
 */
public class Controller implements IObserver {

    @FXML
    private GridPane grid;

    @FXML
    private Label PointLabel;

    private Stage mPrimaryStage ;
    private final static int CircleSize = 20 ;
    private Circle[][] mCircleTable = {{new Circle(CircleSize),new Circle(CircleSize),new Circle(CircleSize),new Circle(CircleSize)},
            {new Circle(CircleSize),new Circle(CircleSize),new Circle(CircleSize),new Circle(CircleSize)},
            {new Circle(CircleSize),new Circle(CircleSize),new Circle(CircleSize),new Circle(CircleSize)},
            {new Circle(CircleSize),new Circle(CircleSize),new Circle(CircleSize),new Circle(CircleSize)}};

    private int x1=-1,x2=-1,y1=-1,y2 = -1 ;

    final Lock newCoord = new ReentrantLock() ;
    private Condition waitForCoord = newCoord.newCondition() ;

    public void initialize(Stage primaryStage) throws IOException {
        this.mPrimaryStage = primaryStage ;

        /*BackgroundImage myBI= new BackgroundImage(new Image("E:/Git Repos/ProjectJewelery/src/com/Gui/background.jpg",32,32,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        grid.setBackground(new Background(myBI));*/



        for (int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                grid.add(mCircleTable[i][j],i,j);
                grid.setHalignment(mCircleTable[i][j], HPos.CENTER);

            }
        }
    }

    @Override
    synchronized public void update(GameModel model) {


            Platform.runLater(() -> {

                Piece[][] board = model.getBoard();

                PointLabel.setText("Points: " + model.getPoints());

                for (int i = 0; i < model.getRows(); i++) {
                    for (int j = 0; j < model.getColumns(); j++) {
                        switch (board[i][j].getKind()) {
                            case BLUE:
                                mCircleTable[j][i].setFill(Color.BLUE);
                                break;
                            case RED:
                                mCircleTable[j][i].setFill(Color.RED);
                                break;
                            case GREEN:
                                mCircleTable[j][i].setFill(Color.GREEN);
                                break;
                            case WHITE:
                                mCircleTable[j][i].setFill(Color.WHEAT);
                                break;
                            case BLACK:
                                mCircleTable[j][i].setFill(Color.BLACK);
                                break;
                            case YELLOW:
                                mCircleTable[j][i].setFill(Color.YELLOW);
                                break;
                            case NON:
                                mCircleTable[j][i].setFill(Color.TRANSPARENT);
                                break;
                            default:
                                mCircleTable[j][i].setFill(Color.TRANSPARENT);
                                break;
                        }
                    }
                }

            });

        try {
            wait(1000);
        }catch(InterruptedException e){
            Log.debug("Update function threw InterruptedException " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public int[] userInput() {

        int x1=-1,x2=-1,y1=-1,y2 = -1 ;


        newCoord.lock();
        Platform.runLater(() -> {
            getCoord();
        });

            try {
                while(this.x1 == -1)
                    waitForCoord.await();
                newCoord.lock();
            }catch(InterruptedException e){
                Log.debug("Condition waitForCoord got interrupted");
            }

                x1 = this.x1;
                y1 = this.y1;

        this.x1 = -1 ;

        try {
            while(this.x1 == -1)
                waitForCoord.await();
            newCoord.lock();
        }catch(InterruptedException e){
            Log.debug("Condition waitForCoord got interrupted");
        }

        x2 = this.x1;
        y2 = this.y1;


        this.x1 = -1;
        this.x2 = -1 ;
        this.y1 = -1 ;
        this.y2 = -1 ;


        int[] result = {x1,y1,x2,y2};
        return result;
    }

    private void getCoord(){

        for (int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < 4 ; j++){

                final int iEvent = i ;
                final int jEvent = j ;
                mCircleTable[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        newCoord.lock();
                        Node source = mCircleTable[iEvent][jEvent];

                        Integer colIndex = GridPane.getColumnIndex(source);
                        Integer rowIndex = GridPane.getRowIndex(source);
                        Log.info("Received Information on cell (" + colIndex +","+rowIndex+")");

                        int[] result = {rowIndex,colIndex};

                        x1 = rowIndex ;
                        y1 = colIndex;

                        waitForCoord.signal();
                        newCoord.unlock();
                    }
                });
            }
        }

    }
}
