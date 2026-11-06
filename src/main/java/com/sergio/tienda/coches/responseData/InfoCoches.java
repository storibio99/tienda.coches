package com.sergio.tienda.coches.responseData;


import java.util.List;
import java.util.Map;

//tipo de dato para devolver los coches
//y su total para el js mostrar_videojuegos
public class InfoCoches {

    private List<Map<String, Object>> coches;
    private int totalCoches;


    public InfoCoches() {
    }

    public List<Map<String, Object>> getCoches() {
        return coches;
    }

    public void setCoches(List<Map<String, Object>> coches) {
        this.coches = coches;
    }

    public int getTotalCoches() {
        return totalCoches;
    }

    public void setTotalCoches(int totalCoches) {
        this.totalCoches = totalCoches;
    }
}
