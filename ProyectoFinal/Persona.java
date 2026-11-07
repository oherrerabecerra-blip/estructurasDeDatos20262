public abstract class Persona {
    protected String nombre;
    protected String id;
    protected String email;

    public Persona(String nombre, String id, String email) {
        this.nombre = nombre;
        this.id = id;
        this.email = email;
    }

    public abstract void mostrarInformacion();

    public String getNombre() { return nombre; }
    public String getId() { return id; }
    public String getEmail() { return email; }
}
