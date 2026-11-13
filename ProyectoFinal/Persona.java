package ProyectoFinal;
public abstract class Persona {
    private String id;
    private String nombre;
    private String email;
    public Persona(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }
    public String getId() {
         return id;
         }
    public String getNombre() {
         return nombre; 
        }
    public String getEmail() { 
        return email; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre;
     }
    public void setEmail(String email) {
         this.email = email; 
        }
    public abstract void mostrarInformacion();
}