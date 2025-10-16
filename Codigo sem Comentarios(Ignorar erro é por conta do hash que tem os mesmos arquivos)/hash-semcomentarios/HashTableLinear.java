package hash;

public class HashTableLinear implements HashTable {
    private Registro[] tabela;
    private long numeroColisoes;

    public HashTableLinear(int tamanho) {
        tabela = new Registro[tamanho];
        numeroColisoes = 0;
    }

    private int hash(long chave) {
        double A = (Math.sqrt(5) - 1) / 2;
        return (int) (tabela.length * ((chave * A) % 1));
    }

    @Override
    public void inserir(Registro r) {
        int tamanho = tabela.length;
        int pos = hash(r.getCodigo());
        int tentativas = 0;

        while (tabela[pos] != null) {
            numeroColisoes++;
            tentativas++;
            pos = (pos + 1) % tamanho;
            if (tentativas >= tamanho) return;
        }
        tabela[pos] = r;
    }

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
