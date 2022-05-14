import Objetos.AgenciasLista;
import Utilidades.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.InputMismatchException;

public class Main {
    private AgenciasLista agenciasLista;

    public Main() {
    }

    public static void main(String[] args) {
        Main app;
        try {
            app = new Main();
            app.setAgencias();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        app.tituloMenu();
        app.telaPrincipal();
    }

    public void setAgencias() throws IOException {
        this.agenciasLista = new AgenciasLista();
        this.agenciasLista.carregarDados();
    }

    private void tituloMenu() {
        System.out.println("...Controle Bancário...\n");
    }

    private void opcaoInvalida() {
        System.out.println("Opção inválida.");
    }
    private void telaPrincipal() {
        int opcao = -1;
        do {
            try {
                opcao = opcaoMenuPadrao();
                this.tituloMenu();
                switch (opcao) {
                    case 1 -> menuGerenciamentoAgencias();
                    case 4 -> menuOpcaoRelatorios();
                    case 0 -> System.out.println("Saindo do Sistema!");
                    default -> opcaoInvalida();
                }
                File file = new File(new File(".").getCanonicalPath() + "/src/system.txt");
//            PrintWriter writer = new PrintWriter(file);
//            writer.print("");
//            writer.close();
                this.agenciasLista.salvarDados();
            }
            catch (IOException | InputMismatchException ignored){
            }
        } while (opcao != 0);
    }

    private int opcaoMenuPadrao() {
        int opcao;
        System.out.println("""
				           BANCO DIGITAL LTDA.
					SISTEMA DE GERENCIAMENTO BANCÁRIO
								
						   MENU PRINCIPAL
				1 - GERENCIAMENTO DE AGÊNCIAS
				2 - GERENCIAMENTO DE CLIENTES
				3 - GERENCIAMENTO DE CONTAS
				4 - RELATÓRIOS
				0 - FINALIZAR
				OPÇÃO:\040""");
        opcao = Utilidades.getEscolhaMenu();
        return opcao;
    }

    private void menuGerenciamentoAgencias() {
        int opcao;
        do {
            System.out.println("""
					  GERENCIAMENTO DE AGÊNCIAS
								
							    Menu
					1 - INCLUSÃO
					2 - ALTERAÇÃO
					3 - EXCLUSÃO
					4 - CONSULTA
					0 - RETORNAR
					OPÇÃO:\040""");
            opcao = Utilidades.getEscolhaMenu();
            switch (opcao) {
                case 1 -> Operacoes.cadastrarAgencia(this.agenciasLista);
                case 2 -> Operacoes.alterarAgencia(this.agenciasLista);
                case 3 -> Operacoes.excluirAgencia(this.agenciasLista);
                case 4 -> Operacoes.consultarAgencia(this.agenciasLista);
                case 0 -> System.out.println("Retornando para o Menu Principal...\n");
                default -> opcaoInvalida();
            }
        } while (opcao != 0);
    }

    private void menuOpcaoRelatorios() {
        int opcao;
        do {
            System.out.println("""
					         RELATÓRIOS
								
							    Menu
					1 - AGÊNCIAS
					2 - CLIENTES
					3 - CONTAS
					0 - RETORNAR
					OPÇÃO:\040""");
            opcao = Utilidades.getEscolhaMenu();
            switch (opcao) {
                case 1 -> Operacoes.relatorioDeAgencias(this.agenciasLista);
                case 0 -> System.out.println("Retornando para o Menu Principal...\n");
                default -> opcaoInvalida();
            }
        } while (opcao != 0);
    }
}