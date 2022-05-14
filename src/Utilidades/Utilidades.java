package Utilidades;

import Objetos.AgenciasLista;
import Objetos.Agencia;
import Objetos.Cliente;
import Objetos.ClientesLista;

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

    public static void mensagemConsultasNaoEncontrada() {
        System.out.println("Nada foi encontrado.");
    }

    public static boolean validarDadosAgencia(Agencia agencia, AgenciasLista agenciasLista, String operacao) {
        short erros = 0;
        String mensagemDeErro = "Os seguintes critérios de validação não foram atendidos:\n";

        Agencia compare_nome = agenciasLista.listarAgenciaPorNome(agencia.getNome());
        Agencia compare_numero = agenciasLista.listarAgenciaPorNumero(agencia.getNumero());
        if (operacao.equalsIgnoreCase("cadastrar") || operacao.equalsIgnoreCase("atualizar")) {
            if (compare_nome != null && !compare_nome.equals(agencia)) {
                mensagemDeErro += String.format("- Agência com nome \"%s\" já cadastrada\n", agencia.getNome());
                erros += 1;
            }
        }
        else if (compare_numero != null && !compare_numero.equals(agencia)) {
            mensagemDeErro += String.format("- Agência com número \"%d\" já cadastrada\n", agencia.getNumero());
            erros += 1;
        }
        else if (agencia.getNumero() <= 0) {
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

    public static boolean validarDadosCliente(Cliente cliente, ClientesLista clientesLista, String operacao) {
        short erros = 0;
        String mensagemDeErro = "Os seguintes critérios de validação não foram atendidos:\n";

        Cliente compare_numero = clientesLista.listarClientePorCpf(cliente.getCpf());
        if (operacao.equalsIgnoreCase("cadastrar") || operacao.equalsIgnoreCase("atualizar")) {
            if (compare_numero != null && !compare_numero.equals(cliente)) {
                mensagemDeErro += String.format("- Cliente com cpf \"%s\" já cadastrado\n", cliente.getCpf());
                erros += 1;
            }
        }
        else if (cliente.getIdade() < 0) {
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
}
