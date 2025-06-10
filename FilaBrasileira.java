import java.util.*;

class FilaBrasileira {
    private LinkedHashMap<String, List<String>> filas;
    private GrupoGeralPessoas grupoGeralPessoas;

    public FilaBrasileira(GrupoGeralPessoas grupoGeralPessoas) {
        this.filas = new LinkedHashMap<>();
        this.grupoGeralPessoas = grupoGeralPessoas;
    }

    public void criaFila(String id, List<String> pessoas) {
        List<String> fila = new LinkedList<>(pessoas);
        filas.put(id, fila);
    }

    public void atendeFilas(List<String> ids) {
        for (String id : ids) {
            filas.get(id).remove(0);
        }
    }

    public void chegou(List<String> nomes) {
        for (String nome : nomes) {
            adicionaNaFila(nome);
        }
    }

    private void adicionaNaFila(String nome) {
        String melhorFilaDeDesconhecidos = "";
        String melhorFilaDeConhecidos = "";
        int comprimentoMenorFila = Integer.MAX_VALUE;
        int posicaoAposAmigoEncontrado = Integer.MAX_VALUE;
        
        for (String filaAtual : filas.keySet()) {
            List<String> conteudoFila = filas.get(filaAtual);
            for (int i = 0; i < conteudoFila.size(); i++) {
                String pessoaNaFila = conteudoFila.get(i);
                boolean conheceAlguemNaFila = grupoGeralPessoas.verificaconhecimentoPessoas(nome, pessoaNaFila);

                if (conheceAlguemNaFila) {
                    if (melhorFilaDeConhecidos.equals(filaAtual)) {
                        posicaoAposAmigoEncontrado = i + 1;
                    } else if (i + 1 < posicaoAposAmigoEncontrado && !melhorFilaDeConhecidos.equals(filaAtual)) {
                        melhorFilaDeConhecidos = filaAtual;
                        posicaoAposAmigoEncontrado = i + 1;
                    }
                }
            }
            if (conteudoFila.size() <= comprimentoMenorFila) {
                melhorFilaDeDesconhecidos = filaAtual;
                comprimentoMenorFila = conteudoFila.size();
            }
        }
        if (posicaoAposAmigoEncontrado < Integer.MAX_VALUE && posicaoAposAmigoEncontrado < comprimentoMenorFila) {
            filas.get(melhorFilaDeConhecidos).add(posicaoAposAmigoEncontrado, nome);
            return;
        }
        filas.get(melhorFilaDeDesconhecidos).add(nome);
    }

    public void desiste(List<String> nomes) {
        for (String nome : nomes) {
            filas.values().forEach(fila -> fila.remove(nome));
        }
    }

    public void imprimeFilas() {
        filas.forEach((id, fila) -> System.out.println("#" + id + " " + fila));
    }
}
