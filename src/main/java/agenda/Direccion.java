package agenda;

public class Direccion {
    int CP;
    String provincia;
    String poblacion;

    public Direccion(int CP, String provincia, String poblacion) {
        this.CP = CP;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "CP=" + CP +
                ", provincia='" + provincia + '\'' +
                ", poblacion='" + poblacion + '\'' +
                '}';
    }
}
