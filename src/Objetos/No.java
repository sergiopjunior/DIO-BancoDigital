package Objetos;

import java.util.Objects;
import java.util.function.Supplier;

public class No<T> {
    private T elemento;
    private No<T> anterior;
    private No<T> proximo;

    private T p;

    No(Supplier<? extends T> ctor) {
        this.elemento = Objects.requireNonNull(ctor).get();
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public No<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(No<T> anterior) {
        this.anterior = anterior;
    }

    public No<T> getProximo() {
        return proximo;
    }

    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return this.getElemento().toString();
    }

}

