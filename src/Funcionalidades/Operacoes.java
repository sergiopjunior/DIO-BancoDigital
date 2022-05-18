package Funcionalidades;

import Objetos.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

record ContaDados(String tipo_conta, String cpf, String agenciaNumero) {
}

public final class Operacoes {
    private Operacoes() {}

    //////////////////////// Agências ////////////////////////
    public static void cadastrarAgencia(AgenciasLista agenciasLista) {
        String opcao;
        do {
            System.out.println("CADASTRO DE AGÊNCIAS");
            Agencia nova_agencia;

            do {
                nova_agencia = setDadosAgencia();
            } while (!Utilidades.validarDadosAgencia(nova_agencia, -1, agenciasLista));

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
            System.out.println("Informe o ID da agência a ser alterada: ");
            int agencia_id = scanner.nextInt();

            Agencia agencia = agenciasLista.listarAgenciaPorID(agencia_id);

            if (agencia != null) {
                System.out.println("AGÊNCIA ENCONTRADA\n");
                System.out.println(agencia);
                Agencia agencia_alterar;

                do {
                    agencia_alterar = setDadosAgencia();
                    is_valid = Utilidades.validarDadosAgencia(agencia_alterar, agencia_id, agenciasLista);

                    if (is_valid) {
                        escolha = Utilidades.confirmaOperacao();
                        if (escolha.equalsIgnoreCase("S")) {
                            agenciasLista.atualizarAgencia(agencia_id, agencia_alterar);
                            System.out.println("Dados da agência alterados com sucesso.");
                            continuar = "N";
                        }
                    }

                } while (!is_valid || continuar.equalsIgnoreCase("S"));
            }
            else Utilidades.mensagemConsultaNaoEncontrada();

            escolha = Utilidades.getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    public static void excluirAgencia(AgenciasLista agenciasLista) throws InputMismatchException {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("EXCLUSÃO DE AGÊNCIAS");
            System.out.println("Informe o número da agência a ser excluída: ");
            String agencia_numero = scanner.nextLine();

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
            else Utilidades.mensagemConsultaNaoEncontrada();

            escolha = Utilidades.getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    public static void consultarAgencia(AgenciasLista agenciasLista) throws InputMismatchException{
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("CONSULTAR AGÊNCIA");
            System.out.println("Informe o número da agência a ser pesquisada: ");
            String agencia_numero = scanner.nextLine();

            Agencia agencia = agenciasLista.listarAgenciaPorNumero(agencia_numero);

            if (agencia != null) {
                System.out.println("AGÊNCIA ENCONTRADA\n");
                System.out.println(agencia);
            }
            else Utilidades.mensagemConsultaNaoEncontrada();

            escolha = Utilidades.getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private static Agencia setDadosAgencia() throws InputMismatchException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o nome da agência: ");
        String nome = scanner.nextLine();

        System.out.println("Informe o número da agência: ");
        String numero = scanner.nextLine();

        Agencia agencia = new Agencia();
        agencia.Setup(numero, nome);

        return agencia;
    }

    public static void relatorioDeAgencias(AgenciasLista agenciasLista) {
        System.out.println("RELATÓRIO\n");
        agenciasLista.listarAgencias();

        Scanner scanner = new Scanner(System.in);
        System.out.println("APERTE QUALQUER LETRA + ENTER PARA CONTINUAR");
        scanner.next();
    }

    //////////////////////// Clientes ////////////////////////
    public static void cadastrarCliente(ClientesLista clientesLista) {
        String opcao;
        do {
            System.out.println("CADASTRO DE CLIENTES");
            Cliente novo_cliente;

            do {
                novo_cliente = setDadosCliente();
            } while (!Utilidades.validarDadosCliente(novo_cliente, -1, clientesLista));

            opcao = Utilidades.confirmaOperacao();
            if (opcao.equalsIgnoreCase("S")) {
                clientesLista.inserirCliente(novo_cliente);
                System.out.println("Cliente cadastrado com sucesso.");
            }
            opcao = Utilidades.getRepetirOperacao();

        } while (opcao.equalsIgnoreCase("S"));
    }

    public static void alterarCliente(ClientesLista clientesLista) throws InputMismatchException {
        String escolha;
        String continuar = "S";
        boolean is_valid;
        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("ALTERAÇÃO DE CLIENTE");
            System.out.println("Informe o ID do cliente a ser alterado: ");
            int cliente_id = scanner.nextInt();

            Cliente cliente = clientesLista.listarClientePorID(cliente_id);

            if (cliente != null) {
                System.out.println("CLIENTE ENCONTRADO\n");
                Cliente cliente_alterar;

                do {
                    cliente_alterar = setDadosCliente();
                    is_valid = Utilidades.validarDadosCliente(cliente_alterar, cliente_id, clientesLista);

                    if (cliente_alterar.getCpf().equalsIgnoreCase(cliente.getCpf()) && is_valid) {
                        escolha = Utilidades.confirmaOperacao();
                        if (escolha.equalsIgnoreCase("S")) {
                            clientesLista.atualizarCliente(cliente_id, cliente_alterar);
                            System.out.println("Dados do cliente alterados com sucesso.");
                            continuar = "N";
                        }
                    }
                    else {
                        System.out.println("O cpf do cliente não pode ser alterado! " +
                                "Para tal deve-se excluí-lo e recadastrá-lo");
                        is_valid = false;
                        continuar = Utilidades.getRepetirOperacao();
                    }
                } while (!is_valid || continuar.equalsIgnoreCase("S"));
            }
            else Utilidades.mensagemConsultaNaoEncontrada();

            escolha = Utilidades.getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    public static void excluirCliente(ClientesLista clientesLista, ContasLista contasLista) throws InputMismatchException {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("EXCLUSÃO DE CLIENTES");
            System.out.println("Informe o ID do cliente a ser excluído: ");
            int cliente_id = scanner.nextInt();

            Cliente cliente = clientesLista.listarClientePorID(cliente_id);
            List<Conta> contas = contasLista.listarContasPorClienteID(cliente_id);

            if (cliente != null) {
                if (contas.size() == 0){
                    System.out.println("CLIENTE ENCONTRADO\n");
                    System.out.println(cliente);
                    escolha = Utilidades.confirmaOperacao();
                    if (escolha.equalsIgnoreCase(("S"))) {
                        clientesLista.excluirCliente(cliente_id);
                        System.out.println("CLIENTE DELETADO!\n");
                    }
                }
                else {
                    System.out.println("O cliente possui as seguintes contas abertas: ");
                    contas.forEach(s -> System.out.printf("- %s%n", s));
                }
            }
            else Utilidades.mensagemConsultaNaoEncontrada();

            escolha = Utilidades.getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    public static void consultarCliente(ClientesLista clientesLista) throws InputMismatchException{
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("CONSULTAR CLIENTE");
            System.out.println("Informe o cpf do cliente a ser pesquisado: ");
            String cliente_cpf = scanner.nextLine();

            Cliente cliente = clientesLista.listarClientePorCpf(cliente_cpf);

            if (cliente != null) {
                System.out.println("CLIENTE ENCONTRADO\n");
                System.out.println(cliente);
            }
            else Utilidades.mensagemConsultaNaoEncontrada();

            escolha = Utilidades.getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private static Cliente setDadosCliente() throws InputMismatchException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.println("Informe a idade do cliente: ");
        short idade = Short.parseShort(scanner.nextLine());

        System.out.println("Informe o cpf do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.Setup(nome, idade, cpf);

        return cliente;
    }

    public static void relatorioDeClientes(ClientesLista clientesLista) {
        System.out.println("RELATÓRIO\n");
        clientesLista.listarClientes();

        Scanner scanner = new Scanner(System.in);
        System.out.println("APERTE QUALQUER LETRA + ENTER PARA CONTINUAR");
        scanner.next();
    }

    //////////////////////// Contas ////////////////////////
    public static void cadastrarConta(ContasLista contasLista, ClientesLista clientesLista, AgenciasLista agenciasLista) {
        String opcao;
        do {
            System.out.println("ABERTURA DE CONTAS");
            ContaDados data;
            Conta nova_conta;
            do {
                data = setDadosConta();
                nova_conta = Utilidades.validarDadosConta(data, contasLista, clientesLista, agenciasLista, "cadastrar");
            } while (nova_conta == null);

            opcao = Utilidades.confirmaOperacao();
            if (opcao.equalsIgnoreCase("S")) {
                contasLista.inserirConta(nova_conta);
                System.out.println("Conta aberta com sucesso.");
            }
            opcao = Utilidades.getRepetirOperacao();

        } while (opcao.equalsIgnoreCase("S"));
    }

    public static void excluirConta(ContasLista contasLista) throws InputMismatchException {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("EXCLUSÃO DE CLIENTES");
            System.out.println("Informe o Nº da conta a ser fechada: ");
            String conta_numero = scanner.nextLine();

            Conta conta = contasLista.listarContaPorNumero(conta_numero);

            if (conta != null) {
                System.out.println("CONTA ENCONTRADA\n");
                System.out.println(conta);
                escolha = Utilidades.confirmaOperacao();
                if (escolha.equalsIgnoreCase(("S"))) {
                    contasLista.excluirConta(conta.getID());
                    System.out.println("CONTA FECHADA!\n");
                }
            }
            else Utilidades.mensagemConsultaNaoEncontrada();


            escolha = Utilidades.getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    public static void consultarConta(ContasLista contasLista, ClientesLista clientesLista) throws InputMismatchException{
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("CONSULTAR CONTA");
            System.out.println("Informe o Nº da conta a ser pesquisada: ");
            String numero = scanner.nextLine();

            Conta conta = contasLista.listarContaPorNumero(numero);

            if (conta != null) {
                System.out.println("CONTA ENCONTRADA\n");
                System.out.println(conta);
            }
            else Utilidades.mensagemConsultaNaoEncontrada();

            escolha = Utilidades.getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private static ContaDados setDadosConta() throws InputMismatchException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o CPF do titular: ");
        String cpf = scanner.nextLine();

        System.out.println("Escolha o tipo de conta. \n1- Corrente \n2- Poupança: ");
        String tipo_conta = Short.parseShort(scanner.nextLine()) == 1 ? "Corrente" : "Poupança";

        System.out.println("Informe o número da agência: ");
        String numero = scanner.nextLine();

        return new ContaDados(tipo_conta, cpf, numero);
    }

    public static void relatorioDeContas(ContasLista contasLista) {
        System.out.println("RELATÓRIO\n");
        contasLista.listarContas();

        Scanner scanner = new Scanner(System.in);
        System.out.println("APERTE QUALQUER LETRA + ENTER PARA CONTINUAR");
        scanner.next();
    }
}