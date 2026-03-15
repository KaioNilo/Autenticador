class Nodo {
    String palavra;
    int altura;
    Nodo esquerdo, direito;

    Nodo(String d) {
        palavra = d;
        altura = 1;
    }
}

public class ArvoreAVL {
    private Nodo raiz;

    // Metodo principal para inserir desconsiderando duplicatas
    public void inserir(String palavra) {
        raiz = inserirRecursivo(raiz, palavra);
    }

    private Nodo inserirRecursivo(Nodo nodo, String palavra) {
        if (nodo == null) return new Nodo(palavra);

        // Comparação lexicográfica ignorando maiúsculas/minúsculas
        int comp = palavra.compareToIgnoreCase(nodo.palavra);

        if (comp < 0) {
            nodo.esquerdo = inserirRecursivo(nodo.esquerdo, palavra);
        } else if (comp > 0) {
            nodo.direito = inserirRecursivo(nodo.direito, palavra);
        } else {
            return nodo; // Palavra duplicada: não faz nada
        }

        // Aqui entrariam as rotações de equilíbrio (omitidas pela simplicidade)
        return nodo;
    }
}