package entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SBS
 */
public class Groupe {
    private int id;
    private String nom;
    private int nbr_enfant;
    private int id_enseignant;
    private int age;
    
    public Groupe(){
        
    }
    public Groupe(int id ,String nom ,int nbr_enfant,int id_enseignant,int age){
        this.id= id;
        this.nom=nom;
        this.nbr_enfant=nbr_enfant;
        this.id_enseignant=id_enseignant;
        this.age=age;
    }
    
    public Groupe(String nom ,int nbr_enfant,int id_enseignant,int age){
        this.nom=nom;
        this.nbr_enfant=nbr_enfant;
        this.id_enseignant=id_enseignant;
        this.age=age;
    }
    
    public int getId(){
        return id;
    }
    
    public void SetId(int id){
        this.id=id;
    }
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getNbr_enfant(){
        return nbr_enfant;
    }
    
    public void SetNbr_enfant(int nbr_enfant){
        this.nbr_enfant=nbr_enfant;
    }
    
    public int getId_enseignant() {
        return id_enseignant;
    }
    public void setId_enseignant(int id_enseignant) {
        this.id_enseignant = id_enseignant;
    }
    
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
    public  String toString(){
        return "Groupe : Id :" +id+ " Nom :" +nom+ "Nombre d'enfants :"+nbr_enfant+" Id Enseignant :"+id_enseignant+" Age : "+age;
    }
    

}
