package Tarea_Sockets_JavierContrerasLopez_JeaustinMoralesHidalgo.src;

public class TCPClienteApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            TCPClienteGUI cliente = new TCPClienteGUI();
            cliente.setVisible(true);
        });
    }
}

