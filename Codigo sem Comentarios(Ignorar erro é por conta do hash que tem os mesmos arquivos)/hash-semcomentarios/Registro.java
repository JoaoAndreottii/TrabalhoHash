package hash;

/**
 * Classe Registro
 * Representa o objeto que será inserido nas tabelas hash.
 * Contém apenas um código de 9 dígitos.
 */
public class Registro {
    private long codigo;

    public Registro(long codigo) {
        if (codigo < 100_000_000 || codigo > 999_999_999) {
            throw new IllegalArgumentException("Código deve ter 9 dígitos.");
        }
        this.codigo = codigo;
    }

    public long getCodigo() {
        return codigo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Registro r = (Registro) obj;
        return codigo == r.codigo;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(codigo);
    }

    @Override
    public String toString() {
        return String.valueOf(codigo);
    }
}
