package hash;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Classe Main
 * Realiza testes das tabelas hash:
 * - Linear (linear probing)
 * - Duplo (double hashing)
 * - Encadeamento (separate chaining)
 * Mede tempo, colisões, gaps e maiores listas.
 * Gera registros com seed para reprodutibilidade.
 */
public class Main {
    public static void main(String[] args) {
        // Tamanhos dos vetores (de 10x em 10x)
        int[] tamanhosVetores = {150_000, 1_500_000, 15_000_000};
        // Tamanhos dos conjuntos de dados
        int[] tamanhosConjuntos = {100_000, 1_000_000, 10_000_000};
        long seed = 123456789; // Seed fixa para reprodutibilidade
        Random random = new Random(seed);

        // Arquivo CSV para exportar métricas
        try (FileWriter csv = new FileWriter("metricas.csv")) {
            csv.write("Tabela,TamanhoVetor,TamanhoConjunto,TempoInsercao(ms),Colisoes,TempoBusca(ms),GapMenor,GapMaior,GapMedia,Lista1,Lista2,Lista3\n");

            for (int idx = 0; idx < tamanhosVetores.length; idx++) {
                int tamanhoVetor = tamanhosVetores[idx];
                int tamanhoConjunto = tamanhosConjuntos[idx];

                System.out.println("\n=== Tabela tamanho: " + tamanhoVetor + " ===");
                System.out.println("Inserindo " + tamanhoConjunto + " registros...");

                // Gera registros com seed
                Registro[] registros = gerarRegistros(tamanhoConjunto, random);

                // Cria tabelas hash
                HashTableLinear linear = new HashTableLinear(tamanhoVetor);
                HashTableDuplo duplo = new HashTableDuplo(tamanhoVetor);
                HashTableEncadeamento encadeamento = new HashTableEncadeamento(tamanhoVetor);

                // -------------------- Linear --------------------
                long start = System.currentTimeMillis();
                for (Registro r : registros) linear.inserir(r);
                long end = System.currentTimeMillis();
                long tempoInsercao = end - start;
                long colisoes = linear.getNumeroColisoes();

                start = System.currentTimeMillis();
                for (Registro r : registros) linear.buscar(r);
                end = System.currentTimeMillis();
                long tempoBusca = end - start;

                int[] gaps = linear.calcularGaps();
                csv.write("Linear," + tamanhoVetor + "," + tamanhoConjunto + "," + tempoInsercao + "," + colisoes + "," + tempoBusca + "," + gaps[0] + "," + gaps[1] + "," + gaps[2] + ",,,\n");

                System.out.println("Linear finalizado. Inserção: " + tempoInsercao + "ms, Colisões: " + colisoes + ", Busca: " + tempoBusca + "ms, Gaps: " + gaps[0] + "/" + gaps[1] + "/" + gaps[2]);

                // -------------------- Duplo --------------------
                start = System.currentTimeMillis();
                for (Registro r : registros) duplo.inserir(r);
                end = System.currentTimeMillis();
                tempoInsercao = end - start;
                colisoes = duplo.getNumeroColisoes();

                start = System.currentTimeMillis();
                for (Registro r : registros) duplo.buscar(r);
                end = System.currentTimeMillis();
                tempoBusca = end - start;

                gaps = duplo.calcularGaps();
                csv.write("Duplo," + tamanhoVetor + "," + tamanhoConjunto + "," + tempoInsercao + "," + colisoes + "," + tempoBusca + "," + gaps[0] + "," + gaps[1] + "," + gaps[2] + ",,,\n");

                System.out.println("Duplo finalizado. Inserção: " + tempoInsercao + "ms, Colisões: " + colisoes + ", Busca: " + tempoBusca + "ms, Gaps: " + gaps[0] + "/" + gaps[1] + "/" + gaps[2]);

                // -------------------- Encadeamento --------------------
                start = System.currentTimeMillis();
                for (Registro r : registros) encadeamento.inserir(r);
                end = System.currentTimeMillis();
                tempoInsercao = end - start;
                colisoes = encadeamento.getNumeroColisoes();

                start = System.currentTimeMillis();
                for (Registro r : registros) encadeamento.buscar(r);
                end = System.currentTimeMillis();
                tempoBusca = end - start;

                int[] maioresListas = encadeamento.calcularMaioresListas();
                csv.write("Encadeamento," + tamanhoVetor + "," + tamanhoConjunto + "," + tempoInsercao + "," + colisoes + "," + tempoBusca + ",,," + "," + maioresListas[0] + "," + maioresListas[1] + "," + maioresListas[2] + "\n");

                System.out.println("Encadeamento finalizado. Inserção: " + tempoInsercao + "ms, Colisões: " + colisoes + ", Busca: " + tempoBusca + "ms, Maiores listas: " + maioresListas[0] + "/" + maioresListas[1] + "/" + maioresListas[2]);
            }

            System.out.println("\nMétricas exportadas para metricas.csv para análise gráfica.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gera um conjunto de registros aleatórios com 9 dígitos.
     * Usa seed para garantir que os conjuntos possam ser reproduzidos.
     */
    public static Registro[] gerarRegistros(int n, Random random) {
        Registro[] registros = new Registro[n];
        for (int i = 0; i < n; i++) {
            registros[i] = new Registro(100_000_000 + random.nextInt(900_000_000));
        }
        return registros;
    }
}
