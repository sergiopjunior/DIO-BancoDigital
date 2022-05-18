package Objetos;

import Excecoes.SaldoInsuficienteException;
import Excecoes.OperacaoBloqueadaException;
import Excecoes.ValorLimiteAtingidoException;
import Excecoes.ValorZeradoException;
import Modelos.IConta;

public abstract class Conta implements IConta {
    private int ID;
    private int clienteID;
    private String numero;
    private int agenciaID;
    private final String tipo_conta;
    private double saldo;
    private final double taxa_transferencia;
    private final double taxa_saque;
    private final double deposito_limite;
    private final double saque_limite;
    private final double transferencia_limite;

    public Conta(String tipo_conta, double deposito_limite, double saque_limite,
                 double transferencia_limite, double taxa_transferencia, double taxa_saque){
        this.tipo_conta = tipo_conta;
        this.deposito_limite = deposito_limite;
        this.saque_limite = saque_limite;
        this.transferencia_limite = transferencia_limite;
        this.taxa_transferencia = taxa_transferencia;
        this.taxa_saque = taxa_saque;
    }

    public void Setup(int ID, int clienteID, int agenciaID, double saldo){
        this.ID = ID;
        this.clienteID = clienteID;
        this.agenciaID = agenciaID;
        this.saldo = saldo;
        this.numero = String.valueOf(Math.abs(this.hashCode()));
    }

    public int getID() {
        return ID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public String getNumero() {
        return numero;
    }

    public int getAgenciaID() {
        return agenciaID;
    }

    public String getTipoConta() {
        return tipo_conta;
    }

    protected double getSaldo() {
        return saldo;
    }

    protected void setSaldo(double valor) {
        this.saldo += valor;
    }

    public double getTaxaTransferencia() {
        return this.taxa_transferencia;
    }

    public double getDepositoLimite() {
        return deposito_limite;
    }

    public double getSaqueLimite() {
        return saque_limite;
    }

    public double getTransferenciaLimite() {
        return transferencia_limite;
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
        if (valor > this.saque_limite)
            throw new ValorLimiteAtingidoException(String.format("O valor R$%d supera o limite de R$%.2f para saques.\n",
                    valor, this.saque_limite));
        else if (valor <= 0)
            throw new ValorZeradoException("O valor da operação deve ser maior que R$0,00.\n");
        else if (this.saldo - valor - this.taxa_saque < 0) {
            String msg = this.taxa_saque > 0 ? String.format(" somado à taxa de %.2f", this.taxa_saque) : "";
            throw new SaldoInsuficienteException(String.format("Erro ao efetuar saque. " +
                    "O valor do saque deve ser maior do que o saldo da conta%s.\n", msg));
        }
        this.setSaldo(-(valor - this.taxa_saque));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        else if (!(o instanceof Conta)) {
            return false;
        }

        return this.numero.equals(((Conta) o).getNumero())
                && this.agenciaID == ((Conta) o).getAgenciaID();
    }

    @Override
    public int hashCode() {
        return this.tipo_conta.hashCode() + this.agenciaID + this.clienteID + this.ID;
    }

    @Override
    public String toString() {
        return String.format("Número: %s - AgênciaID: %d - Tipo: %s - ClienteID: %d - Saldo: %f\n",
                this.getNumero(), this.getAgenciaID(), this.tipo_conta, this.getClienteID(), this.getSaldo());
    }

    public String dataString() {
        return String.format("%d - %d - %s - %d - %s - %s\n",
                this.getID(), this.getClienteID(), this.getNumero(), this.getAgenciaID(), this.tipo_conta, this.getSaldo()).replace(",", ".");
    }
}