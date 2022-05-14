package Objetos;

import java.io.*;
import java.util.Objects;

class NoCliente {
    private Cliente elemento;
    private NoCliente anterior;
    private NoCliente proximo;

    public Cliente getElemento() {
        return elemento;
    }

    public void setElemento(Cliente elemento) {
        this.elemento = elemento;
    }

    public NoCliente getAnterior() {
        return anterior;
    }

    public void setAnterior(NoCliente anterior) {
        this.anterior = anterior;
    }

    public NoCliente getProximo() {
        return proximo;
    }

    public void setProximo(NoCliente proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return this.getElemento().toString();
    }
}

public class ClientesLista {
    private NoCliente primeiro_no;
    private NoCliente ultimo_no;
    private int tamanho = 0;
    private int proximo_codigo = 1;


    public ClientesLista() {
    }

    public int getTamanho() {
        return tamanho;
    }

    private void setTamanho(int valor) {
        this.tamanho += valor;
    }

    private int getProximoCodigo() {
        return proximo_codigo;
    }

    private void setProximoCodigo() {
        this.proximo_codigo += 1;
    }

    public void carregarDados() throws IOException {
        String path = new File (".").getCanonicalPath();
        File file = new File(path + "/src/clientes.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(" - ");
            Cliente cliente = new Cliente(data[1], Short.parseShort(data[2]), data[3]);
            cliente.setID(Integer.parseInt(data[0]));
            this.inserirCliente(cliente);
        }
        file = new File(path + "/src/system.txt");
        br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            String[] data = line.split(" - ");
            if (data[0].equalsIgnoreCase("ClienteNextID")) {
                this.proximo_codigo = Integer.parseInt(data[1]);
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
        bw.write(String.format("ClienteNextID - %d", this.proximo_codigo));

        fw  = new FileWriter(path + "/src/clientes.txt", false);
        bw = new BufferedWriter(fw);

        NoCliente cabeca = this.primeiro_no;
        while (cabeca != null) {
            bw.write(cabeca.getElemento().dataString());
            cabeca = cabeca.getProximo();
        }
        bw.close();
    }
    public boolean contem(Cliente cliente) {
        NoCliente cabeca = this.primeiro_no;

        while (cabeca != null) {
            if (cabeca.getElemento() == cliente) return true;
            cabeca = cabeca.getProximo();
        }
        return false;
    }

    private NoCliente buscarNoPorID(int ID) {
        NoCliente cabeca = this.primeiro_no;

        while(cabeca != null) {
            if (cabeca.getElemento().getID() == ID) break;
            cabeca = cabeca.getProximo();
        }

        return cabeca;
    }

    private NoCliente buscarNoPorNome(String produto_nome) {
        NoCliente cabeca = this.primeiro_no;

        while(cabeca != null) {
            if (cabeca.getElemento().getNome().equalsIgnoreCase(produto_nome)) break;
            cabeca = cabeca.getProximo();
        }

        return cabeca;
    }

    private NoCliente buscarNoPorCpf(String cpf) {
        NoCliente cabeca = this.primeiro_no;

        while(cabeca != null) {
            if (cabeca.getElemento().getCpf().equalsIgnoreCase(cpf)) break;
            cabeca = cabeca.getProximo();
        }

        return cabeca;
    }

    public void inserirCliente(Cliente novo_cliente) {
        if (novo_cliente.getID() <= 0) novo_cliente.setID(this.getProximoCodigo());
        NoCliente novo_no = new NoCliente();
        novo_no.setElemento(novo_cliente);

        if (this.getTamanho() == 0) this.primeiro_no = novo_no;
        else {
            this.ultimo_no.setProximo(novo_no);
            novo_no.setAnterior(this.ultimo_no);
        }
        this.ultimo_no = novo_no;
        this.setTamanho(1);
        this.setProximoCodigo();
    }

    public void excluirCliente(int cliente_id) {
        NoCliente no_excluir = this.buscarNoPorID(cliente_id);
        NoCliente no_anterior;
        NoCliente no_proximo;

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

    public void atualizarCliente(int ID, Cliente cliente_alterado) {
        NoCliente no_cliente = buscarNoPorID(ID);

        no_cliente.getElemento().setNome(cliente_alterado.getNome());
        no_cliente.getElemento().setIdade(cliente_alterado.getIdade());
    }

    public Cliente listarClientePorID(int ID) {
        NoCliente no_cliente = this.buscarNoPorID(ID);
        Cliente result = null;

        if (no_cliente != null) result = no_cliente.getElemento();

        return result;
    }

    public Cliente listarClientePorNome(String cliente_nome) {
        NoCliente no_cliente = this.buscarNoPorNome(cliente_nome);
        Cliente result = null;

        if (no_cliente != null) result = no_cliente.getElemento();

        return result;
    }

    public Cliente listarClientePorCpf(String cliente_cpf) {
        NoCliente no_cliente = this.buscarNoPorCpf(cliente_cpf);
        Cliente result = null;

        if (no_cliente != null) result = no_cliente.getElemento();

        return result;
    }

    public void listarClientes() {
        if (this.getTamanho() > 0) {
            NoCliente cabeca = this.primeiro_no;

            while (cabeca != null) {
                System.out.println(cabeca);
                cabeca = cabeca.getProximo();
            }
        }
        else {
            System.out.println("Não existem clientes cadastrados. Cadastre um cliente para gerar um relatório.");
        }
    }
}
