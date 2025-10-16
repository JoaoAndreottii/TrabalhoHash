package hash;

public interface HashTable {
    void inserir(Registro r);
    Registro buscar(Registro r);
    long getNumeroColisoes();
}
