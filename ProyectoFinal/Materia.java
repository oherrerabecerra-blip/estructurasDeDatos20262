package ProyectoFinal;
import java.util.LinkedList;
import java.util.List;
public class Materia {
    private String codigo, nombre;
    private int cupo, creditos;
    private LinkedList<Estudiante> inscritos = new LinkedList<>();
    private LinkedList<String> prerrequisitos = new LinkedList<>();
    private LinkedList<String> historialMaterias = new LinkedList<>();
    public Materia(String codigo, String nombre, int cupo){
        this(codigo, nombre, cupo, 0);
    }
    public Materia(String codigo, String nombre, int cupo, int creditos) {
        if (cupo <= 0) 
            throw new IllegalArgumentException("Cupo invalido.");
        this.codigo = codigo; this.nombre = nombre; this.cupo = cupo; this.creditos = creditos;
    }
    public String getCodigo(){
        return codigo; 
    }
    public String getNombre() { 
        return nombre;
     }
    public int getCupo() {
         return cupo;
         }
    public int getCreditos() {
         return creditos; 
        }
    public int getDisponibles() { 
        return cupo - inscritos.size(); 
    }
    public List<Estudiante> getInscritos() { 
        return new LinkedList<>(inscritos); 
    }
    public void agregarPrerrequisito(String codigo) { 
        if (!prerrequisitos.contains(codigo)) 
            prerrequisitos.add(codigo); 
    }
    public List<String> listarPrerrequisitos() { 
        return new LinkedList<>(prerrequisitos);
     }
    public void agregarHistorial(String materia) { 
        historialMaterias.add(materia); 
    }
    public List<String> getHistorialMaterias() { 
        return new LinkedList<>(historialMaterias); 
    }
    public boolean inscribir(Estudiante e) {
        if (e == null || getDisponibles() <= 0 || inscritos.contains(e)) 
            return false;
        inscritos.add(e); historialMaterias.add(nombre); e.agregarMateriaCursada(nombre);
             return true;
    }
    public boolean retirar(Estudiante e) { 
        return inscritos.remove(e);
     }
    @Override public String toString() { return codigo + " - " + nombre + " | Cupos: " + getDisponibles() + "/" + cupo; }
}
