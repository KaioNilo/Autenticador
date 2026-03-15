import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Pilha que armazenará uma Árvore AVL para cada linha do arquivo
        Stack<ArvoreAVL> pilhaDeArvores = new Stack<>();

        try {
            File arquivo = new File("texto.txt");
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] palavrasDaLinha = linha.split(" ");

                // Lista temporária para inverter a ordem das palavras da linha
                List<String> listaTemporaria = new ArrayList<>();
                for (String p : palavrasDaLinha) {
                    if (!p.isBlank()) listaTemporaria.add(p);
                }

                // Cria uma árvore AVL nova para ESTA linha
                ArvoreAVL arvoreDaLinha = new ArvoreAVL();

                // Insere na árvore no sentido reverso
                for (int i = listaTemporaria.size() - 1; i >= 0; i--) {
                    arvoreDaLinha.inserir(listaTemporaria.get(i));
                }

                // Guarda a árvore da linha na pilha
                pilhaDeArvores.push(arvoreDaLinha);
            }
            leitor.close();

            System.out.println("Processamento concluído. Gerando códigos (hashes):\n");

            // Desempilha cada árvore e processa
            while (!pilhaDeArvores.isEmpty()) {
                ArvoreAVL arvore = pilhaDeArvores.pop();

                // Aqui você deve chamar o seu método de travessia/hash
                // Exemplo: String hash = arvore.gerarHash();
                // System.out.println(hash);

                System.out.println("Árvore desempilhada e processada.");
            }

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}