
import java.util.Arrays;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        GrupoGeralPessoas grupoGeralPessoas = new GrupoGeralPessoas();
        FilaBrasileira filaBrasileira = new FilaBrasileira(grupoGeralPessoas);

        Parser parser = new Parser(new File("exemplo_entrada.txt"));
        processarComandos(parser, grupoGeralPessoas, filaBrasileira);
    }



    public static void processarComandos(Parser parser, GrupoGeralPessoas grupoGeral, FilaBrasileira filaBrasileira) {
        while (parser.hasNext()) {
            String line = parser.nextLine();
            String tokens[] = line.split(" ");
            String input = tokens[0];

            switch (input) {
                case "grupo:" -> grupoGeral.criarGrupo(tokens[1], Arrays.copyOfRange(tokens, 2, tokens.length));
                case "existe:" -> System.out.println(grupoGeral.verificarExistenciaPessoa(tokens[1]));
                case "conhece:" -> System.out.println(grupoGeral.printConhecimentoPessoas(tokens[1], tokens[2]));
                case "criaFila:" -> filaBrasileira.criaFila(tokens[1], Arrays.asList(Arrays.copyOfRange(tokens, 2, tokens.length)));
                case "atendeFila:" -> filaBrasileira.atendeFilas(Arrays.asList(Arrays.copyOfRange(tokens, 1, tokens.length)));
                case "chegou:" -> filaBrasileira.chegou(Arrays.asList(Arrays.copyOfRange(tokens, 1, tokens.length)));
                case "desiste:" -> filaBrasileira.desiste(Arrays.asList(Arrays.copyOfRange(tokens, 1, tokens.length)));
                case "imprime:" -> filaBrasileira.imprimeFilas();
            }
        }
    }
}
