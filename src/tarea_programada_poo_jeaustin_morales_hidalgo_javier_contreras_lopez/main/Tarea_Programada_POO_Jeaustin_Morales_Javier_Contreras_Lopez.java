package tarea_programada_poo_jeaustin_morales_hidalgo_javier_contreras_lopez.main;

import javax.swing.SwingUtilities;

public class Tarea_Programada_POO_Jeaustin_Morales_Javier_Contreras_Lopez {
     public static void main(String[] args) {
//instancia de la clase RegistroUsuarioGUI para hacerla visible, con Swingutilities para hacerlo menos propenso a errores al correr
         SwingUtilities.invokeLater(() -> {
         RegistroUsuarioGUI gui = new RegistroUsuarioGUI();
         gui.setVisible(true);
    });


}
}
