package Funcionalidades;

import Objetos.*;

import java.util.Scanner;

public final class Utilidades {
    private Utilidades() {}

    public static String getRepetirOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("REPETIR OPERAÇÃO ( S/N ) ? ");
        escolha = scanner.next();
        return escolha;
    }

    public static String confirmaOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("CONFIRMA OPERAÇÃO ( S/N ) ?");
        escolha = scanner.next();
        return escolha;
    }

    public static int getEscolhaMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            return Integer.parseInt(scanner.next());
        }
        catch(Exception e) {
            return getEscolhaMenu();
        }
    }

    public static void mensagemConsultaNaoEncontrada() {
        System.out.println("Erro ao realizar consulta. Verifique os dados a serem pesquisados.");
    }

    public static boolean validarDadosAgencia(Agencia agencia, int agencia_id, AgenciasLista agenciasLista) {
        short erros = 0;
        String mensagemDeErro = "Os seguintes critérios de validação não foram atendidos:\n";

        Agencia compare_nome = agenciasLista.listarAgenciaPorNome(agencia.getNome());
        Agencia compare_numero = agenciasLista.listarAgenciaPorNumero(agencia.getNumero());

        if (compare_nome != null && compare_nome.getID() != agencia_id) {
                mensagemDeErro += String.format("- Agência com nome \"%s\" já cadastrada\n", agencia.getNome());
                erros += 1;
        }
        if (compare_numero != null && compare_numero.getID() != agencia_id) {
            mensagemDeErro += String.format("- Agência com número \"%s\" já cadastrada\n", agencia.getNumero());
            erros += 1;
        }
        if (Integer.parseInt(agencia.getNumero()) <= 0) {
            mensagemDeErro += "- O numero da agência deve ser maior que zero\n";
            erros += 1;
        }

        if (erros > 0)
        {
            System.out.println(mensagemDeErro);
            return false;
        }

        return true;
    }

    public static boolean validarDadosCliente(Cliente cliente, int cliente_id, ClientesLista clientesLista) {
        short erros = 0;
        String mensagemDeErro = "Os seguintes critérios de validação não foram atendidos:\n";

        Cliente compare_cpf = clientesLista.listarClientePorCpf(cliente.getCpf());

        if (compare_cpf != null && compare_cpf.getID() != cliente_id) {
                mensagemDeErro += String.format("- Cliente com cpf \"%s\" já cadastrado\n", cliente.getCpf());
                erros += 1;
        }
        if (cliente.getIdade() < 0) {
            mensagemDeErro += "- A idade deve ser maior ou igual a zero\n";
            erros += 1;
        }

        if (erros > 0)
        {
            System.out.println(mensagemDeErro);
            return false;
        }

        return true;
    }

    public static Conta validarDadosConta(ContaDados data, ContasLista contasLista, ClientesLista clientesLista, AgenciasLista agenciasLista, String operacao) {
        short erros = 0;
        String mensagemDeErro = "Os seguintes critérios de validação não foram atendidos:\n";

        Conta nova_conta = data.tipo_conta().equalsIgnoreCase("Corrente") ? new ContaCorrente() : new ContaPoupanca();
        Agencia agencia = agenciasLista.listarAgenciaPorNumero(data.agenciaNumero());
        Cliente cliente= clientesLista.listarClientePorCpf(data.cpf());

        if (agencia == null){
            mensagemDeErro += String.format("- A agência de Nº%s não existe\n", data.agenciaNumero());
            erros += 1;
        }
        if (cliente == null) {
            mensagemDeErro += String.format("- CPF-%s inválido", data.cpf());
            erros += 1;
        }
        if (erros == 0) {
            nova_conta.Setup(contasLista.getProximoCodigo(), cliente.getID(), agencia.getID(), 0);
        }

        if (operacao.equalsIgnoreCase("cadastrar") || operacao.equalsIgnoreCase("atualizar")) {
            if (contasLista.contem(nova_conta)) {
                mensagemDeErro += "- Conta já existente\n";
                erros += 1;
            }
        }

        if (erros > 0)
        {
            System.out.println(mensagemDeErro);
            return null;
        }

        return nova_conta;
    }
}
