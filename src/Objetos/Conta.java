package Objetos;

import Excecoes.SaldoInsuficienteException;
import Excecoes.OperacaoBloqueadaException;
import Excecoes.ValorLimiteAtingidoException;
import Excecoes.ValorZeradoException;
import Modelos.IConta;

public abstract class Conta implements IConta {
    private final int ID;
    private final Cliente titular;
    private final long numero;
    private final int agenciaID;
    private final String tipo_conta;
    private double saldo = 0;
    private final double taxa_transferencia;
    private final double taxa_saque;
    private final double deposito_limite;
    private final double saque_limite;
    private final double transferencia_limite;

    public Conta(int id, Cliente titular, long numero, int agenciaID,
                 String tipo_conta, double deposito_limite, double saque_limite,
                 double transferencia_limite, double taxa_transferencia, double taxa_saque){
        this.ID = id;
        this.titular = titular;
        this.numero = numero;
        this.agenciaID = agenciaID;
        this.tipo_conta = tipo_conta;
        this.deposito_limite = deposito_limite;
        this.saque_limite = saque_limite;
        this.transferencia_limite = transferencia_limite;
        this.taxa_transferencia = taxa_transferencia;
        this.taxa_saque = taxa_saque;
    }

    @Override
    public void depositar(double valor) throws Exception {
        if (valor > this.deposito_limite)
            throw new ValorLimiteAtingidoException(String.format("O valor R$%.2f supera o limite de R$%.2f para depósitos.\n",
                    valor, this.deposito_limite));

        this.setSaldo(valor);
    }

    @Override
    public void transferir(Conta conta, double valor) throws Exception {
        if (valor > this.transferencia_limite)
            throw new ValorLimiteAtingidoException(String.format("O valor R$%.2f supera o limite de R$%.2f para transferências.\n",
                    valor, this.transferencia_limite));
        else if (this.saldo - valor - this.taxa_transferencia < 0)
            throw new SaldoInsuficienteException(String.format("O valor da transferência deve ser maior do que o " +
                    "saldo da conta somado à taxa de R$%.2f.\n", this.taxa_transferencia));
        if (this.agenciaID != conta.agenciaID)
            throw new OperacaoBloqueadaException("Não é possível realizar transferências entre contas de diferentes agências.\n");

        this.setSaldo(-(valor + this.taxa_transferencia));

    }

    @Override
    public void sacar(int valor) throws Exception {
        if (valor > this.getSaque_limite())
            throw new ValorLimiteAtingidoException(String.format("O valor R$%d supera o limite de R$%.2f para saques.\n",
                    valor, this.getSaque_limite()));
        else if (valor <= 0)
            throw new ValorZeradoException("O valor da operação deve ser maior que R$0,00.\n");
        else if (this.saldo - valor - this.taxa_saque < 0) {
            String msg = this.taxa_saque > 0 ? String.format(" somado à taxa de %.2f", this.taxa_saque) : "";
            throw new SaldoInsuficienteException(String.format("Erro ao efetuar saque. " +
                    "O valor do saque deve ser maior do que o saldo da conta%s.\n", msg));
        }
        this.setSaldo(-(valor - this.taxa_saque));
    }

    public int getID() {
        return ID;
    }

    public Cliente getTitular() {
        return titular;
    }

    public long getNumero() {
        return numero;
    }

    public int getAgenciaID() {
        return agenciaID;
    }

    public String getTipo_conta() {
        return tipo_conta;
    }

    protected double getSaldo() {
        return saldo;
    }

    protected void setSaldo(double valor) {
        this.saldo += valor;
    }

    public double getTaxa_transferencia() {
        return this.taxa_transferencia;
    }

    public double getDeposito_limite() {
        return deposito_limite;
    }

    public double getSaque_limite() {
        return saque_limite;
    }

    public double getTransferencia_limite() {
        return transferencia_limite;
    }
}
