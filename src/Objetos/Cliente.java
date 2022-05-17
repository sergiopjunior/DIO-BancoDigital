package Objetos;

import java.util.Objects;

public class Cliente {
    private int ID;
    private String nome;
    private short idade;
    private String cpf;

    public Cliente() {
    }

    public void Setup(String nome, short idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

    public int getID() {
        return this.ID;
    }

    protected void setID(int ID) {
        this.ID = ID;
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
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        else if (!(o instanceof Cliente)) {
            return false;
        }

        return this.cpf.equalsIgnoreCase(((Cliente) o).getCpf())
                && this.nome.equalsIgnoreCase(((Cliente) o).getNome()) && this.idade == ((Cliente) o).getIdade();
    }

    @Override
    public String toString() {
        return String.format("Nome: %s - Idade: %s - CPF: %s", nome, idade, cpf);
    }

    public String dataString() {
        return String.format("%d - %s - %d - %s\n",
                this.getID(), this.getNome(), this.getIdade(), this.getCpf());
    }
}
