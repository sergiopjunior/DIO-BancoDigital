package Objetos;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(int id, Cliente titular, long numero, int agenciaID){
        super(id, titular, numero, agenciaID, "Corrente", 800, 1000, 500, 0.7, 5);
    }
}
