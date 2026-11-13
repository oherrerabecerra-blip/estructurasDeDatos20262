package ProyectoFinal;
public class Campus {
    private String nombre;
    public Campus(String nombre) {
        if (nombre == null || nombre.isBlank()) 
            throw new IllegalArgumentException("Nombre invalido.");
        this.nombre = nombre;
    }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) 
            throw new IllegalArgumentException("Nombre invalido.");
        this.nombre = nombre;
    }
}
