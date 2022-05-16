package Objetos;

import Excecoes.SaldoInsuficienteException;
import Excecoes.ValorLimiteAtingidoException;

public class ContaCorrente extends Conta {
    public ContaCorrente(int clienteID, String numero, int agenciaID, double saldo){
       super(clienteID, numero, agenciaID, "Corrente", saldo, 1500, 2000, 1000, 2, 0);
    }
}