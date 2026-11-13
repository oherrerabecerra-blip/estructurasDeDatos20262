package ProyectoFinal;
import java.util.HashMap;
public class Notas {
    private HashMap<String, RegistroNotas> estudiantes = new HashMap<>();
    private DeshacerRehacer historial = new DeshacerRehacer();
    public void crearRegistro(String id) { 
        estudiantes.putIfAbsent(id, new RegistroNotas()); 
        }
    public void agregarNota(String id, int semestre, int posicion, String materia, double nota) {
        RegistroNotas r = estudiantes.get(id);
        if (r == null) { System.out.println("No existe el registro de notas."); 
        return;
        }
        r.agregarNota(semestre, posicion, materia, nota);
        }
    public void cambiarNota(String id, int semestre, int posicion, double nueva) {
        RegistroNotas r = estudiantes.get(id);
        if (r == null) {
             System.out.println("No existe el registro de notas.");
         return;
        }
        Double anterior = r.obtenerNota(semestre, posicion);
        if (anterior == null){
            System.out.println("No existe una nota en esa posicion."); return;
        }
        r.cambiarNota(semestre, posicion, nueva);
        historial.registrar(new CambioNotas(id, semestre, posicion, anterior, nueva));
    }

    public void deshacer() {
        CambioNotas c = historial.obtenerDeshacer();
        if (c == null){
            System.out.println("No hay cambios para deshacer."); 
            return;
        }
        estudiantes.get(c.getId()).cambiarNota(c.getSemestre(), c.getPosicion(), c.getAnterior());
        System.out.println("Cambio deshecho.");
    }
    public void rehacer() {
        CambioNotas c = historial.obtenerRehacer();
        if (c == null) { System.out.println("No hay cambios para rehacer."); 
        return; 
        }
        estudiantes.get(c.getId()).cambiarNota(c.getSemestre(), c.getPosicion(), c.getNueva());
        System.out.println("Cambio rehecho.");
    }
    public void mostrar(String id) {
        RegistroNotas r = estudiantes.get(id);
        if (r == null) { System.out.println("No hay notas para ese estudiante."); 
        return; 
        }
        r.mostrarNotas();
        r.mostrarReprobadas();
    }
}