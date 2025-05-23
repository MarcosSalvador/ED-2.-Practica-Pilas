public class Aplicacion {
    public static void main(String[] args) {
        String expr = "a*(b+a)/c";  // Poner aqui la expresión con la que trabajar

        Expresion expresion = new Expresion(expr);

        if (!expresion.validarEstructura()) {
            System.out.println("Estructura incorrecta");
            return;
        }
        if (!expresion.comprobarParentesis()) {
            System.out.println("Parentesis mal emparejados");
            return;
        }

        String notacionPostfija = expresion.notacionPostfija();
        System.out.println("Notacion postfija: " + notacionPostfija);

        try {
            Fichero fichero = new Fichero("datos.txt");
            double[] valores;
            while ((valores = fichero.datosLinea()) != null) {
                double resultado = expresion.calcularResultado(valores);
                System.out.println("Valores: " + java.util.Arrays.toString(valores) + " Resultado: " + resultado);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo de datos.");
        }
    }
}
