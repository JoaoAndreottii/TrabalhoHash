package hash;

import java.util.LinkedList;

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

    private int hash(long chave) {
        return (int)(chave % tabela.length);
    }

    @Override
    public void inserir(Registro r) {
        int pos = hash(r.getCodigo());
        if (!tabela[pos].isEmpty()) numeroColisoes++;
        tabela[pos].add(r);
    }

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
