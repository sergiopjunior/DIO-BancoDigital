package Objetos;

public class Agencia {
    private int ID;
    private String numero;
    private String nome;

    public Agencia() {
    }

    public void Setup(String numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public int getID(){
        return this.ID;
    }

    protected void setID(int codigo) {
        this.ID = codigo;
    }

    public String getNumero(){
        return this.numero;
    }

    protected void setNumero(String numero) {
        this.numero = numero;
    }
    public String getNome(){
        return this.nome;
    }

    protected void setNome(String nome){
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        else if (!(o instanceof Agencia)) {
            return false;
        }

        return this.numero.equals(((Agencia) o).getNumero())
                && this.nome.equals(((Agencia) o).getNome());
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Nome: %s - Número: %s",
                this.getID(), this.getNome(), this.getNumero());
    }

    public String dataString() {
        return String.format("%d - %s - %s\n",
                this.getID(), this.getNome(), this.getNumero());
    }
}
