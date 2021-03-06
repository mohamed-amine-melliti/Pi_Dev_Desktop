/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Test.Demande;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import pidev.DataBase.DataBase;
import pidev.Entite.Groupe;
import pidev.Entite.Tab_Demande;
import pidev.Service.ServiceGroupe;
import pidev.Service.ServiceTab_Demande;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class AjouterGroupeController implements Initializable {
        private Statement ste;
    private Connection con;


    @FXML
    private TextField nom;
    @FXML
    private TextField nbr_enfant;
    @FXML
    private TableView<Groupe> AffichageTabGroupe;
    @FXML
    private TableColumn<Groupe , Integer> idtab;
    @FXML
    private TableColumn<Groupe, String> nomtab;
    @FXML
    private TableColumn<Groupe, Integer> nbr_enfanttab;
    @FXML
    private TableColumn<Groupe, String> id_enseignanttab;
    @FXML
    private TableColumn<Groupe, Integer> agetab;
    @FXML
    private Button Supprimer;
    @FXML
    private TextField recherche;
    private final ObservableList<Groupe> data = FXCollections.observableArrayList();
        ServiceGroupe SG = new ServiceGroupe(); // SG = Service Groupe
    @FXML
    private Button AjouterGroupe;
    @FXML
    private TextField age;
    @FXML
    private ComboBox<String> comboens;
    ObservableList<String> listnom = FXCollections.observableArrayList();
    @FXML
    private PieChart bookChart;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Aff();
        RechercheAV();
            try {
                fillcombo();
            } catch (SQLException ex) {
                Logger.getLogger(AjouterGroupeController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }    
        public void fillcombo() throws SQLException{
                ServiceTab_Demande ser = new ServiceTab_Demande();
        List<Tab_Demande> list = ser.readens();
        ObservableList<Tab_Demande> cls = FXCollections.observableArrayList();
        for (Tab_Demande aux : list)
        {
          cls.add(new Tab_Demande(aux.getNom()));  
          listnom.add(aux.getNom());
        }
        comboens.setItems(listnom);
    }
    
     public void Aff(){
                        try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("select * from Groupe");
            while(rs.next()){
                data.add(new Groupe(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5)));
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomtab.setCellValueFactory(new PropertyValueFactory<>("nom"));
            nbr_enfanttab.setCellValueFactory(new PropertyValueFactory<>("nbr_enfant"));
            id_enseignanttab.setCellValueFactory(new PropertyValueFactory<>("enseignant"));
            agetab.setCellValueFactory(new PropertyValueFactory<>("age"));

            AffichageTabGroupe.setItems(data);
            AffichageTabGroupe.setEditable(true);
            nomtab.setCellFactory(TextFieldTableCell.forTableColumn());
            nbr_enfanttab.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            id_enseignanttab.setCellFactory(TextFieldTableCell.forTableColumn());
            agetab.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }
     
      public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Groupe> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(groupe -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (groupe.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(groupe.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Groupe> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(AffichageTabGroupe.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		AffichageTabGroupe.setItems(sortedData);
    }
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 3;
    }
            private boolean Validchampp(TextField T){
        return !T.getText().isEmpty();
    }
    @FXML
    private void AjouterGroupe(MouseEvent event) throws SQLException{
                               con = DataBase.getInstance().getConnection();
             ste = con.createStatement();
             if(Validchamp(nom)&&Validchampp(nbr_enfant)&&Validchampp(age)){
                try {
                
                    ServiceGroupe ser =new ServiceGroupe();
                    ser.ajouter(new Groupe(nom.getText(),Integer.valueOf(nbr_enfant.getText()),comboens.getValue(),Integer.valueOf(age.getText())));
                    Aff();
                    RechercheAV();
                }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
                msg.setText("Ajout avec succée");
                nom.setText("");
                nbr_enfant.setText("");
                age.setText("");
             }
             else{
                 msg.setText("Verifier vos données");
             }
    }

    @FXML
     public void Change_Nom(TableColumn.CellEditEvent bb) throws SQLException{
     Groupe tab_Groupeselected = AffichageTabGroupe.getSelectionModel().getSelectedItem();
     tab_Groupeselected.setNom(bb.getNewValue().toString());
     SG.updatetab(tab_Groupeselected);
 }

    @FXML
    private void Change_Nbenfant(TableColumn.CellEditEvent bb) throws SQLException{
     Groupe tab_Groupeselected = AffichageTabGroupe.getSelectionModel().getSelectedItem();
     tab_Groupeselected.SetNbr_enfant(Integer.parseInt(bb.getNewValue().toString()));
     SG.updatetab(tab_Groupeselected);

    }

    @FXML
    private void Change_IdEns(TableColumn.CellEditEvent bb) throws SQLException{
     Groupe tab_Groupeselected = AffichageTabGroupe.getSelectionModel().getSelectedItem();
     tab_Groupeselected.setEnseignant(bb.getNewValue().toString());
     SG.updatetab(tab_Groupeselected);
    }

    @FXML
    private void ButtonSupprimer(ActionEvent event) throws SQLException {
                 AffichageTabGroupe.setItems(data);

             ObservableList<Groupe> allDemandes,SingleDemandes ;
             allDemandes=AffichageTabGroupe.getItems();
             SingleDemandes=AffichageTabGroupe.getSelectionModel().getSelectedItems();
             Groupe A = SingleDemandes.get(0);
             ServiceGroupe STD = new ServiceGroupe(); // STD = Service TAB DEMANDE
             SG.delete(A.getId());
             SingleDemandes.forEach(allDemandes::remove);
             RechercheAV();

    }

    @FXML
    private void Change_Age(TableColumn.CellEditEvent bb) throws SQLException{
     Groupe tab_Groupeselected = AffichageTabGroupe.getSelectionModel().getSelectedItem();
     tab_Groupeselected.setAge(Integer.parseInt(bb.getNewValue().toString()));
     SG.updatetab(tab_Groupeselected);

    }
    
}
