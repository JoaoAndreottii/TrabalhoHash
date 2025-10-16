package hash;

public class HashTableDuplo implements HashTable {
    private Registro[] tabela;
    private long numeroColisoes;

    public HashTableDuplo(int tamanho) {
        tabela = new Registro[tamanho];
        numeroColisoes = 0;
    }

    private int hash1(long chave) {
        return (int) (chave % tabela.length);
    }

    private int hash2(long chave) {
        return 1 + (int)((chave / tabela.length) % (tabela.length - 1));
    }

    @Override
    public void inserir(Registro r) {
        int tamanho = tabela.length;
        int h1 = hash1(r.getCodigo());
        int h2 = hash2(r.getCodigo());
        int i = 0;
        int pos = h1;

        while (tabela[pos] != null && i < tamanho) {
            numeroColisoes++;
            i++;
            pos = (h1 + i * h2) % tamanho;
        }
        if (i < tamanho) tabela[pos] = r;
    }

    @Override
    public Registro buscar(Registro r) {
        int tamanho = tabela.length;
        int h1 = hash1(r.getCodigo());
        int h2 = hash2(r.getCodigo());
        int i = 0;
        int pos = h1;

        while (tabela[pos] != null && i < tamanho) {
            if (tabela[pos].equals(r)) return tabela[pos];
            i++;
            pos = (h1 + i * h2) % tamanho;
        }
        return null;
    }

    @Override
    public long getNumeroColisoes() {
        return numeroColisoes;
    }

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
        int media = cont == 0 ? 0 : (int)(soma / cont);
        return new int[]{menor, maior, media};
    }
}
