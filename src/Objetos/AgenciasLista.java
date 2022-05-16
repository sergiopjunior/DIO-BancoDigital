package Objetos;

import java.io.*;

class NoAgencia {
    private Agencia elemento;
    private NoAgencia anterior;
    private NoAgencia proximo;

    public Agencia getElemento() {
        return elemento;
    }

    public void setElemento(Agencia elemento) {
        this.elemento = elemento;
    }

    public NoAgencia getAnterior() {
        return anterior;
    }

    public void setAnterior(NoAgencia anterior) {
        this.anterior = anterior;
    }

    public NoAgencia getProximo() {
        return proximo;
    }

    public void setProximo(NoAgencia proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return this.getElemento().toString();
    }
}

public class AgenciasLista extends Lista {
    private NoAgencia primeiro_no;
    private NoAgencia ultimo_no;

    public AgenciasLista() {
    }

    public void carregarDados() throws IOException {
        String path = new File (".").getCanonicalPath();
        File file = new File(path + "/src/agencias.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(" - ");
            Agencia agencia = new Agencia(Short.parseShort(data[2]), data[1]);
            agencia.setID(Integer.parseInt(data[0]));
            this.inserirAgencia(agencia);
        }
        file = new File(path + "/src/system.txt");
        br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            String[] data = line.split(" - ");
            if (data[0].equalsIgnoreCase("AgenciaNextID")) {
                this.setProximoCodigo(Integer.parseInt(data[1]));
                break;
            }
        }
        br.close();
    }

    public void salvarDados() throws IOException {
        String path = new File (".").getCanonicalPath();

        FileWriter fw = new FileWriter(path + "/src/system.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.format("AgenciaNextID - %d", this.getProximoCodigo()));

        fw = new FileWriter(path + "/src/agencias.txt", false);
        bw = new BufferedWriter(fw);

        NoAgencia cabeca = this.primeiro_no;
        while (cabeca != null) {
            bw.write(cabeca.getElemento().dataString());
            cabeca = cabeca.getProximo();
        }
        bw.close();
    }
    public boolean contem(Agencia agencia) {
        NoAgencia cabeca = this.primeiro_no;

        while (cabeca != null) {
            if (cabeca.getElemento() == agencia) return true;
            cabeca = cabeca.getProximo();
        }
        return false;
    }

    private NoAgencia buscarNoPorNome(String produto_nome) {
        NoAgencia cabeca = this.primeiro_no;

        while(cabeca != null) {
            if (cabeca.getElemento().getNome().equalsIgnoreCase(produto_nome)) break;
            cabeca = cabeca.getProximo();
        }

        return cabeca;
    }

    private NoAgencia buscarNoPorNumero(short numero) {
        NoAgencia cabeca = this.primeiro_no;

        while(cabeca != null) {
            if (cabeca.getElemento().getNumero() == numero) break;
            cabeca = cabeca.getProximo();
        }

        return cabeca;
    }

    public void inserirAgencia(Agencia nova_agencia) {
        if (nova_agencia.getID() <= 0) nova_agencia.setID(this.getProximoCodigo());
        NoAgencia novo_no = new NoAgencia();
        novo_no.setElemento(nova_agencia);

        if (this.getTamanho() == 0) this.primeiro_no = novo_no;
        else {
            this.ultimo_no.setProximo(novo_no);
            novo_no.setAnterior(this.ultimo_no);
        }
        this.ultimo_no = novo_no;
        this.setTamanho(1);
        this.setProximoCodigo();
    }

    public void excluirAgencia(short agencia_numero) {
        NoAgencia no_excluir = this.buscarNoPorNumero(agencia_numero);
        NoAgencia no_anterior;
        NoAgencia no_proximo;

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

    public void atualizarAgencia(short agencia_numero, Agencia agencia_alterada) {
        NoAgencia no_agencia = buscarNoPorNumero(agencia_numero);

        no_agencia.getElemento().setNome(agencia_alterada.getNome());
        no_agencia.getElemento().setNumero(agencia_alterada.getNumero());
    }

    public Agencia listarAgenciaPorNome(String agencia_nome) {
        NoAgencia no_agencia = this.buscarNoPorNome(agencia_nome);
        Agencia result = null;

        if (no_agencia != null) result = no_agencia.getElemento();

        return result;
    }

    public Agencia listarAgenciaPorNumero(short agencia_numero) {
        NoAgencia no_agencia = this.buscarNoPorNumero(agencia_numero);
        Agencia result = null;

        if (no_agencia != null) result = no_agencia.getElemento();

        return result;
    }

    public void listarAgencias() {
        if (this.getTamanho() > 0) {
            NoAgencia cabeca = this.primeiro_no;

            while (cabeca != null) {
                System.out.println(cabeca);
                cabeca = cabeca.getProximo();
            }
        }
        else {
            System.out.println("Não existem agência cadastradas. Cadastre uma agência para gerar um relatório.");
        }
    }
}