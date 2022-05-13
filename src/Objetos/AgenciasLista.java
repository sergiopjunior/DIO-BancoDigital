package Objetos;

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

public class AgenciasLista {
    private NoAgencia primeiro_no;
    private NoAgencia ultimo_no;
    private int tamanho = 0;
    private int proximo_codigo = 0;


    public AgenciasLista() {
        this.setProximoCodigo();
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

    public void inserirAgencia(Agencia nova_agencia) {
        nova_agencia.setID(this.getProximoCodigo());
        NoAgencia novo_no = new NoAgencia();
        novo_no.setElemento(nova_agencia);

        if (this.getTamanho() == 0) {
            this.primeiro_no = novo_no;
        }
        else {
            this.ultimo_no.setProximo(novo_no);
            novo_no.setAnterior(this.ultimo_no);
        }
        this.ultimo_no = novo_no;
        this.setTamanho(1);
        this.setProximoCodigo();
    }

    public void excluirAgencia(String agencia_nome) {
        NoAgencia no_excluir = this.buscarNoPorNome(agencia_nome);
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

    public void atualizarAgencia(String agencia_nome, Agencia agencia_alterada) {
        NoAgencia no_agencia = buscarNoPorNome(agencia_nome);

        no_agencia.getElemento().setNome(agencia_alterada.getNome());
    }

    private NoAgencia buscarNoPorNome(String produto_nome) {
        NoAgencia cabeca = this.primeiro_no;

        while(cabeca != null) {
            if (cabeca.getElemento().getNome().equalsIgnoreCase(produto_nome)) {
                break;
            }
            cabeca = cabeca.getProximo();
        }

        return cabeca;
    }

    public Agencia listarAgenciaPorNome(String Agencia_nome) {
        NoAgencia no_agencia = this.buscarNoPorNome(Agencia_nome);
        Agencia result = null;

        if (no_agencia != null) {
            result = no_agencia.getElemento();
        }

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