package ProyectoFinal;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Deque;
//calculas promedio de notas
//hacer el deshacer y rehacer con pilas en java
public class Notas { 
public static class registroNotas{
    private Double[][] notas = new Double[10][20];
    private String [][] materias = new String[10][20];
    private int [][] cantidadNotas = new int[10][20];
}

    public static class deshacerRehacer {
        private final Deque<cambioNotas> deshacer = new ArrayDeque<>();
        private final Deque<cambioNotas> rehacer = new ArrayDeque<>();
        public void registrar(cambioNotas cambio) {
            deshacer.push(cambio);
            rehacer.clear();
        }
        public void deshacer() {
            if (!deshacer.isEmpty()) {
                cambioNotas cambio = deshacer.pop();
                cambio.deshacer();
                rehacer.push(cambio);
            }
        }
        public void rehacer() {
            if (!rehacer.isEmpty()) {
                cambioNotas cambio = rehacer.pop();
                cambio.rehacer();
                deshacer.push(cambio);
            }
        }
    }
    public static class cambioNotas {
        private final Notas notas;
        private final String id;
        private final int semestre;
        private final int posicion;
        private final double anterior;
        private final double nueva;
        public cambioNotas(Notas notas, String id, int semestre, int posicion, double anterior, double nueva) {
            this.notas = notas;
            this.id = id;
            this.semestre = semestre;
            this.posicion = posicion;
            this.anterior = anterior;
            this.nueva = nueva;
        }
        private void deshacer() {
            notas.estudiantes.get(id).notas[semestre][posicion] = anterior;
        }
        private void rehacer() {
            notas.estudiantes.get(id).notas[semestre][posicion] = nueva;
        }
    }
    private HashMap<String, registroNotas> estudiantes = new HashMap<>();
    private registroNotas obtenerRegistro(String id) {
        return estudiantes.get(id);
    }
    public void agregarEstudiante(String id) {
        if(estudiantes.containsKey(id)){
            System.out.println("El estudiante ya esta registrado");
            return;
        }
        estudiantes.put(id, new registroNotas());
        System.out.println("Estudiante agregado correctamente");
    }
    public void registarNotas(String id, int semestre, String materia, double nota, deshacerRehacer historial, double anterior) {
        if(nota < 0 || nota > 5){
            System.out.println("La nota debe estar entre 0 y 5");
            return;
        }
        if(!(semestre < 1) || semestre > 10){
            System.out.println("El semestre debe estar entre 1 y 10");
            return;
        }
        registroNotas registro = obtenerRegistro(id);
        int indice = semestre - 1;
        int pocicion = registro.cantidadNotas[indice][0];
        if(pocicion >= 20){
            System.out.println("el semestre ya tiene 20 materias registradas");
            return;
        }
        Double promedio = registro.notas[indice][pocicion];
        registro.notas[indice][pocicion] = nota;
        registro.materias[indice][pocicion] = materia;
        registro.cantidadNotas[indice][0]++;
        if(historial != null){
            historial.registrar(new cambioNotas(this, id, indice, pocicion, anterior, nota));
        }
        System.out.println("Nota registrada correctamente");
    }
    //deshacer y rehacer
    public void cambioNotas(String id, int semestre, int posicion, Double valor){
        registroNotas registro = obtenerRegistro(id);
        registro.notas[semestre][posicion] = valor;
    }
    public void eliminarNotas(String id, int semestre, int posicion){
        registroNotas registro = obtenerRegistro(id);
        if(posicion == registro.cantidadNotas[semestre][0]-1){
            registro.notas[semestre][posicion] = null;
            registro.cantidadNotas[semestre][0]--;
        }
    }
    public void restaurarNotas(String id, int semestre, int posicion, Double valor){
        registroNotas registro = obtenerRegistro(id);
        if(posicion == registro.cantidadNotas[semestre][0]){
            registro.notas[semestre][posicion] = valor;
            registro.cantidadNotas[semestre][0]++;
        }else{
            registro.notas[semestre][posicion] = valor;
        }
    }
    // promedio de notas
    public Double promedioNotas(String id, int semestre){
        if(semestre < 1 || semestre > 10){
            System.out.println("El semestre debe estar entre 1 y 10");
            return null;
        }
        registroNotas registro = obtenerRegistro(id);
        int indice = semestre - 1;
        if(registro.cantidadNotas[indice][0] == 0){
            System.out.println("El estudiante no tiene notas registradas en este semestre");
            return 0.0;
        }
        double suma = 0.0;
        for(int i = 0; i < registro.cantidadNotas[indice][0]; i++){
            suma += registro.notas[indice][i];
        }
        return suma / registro.cantidadNotas[indice][0];
    }
    //promedio acumulado
    public Double promedioAcumulado(String id){
        registroNotas registro = obtenerRegistro(id);
        double suma = 0.0;
        int cantidadTotal = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < registro.cantidadNotas[i][0]; j++){
                suma += registro.notas[i][j];
                cantidadTotal++;
            }
        }
        if(cantidadTotal == 0){
            System.out.println("El estudiante no tiene notas registradas");
            return 0.0;
        }
        return suma / cantidadTotal;
    }
    //materias reprobadas
    public void materiasReprobadas(String id){
        registroNotas registro = obtenerRegistro(id);
        boolean tieneReprobadas = false;
        System.out.println("Materias reprobadas del estudiante " + id + ":");
        for(int i = 0; i < 10 ; i++){
            for(int j = 0; j < registro.cantidadNotas[i][0]; j++){
                if(registro.notas[i][j] < 3.0){
                    System.out.println("Semestre: " + (i+1) + ", Materia: " + registro.materias[i][j] + ", Nota: " + registro.notas[i][j]);
                    tieneReprobadas = true;
                }
            }
        }
        if(!tieneReprobadas){
            System.out.println("El estudiante no tiene materias reprobadas");
        }
    }
    //notas 
    public void mostrarNotas(String id){
        registroNotas registro = obtenerRegistro(id);
        System.out.println("----notas----");
        System.out.println("Estudiante: " + id);
        for(int i = 0; i < 10; i++){
            if(registro.cantidadNotas[i][0] > 0){
                System.out.println("Semestre: " + (i+1));
                for(int j = 0; j < registro.cantidadNotas[i][0]; j++){
                    System.out.println("Materia: " + registro.materias[i][j] + ", Nota: " + registro.notas[i][j]);
                }
                System.out.println("Promedio del semestre: " + promedioNotas(id, i+1));
            }
        }
        System.out.println("Promedio acumulado: " + promedioAcumulado(id));
        return;
    }
     // Buscar estudiante en HashMap
    private registroNotas buscarEstudiante(String id) {
        registroNotas registro = estudiantes.get(id);
        if (registro == null) {
            System.out.println("El estudiante con ID " + id + " no está registrado.");
        }
        return registro;
    }
}