package ProyectoFinal;
public class Aula extends Campus {
    private int capacidad;
    private boolean[][] ocupado = new boolean[7][24];
    public Aula(String nombre, int capacidad) {
        super(nombre);
        if (capacidad <= 0) 
            throw new IllegalArgumentException("Capacidad invalida.");
            this.capacidad = capacidad;
    }
    public int getCapacidad(){ 
        return capacidad;
    }
    public boolean reservar(int dia, int hora, int duracion) {
        if (!rangoValido(dia, hora, duracion)) return false;
        for (int h = hora; h < hora + duracion; h++) if (ocupado[dia][h]) return false;
        for (int h = hora; h < hora + duracion; h++) ocupado[dia][h] = true;
        return true;
    }
    public boolean liberar(int dia, int hora, int duracion) {
        if (!rangoValido(dia, hora, duracion)) return false;
        for (int h = hora; h < hora + duracion; h++) ocupado[dia][h] = false;
        return true;
    }
    public boolean consultarDisponibilidad(int dia, int hora) {
        return dia >= 0 && dia < 7 && hora >= 0 && hora < 24 && !ocupado[dia][hora];
    }
    private boolean rangoValido(int dia, int hora, int duracion) {
        return dia >= 0 && dia < 7 && hora >= 0 && duracion > 0 && hora + duracion <= 24;
    }
    public void mostrarEstado(){
        String[] dias = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
        System.out.println("Aula " + getNombre() + " | Capacidad: " + capacidad);
        for (int d = 0; d < 7; d++) {
            System.out.print(dias[d] + ": ");
            for (int h = 0; h < 24; h++) if (ocupado[d][h]) System.out.print(h + ":00 ");
            System.out.println();
        }
    }
}
