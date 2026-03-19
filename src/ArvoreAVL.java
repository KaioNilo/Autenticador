import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe que representa cada nó da Árvore AVL.
 * Armazena a palavra, a altura do nó para balanceamento e as referências para os filhos.
 */
class Nodo {
    String palavra;
    int altura;
    Nodo esquerdo, direito;
    String hash; // Armazenará o hash SHA-1 deste nó específico

    Nodo(String d) {
        this.palavra = d;
        this.altura = 1;
    }
}

/**
 * Implementação da Árvore AVL com balanceamento automático
 */
public class ArvoreAVL {
    private Nodo raiz;

    // --- MÉTODOS DE APOIO PARA BALANCEAMENTO ---

    private int altura(Nodo n) {
        return (n == null) ? 0 : n.altura;
    }

    private int getFatorEquilibrio(Nodo n) {
        return (n == null) ? 0 : altura(n.esquerdo) - altura(n.direito);
    }

    // --- ROTAÇÕES (Mecânica de Auto-Ajuste) ---

    // Rotação à Direita:
    private Nodo rotacionarDireita(Nodo y) {
        Nodo x = y.esquerdo;
        Nodo T2 = x.direito;

        x.direito = y;
        y.esquerdo = T2;

        // Atualiza as alturas após a mudança
        y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;
        x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;

        System.out.println("   [AVL] Rotação à Direita aplicada na palavra: " + y.palavra);

        return x;
    }

    // Rotação à Esquerda:
    private Nodo rotacionarEsquerda(Nodo x) {
        Nodo y = x.direito;
        Nodo T2 = y.esquerdo;

        y.esquerdo = x;
        x.direito = T2;

        x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;
        y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;

        System.out.println("   [AVL] Rotação à Esquerda aplicada na palavra: " + x.palavra);

        return y;
    }

    // --- INSERÇÃO---

    public void inserir(String palavra) {
        raiz = inserirRecursivo(raiz, palavra);
    }

    private Nodo inserirRecursivo(Nodo nodo, String palavra) {
        // Inserção Árvore Binária
        if (nodo == null) return new Nodo(palavra);

        // compareToIgnoreCase
        int comp = palavra.compareToIgnoreCase(nodo.palavra);

        if (comp < 0) {
            nodo.esquerdo = inserirRecursivo(nodo.esquerdo, palavra);
        } else if (comp > 0) {
            nodo.direito = inserirRecursivo(nodo.direito, palavra);
        } else {
            return nodo; // Desconsiderando duplicatas
        }

        // Atualiza altura do ancestral
        nodo.altura = 1 + Math.max(altura(nodo.esquerdo), altura(nodo.direito));

        // Verifica Fator de Equilíbrio
        int fe = getFatorEquilibrio(nodo);

        // Rotações se necessário

        // Caso Esquerda-Esquerda
        if (fe > 1 && palavra.compareToIgnoreCase(nodo.esquerdo.palavra) < 0) {
            return rotacionarDireita(nodo);
        }

        // Caso Direita-Direita
        if (fe < -1 && palavra.compareToIgnoreCase(nodo.direito.palavra) > 0) {
            return rotacionarEsquerda(nodo);
        }

        // Caso Esquerda-Direita (Rotação Dupla)
        if (fe > 1 && palavra.compareToIgnoreCase(nodo.esquerdo.palavra) > 0) {
            nodo.esquerdo = rotacionarEsquerda(nodo.esquerdo);
            return rotacionarDireita(nodo);
        }

        // Caso Direita-Esquerda (Rotação Dupla)
        if (fe < -1 && palavra.compareToIgnoreCase(nodo.direito.palavra) < 0) {
            nodo.direito = rotacionarDireita(nodo.direito);
            return rotacionarEsquerda(nodo);
        }

        return nodo;
    }

    // Comentários de Status do balanceamento
    public void verificarIntegridade() {
        if (isBalanceada(raiz)) {
            System.out.println("Status: Árvore AVL 100% Equilibrada.");
        } else {
            System.out.println("Aviso: A árvore detectou um desbalanceamento!");
        }
    }

    private boolean isBalanceada(Nodo n) {
        if (n == null) return true;
        int fe = getFatorEquilibrio(n);
        if (Math.abs(fe) > 1) return false;
        return isBalanceada(n.esquerdo) && isBalanceada(n.direito);
    }

    // --- GERAÇÃO DE HASH SHA-1 ---
    public String obterHashFinal() {
        if (raiz == null) return "";
        calcularHashNos(raiz);
        return raiz.hash;
    }

    private void calcularHashNos(Nodo nodo) {
        if (nodo == null) return;

        // Calcular filhos primeiro
        calcularHashNos(nodo.esquerdo);
        calcularHashNos(nodo.direito);

        // Hash(P) = h( h(E) + h(D) + h(Palavra) )
        String hashE = (nodo.esquerdo != null) ? nodo.esquerdo.hash : "";
        String hashD = (nodo.direito != null) ? nodo.direito.hash : "";

        nodo.hash = sha1(hashE + hashD + sha1(nodo.palavra));
    }

    /**
     * Função auxiliar para converter strings em SHA-1.
     */
    private String sha1(String input) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}