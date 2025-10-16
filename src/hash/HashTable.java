package hash;

/**
 * Interface HashTable
 * Define o contrato para todas as implementações de tabela hash.
 * Todas devem implementar inserção, busca e contagem de colisões.
 */
public interface HashTable {
    /**
     * Insere um registro na tabela hash.
     * @param r Registro a ser inserido.
     */
    void inserir(Registro r);

    /**
     * Busca um registro na tabela hash.
     * @param r Registro a ser buscado.
     * @return O registro encontrado ou null se não existir.
     */
    Registro buscar(Registro r);

    /**
     * Retorna o número de colisões ocorridas na tabela.
     * @return Número de colisões.
     */
    long getNumeroColisoes();
}
