package views;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {

    public MenuView() {
        setTitle("Menú Principal - Logística");
        setSize(300, 200);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra el programa al salir
        setLayout(new GridLayout(2, 1, 15, 15)); // Grilla para los botones

        JButton btnListar = new JButton("Listar Vehículos");
        JButton btnAgregar = new JButton("Agregar Vehículo");

        // Acción para abrir la ventana de Listar
        btnListar.addActionListener(e -> {
            ListarVehiculosView listarView = new ListarVehiculosView();
            // Cambiamos el comportamiento para que al cerrar el listado no se cierre todo el programa
            listarView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
            listarView.setVisible(true);
        });

        // Acción para abrir la ventana de Alta
        btnAgregar.addActionListener(e -> {
            AltaVehiculoView altaView = new AltaVehiculoView();
            altaView.setVisible(true);
        });

        // Agregamos un poco de margen (padding) invisible
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(btnListar);
        panel.add(btnAgregar);
        
        add(panel);
    }
}