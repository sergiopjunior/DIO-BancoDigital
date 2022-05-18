package Funcionalidades;

import Objetos.Agencia;
import Objetos.AgenciasLista;
import Objetos.Conta;
import Objetos.ContasLista;

import java.util.Scanner;

public class Transacoes {
    public static void Depositar(ContasLista contasLista) {
        String opcao;
        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("DEPÓSITO");
            System.out.println("Informe o número da conta e o valor do depósito separados por \", \". (Ex: 928321, 150): ");
            try {
                String[] data = scanner.nextLine().split(", ");
                String conta_numero = data[0];
                double valor_deposito = Double.parseDouble(data[1]);
                Conta conta = contasLista.listarContaPorNumero(conta_numero);

                if (conta != null) {
                    System.out.println("Informações da transação:");
                    System.out.printf("- Conta: %s\n- Valor do depósito: R$%.2f%n", conta, valor_deposito);
                    opcao = Utilidades.confirmaOperacao();
                    if (opcao.equalsIgnoreCase("S")) {
                        try {
                            conta.depositar(valor_deposito);
                            System.out.println("Depósito realizado com sucesso.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else Utilidades.mensagemConsultaNaoEncontrada();
            }
            catch (Exception e) {
                System.out.println("Formato dos dados fornecidos incorreto.");
            }

            opcao = Utilidades.getRepetirOperacao();

        } while (opcao.equalsIgnoreCase("S"));
    }

    public static void Sacar(ContasLista contasLista) {
        String opcao;
        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("SAQUE");
            System.out.println("Informe o número da conta e o valor do saque separados por \", \". (Ex: 928321, 150): ");
            try {
                String[] data = scanner.nextLine().split(", ");

                String conta_numero = data[0];
                int valor_saque = Integer.parseInt(data[1]);
                Conta conta = contasLista.listarContaPorNumero(conta_numero);

                if (conta != null) {
                    System.out.println("Informações da transação:");
                    System.out.printf("- Conta: %s\n- Valor do saque: R$%d%n", conta, valor_saque);
                    opcao = Utilidades.confirmaOperacao();
                    if (opcao.equalsIgnoreCase("S")) {
                        try {
                            conta.sacar(valor_saque);
                            System.out.println("Saque realizado com sucesso.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else Utilidades.mensagemConsultaNaoEncontrada();
            }
            catch (Exception e) {
            System.out.println("Formato dos dados fornecidos incorreto.");
            }

            opcao = Utilidades.getRepetirOperacao();

        } while (opcao.equalsIgnoreCase("S"));
    }

    public static void Transferir(ContasLista contasLista) {
        String opcao;
        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("TRANSFERÊMCIA");
            System.out.println("Informe o número da conta a realizar a transferência, o valor e a conta a receber separados por \", \". (Ex: 928321, 150, 10234921): ");
            try {
                String[] data = scanner.nextLine().split(", ");

                double valor_transferencia = Double.parseDouble(data[1]);
                Conta conta_origem = contasLista.listarContaPorNumero(data[0]);
                Conta conta_destino = contasLista.listarContaPorNumero(data[2]);

                if (conta_origem != null && conta_destino != null) {
                    System.out.println("Informações da transação:");
                    System.out.printf("- Conta de origem: %s\n- Conta de destino: %s \n- Valor da transferência: R$%.2f%n", conta_origem, conta_destino, valor_transferencia);
                    opcao = Utilidades.confirmaOperacao();
                    if (opcao.equalsIgnoreCase("S")) {
                        try {
                            conta_origem.transferir(conta_destino, valor_transferencia);
                            System.out.println("Transferência realizada com sucesso.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else Utilidades.mensagemConsultaNaoEncontrada();
            }
            catch (Exception e) {
            System.out.println("Formato dos dados fornecidos incorreto.");
            }

            opcao = Utilidades.getRepetirOperacao();

        } while (opcao.equalsIgnoreCase("S"));
    }
}
