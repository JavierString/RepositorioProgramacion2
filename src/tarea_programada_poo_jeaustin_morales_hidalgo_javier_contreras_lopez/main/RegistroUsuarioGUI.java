package tarea_programada_poo_jeaustin_morales_hidalgo_javier_contreras_lopez.main;

import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
public class RegistroUsuarioGUI extends JFrame{
//atributod privados para ingresar los datos en el panel
    private JTextField nombre, telefono, correo, cedula;
    private JTextArea areaConsulta;
    private JTextField cedulaBuscar, cedulaEliminar;
    private JButton botonRegistrar, botonConsultar, botonActualizar, botonEliminar;
        //psnel que se enseña al usuario
    public RegistroUsuarioGUI() {
        setTitle("Sistema de Usuarios - Tránsito CR");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Registrar", crearPanelRegistro());
        tabs.add("Consultar", crearPanelConsulta());
        tabs.add("Actualizar", crearPanelActualizar());
        tabs.add("Eliminar", crearPanelEliminar());

        add(tabs);
    }
    //metodo que hace que se enseñe el formulario en donde se llenaran los campos para el usuario
    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nombre = new JTextField();
        telefono = new JTextField();
        correo = new JTextField();
        cedula = new JTextField();

        botonRegistrar = new JButton("Registrar Usuario");
        botonRegistrar.addActionListener(this::registrarUsuario);

        panel.add(new JLabel("Nombre completo:"));
        panel.add(nombre);
        panel.add(new JLabel("Teléfono:"));
        panel.add(telefono);
        panel.add(new JLabel("Correo:"));
        panel.add(correo);
        panel.add(new JLabel("Cédula:"));
        panel.add(cedula);
        panel.add(new JLabel());
        panel.add(botonRegistrar);

        return panel;
    }
    //metodo que crea el panel de consultar 
    private JPanel crearPanelConsulta() {
        JPanel panel = new JPanel(new BorderLayout());
        areaConsulta = new JTextArea();
        areaConsulta.setEditable(false);
        botonConsultar = new JButton("Cargar Usuarios");

        botonConsultar.addActionListener(e -> {
            try {
                areaConsulta.setText(ManejadorUsuarios.leerUsuariosComoTexto());
            } catch (IOException ex) {
                mostrarError("Error al leer archivo.");
            }
        });

        panel.add(new JScrollPane(areaConsulta), BorderLayout.CENTER);
        panel.add(botonConsultar, BorderLayout.SOUTH);

        return panel;
    }
    //método que crea el panel para actualizar 
    private JPanel crearPanelActualizar() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField nuevoNombre = new JTextField();
        JTextField nuevoTelefono = new JTextField();
        JTextField nuevoCorreo = new JTextField();
        cedulaBuscar = new JTextField();
        //boton que al presionar, nos permite actualizar un usuario
        botonActualizar = new JButton("Actualizar Usuario");
        botonActualizar.addActionListener(e -> {
         //Este bloque actualiza un usuario según su cédula y muestra un mensaje según el resultado o si ocurre un error.

            try {
                boolean actualizado = ManejadorUsuarios.actualizarUsuario(
                        cedulaBuscar.getText().trim(),
                        nuevoNombre.getText().trim(),
                        nuevoTelefono.getText().trim(),
                        nuevoCorreo.getText().trim()
                );
                mostrarMensaje(actualizado ? "Usuario actualizado." : "Usuario no encontrado.");
            } catch (IOException ex) {
                mostrarError("Error al actualizar.");
            }
        });
        //campos que crea los campos a rellenar con los datos actualizados
        panel.add(new JLabel("Cédula del usuario a actualizar:"));
        panel.add(cedulaBuscar);
        panel.add(new JLabel("Nuevo nombre:"));
        panel.add(nuevoNombre);
        panel.add(new JLabel("Nuevo teléfono:"));
        panel.add(nuevoTelefono);
        panel.add(new JLabel("Nuevo correo:"));
        panel.add(nuevoCorreo);
        panel.add(new JLabel());
        panel.add(botonActualizar);

        return panel;
    }
    //método que crea el panel para actualizar 
    private JPanel crearPanelEliminar() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        cedulaEliminar = new JTextField();
        botonEliminar = new JButton("Eliminar Usuario");

        botonEliminar.addActionListener(e -> {
            try {
                boolean eliminado = ManejadorUsuarios.eliminarUsuario(cedulaEliminar.getText().trim());
                mostrarMensaje(eliminado ? "Usuario eliminado." : "Usuario no encontrado.");
            } catch (IOException ex) {
                mostrarError("Error al eliminar.");
            }
        });
        
        panel.add(new JLabel("Cédula del usuario a eliminar:"));
        panel.add(cedulaEliminar);
        panel.add(new JLabel());
        panel.add(botonEliminar);

        return panel;
    }

    private void registrarUsuario(ActionEvent e) {
        String nom = nombre.getText().trim();
        String telf = telefono.getText().trim();
        String corr = correo.getText().trim();
        String cedu = cedula.getText().trim();
      
        //esta cadena de sentencias permiten que el usuario tenga que agregar todos los datos
        //también que se digiten el telefono, cedula y correo en el formato indicado
        if (nom.isEmpty() || telf.isEmpty() || corr.isEmpty() || cedu.isEmpty()) {
            mostrarError("Todos los campos son obligatorios.");
            return;
        }

        if (!corr.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            mostrarError("Correo inválido.");
            return;
        }

        if (!telf.matches("\\d{8,}")) {
            mostrarError("Teléfono inválido.");
            return;
        }

        if (!cedu.matches("\\d{9,}")) {
            mostrarError("Cédula inválida.");
            return;
        }
        //aqui se crea un nuevo objeto usuario con su respectivo 
        String clave = GeneradorClave.generarClave(8);
        Usuario nuevoUsuario = new Usuario(nom, telf, corr, cedu, clave);

        try {
            ManejadorUsuarios.guardarUsuario(nuevoUsuario);

            JOptionPane.showMessageDialog(this, nuevoUsuario.obtenerDatosUsuario(), "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

            nombre.setText("");
            telefono.setText("");
            correo.setText("");
            cedula.setText("");

        } catch (IOException excepcion) {
            mostrarError("No se pudo guardar el usuario: " + excepcion.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
