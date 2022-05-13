package Objetos;

public class Agencia {
    private int ID;
    private final short numero;
    private String nome;

    public Agencia(short numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public int getID(){
        return this.ID;
    }

    protected void setID(int codigo) {
        this.ID = codigo;
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
