package hash;

/**
 * HashTableLinear
 * Implementa tabela hash usando rehashing linear (linear probing).
 * Mede colisões, gaps e fornece funções de busca.
 */
public class HashTableLinear implements HashTable {
    private Registro[] tabela;
    private long numeroColisoes;

    public HashTableLinear(int tamanho) {
        tabela = new Registro[tamanho];
        numeroColisoes = 0;
    }

    /**
     * Função hash baseada em multiplicação.
     * Evita clusters grandes em comparação com resto da divisão simples.
     */
    private int hash(long chave) {
        double A = (Math.sqrt(5) - 1) / 2; // constante de Knuth
        return (int) (tabela.length * ((chave * A) % 1));
    }

    /**
     * Insere um registro usando linear probing.
     * Cada tentativa em posição ocupada é contada como colisão.
     */
    @Override
    public void inserir(Registro r) {
        int tamanho = tabela.length;
        int pos = hash(r.getCodigo());
        int tentativas = 0;

        while (tabela[pos] != null) {
            numeroColisoes++;
            tentativas++;
            pos = (pos + 1) % tamanho; // linear probing
            if (tentativas >= tamanho) return; // evita loop infinito
        }
        tabela[pos] = r;
    }

    /**
     * Busca um registro na tabela hash.
     */
    @Override
    public Registro buscar(Registro r) {
        int tamanho = tabela.length;
        int pos = hash(r.getCodigo());
        int tentativas = 0;

        while (tabela[pos] != null && tentativas < tamanho) {
            if (tabela[pos].equals(r)) return tabela[pos];
            pos = (pos + 1) % tamanho;
            tentativas++;
        }
        return null;
    }

    @Override
    public long getNumeroColisoes() {
        return numeroColisoes;
    }

    /**
     * Calcula gaps entre elementos ocupados na tabela.
     * Retorna: [menor gap, maior gap, média de gaps].
     */
    public int[] calcularGaps() {
        int menor = Integer.MAX_VALUE, maior = Integer.MIN_VALUE;
        long soma = 0;
        int cont = 0;
        int ultimoIndex = -1;

        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null) {
                if (ultimoIndex != -1) {
                    int gap = i - ultimoIndex;
                    menor = Math.min(menor, gap);
                    maior = Math.max(maior, gap);
                    soma += gap;
                    cont++;
                }
                ultimoIndex = i;
            }
        }
        int media = cont == 0 ? 0 : (int) (soma / cont);
        return new int[]{menor, maior, media};
    }
}
