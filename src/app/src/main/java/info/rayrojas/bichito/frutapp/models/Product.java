package info.rayrojas.bichito.frutapp.models;

import java.util.ArrayList;

public class Product {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ArrayList<String> getProductsAsString() {
        ArrayList<String> o = new ArrayList<>();
        o.add("Granolas");
        o.add("Galletas Integrales");
        o.add("Granolas con Miel");
        o.add("Granolas de Trigo");
        o.add("Salvado de Trigo");
        o.add("Quiwicha con miel");
        o.add("Miel de eucalipto");
        o.add("Pecanas");
        o.add("Avellanas");
        o.add("Mani");
        o.add("Frutos secos");
        o.add("Almendras");
        o.add("Leche de Almendras");
        o.add("Leche de soja");
        o.add("Colageno");
        o.add("Galle de algarrobina");
        o.add("Galleta integral");
        return o;
    }

}
