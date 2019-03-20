package agenda.clientes;

import agenda.Direccion;
import agenda.Tarifa;

public class Particular extends Cliente {
    String apellidos;

    public Particular(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa, String apellidos) {
        super(nombre, nif, direccion, correo, tipoTarifa);
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Particular: " +
                "nombre='" + super.getNombre() + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nif='" + super.getNif() + '\'' +
                ", Direccion: " + super.getDireccion() +
                ", correo='" + super.getCorreo() + '\'' +
                ", fechaDeAlta=" + getFechaDeAlta() +
                ", tipoTarifa=" + super.getTarifa().getTipoTarifa();
    }
}
