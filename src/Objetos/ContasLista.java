package Objetos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContasLista extends Lista {
    private No<Conta> primeiro_no;
    private No<Conta> ultimo_no;

    public ContasLista() {
    }

    public void carregarDados() throws IOException {
        String path = new File (".").getCanonicalPath();
        File file = new File(path + "/src/contas.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(" - ");
            Conta conta = null;
            int id = Integer.parseInt(data[0]);
            int clienteID = Integer.parseInt(data[1]);
            String numero = data[2];
            short agenciaID = Short.parseShort(data[3]);
            String tipo_conta = data[4];
            double saldo = Double.parseDouble(data[5]);

            if (tipo_conta.equalsIgnoreCase("Poupança"))
                conta = new ContaPoupanca();
            else if (tipo_conta.equalsIgnoreCase("Corrente"))
                conta = new ContaCorrente();

            if (conta != null) {
                conta.Setup(id, agenciaID, clienteID, saldo);
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

        No<Conta> cabeca = this.primeiro_no;
        while (cabeca != null) {
            bw.write(cabeca.getElemento().dataString());
            cabeca = cabeca.getProximo();
        }
        bw.close();
    }
    public boolean contem(Conta conta) {
        No<Conta> cabeca = this.primeiro_no;

        while (cabeca != null) {
            if (cabeca.getElemento() == conta) return true;
            cabeca = cabeca.getProximo();
        }
        return false;
    }

    private No<Conta> buscarNoPorID(int ID) {
        No<Conta> cabeca = this.primeiro_no;

        while(cabeca != null) {
            if (cabeca.getElemento().getID() == ID) break;
            cabeca = cabeca.getProximo();
        }

        return cabeca;
    }

    private No<Conta> buscarNoPorClienteID(int cliente_id) {
        No<Conta> cabeca = this.primeiro_no;

        while(cabeca != null) {
            if (cabeca.getElemento().getClienteID() == cliente_id) break;
            cabeca = cabeca.getProximo();
        }

        return cabeca;
    }

    private No<Conta> buscarNoPorNumero(String numero) {
        No<Conta> cabeca = this.primeiro_no;

        while(cabeca != null) {
            if (cabeca.getElemento().getNumero().equalsIgnoreCase(numero)) break;
            cabeca = cabeca.getProximo();
        }

        return cabeca;
    }

    public void inserirConta(Conta nova_conta) {
        No<Conta> nova_no = nova_conta.getTipoConta().equalsIgnoreCase("Corrente") ?
                new No<>(ContaCorrente::new) : new No<>(ContaPoupanca::new);
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
        No<Conta> no_excluir = this.buscarNoPorID(conta_id);
        No<Conta> no_anterior;
        No<Conta> no_proximo;

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
        No<Conta> no_conta = this.buscarNoPorID(ID);
        Conta result = null;

        if (no_conta != null) result = no_conta.getElemento();

        return result;
    }

    public Conta listarContaPorNumero(String conta_numero) {
        No<Conta> no_conta = this.buscarNoPorNumero(conta_numero);
        Conta result = null;

        if (no_conta != null) result = no_conta.getElemento();

        return result;
    }

    public List<Conta> listarContasPorClienteID(int cliente_id) {
        List<Conta> contas = new ArrayList<>();
        No<Conta> cabeca = this.primeiro_no;

        while (cabeca != null) {
            contas.add(cabeca.getElemento());
            cabeca = cabeca.getProximo();
        }

        return contas;
    }

    public void listarContas() {
        if (this.getTamanho() > 0) {
            No<Conta> cabeca = this.primeiro_no;

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
