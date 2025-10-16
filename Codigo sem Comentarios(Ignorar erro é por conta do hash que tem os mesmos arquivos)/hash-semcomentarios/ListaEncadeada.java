package hash;

class No {
    Registro registro;
    No proximo;

    No(Registro registro) {
        this.registro = registro;
        this.proximo = null;
    }
}

public class ListaEncadeada {
    private No inicio;
    private int tamanho;

    public ListaEncadeada() {
        inicio = null;
        tamanho = 0;
    }

    public void adicionar(Registro r) {
        No no = new No(r);
        no.proximo = inicio;
        inicio = no;
        tamanho++;
    }

    public Registro buscar(Registro r) {
        No atual = inicio;
        while (atual != null) {
            if (atual.registro.getCodigo() == r.getCodigo())
                return atual.registro;
            atual = atual.proximo;
        }
        return null;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}
