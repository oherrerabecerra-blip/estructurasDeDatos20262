package ProyectoFinal;
public class DistanciaEdi {
    private String[] edificio;
    private int[][] distancia;
    public DistanciaEdi(String[] edificio, int[][] distancia) {
        this.edificio = edificio;
        this.distancia = distancia;
        if (distancia == null || edificio.length < 3){
            System.out.println("Debe haber al menos 3 edificios");
        }
        this.distancia = new int[edificio.length][edificio.length];
    }
    // numero de edificio 
    public int cantidadEdificios() {
        return edificio.length;
    }
    //nombre edificio
    public String getEdificio(int indice){
        return edificio[indice];
    }
    //matriz de distancias
    public int [][] getDistancia(int indice){
        return distancia;
    }
    //buscar el edificio
    public int buscarEdificio(String nombreEdificio){
        for (int i = 0; i < edificio.length; i++){
            if(edificio[i].equalsIgnoreCase(nombreEdificio)){
                return i;
            }
        }
        return -1;
    }
    // conexion entre edificios
    public void conectarEdificio(String origen, String destino, int metros){
        if(metros <= 0 ){
            System.out.println("La distancia debe ser mayor a 0");
            return;
        }
        int f = buscarEdificio(origen);
        int c = buscarEdificio(destino);
        if(f == -1 || c == -1){
            System.out.println("Uno o ambos edificios no existen");
            return;
        }
        this.distancia[f][c] = metros;
        this.distancia[c][f] = metros;
    }
    // mostrar la distancia entre dos edificios
    public void mostrarDistancia(){
        for(int i = 0; i < edificio.length; i++){
            for(int j = 0; j <edificio.length; j++){
                System.out.print(distancia[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public String[] getEdificio() {
        return edificio;
        }
    public int[][] getDistancia() {
        return distancia;
        }
}