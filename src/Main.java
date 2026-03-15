import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Questão 2
public class Main {
    public static void main(String[] args) {
        // Criação da lista dinâmica
        Stack<ArvoreAVL> pilhaDeArvores = new Stack<>();

        try {
            File arquivo = new File("texto.txt");
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                // Quebrar a linha em palavras
                String[] palavrasDaLinha = linha.split(" ");

                List<String> listaTemporaria = new ArrayList<>();

                for (String p : palavrasDaLinha) {
                    if (!p.isBlank()) {
                        listaDePalavras.add(p);
                    }
                    ArvoreAVL arvoreDaLinha = new ArvoreAVL();
                }
            }

// QuestãoInserção da arvore
            for (int i = listaDePalavras.size() - 1; i >= 0; i--) {
                String palavra = listaDePalavras.get(i);
                arvore.inserir(palavra); // Insere na árvore (ela já trata duplicatas)
            }
            pilhaDeArvores.push(arvoreDaLinha);
        }
            leitor.close();

        System.out.println("Processamento concluído. Gerando códigos (hashes):\n");
        while (!pilhaDeArvores.isEmpty()) {
            ArvoreAVL arvore = pilhaDeArvores.pop();

            // Aqui você deve chamar o seu metodo de travessia/hash
            // Exemplo: String hash = arvore.gerarHash();
            // System.out.println(hash);

            System.out.println("Árvore desempilhada e processada.");
        }

    } catch (Exception e) {
        System.err.println("Erro: " + e.getMessage());
    }
}
