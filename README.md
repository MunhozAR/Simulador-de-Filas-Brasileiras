# A3

Nome e RA dos integrantes:

Felipe Mori Ferreira RA:822129885 
Carlos Armando Munhoz Vilela RA: 822138213 
Giulio Enrico Miranda Maciotta RA:822159355


Fase 1: Gerenciamento de Relações Pessoais
A classe RedeDePessoas foi criada para organizar as informações sobre indivíduos e seus relacionamentos. Ela contém um atributo chamado conexões, que é um Map<String, Set<String>>. Nele, cada chave representa o nome de uma pessoa e o conjunto associado (Set<String>) armazena os nomes de seus conhecidos.

Para verificar a existência de um nome, o sistema busca em duas frentes:

Entre as chaves do mapa (conexões).
Dentro dos conjuntos de nomes conhecidos armazenados no mapa.
Se o nome for localizado em qualquer um desses locais, uma confirmação de sua presença é retornada. Caso contrário, é informado que o nome não foi encontrado.

Para verificar o conhecimento entre duas pessoas, o processo é semelhante, mas exige que os nomes estejam interligados dentro da mesma relação chave-valor no mapa. Para isso, o sistema percorre as entradas do mapa, procurando a coexistência dos dois nomes na chave ou em suas respectivas listas de conhecidos, e retorna o resultado apropriado.

Uma função auxiliar, verificaConexaoEntrePessoas, foi desenvolvida para a Fase 2. Ela emprega a mesma lógica de verificação de conhecimento, mas seu retorno é um booleano, otimizando seu uso na etapa seguinte.

Fase 2: Organização de Filas com Base em Conexões
A classe FilaBrasileira foi desenvolvida para gerenciar a disposição de pessoas em filas, considerando suas conexões. Ela é composta por dois atributos:

Um objeto da classe RedeDePessoas.
Um mapa (Map<String, List<String>>) onde a chave é o nome da fila e o valor é uma lista ordenada dos nomes das pessoas que a compõem.
As funcionalidades básicas, como criar fila, atender, desistir e imprimir as filas, utilizam métodos padrão do Java, como forEach para percorrer e exibir o conteúdo do mapa. A função chegada recebe uma lista de nomes de pessoas recém-chegadas e, por meio de um loop, invoca individualmente a função adicionaNaFila, que contém a lógica para inserir os nomes conforme as regras de negócio estabelecidas.

A função adicionaNaFila opera da seguinte maneira:

Recebe como parâmetro o nome da pessoa a ser adicionada.

Internamente, utiliza quatro variáveis auxiliares:

Para armazenar a chave da melhor fila geral/de desconhecidos.
Para armazenar a chave da melhor fila de conhecidos.
Para guardar o comprimento da menor fila geral.
Para registrar a posição de inserção do nome caso um conhecido seja encontrado.
Através de um loop externo, percorre as chaves do mapa de filas.

Para cada fila no mapa, um novo loop interno é executado:

Cada iteração interna verifica se a pessoa atual na fila conhece a pessoa que será inserida (utilizando a função verificaConexaoEntrePessoas).
Se um conhecido for encontrado, verifica-se se uma posição já havia sido salva naquela mesma fila:
Se sim, apenas a posição é atualizada.
Caso contrário (em uma nova fila), é verificado se a posição encontrada nesta fila é melhor do que a posição salva anteriormente:
Se for melhor, a posição e o nome da nova fila são atualizados.
Caso contrário, os valores anteriores são mantidos.
Após percorrer todas as pessoas na fila atual, verifica-se se esta fila é a menor até o momento; se for, sua chave e tamanho são armazenados.
Depois de percorrer todas as filas, duas condições são avaliadas:

Se uma posição após um amigo foi encontrada E essa posição é menor do que o comprimento da menor fila geral:
Se sim, o nome é adicionado logo após o seu conhecido.
Caso contrário, o nome é adicionado à melhor fila geral.
