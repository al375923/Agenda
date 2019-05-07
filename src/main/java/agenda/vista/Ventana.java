package agenda.vista;

import agenda.controlador.GestionarAgenda;

import javax.swing.*;
import java.awt.*;

public class Ventana {
    GestionarAgenda controlador;//=new GestionarAgenda();
    JFrame ventana=new JFrame("Agenda");//Creamos el JFrame

    public void setControlador(GestionarAgenda gestorAgenda) {
        this.controlador = gestorAgenda;
    }

    public void run() throws ClassNotFoundException {
        controlador.cargarDatos();

        //Modificamos el icono de la ventana
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/icono.png"); //Creamos una IMAGE
        ventana.setIconImage(icono); //Añadimos la IMAGE creada
        JTabbedPane pestanyas = new JTabbedPane();
        pestanyas.add("Clentes", new PanelClientes());

        pestanyas.add("Facturas", new PanelFacturas());
        pestanyas.add("Llamadas", new PanelLlamadas());

        ventana.add(pestanyas);
        ventana.pack();
        // ventana.setSize(800,500);//Definimos el tamaño
        ventana.setVisible(true);// hacemos la ventsana visibel

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
