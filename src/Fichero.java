import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Fichero {
    BufferedReader fichero;

    public Fichero(String nombre) {
        try {
            fichero = new BufferedReader(new FileReader(nombre));
        } catch (IOException ex) {
            System.out.println("Error apertura de fichero");
        }
    }

    public double[] datosLinea() {
        double[] datos = null;
        try {
            String linea = fichero.readLine();
            if (linea != null) {
                String[] cadenas = linea.split(" +");
                datos = new double[5];
                for (int i = 0; i < cadenas.length; i++) {
                    datos[i] = Double.parseDouble(cadenas[i]);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error lectura de fichero");
        }
        return datos;
    }
}
