package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.Modelo;
import agenda.modelo.excepciones.ClientNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;

public class PanelClientes extends JPanel {
    JTable tbl;
    JScrollPane panel;
    Controlador controlador;
    Modelo modelo;
    JTextArea areaDatos =new JTextArea(20,10);

    public PanelClientes(Controlador controlador, Modelo modelo) {
        this.controlador = controlador;
        this.modelo = modelo;

        // JButton button = new JButton("Añadir Cliente");
        JButton bBuscarCliente = new JButton("Buscar Cliente");
        JButton bAñadirCliente = new JButton("Añadir Cliente");
        JButton bSave = new JButton("Guardar");
        JButton bBorrarCliente = new JButton("Borrar Cliente ");
        JButton bEditarCliente = new JButton("Editar Tarifa ");
        JButton bListarEntre2 = new JButton("Listar entre dos Fechas");

        JLabel borrar = new JLabel("DNI del cliente: ");
        JTextField dniCliente = new JTextField(10);



        JPanel panelBorrar = new JPanel();
        panelBorrar.add(borrar);
        panelBorrar.add(dniCliente);
        panelBorrar.add(bBorrarCliente);

        Vector cabecera = new Vector();
        cabecera.add("NIF");
        cabecera.add("Nombre");
        cabecera.add("Dirección");
        cabecera.add("Correo");
        cabecera.add("Tarifa");
        cabecera.add("Fecha");

        Vector datos = modelo.informacionClientes(modelo.getClientes());

        JScrollPane panel = new JScrollPane(areaDatos);
        panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rellenarInformacion(datos);

        bBuscarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new FormularioBuscarCliente(controlador);
                controlador.devolverCliente(dniCliente.getText());
            }
        });


        bAñadirCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Creando cliente...");
                new FormularioCliente(controlador);
            }
        });
        bEditarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Editar cliente...");
                new FormularioEditarTarifa(controlador);
            }
        });
        bListarEntre2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FormularioListarEntre2Fechas(controlador);
            }
        });

        bBorrarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Borrando cliente...");
                if (dniCliente.getText().length() > 0) {
                    try {

                        controlador.eliminarCliente(dniCliente.getText());
                        dniCliente.setText("");
                        Vector datos = modelo.informacionClientes(modelo.getClientes());

                        rellenarInformacion(datos);

                    } catch (ClientNotFound clientNotFound) {
                        clientNotFound.printStackTrace();
                    }
                } else {System.out.printf("Dni no introducido");
                    new PopUp("DNI no introducido");
                }
            }

        });
        bSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Guardando Datos...");
                try {
                    controlador.guardarDatos();
                //    actualizarTabla(tabla,datos);
                    Vector datos = modelo.informacionClientes(modelo.getClientes());

                    rellenarInformacion(datos);


                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        //Añadimos los elementos
        Container contenedor = new Container();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JPanel panelOption = new JPanel();

        panelOption.add(bBuscarCliente);
        panelOption.add(bAñadirCliente);
        panelOption.add(panelBorrar);
        panelOption.add(bEditarCliente);
        panelOption.add(bListarEntre2);
        panelOption.add(bSave);

        contenedor.add(panelOption);
        contenedor.add(panel);
        add(contenedor);

        areaDatos.setForeground(Color.BLACK);
        areaDatos.setEditable(false);



    }
    public void rellenarInformacion(Vector datos){
        areaDatos.setText("");
        areaDatos.append("NIF\tNombre\tCP\tProvincia\tPoblación\tCorreo\tTarifa\tFecha\n");

        for(int i=0; i<datos.size();i++){
            Vector dades=(Vector) datos.get(i);
            areaDatos.append(dades.get(0)+"\t"+dades.get(1)+"\t"+dades.get(2)+"\t"+dades.get(3)+"\t"+dades.get(4)+"\t"+dades.get(5)+"\n");
        }

    }

}