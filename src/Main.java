import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Stack<ArvoreAVL> pilhaDeArvores = new Stack<>();
        int contadorLinhas = 0;

        try {
            File arquivo = new File("texto.txt");
            Scanner leitor = new Scanner(arquivo);

            System.out.println("=== INICIANDO PROCESSAMENTO DO ARQUIVO ===");

            while (leitor.hasNextLine()) {
                contadorLinhas++;
                String linha = leitor.nextLine();
                if (linha.isBlank()) continue;

                System.out.println("\nLinha " + contadorLinhas + ": Lendo e invertendo palavras...");

                // Inserir em lista dinâmica
                String[] palavras = linha.split(" ");
                List<String> lista = new ArrayList<>(Arrays.asList(palavras));

                // Criar árvore e inserir no sentido REVERSO
                ArvoreAVL arvore = new ArvoreAVL();
                for (int i = lista.size() - 1; i >= 0; i--) {
                    String p = lista.get(i).replaceAll("[^a-zA-Zá-úÁ-Ú]", ""); // Limpa pontuação
                    if (!p.isBlank()) {
                        arvore.inserir(p);
                    }
                }

                // Empilhar a árvore
                pilhaDeArvores.push(arvore);
                System.out.println("   -> Árvore balanceada e empilhada com sucesso.");
            }
            leitor.close();

            System.out.println("\n=== GERANDO CÓDIGOS DE AUTENTICAÇÃO (DESEMPILHANDO) ===\n");

            // Desempilhar e gerar Hash
            while (!pilhaDeArvores.isEmpty()) {
                // Desempilha a árvore
                ArvoreAVL arvore = pilhaDeArvores.pop();

                // Gera o hash para a árvore desempilhada
                String hashFinal = arvore.obterHashFinal();

                // Quebra de linha
                System.out.println(hashFinal);
            }

        } catch (Exception e) {
            System.err.println("Erro crítico: " + e.getMessage());
        }
    }
}