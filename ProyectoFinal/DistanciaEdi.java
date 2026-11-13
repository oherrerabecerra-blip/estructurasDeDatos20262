package ProyectoFinal;
public class DistanciaEdi {
    private String[] edificios;
    private int[][] distancias;
    private int cantidad;
    public DistanciaEdi(int capacidad) {
        if (capacidad < 1) 
            throw new IllegalArgumentException("Capacidad invalida.");
        edificios = new String[capacidad];
        distancias = new int[capacidad][capacidad];
        for (int i = 0; i < capacidad; i++)
            for (int j = 0; j < capacidad; j++)
                distancias[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
    }
    public void agregarEdificio(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre invalido.");
        if (indice(nombre) != -1) return;
        if (cantidad == edificios.length) throw new IllegalStateException("No hay espacio para mas edificios.");
        edificios[cantidad++] = nombre;
    }
    public void conectar(String a, String b, int distancia) {
        int i = indice(a), j = indice(b);
        if (i < 0 || j < 0 || distancia < 0) throw new IllegalArgumentException("Conexion invalida.");
        distancias[i][j] = distancia;
        distancias[j][i] = distancia;
    }
    public int indice(String nombre) {
        for (int i = 0; i < cantidad; i++)
            if (edificios[i].equalsIgnoreCase(nombre)) return i;
        return -1;
    }
    public void mostrarMatriz() {
        System.out.println("Matriz de distancias (INF = sin conexion)");
        for (int i = 0; i < cantidad; i++) {
            for (int j = 0; j < cantidad; j++)
                System.out.print((distancias[i][j] == Integer.MAX_VALUE ? "INF" : distancias[i][j]) + "\t");
            System.out.println();
        }
    }
    public void dijkstra(String origen, String destino) {
        int inicio = indice(origen), fin = indice(destino);
        if (inicio < 0 || fin < 0) { System.out.println("Edificio no encontrado."); return; }
        int[] dist = new int[cantidad], anterior = new int[cantidad];
        boolean[] visitado = new boolean[cantidad];
        for (int i = 0; i < cantidad; i++) { dist[i] = Integer.MAX_VALUE; anterior[i] = -1; }
        dist[inicio] = 0;
        for (int paso = 0; paso < cantidad; paso++) {
            int actual = -1;
            for (int i = 0; i < cantidad; i++)
                if (!visitado[i] && (actual == -1 || dist[i] < dist[actual])) actual = i;
            if (actual == -1 || dist[actual] == Integer.MAX_VALUE) break;
            visitado[actual] = true;
            for (int j = 0; j < cantidad; j++) {
                if (!visitado[j] && distancias[actual][j] != Integer.MAX_VALUE
                        && dist[actual] <= Integer.MAX_VALUE - distancias[actual][j]) {
                    int nueva = dist[actual] + distancias[actual][j];
                    if (nueva < dist[j]) { dist[j] = nueva; anterior[j] = actual; }
                }
            }
        }
        if (dist[fin] == Integer.MAX_VALUE) { System.out.println("No existe una ruta."); return; }
        imprimirCamino(fin, anterior);
        System.out.println("\nDistancia total: " + dist[fin] + " metros");
    }
    private void imprimirCamino(int destino, int[] anterior) {
        if (anterior[destino] != -1) { imprimirCamino(anterior[destino], anterior); System.out.print(" -> "); }
        System.out.print(edificios[destino]);
    }
}