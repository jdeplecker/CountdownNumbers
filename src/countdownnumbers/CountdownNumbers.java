/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countdownnumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Joeri
 */
public class CountdownNumbers extends Application {
    
    Random rnd = new Random();
    
    
    @Override
    public void start(Stage primaryStage) {
        defaultStart(primaryStage);
        int target = generateTarget();
        ArrayList<Integer> question = generateQuestion(4);
        System.out.println("Target: " + target);
        System.out.println("Question: " + question);
        
        long timerstart = System.currentTimeMillis();
        solve(target, question);
        System.out.println("Time taken: " + ((double)System.currentTimeMillis()-timerstart)/1000 + "s");
    }

    
    public void defaultStart(Stage primaryStage){
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public ArrayList<Integer> generateQuestion(int bignumcount){
        Integer[] a = {25,50,75,100}; 
        ArrayList<Integer> bignumbers= new ArrayList<>(Arrays.asList(a)); 
        ArrayList<Integer> result = new ArrayList<>();
        
        int i = 0;
        while (i < bignumcount && i<4) {
            result.add(bignumbers.remove(rnd.nextInt(bignumbers.size())));
            i++;
        }
        while (i<6) {
            result.add(rnd.nextInt(9)+1);
            i++;
        }
        
        return result;       
    }
    
    public int generateTarget(){
        return rnd.nextInt(899)+100;
    }
    
    
    public void solve(int target, ArrayList<Integer> question){
        CountdownRecursive recursive = new CountdownRecursive(question);
        recursive.solve(target);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
