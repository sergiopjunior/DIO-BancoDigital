package Objetos;

public class Cliente {
    private final int ID;
    private String nome;
    private short idade;
    private final String cpf;

    Cliente(int id, String nome, short idade, String cpf) {
        ID = id;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

    public int getID() {
        return this.ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public short getIdade() {
        return idade;
    }

    public void setIdade(short idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s - Idade: %s - CPF: %s", nome, idade, cpf);
    }
}
