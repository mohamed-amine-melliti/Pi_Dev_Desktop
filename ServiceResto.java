/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;
import entities.Resto;
import utils.ConnexionBD;

/**
 *
 * @author ASUS
 */

public class ServiceResto {
    
    Connection c = ConnexionBD.getInstance().getCnx();
    
    
    public void ajouterResto(Resto r){
    /* try{
       Statement st = c.createStatement();
        String req="insert into personne values("'+p.getId()+' ","'+p.getNom()+'","'+p.getPrenom()+'")" ;
        
        st.executeUpdate(req);
     }
          catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }*/
     try{
    String req = "INSERT INTO `resto` (idR,typeA,effectif,responsableResto,etat)" + " VALUES (?,?,?,?,?)";
        PreparedStatement pre = c.prepareStatement(req);
        pre.setInt(1,r.getIdR() );
        pre.setString(2, r.getTypeA());
        pre.setInt(3, r.getEffectif());
        pre.setInt(4, r.getResponsableResto());
        pre.setBoolean(5, r.getEtat());
       
        pre.executeUpdate();
     }
      catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
     
       }
    }
     
      

      
 
       
       public void modifierResto(Resto r,String typeA) {
           try{
        PreparedStatement pt=c.prepareStatement("update resto set typeA= ? where idR = ?");
        pt.setString(1,typeA);
        pt.setInt(2,r.getIdR());
        pt.executeUpdate() ;
           }
             catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
        
       }
       
       public void incrementerEffectif(String typer)
       {
           try{
              PreparedStatement pt=c.prepareStatement("select effectif from  resto where typeA = '" + typer + "';"  );
                ResultSet rs=pt.executeQuery();

                
               
             
            while(rs.next()) { //identifier de id=1
                 System.out.println("Resto [ effectif : "+rs.getInt(1) + " ]");
                 int effectif= rs.getInt(1) +1 ;
        PreparedStatement ptt=c.prepareStatement("update resto set effectif= ? where  typeA = '" + typer + "';");
        ptt.setInt(1,effectif);
        ptt.executeUpdate() ;
             }
              
           }
           
               catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
          
              
       }
     public void afficherRestos(){
         try{
           PreparedStatement pt=c.prepareStatement("select * from resto");
           ResultSet rs=pt.executeQuery();
         
             
             while(rs.next()) { //identifier de id=1
                 System.out.println("Resto [ id : "+rs.getInt(1)+ " type : " +rs.getString(2) + " effectif : " + rs.getInt(3) +  " responsableResto : " + rs.getInt(4) +  " etat : " + rs.getBoolean(5) + " ]");
             }
         }
           catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
             
         
     }
     
     public void supprimerResto(Resto r){
         try{
         PreparedStatement pt= c.prepareStatement("delete from resto where idR =? ") ;
         pt.setInt(1,r.getIdR());
         pt.executeUpdate();
         }
           catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
         
         
     }
        
        
 
    
}

    

