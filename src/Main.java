import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File arquivo = new File("texto.txt");
            Scanner leitor = new Scanner(arquivo);

            System.out.println("--- Iniciando Leitura do Documento ---");

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] palavras = linha.split(" ");

                System.out.println("Linha lida com " + palavras.length + " palavras.");
            }

            leitor.close();
            System.out.println("--- Leitura Finalizada com Sucesso ---");

        } catch (Exception e) {
            System.err.println("Erro: Certifique-se de que 'texto.txt' está na pasta do projeto.");
        }
    }
}