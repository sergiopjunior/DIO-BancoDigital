package Utilidades;

import Objetos.AgenciasLista;
import Objetos.Agencia;

import java.util.InputMismatchException;
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

    public static void alterarAgencia(AgenciasLista agenciasLista) throws InputMismatchException {
        String escolha;
        String continuar = "S";
        boolean is_valid;
        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("ALTERAÇÃO DE AGÊNCIA");
            System.out.println("Informe o número da agência a ser alterada: ");
            short agencia_numero = scanner.nextShort();

            Agencia agencia = agenciasLista.listarAgenciaPorNumero(agencia_numero);

            if (agencia != null) {
                System.out.println("AGÊNCIA ENCONTRADA\n");
                Agencia agencia_alterar;

                do {
                    agencia_alterar = setDadosAgencia();
                    is_valid = Utilidades.validarDadosAgencia(agencia_alterar, agenciasLista, "atualizar");

                    if (agencia_alterar.getNumero() == agencia_numero && is_valid) {
                        escolha = Utilidades.confirmaOperacao();
                        if (escolha.equalsIgnoreCase("S")) {
                            agenciasLista.atualizarAgencia(agencia_numero, agencia_alterar);
                            System.out.println("Dados da agência alterados com sucesso.");
                            continuar = "N";
                        }
                    }
                    else {
                        System.out.println("O número do agência não pode ser alterado! " +
                                "Para tal deve-se excluí-la e recadastrá-la");
                        is_valid = false;
                        continuar = Utilidades.getRepetirOperacao();
                    }
                } while (!is_valid || continuar.equalsIgnoreCase("S"));
            }
            else {
                Utilidades.mensagemConsultaNaoEncontrada();
            }

            escolha = Utilidades.getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    public static void excluirAgencia(AgenciasLista agenciasLista) throws InputMismatchException {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("EXCLUSÃO DE AGÊNCIAS");
            System.out.println("Informe o número da agência a ser excluída: ");
            short agencia_numero = scanner.nextShort();

            Agencia agencia = agenciasLista.listarAgenciaPorNumero(agencia_numero);

            if (agencia != null) {
                System.out.println("AGÊNCIA ENCONTRADA\n");
                System.out.println(agencia);
                escolha = Utilidades.confirmaOperacao();
                if (escolha.equalsIgnoreCase(("S"))) {
                    agenciasLista.excluirAgencia(agencia_numero);
                    System.out.println("AGÊNCIA DELETADA!\n");
                }
            }
            else {
                Utilidades.mensagemConsultaNaoEncontrada();
            }

            escolha = Utilidades.getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    public static void consultarAgencia(AgenciasLista agenciasLista) throws InputMismatchException{
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("CONSULTAR AGÊNCIA");
            System.out.println("Informe o número da agência a ser pesquisada: ");
            short agencia_numero = scanner.nextShort();
            System.out.println(agencia_numero);
            Agencia agencia = agenciasLista.listarAgenciaPorNumero(agencia_numero);

            if (agencia != null) {
                System.out.println("AGÊNCIA ENCONTRADA\n");
                System.out.println(agencia);
            }
            else {
                Utilidades.mensagemConsultaNaoEncontrada();
            }

            escolha = Utilidades.getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private static Agencia setDadosAgencia() throws InputMismatchException{
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