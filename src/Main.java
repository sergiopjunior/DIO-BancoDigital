import Objetos.AgenciasLista;
import Utilidades.*;

public class Main {
    private final AgenciasLista agenciasLista = new AgenciasLista();
    public static void main(String[] args) {
        Main app = new Main();
        app.tituloMenu();
        app.telaPrincipal();
    }

    private void tituloMenu() {
        System.out.println("...Controle Bancário...\n");
    }

    private void opcaoInvalida() {
        System.out.println("Opção inválida.");
    }
    private void telaPrincipal() {
        int opcao;
        do {
            opcao = opcaoMenuPadrao();
            this.tituloMenu();
            switch (opcao) {
                case 1 -> menuCadastroAgencias();
                case 4 -> menuOpcaoRelatorios();
                case 0 -> System.out.println("Saindo do Sistema!");
                default -> opcaoInvalida();
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

    private void menuCadastroAgencias() {
        int opcao;
        do {
            System.out.println("""
					  GERENCIAMENTO DE AGÊNCIAS
								
							    Menu
					1 - INCLUSÃO
					2 - ALTERAÇÃO
					3 - CONSULTA
					4 - EXCLUSÃO
					0 - RETORNAR
					OPÇÃO:\040""");
            opcao = Utilidades.getEscolhaMenu();
            switch (opcao) {
                case 1 -> Operacoes.cadastrarAgencia(this.agenciasLista);
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