package hash;

// Classe que representa um nó da lista encadeada
class No {
    Registro registro; // O dado armazenado neste nó (um objeto Registro)
    No proximo;        // Referência para o próximo nó da lista

    // Construtor do nó: inicializa o registro e define o próximo como null
    No(Registro registro) {
        this.registro = registro;
        this.proximo = null;
    }
}

// Classe que implementa a lista encadeada
public class ListaEncadeada {
    private No inicio; // Referência para o primeiro nó da lista
    private int tamanho; // Quantidade de elementos na lista

    // Construtor da lista: cria uma lista vazia
    public ListaEncadeada() {
        inicio = null; // Lista começa vazia, sem início
        tamanho = 0;   // Tamanho inicial é 0
    }

    // Método para adicionar um novo registro no início da lista
    public void adicionar(Registro r) {
        No no = new No(r); // Cria um novo nó com o registro fornecido
        no.proximo = inicio; // Faz o próximo do novo nó apontar para o nó atual do início
        inicio = no;         // Atualiza o início da lista para o novo nó
        tamanho++;           // Incrementa o tamanho da lista
    }

    // Método para buscar um registro na lista
    public Registro buscar(Registro r) {
        No atual = inicio; // Começa a busca pelo início da lista
        while (atual != null) { // Percorre a lista enquanto houver nós
            if (atual.registro.getCodigo() == r.getCodigo()) // Se encontrar o registro com o mesmo código
                return atual.registro; // Retorna o registro encontrado
            atual = atual.proximo; // Avança para o próximo nó
        }
        return null; // Retorna null se o registro não for encontrado
    }

    // Método para retornar o tamanho da lista
    public int tamanho() {
        return tamanho;
    }

    // Método para verificar se a lista está vazia
    public boolean estaVazia() {
        return inicio == null; // Lista vazia se não há nó inicial
    }
}
