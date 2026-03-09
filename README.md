# Atividade 1 de boas práticas de programação

## Integrantes da equipe:

- LUIS FELIPE FERREIRA CALDARELLI
- MARCOS YUDI KIMURA
- RAFAEL EIJI HOKAMA UTIYAMA
- SIBELLY VITÓRIA ANTONIO
- THIAGO MOREIRA ANTONIASSI
- WITOR TENÃ

# Relatório de Refatoração do Sistema de Biblioteca (AT_01)

Este repositório contém a refatoração de um sistema de gerenciamento de biblioteca legado construído em Java. O objetivo principal deste projeto foi aplicar boas práticas de Engenharia de Software, como os princípios SOLID, Clean Code e o padrão de projeto Service, transformando um código "God Class" em uma arquitetura modular, testável e manutenível.

---

## Arquitetura e Orientação a Objetos

A estrutura do projeto foi redesenhada para separar as responsabilidades de interface de usuário (UI), regras de negócio e acesso a dados:

* **Criação da Entidade Principal:** Implementação da classe `Livro` para encapsular atributos de domínio, incluindo a propriedade de `quantidade`.
* **Padrão Service:** Criação das classes `BibliotecaServico` e `LivroServico` para isolar a lógica de negócios (buscar, guardar, emprestar) da interface de linha de comando.
* **Desacoplamento de Leitura:** O método `carregarLivros()` foi movido da classe principal para o `BibliotecaServico`, centralizando o manuseio e o estado da lista de livros.
* **Encapsulamento e Modificadores:** Revisão e remoção do uso excessivo e inadequado de métodos estáticos (`static`) e privados (`private`).

## Refatoração do Fluxo Principal (`Main`)

O método `main` agora atua estritamente como a interface do usuário (UI), delegando todo o processamento para a camada de serviço.

* **Loop Controlado:** Adicionada uma variável booleana `executar` para gerenciar o estado do loop `while` principal, permitindo um encerramento limpo da aplicação.
* **Limpeza de Condicionais:** Substituição de uma longa e complexa cadeia de `if/else` por uma estrutura `switch case` baseada na `opção` do usuário.
* **Delegação de Casos de Uso:**
  * **Case 1 (Adicionar):** Apenas captura o título/quantidade e chama `adicionarLivro()` no Serviço.
  * **Case 2 (Emprestar):** Captura a entrada do usuário e repassa para `emprestarLivro()` no Serviço, removendo a regra de busca da UI.
  * **Case 3 (Listar):** O laço `for` de exibição foi removido. Agora a UI apenas chama `listar()` a partir da `BibliotecaServico`.
  * **Case 4 (Sair):** Altera o valor de `executar` para `false`, encerrando o programa de forma elegante e segura.

## Correções de Bugs e Regras de Negócio

Problemas críticos de lógica e segurança da informação foram resolvidos:

* **Controle de Estoque:** Corrigida a vulnerabilidade onde emprestar o mesmo livro repetidamente deixava a quantidade de exemplares negativa.
* **Lógica de Adição:** Alterada a condicional de cadastro. Se um livro já existente for adicionado, o sistema agora incrementa sua quantidade no acervo em vez de apenas emitir um aviso de erro.
* **Tratamento de Exceções:** Remoção de blocos `try-catch` desnecessários no carregamento de dados. Em caso de falha crítica na leitura, o sistema agora retorna `null` para tratamento adequado pela camada superior.
* **Nova Funcionalidade:** Adicionado o método `editarLivro()` para manutenção do acervo.

## Clean Code e Nomenclatura

Refatoração intensiva para melhorar a legibilidade e a semântica do código, que anteriormente carecia de comentários e nomes claros:

* **Adequação de Variáveis e Instâncias:**
  * `File f` $\rightarrow$ `File arquivo`
  * `Scanner fileSc` $\rightarrow$ `Scanner scannerArquivo`
  * `int opDeEntrada` / `op` $\rightarrow$ `int opcaoDeEntrada` / `opção`
  * `Scanner sc` $\rightarrow$ `Scanner scanner`
  * `PrintWriter pw` $\rightarrow$ `PrintWriter printWriter`
  * `l` $\rightarrow$ `livros`
* **Semântica de Métodos:** `salvarLivros()` renomeado para `salvaLivro()`, refletindo a ação de salvar um único item por vez.
* **Injeção de Argumentos:** Métodos que dependiam de estado global passaram a receber argumentos explícitos (ex: `(livros)`), reduzindo o acoplamento oculto.

---

## Próximos Passos (To-Do)

- [ ] **Rastreamento de Empréstimos:** Criar um sistema de log para registrar qual usuário está com qual livro.
- [ ] **Validação Dedicada:** Isolar as validações do sistema (verificação se o livro existe, disponibilidade de estoque, regras de nomenclatura válida) em um método ou classe específica de validação.
- [ ] **Refinamento de Feedback:** Atualizar as mensagens genéricas de sucesso e erro do console para respostas mais amigáveis, específicas e autoexplicativas.
