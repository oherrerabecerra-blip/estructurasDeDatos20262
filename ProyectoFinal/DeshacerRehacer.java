package ProyectoFinal;
import java.util.ArrayDeque;
import java.util.Deque;
public class DeshacerRehacer {
    private Deque<CambioNotas> deshacer = new ArrayDeque<>();
    private Deque<CambioNotas> rehacer = new ArrayDeque<>();
    public void registrar(CambioNotas cambio) {
        deshacer.push(cambio);
        rehacer.clear();
        }
    public CambioNotas obtenerDeshacer() {
        if (deshacer.isEmpty()) return null;
        CambioNotas c = deshacer.pop(); rehacer.push(c); 
        return c;
        }
    public CambioNotas obtenerRehacer() {
        if (rehacer.isEmpty()) return null;
        CambioNotas c = rehacer.pop(); deshacer.push(c);
        return c;
        }
    }