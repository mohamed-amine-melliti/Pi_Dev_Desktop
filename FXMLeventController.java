/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yerabbyyyy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLeventController implements Initializable {

    @FXML
    private Button id1;
    @FXML
    private Button id3;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterAction(ActionEvent event) throws IOException {
           Parent tableview = FXMLLoader.load(getClass().getResource("AjouterEvent1.fxml"));
    
    
   
       Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
        
    }

   

    @FXML
    private void AfficherAction(ActionEvent event) throws IOException {
          Parent tableview = FXMLLoader.load(getClass().getResource("AfficherEvent1.fxml"));
    
    
   
       Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void btnRetourAction(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("clubs1.fxml"));
    
    
   
       Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    private void ParticiperAction(ActionEvent event) throws IOException {
              Parent tableview = FXMLLoader.load(getClass().getResource("Reservation.fxml"));
    
    
   
       Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }


   
    
}
