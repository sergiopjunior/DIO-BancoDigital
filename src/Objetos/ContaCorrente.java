package Objetos;

import Excecoes.SaldoInsuficienteException;
import Excecoes.ValorLimiteAtingidoException;

public class ContaCorrente extends Conta {
    public ContaCorrente(){
       super("Corrente", 1500, 2000, 1000, 2, 0);
    }
}