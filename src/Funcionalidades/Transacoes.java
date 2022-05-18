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
            String[] data = scanner.nextLine().split(", ");

            String conta_numero = data[0];
            double valor_deposito = Double.parseDouble(data[1]);
            Conta conta = contasLista.listarContaPorNumero(conta_numero);

            if (conta != null){
                System.out.println("Informações da transação:");
                System.out.printf("- Conta: %s\n- Valor do depósito: R$%.2f%n", conta, valor_deposito);
                opcao = Utilidades.confirmaOperacao();
                if (opcao.equalsIgnoreCase("S")) {
                    try {
                        conta.depositar(valor_deposito);
                        System.out.println("Depósito realizado com sucesso.");
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            else Utilidades.mensagemConsultaNaoEncontrada();

            opcao = Utilidades.getRepetirOperacao();

        } while (opcao.equalsIgnoreCase("S"));
    }
}
