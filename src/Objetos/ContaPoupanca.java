package Objetos;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(int clienteID, String numero, int agenciaID, double saldo){
        super(clienteID, numero, agenciaID, "Poupança", saldo, 800, 1000, 500, 0.7, 5);
    }
}
