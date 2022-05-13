package Utilidades;

import Objetos.AgenciasLista;
import Objetos.Agencia;

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

    public static boolean validarDadosAgencia(Agencia agencia, AgenciasLista agenciasLista, String operacao) {
        short erros = 0;
        String mensagemDeErro = "Os seguintes critérios de validação não foram atendidos:\n";

        String nome = agencia.getNome();
        short numero = agencia.getNumero();

        if (operacao.equalsIgnoreCase("cadastrar") || operacao.equalsIgnoreCase("atualizar")) {
            if (agenciasLista.listarAgenciaPorNome(nome) != null) {
                mensagemDeErro += String.format("- Agência com nome \"%s\" já cadastrada\n", nome);
                erros += 1;
            }
        }
        if (numero <= 0) {
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
}
