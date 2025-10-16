package hash;

import java.util.LinkedList;

/**
 * HashTableEncadeamento
 * Implementa tabela hash usando encadeamento (separate chaining).
 * Cada colisão é contada quando um elemento é adicionado a uma lista não vazia.
 */
public class HashTableEncadeamento implements HashTable {
    private LinkedList<Registro>[] tabela;
    private long numeroColisoes;

    @SuppressWarnings("unchecked")
    public HashTableEncadeamento(int tamanho) {
        tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
        numeroColisoes = 0;
    }

    /**
     * Função hash: resto da divisão.
     * Pode ser substituída por multiplicação ou outras funções.
     */
    private int hash(long chave) {
        return (int)(chave % tabela.length);
    }

    /**
     * Insere um registro na tabela hash.
     * Cada inserção em lista não vazia é contada como colisão.
     */
    @Override
    public void inserir(Registro r) {
        int pos = hash(r.getCodigo());
        if (!tabela[pos].isEmpty()) numeroColisoes++;
        tabela[pos].add(r);
    }

    /**
     * Busca um registro na tabela hash.
     */
    @Override
    public Registro buscar(Registro r) {
        int pos = hash(r.getCodigo());
        for (Registro reg : tabela[pos]) {
            if (reg.equals(r)) return reg;
        }
        return null;
    }

    @Override
    public long getNumeroColisoes() {
        return numeroColisoes;
    }

    /**
     * Retorna os tamanhos das 3 maiores listas da tabela.
     * Útil para análise de desempenho do encadeamento.
     */
    public int[] calcularMaioresListas() {
        int[] maiores = new int[]{0, 0, 0};
        for (LinkedList<Registro> lista : tabela) {
            int size = lista.size();
            if (size > maiores[0]) {
                maiores[2] = maiores[1];
                maiores[1] = maiores[0];
                maiores[0] = size;
            } else if (size > maiores[1]) {
                maiores[2] = maiores[1];
                maiores[1] = size;
            } else if (size > maiores[2]) {
                maiores[2] = size;
            }
        }
        return maiores;
    }
}
