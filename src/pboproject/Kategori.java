/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pboproject;

/**
 *
 * @author Makhluk Hidup
 */
public class Kategori {
    int id_type;
    String kategori;

    public Kategori(int id_type, String kategori) {
        this.id_type = id_type;
        this.kategori = kategori;
    }

    public int getId_type() {
        return id_type;
    }

    public String getKategori() {
        return kategori;
    }
    
    
}
