package agenda;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Factura implements Serializable {
    int tipoTarifa;
    static int cod=0;
    LocalDate inicio;
    LocalDate fin;
    LocalDate emision;
    Double importe=0.0;


    public Factura(Cliente cliente, LocalDate inico, LocalDate fin) {

        this.tipoTarifa = cliente.tipoTarifa;
        this.cod += 1;
        this.inicio = inico;
        this.fin = fin;
        this.importe = importe(cliente, inicio, fin);
        this.emision = LocalDate.now();
    }

    public static int getCod() {
        return cod;
    }

    public Double getImporte() {
        if (importe==null) return 0.0;
        return importe;
    }

    public Double importe(Cliente cliente, LocalDate inicio, LocalDate fin) {
        double importe=0; //€/min
        if (cliente==null || cliente.llamadas== null) return 0.0;
        for (Llamada llamada : cliente.llamadas) {
            //si es igual a inicio o a fin o posterior a inicio o anteror a fin.
            if((llamada.fechaLlamada.equals(inicio)  || llamada.fechaLlamada.isAfter(inicio)) && (llamada.fechaLlamada.equals(fin) || llamada.fechaLlamada.isBefore(fin))){
                importe += cliente.tipoTarifa * llamada.duracion;
            }
        }
        return importe;
    }

    @Override
    public String toString() {
        return "Factura :" +
                "tipoTarifa=" + this.tipoTarifa +
                ", cod=" + this.cod +
                ", inicio=" + this.inicio +
                ", fin=" + this.fin +
                ", importe=" + this.importe;
    }

}
