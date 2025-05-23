import java.util.Stack;

class Expresion {
    String expresion;

    public Expresion(String expresion) {
        this.expresion = expresion;
    }

    private static boolean esOperando(char car) {
        return car >= 'a' && car <= 'e';
    }

    private static boolean esOperador(char car) {
        return car == '+' || car == '-' || car == '/' || car == '*';
    }

    private static int precedencia(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    public boolean validarEstructura() {
        if (expresion.isEmpty() || (!esOperando(expresion.charAt(0)) && expresion.charAt(0) != '(')) return false;
        if (!esOperando(expresion.charAt(expresion.length() - 1)) && expresion.charAt(expresion.length() - 1) != ')') return false;
        for (int i = 0; i < expresion.length() - 1; i++) {
            char actual = expresion.charAt(i);
            char siguiente = expresion.charAt(i + 1);
            if (esOperando(actual) && !esOperador(siguiente) && siguiente != ')') return false;
            if (esOperador(actual) && !esOperando(siguiente) && siguiente != '(') return false;
        }
        return true;
    }

    public boolean comprobarParentesis() {
        Stack<Character> pila = new Stack<>();
        for (char c : expresion.toCharArray()) {
            if (c == '(') pila.push(c);
            else if (c == ')') {
                if (pila.isEmpty()) return false;
                pila.pop();
            }
        }
        return pila.isEmpty();
    }

    public String notacionPostfija() {
        StringBuilder salida = new StringBuilder();
        Stack<Character> pila = new Stack<>();
        for (char c : expresion.toCharArray()) {
            if (esOperando(c)) {
                salida.append(c);
            } else if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    salida.append(pila.pop());
                }
                pila.pop();
            } else if (esOperador(c)) {
                while (!pila.isEmpty() && precedencia(pila.peek()) >= precedencia(c)) {
                    salida.append(pila.pop());
                }
                pila.push(c);
            }
        }
        while (!pila.isEmpty()) {
            salida.append(pila.pop());
        }
        return salida.toString();
    }

    public double calcularResultado(double[] valores) {
        Stack<Double> pila = new Stack<>();
        String postfija = notacionPostfija();
        for (char c : postfija.toCharArray()) {
            if (esOperando(c)) {
                pila.push(valores[c - 'a']);
            } else if (esOperador(c)) {
                double b = pila.pop();
                double a = pila.pop();
                if (c == '+') pila.push(a + b);
                else if (c == '-') pila.push(a - b);
                else if (c == '*') pila.push(a * b);
                else if (c == '/') pila.push(a / b);
            }
        }
        return pila.pop();
    }
}