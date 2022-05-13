package Objetos;

public class Agencia {
    private final int ID;
    private final short numero;
    private String nome;

    public Agencia(int id, short numero, String nome) {
        ID = id;
        this.numero = numero;
        this.nome = nome;
    }

    public int getID(){
        return this.ID;
    }

    public short getNumero(){
        return this.numero;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}
