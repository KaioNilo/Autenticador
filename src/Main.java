import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Questão 2
public class Main {
    public static void main(String[] args) {
        // Criação da lista dinâmica
        List<String> listaDePalavras = new ArrayList<>();

        try {
            File arquivo = new File("texto.txt");
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();

                // Quebrar a linha em palavras
                String[] palavrasDaLinha = linha.split(" ");

                // Inserir cada palavra na lista dinâmica
                for (String p : palavrasDaLinha) {
                    if (!p.isBlank()) { // Ignora espaços extras
                        listaDePalavras.add(p);
                    }
                }
            }
            leitor.close();

            // Exibe o resultado final
            System.out.println("Total de palavras capturadas: " + listaDePalavras.size());
            System.out.println("Lista completa: " + listaDePalavras);

        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}