package Utilidades;

import Objetos.AgenciasLista;
import Objetos.Agencia;

import java.util.Scanner;

public final class Operacoes {
    private Operacoes() {}

    public static void cadastrarAgencia(AgenciasLista agenciasLista) {
        String opcao;
        do {
            System.out.println("CADASTRO DE AGÊNCIAS");
            Agencia nova_agencia;

            do {
                nova_agencia = setDadosAgencia();
            } while (!Utilidades.validarDadosAgencia(nova_agencia, agenciasLista, "cadastrar"));

            opcao = Utilidades.confirmaOperacao();
            if (opcao.equalsIgnoreCase("S")) {
                agenciasLista.inserirAgencia(nova_agencia);
                System.out.println("Agência cadastrada com sucesso.");
            }
            opcao = Utilidades.getRepetirOperacao();

        } while (opcao.equalsIgnoreCase("S"));
    }

    private static Agencia setDadosAgencia() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o nome da agência: ");
        String nome = scanner.nextLine();

        System.out.println("Informe o número da agência: ");
        short numero = scanner.nextShort();

        return new Agencia(numero, nome);
    }

    public static void relatorioDeAgencias(AgenciasLista agenciasLista) {
        System.out.println("RELATÓRIO\n");
        agenciasLista.listarAgencias();

        Scanner scanner = new Scanner(System.in);
        System.out.println("APERTE QUALQUER LETRA + ENTER PARA CONTINUAR");
        scanner.next();
    }
}