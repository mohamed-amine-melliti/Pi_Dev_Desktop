/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Melliti
 */
public class Categorie {
    private int idCategorie;
    private String label;

    public Categorie(int idCategorie, String label) {
        this.idCategorie = idCategorie;
        this.label = label;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    
    public int getIdCategorie() {
        return idCategorie;
    }

    public String getLabel() {
        return label;
    }
    
}
