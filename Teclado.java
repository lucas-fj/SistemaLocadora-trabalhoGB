// classe teclado com loop para entrada de tipos inválidos e aceitando acentos

import java.io.*;
import java.nio.charset.Charset;

/**
 * Classe que permite fazer leitura de dados do teclado
 */
public class Teclado {

    private static String s;
    private static final InputStreamReader i = new InputStreamReader(System.in, Charset.forName("CP850"));
    private static final BufferedReader d = new BufferedReader(i);

    /**
     * Lê um inteiro.
     *
     * @return int
     */
    public static int leInt() {
    while (true) {
        try {
            s = d.readLine();
            return Integer.parseInt(s);
        } catch (IOException e) {
            System.out.println("Erro de I/O: " + e);
        } catch (NumberFormatException e) {
            System.out.println("Erro: digite um número inteiro válido!");
        }
    }
}

    /**
     * Lê um inteiro, com mensagem.
     *
     * @return int
     */
    public static int leInt(String msg) {
        System.out.print(msg);
        return leInt();
    }

    /**
     * Lê um double.
     *
     * @return double
     */
    public static double leDouble() {
    while (true) {
        try {
            s = d.readLine();
            return Double.parseDouble(s);
        } catch (IOException e) {
            System.out.println("Erro de I/O: " + e);
        } catch (NumberFormatException e) {
            System.out.println("Erro: digite um número válido!");
        }
    }
}

    /**
     * Lê um double, com mensagem.
     *
     * @return double
     */
    public static double leDouble(String msg) {
        System.out.print(msg);
        return leDouble();
    }

    /**
     * Lê um string.
     *
     * @return String
     */
    public static String leString() {
        s = "";
        try {
            s = d.readLine();
        } catch (IOException e) {
            System.out.println("Erro de I/O: " + e);
        }
        return s;
    }

    /**
     * Lê um string. com mensagem.
     *
     * @return String
     */
    public static String leString(String msg) {
        System.out.print(msg);
        return leString();
    }
    public static char leChar() {
        while (true) {
            try {
                s = d.readLine();
                if (s != null && s.length() > 0) {
                    return s.charAt(0);
                } else {
                    System.out.println("Erro: digite um caractere válido!");
                }
            } catch (IOException e) {
                System.out.println("Erro de I/O: " + e);
            }
        }
    }

    /**
     * Lê um char, com mensagem.
     *
     * @return char
     */
    public static char leChar(String msg) {
        System.out.print(msg);
        return leChar();
    }
}