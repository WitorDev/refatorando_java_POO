# Atividade 1 de boas práticas de programação

## Integrantes da equipe:

- LUIS FELIPE FERREIRA CALDARELLI
- MARCOS YUDI KIMURA
- RAFAEL EIJI HOKAMA UTIYAMA
- SIBELLY VITÓRIA ANTONIO
- THIAGO MOREIRA ANTONIASSI
- WITOR TENÃ

## Relatório de Refatoração
### Correções de Bugs e Regras de Negócio

- Controle de Estoque: Corrigida a vulnerabilidade onde emprestar o mesmo livro repetidamente deixava a quantidade negativa.
- Lógica de Adição: Adicionada uma condicional onde, ao tentar adicionar um livro já existente, o sistema agora aumenta a sua quantidade em vez de apenas emitir um aviso.

### Arquitetura e Orientação a Objetos

- Criação de Entidades: Criada a classe Livro para lidar com maior complexidade , incluindo o atributo de quantidade.
- Separação de Responsabilidades (MVC): Sugerida a extração da função carregarLivros() para outro arquivo, seguindo as diretrizes do padrão MVC.
- Camada de Serviço: Criadas as classes LivroServico e Biblioteca Serviço para isolar operações essenciais como buscar e guardar livros.
- Ajuste de Modificadores: Remoção do uso excessivo de private e static.

  ### Clean Code e Nomenclatura

  - Nomes Autoexplicativos: Variáveis e classes instanciadas foram renomeadas para refletir claramente sua intenção:
File f alterado para File arquivo.
1. Scanner fileSc alterado para Scanner scannerArquivo.
2. int opDeEntrada alterado para int opcaoDeEntrada.
3. PrintWriter pw alterado para PrintWriter printWriter.
4. Variável genérica l alterada para livros.
5. salvarLivros() renomeado para salvaLivro(), pois a função salva apenas um por vez.

- Documentação: Observado que havia poucos comentários no código original.

### Refatoração de Funções e Fluxo de Controle

 -  Menu Principal (main) : Substituída a estrutura while opcaoDeEntrada por um switch case. A função de salvar livros foi movida para o começo deste bloco. Estas alterações contaram com a participação de Luis.
 -  Função carregarLivros():
1. Removido bloco try catch desnecessário que nunca cairia no catch.
2. A definição da lista de livros foi movida para dentro do método.
3. O método passou a retornar os livros utilizados diretamente dentro do try , retornando null em caso de erro.

- Injeção de Argumentos: A classe passou a receber o argumento (livros) ao ser chamada, o que não ocorria anteriormente.
- Modularização de Processos: * A funcionalidade de emprestar livros foi isolada em um método específico.
- Sugerido um método separado exclusivamente para validação das regras de negócio (se o livro existe, disponibilidade, validade do nome).
- Novas Funções: Criada a função editarLivro().

