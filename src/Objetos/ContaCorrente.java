package Objetos;

import Excecoes.SaldoInsuficienteException;
import Excecoes.ValorLimiteAtingidoException;

public class ContaCorrente extends Conta {
    public ContaCorrente(int id, Cliente titular, long numero, int agenciaID){
       super(id, titular, numero, agenciaID, "Corrente", 1500, 2000, 1000, 2, 0);
    }
}