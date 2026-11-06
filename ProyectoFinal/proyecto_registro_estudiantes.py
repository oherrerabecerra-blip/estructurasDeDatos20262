import csv
import os
import subprocess

ARCHIVO_CSV = "inscripciones.csv"

# 11. Procesamiento y registro de inscripciones CSV

def crear_csv_si_no_existe():
    if not os.path.exists(ARCHIVO_CSV):
        with open(ARCHIVO_CSV, "w", newline="", encoding="utf-8") as archivo:
            escritor = csv.writer(archivo)
            escritor.writerow(["ID", "Nombre", "Programa"])


def procesar_inscripciones():
    crear_csv_si_no_existe()
    print("\n--- INSCRIPCIONES REGISTRADAS ---")
    with open(ARCHIVO_CSV, "r", newline="", encoding="utf-8") as archivo:
        filas = list(csv.reader(archivo))

    if len(filas) <= 1:
        print("No hay estudiantes registrados.")
        return

    for fila in filas:
        print(" | ".join(fila))


def registrar_estudiante():
    crear_csv_si_no_existe()
    print("\n--- REGISTRAR ESTUDIANTE ---")
    identificacion = input("Ingrese la identificacion: ").strip()
    nombre = input("Ingrese el nombre completo: ").strip()
    programa = input("Ingrese el programa academico: ").strip()

    if not identificacion or not nombre or not programa:
        print("Error: todos los campos son obligatorios.")
        return

    with open(ARCHIVO_CSV, "r", newline="", encoding="utf-8") as archivo:
        filas = list(csv.reader(archivo))

    for fila in filas[1:]:
        if fila and fila[0] == identificacion:
            print("Ya existe un estudiante con esa identificacion.")
            return

    with open(ARCHIVO_CSV, "a", newline="", encoding="utf-8") as archivo:
        csv.writer(archivo).writerow([identificacion, nombre, programa])

    print("Estudiante registrado y guardado correctamente.")


# 13. Codigo de arreglos en Python

def arreglos():
    numeros = [10, 20, 30, 40, 50]
    print("\n--- ARREGLOS ---")
    print("Arreglo original:", numeros)
    numeros.append(60)
    print("Arreglo actualizado:", numeros)
    print("Primer elemento:", numeros[0])
    print("Ultimo elemento:", numeros[-1])
    print("Cantidad de elementos:", len(numeros))
    print("Elementos del arreglo:")
    for numero in numeros:
        print(numero)


# 14. Codigo de matrices en Python

def matrices():
    matriz = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
    print("\n--- MATRICES ---")
    for fila in matriz:
        print(fila)

    suma = sum(elemento for fila in matriz for elemento in fila)
    print("Suma de los elementos:", suma)
    print("Elemento de fila 1, columna 2:", matriz[0][1])


# Programa Java

def ejecutar_java():
    codigo_java = r'''import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static final String ARCHIVO = "inscripciones.csv";

    public static void registrarEstudiante() {
        System.out.println("\\n--- REGISTRAR ESTUDIANTE ---");
        System.out.print("Ingrese la identificacion: ");
        String id = sc.nextLine().trim();
        System.out.print("Ingrese el nombre completo: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Ingrese el programa academico: ");
        String programa = sc.nextLine().trim();

        if (id.isEmpty() || nombre.isEmpty() || programa.isEmpty()) {
            System.out.println("Todos los campos son obligatorios.");
            return;
        }

        try {
            FileWriter escritor = new FileWriter(ARCHIVO, true);
            escritor.write(id + "," + nombre + "," + programa + "\\n");
            escritor.close();
            System.out.println("Estudiante registrado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public static void consultarEstudiantes() {
        System.out.println("\\n--- ESTUDIANTES REGISTRADOS ---");
        try {
            BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO));
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea.replace(",", " | "));
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("No se encontraron registros o no se pudo leer el archivo.");
        }
    }

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\\n===== MENU JAVA =====");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Consultar estudiantes");
            System.out.println("3. Salir");
            System.out.print("Seleccione: ");
            String entrada = sc.nextLine().trim();
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opcion invalida.");
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1: registrarEstudiante(); break;
                case 2: consultarEstudiantes(); break;
                case 3: System.out.println("Saliendo..."); break;
                default: System.out.println("Opcion invalida.");
            }
        } while (opcion != 3);
        sc.close();
    }
}'''

    try:
        with open("Main.java", "w", encoding="utf-8") as archivo:
            archivo.write(codigo_java)
        subprocess.run(["javac", "Main.java"], check=True)
        subprocess.run(["java", "Main"], check=True)
    except FileNotFoundError:
        print("Java JDK no esta instalado o java/javac no estan configurados en PATH.")
    except subprocess.CalledProcessError:
        print("Error al compilar o ejecutar el programa Java.")


# 12. Menu principal interactivo

def menu_principal():
    while True:
        print("\\n========== MENU PRINCIPAL ==========")
        print("1. Procesar inscripciones CSV")
        print("2. Ejecutar arreglos")
        print("3. Ejecutar matrices")
        print("4. Ejecutar programa Java")
        print("5. Registrar estudiante")
        print("6. Salir")

        opcion = input("Seleccione una opcion: ").strip()
        if opcion == "1":
            procesar_inscripciones()
        elif opcion == "2":
            arreglos()
        elif opcion == "3":
            matrices()
        elif opcion == "4":
            ejecutar_java()
        elif opcion == "5":
            registrar_estudiante()
        elif opcion == "6":
            print("Programa finalizado.")
            break
        else:
            print("Opcion invalida. Intente nuevamente.")


if __name__ == "__main__":
    menu_principal()
