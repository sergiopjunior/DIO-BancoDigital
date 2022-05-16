package Objetos;

import java.io.*;

class NoConta {
    private Conta elemento;
    private NoConta anterior;
    private NoConta proximo;

    public Conta getElemento() {
        return elemento;
    }

    public void setElemento(Conta elemento) {
        this.elemento = elemento;
    }

    public NoConta getAnterior() {
        return anterior;
    }

    public void setAnterior(NoConta anterior) {
        this.anterior = anterior;
    }

    public NoConta getProximo() {
        return proximo;
    }

    public void setProximo(NoConta proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return this.getElemento().toString();
    }
}

public class ContasLista extends Lista {
    private NoConta primeiro_no;
    private NoConta ultimo_no;

    public ContasLista() {
    }

    public void carregarDados() throws IOException {
        String path = new File (".").getCanonicalPath();
        File file = new File(path + "/src/contas.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(" - ");
            // this.getID(), this.getClienteID(), this.getNumero(), this.getAgenciaID(), this.getTipo_conta(), this.getSaldo()
            Conta conta = null;
            int id = Integer.parseInt(data[0]);
            int clienteID = Integer.parseInt(data[1]);
            String numero = data[2];
            int agenciaID = Integer.parseInt(data[3]);
            String tipo_conta = data[4];
            double saldo = Double.parseDouble(data[5]);

            if (tipo_conta.equalsIgnoreCase("Poupança"))
                conta = new ContaPoupanca(clienteID, numero, agenciaID, saldo);
            else if (tipo_conta.equalsIgnoreCase("Corrente"))
                conta = new ContaCorrente(clienteID, numero, agenciaID, saldo);

            if (conta != null) {
                conta.setID(Integer.parseInt(data[0]));
                this.inserirConta(conta);
            }
        }
        file = new File(path + "/src/system.txt");
        br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            String[] data = line.split(" - ");
            if (data[0].equalsIgnoreCase("ContaNextID")) {
                this.setProximoCodigo(Integer.parseInt(data[1]));
                System.out.println(data[1]);
                break;
            }
        }
        br.close();
    }

    public void salvarDados() throws IOException {
        String path = new File (".").getCanonicalPath();

        FileWriter fw = new FileWriter(path + "/src/system.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.format("ContaNextID - %d", this.getProximoCodigo()));

        fw  = new FileWriter(path + "/src/contas.txt", false);
        bw = new BufferedWriter(fw);

        NoConta cabeca = this.primeiro_no;
        while (cabeca != null) {
            bw.write(cabeca.getElemento().dataString());
            cabeca = cabeca.getProximo();
        }
        bw.close();
    }
    public boolean contem(Conta conta) {
        NoConta cabeca = this.primeiro_no;

        while (cabeca != null) {
            if (cabeca.getElemento() == conta) return true;
            cabeca = cabeca.getProximo();
        }
        return false;
    }

    private NoConta buscarNoPorID(int ID) {
        NoConta cabeca = this.primeiro_no;

        while(cabeca != null) {
            if (cabeca.getElemento().getID() == ID) break;
            cabeca = cabeca.getProximo();
        }

        return cabeca;
    }

    private NoConta buscarNoPorClienteID(int cliente_id) {
        NoConta cabeca = this.primeiro_no;
//        Cliente cliente = clientesLista.listarClientePorCpf(cliente_cpf);
//        if (cliente == null) {
//            System.out.printf("Nenhum cliente com o CPF '%s' encontrado.%n", cliente_cpf);
//            return null
//        }
        while(cabeca != null) {
            if (cabeca.getElemento().getID() == cliente_id) break;
            cabeca = cabeca.getProximo();
        }

        return cabeca;
    }

    private NoConta buscarNoPorNumero(String numero) {
        NoConta cabeca = this.primeiro_no;

        while(cabeca != null) {
            if (cabeca.getElemento().getNumero().equalsIgnoreCase(numero)) break;
            cabeca = cabeca.getProximo();
        }

        return cabeca;
    }

    public void inserirConta(Conta nova_conta) {
        if (nova_conta.getID() <= 0) nova_conta.setID(this.getProximoCodigo());
        NoConta nova_no = new NoConta();
        nova_no.setElemento(nova_conta);

        if (this.getTamanho() == 0) this.primeiro_no = nova_no;
        else {
            this.ultimo_no.setProximo(nova_no);
            nova_no.setAnterior(this.ultimo_no);
        }
        this.ultimo_no = nova_no;
        this.setTamanho(1);
        this.setProximoCodigo();
    }

    public void excluirConta(int conta_id) {
        NoConta no_excluir = this.buscarNoPorID(conta_id);
        NoConta no_anterior;
        NoConta no_proximo;

        if (this.getTamanho() == 1) {
            this.primeiro_no = null;
            this.ultimo_no = null;
        }
        else if (no_excluir.toString().equalsIgnoreCase(this.ultimo_no.toString()))
        {
            no_anterior = this.ultimo_no.getAnterior();
            no_anterior.setProximo(null);
            this.ultimo_no = no_anterior;
        }
        else if (no_excluir.toString().equalsIgnoreCase(this.primeiro_no.toString()))
        {
            no_proximo = this.primeiro_no.getProximo();
            no_proximo.setAnterior(null);
            this.primeiro_no = no_proximo;
        }
        else {
            no_anterior = no_excluir.getAnterior();
            no_proximo = no_excluir.getProximo();

            no_anterior.setProximo(no_proximo);
            no_proximo.setAnterior(no_anterior);
        }

        this.setTamanho(-1);
        System.gc();
    }

    public Conta listarContaPorID(int ID) {
        NoConta no_conta = this.buscarNoPorID(ID);
        Conta result = null;

        if (no_conta != null) result = no_conta.getElemento();

        return result;
    }

    public Conta listarContaPorNumero(String conta_numero) {
        NoConta no_conta = this.buscarNoPorNumero(conta_numero);
        Conta result = null;

        if (no_conta != null) result = no_conta.getElemento();

        return result;
    }

    public Conta listarContaPorClienteID(int cliente_id) {
        NoConta no_conta = this.buscarNoPorClienteID(cliente_id);
        Conta result = null;

        if (no_conta != null) result = no_conta.getElemento();

        return result;
    }

    public void listarContas() {
        if (this.getTamanho() > 0) {
            NoConta cabeca = this.primeiro_no;

            while (cabeca != null) {
                System.out.println(cabeca);
                cabeca = cabeca.getProximo();
            }
        }
        else {
            System.out.println("Não existem contas cadastrados. Cadastre um conta para gerar um relatório.");
        }
    }
}
