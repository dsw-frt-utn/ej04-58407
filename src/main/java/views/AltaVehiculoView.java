package views;

import data.Persistencia;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AltaVehiculoView extends JFrame {
    private JTextField txtPatente, txtMarca, txtModelo, txtAnio, txtCapacidad;
    private JComboBox<String> cmbTipo;
    private JTextField txtKwhBase, txtKmLitro, txtLitrosExtra;
    private JButton btnGuardar;

    public AltaVehiculoView() {
        setTitle("Alta de Vehículo");
        setSize(450, 500);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(new GridLayout(10, 2, 10, 15)); // Grilla para acomodar los campos

        // Creación y agregado de los campos al formulario
        add(new JLabel(" Patente:")); 
        txtPatente = new JTextField(); add(txtPatente);
        
        add(new JLabel(" Marca:")); 
        txtMarca = new JTextField(); add(txtMarca);
        
        add(new JLabel(" Modelo:")); 
        txtModelo = new JTextField(); add(txtModelo);
        
        add(new JLabel(" Año:")); 
        txtAnio = new JTextField(); add(txtAnio);
        
        add(new JLabel(" Cap. Carga (kg):")); 
        txtCapacidad = new JTextField(); add(txtCapacidad);
        
        add(new JLabel(" Tipo de Vehículo:")); 
        cmbTipo = new JComboBox<>(new String[]{"ELÉCTRICO", "COMBUSTIBLE"});
        add(cmbTipo);

        add(new JLabel(" KWh Base (solo Eléctrico):")); 
        txtKwhBase = new JTextField(); add(txtKwhBase);
        
        add(new JLabel(" Km/Litro (solo Combustible):")); 
        txtKmLitro = new JTextField(); add(txtKmLitro);
        
        add(new JLabel(" Litros Extra (solo Combustible):")); 
        txtLitrosExtra = new JTextField(); add(txtLitrosExtra);

        add(new JLabel("")); // Espacio vacío en la grilla
        btnGuardar = new JButton("Guardar Vehículo");
        add(btnGuardar);

        // Acción del botón guardar
        btnGuardar.addActionListener(this::guardarVehiculo);
    }

    private void guardarVehiculo(ActionEvent e) {
        try {
            String patente = txtPatente.getText();
            String marca = txtMarca.getText(); 
            String modelo = txtModelo.getText();
            int anio = Integer.parseInt(txtAnio.getText());
            double capacidad = Double.parseDouble(txtCapacidad.getText());
            
            // Se crea una sucursal por defecto para poder instanciar el vehículo
            Responsable resp = new Responsable("Generico", "00000000", "000");
            Sucursal sucursalNueva = new Sucursal("SUC-03", "Av. Siempre Viva 123", "Tucumán", resp);

            if (cmbTipo.getSelectedIndex() == 0) { 
                // Lógica si es Eléctrico
                double kwh = Double.parseDouble(txtKwhBase.getText());
                VehiculoElectrico ve = new VehiculoElectrico(patente, marca, modelo, anio, capacidad, sucursalNueva, kwh);
                Persistencia.getVehiculos().add(ve);
            } else { 
                // Lógica si es Combustible
                double kmL = Double.parseDouble(txtKmLitro.getText());
                double litExt = Double.parseDouble(txtLitrosExtra.getText());
                VehiculoCombustible vc = new VehiculoCombustible(patente, marca, modelo, anio, capacidad, sucursalNueva, kmL, litExt);
                Persistencia.getVehiculos().add(vc);
            }

            JOptionPane.showMessageDialog(this, "Vehículo guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // Cierra la ventana actual
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, verificá que los campos de Año, Capacidad, Kwh, Km/L y Litros Extra contengan números válidos.", "Error de carga", JOptionPane.ERROR_MESSAGE);
        }
    }
}