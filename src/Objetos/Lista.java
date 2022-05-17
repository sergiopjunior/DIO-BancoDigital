package Objetos;

import java.util.Objects;
import java.util.function.Supplier;

public class Lista {
    private int tamanho = 0;
    private int proximo_codigo = 1;

    public int getTamanho() {
        return tamanho;
    }

    protected void setTamanho(int valor) {
        this.tamanho += valor;
    }

    protected int getProximoCodigo() {
        return proximo_codigo;
    }

    protected void setProximoCodigo() {
        this.proximo_codigo += 1;
    }

    protected void setProximoCodigo(int codigo) {
        this.proximo_codigo = codigo;
    }
}
