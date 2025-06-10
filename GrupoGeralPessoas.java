import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrupoGeralPessoas {
    public Map<String, Set<String>> pessoas;

    public GrupoGeralPessoas() {
        this.pessoas = new HashMap<>();
    }

    public void criarGrupo(String individuo, String[] listaConhecidos) {
        Set<String> amigosIndividuoMap = new HashSet<>(Arrays.asList(listaConhecidos));
        pessoas.put(individuo, amigosIndividuoMap);
    }

    public String verificarExistenciaPessoa(String nome) {
         for (Map.Entry<String, Set<String>> entry : pessoas.entrySet()) {
            if (entry.getValue().contains(nome)) {
                return nome + " existe!";
            }
         }
        return pessoas.containsKey(nome) ? nome + " existe!" : nome + " não existe!";
    }

    public String printConhecimentoPessoas(String nome1, String nome2) {
        for (Map.Entry<String, Set<String>> entry : pessoas.entrySet()) {
            String pessoaChaveAtual = entry.getKey();
            Set<String> pessoasConhecidasDaChave = entry.getValue();
            if(pessoaChaveAtual.equals(nome1) && pessoasConhecidasDaChave.contains(nome2)){
                return nome1 + " conhece " + nome2;
            } else if (pessoaChaveAtual.equals(nome2) && pessoasConhecidasDaChave.contains(nome1)){
                return nome1 + " conhece " + nome2;
            } else if (pessoasConhecidasDaChave.contains(nome1) && (pessoasConhecidasDaChave.contains(nome2))){
                return nome1 + " conhece " + nome2;
            }
        }
        return nome1 + " não conhece " + nome2;
    }

    public boolean verificaconhecimentoPessoas(String nome1, String nome2) {
        for (Map.Entry<String, Set<String>> entry : pessoas.entrySet()) {
            String pessoaChaveAtual = entry.getKey();
            Set<String> pessoasConhecidasDaChave = entry.getValue();
            if(pessoaChaveAtual.equals(nome1) && pessoasConhecidasDaChave.contains(nome2)){
                return true;
            } else if (pessoaChaveAtual.equals(nome2) && pessoasConhecidasDaChave.contains(nome1)){
                return true;
            } else if (pessoasConhecidasDaChave.contains(nome1) && (pessoasConhecidasDaChave.contains(nome2))){
                return true;
            }
        }
        return false;
    }
}
