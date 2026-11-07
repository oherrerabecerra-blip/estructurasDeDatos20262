public class MatricesAcademicas {
    private boolean[][] horarios = new boolean[7][24];
    private int[][] distancias = new int[5][5];

    private String[] dias = {
        "Domingo", "Lunes", "Martes", "Miercoles",
        "Jueves", "Viernes", "Sabado"
    };

    public void reservar(int dia, int hora, int duracion) {
        if (!horarioValido(dia, hora, duracion)) {
            System.out.println("Horario incorrecto.");
            return;
        }

        for (int i = hora; i < hora + duracion; i++) {
            if (horarios[dia][i]) {
                System.out.println("Horario ocupado: " + dias[dia] + " " + i + ":00");
                return;
            }
        }

        for (int i = hora; i < hora + duracion; i++) horarios[dia][i] = true;
        System.out.println("Horario reservado correctamente.");
    }

    public void consultar(int dia, int hora) {
        if (dia < 0 || dia >= 7 || hora < 0 || hora >= 24) {
            System.out.println("Datos incorrectos.");
            return;
        }
        System.out.println(horarios[dia][hora] ? "Horario ocupado." : "Horario disponible.");
    }

    public void liberar(int dia, int hora, int duracion) {
        if (!horarioValido(dia, hora, duracion)) {
            System.out.println("Horario incorrecto.");
            return;
        }
        for (int i = hora; i < hora + duracion; i++) horarios[dia][i] = false;
        System.out.println("Horario liberado.");
    }

    private boolean horarioValido(int dia, int hora, int duracion) {
        return dia >= 0 && dia < 7 && hora >= 0 && hora < 24 &&
               duracion > 0 && hora + duracion <= 24;
    }

    public void registrarDistancia(int origen, int destino, int metros) {
        if (origen < 0 || origen >= 5 || destino < 0 || destino >= 5 ||
            origen == destino || metros <= 0) {
            System.out.println("Datos de distancia incorrectos.");
            return;
        }
        distancias[origen][destino] = metros;
        distancias[destino][origen] = metros;
        System.out.println("Distancia registrada.");
    }

    public void mostrarDistancias() {
        System.out.println("\\nMATRIZ DE DISTANCIAS");
        for (int i = 0; i < distancias.length; i++) {
            for (int j = 0; j < distancias[i].length; j++) {
                System.out.print(distancias[i][j] + "\\t");
            }
            System.out.println();
        }
    }
}
