public class ArreglosAcademicos {
    private Estudiante[] estudiantes = new Estudiante[100];
    private int cantidad = 0;

    public void registrar(Estudiante estudiante) {
        if (cantidad < estudiantes.length) {
            estudiantes[cantidad++] = estudiante;
            System.out.println("Estudiante registrado.");
        } else {
            System.out.println("El arreglo esta lleno.");
        }
    }

    public Estudiante buscar(String id) {
        for (int i = 0; i < cantidad; i++) {
            if (estudiantes[i].getId().equals(id)) return estudiantes[i];
        }
        return null;
    }

    public void listar() {
        if (cantidad == 0) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        for (int i = 0; i < cantidad; i++) {
            estudiantes[i].mostrarInformacion();
            System.out.println("--------------------");
        }
    }

    public boolean eliminar(String id) {
        for (int i = 0; i < cantidad; i++) {
            if (estudiantes[i].getId().equals(id)) {
                for (int j = i; j < cantidad - 1; j++) {
                    estudiantes[j] = estudiantes[j + 1];
                }
                estudiantes[--cantidad] = null;
                return true;
            }
        }
        return false;
    }

    public int getCantidad() { return cantidad; }

    public Estudiante obtener(int posicion) {
        if (posicion < 0 || posicion >= cantidad) return null;
        return estudiantes[posicion];
    }
}
