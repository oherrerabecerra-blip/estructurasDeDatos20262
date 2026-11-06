package ProyectoFinal;

public class Campus {
    private String nombre;
    public Campus(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void mostrarInformacion() {
        System.out.println("Nombre del campus: " + nombre);
    }
}
