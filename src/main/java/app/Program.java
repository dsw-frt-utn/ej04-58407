package app;

import data.Persistencia;
import java.util.InvalidPropertiesFormatException;
import views.MenuView; // <-- IMPORTANTE: Cambiamos la importación

public class Program {
    public static void main(String[] args) throws IllegalArgumentException, InvalidPropertiesFormatException {
        Persistencia.inicializar();
        
        // Hacemos que arranque desde el Menú
        MenuView view = new MenuView();
        view.setVisible(true);
    }
}