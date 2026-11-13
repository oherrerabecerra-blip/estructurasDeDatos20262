package ProyectoFinal;
public class RegistroFacultades {
    private Facultad[] facultades = new Facultad[5];
    private int cantidad = 0;
    public boolean agregar(Facultad facultad) {
        if (facultad == null || cantidad == facultades.length) return false;
        for (int i = 0; i < cantidad; i++)
            if (facultades[i].getCodigo().equalsIgnoreCase(facultad.getCodigo())) return false;
        facultades[cantidad++] = facultad;
        return true;
    }
    public Facultad buscar(String codigo) {
        for (int i = 0; i < cantidad; i++)
            if (facultades[i].getCodigo().equalsIgnoreCase(codigo)) return facultades[i];
        return null;
    }
    public void listar() {
        if (cantidad == 0) System.out.println("No hay facultades registradas.");
        for (int i = 0; i < cantidad; i++) System.out.println(facultades[i]);
    }
}