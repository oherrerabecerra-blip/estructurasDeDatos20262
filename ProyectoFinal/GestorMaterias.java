package ProyectoFinal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class GestorMaterias {
    private HashMap<String, Materia> materias = new HashMap<>();
    private HashMap<String, ColaEspera> esperas = new HashMap<>();
    public void crearMateria(String codigo, String nombre, int cupo) {
        if (materias.containsKey(codigo)){
             System.out.println("La materia ya existe."); 
             return; 
            }
        materias.put(codigo, new Materia(codigo, nombre, cupo));
        esperas.put(codigo, new ColaEspera());
        System.out.println("Materia creada.");
        }
    public List<Materia> listarMaterias(){ 
        return new ArrayList<>(materias.values()); 
        }
    public Materia obtenerMateria(String codigo){
        return materias.get(codigo); 
        }
    public boolean agregarPrerequisito(String materia, String requisito){
        Materia m = materias.get(materia);
        if (m == null || !materias.containsKey(requisito) || materia.equals(requisito))
            return false;
            m.agregarPrerrequisito(requisito); 
            return true;
    }
    public boolean inscribir(String codigo, Estudiante e) {
        Materia m = materias.get(codigo);
        if (m == null || e == null) return false;
        if (m.inscribir(e)){
             System.out.println("Inscripcion realizada.");
            return true; 
            }
        if (m.getDisponibles() <= 0){
            esperas.get(codigo).encolar(e);
            System.out.println("Materia llena. Estudiante agregado a la cola de espera.");
            } else System.out.println("No fue posible realizar la inscripcion.");
                    return false;
    }
    public boolean retirar(String codigo, Estudiante e){
        Materia m = materias.get(codigo);
        if (m == null || !m.retirar(e))
            return false;
        Estudiante siguiente = esperas.get(codigo).desencolar();
        if (siguiente != null) {
            m.inscribir(siguiente);
            System.out.println("Se asigno el cupo liberado a: " + siguiente.getNombre());
        }
        return true;
    }
    public void mostrarEspera(String codigo) {
        ColaEspera c = esperas.get(codigo);
        if (c == null) System.out.println("Materia no encontrada."); else c.mostrar();
    }
}