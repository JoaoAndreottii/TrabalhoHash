Trabalho de Tabelas Hash em Java
Descrição do Projeto

Este projeto tem como objetivo implementar e analisar o desempenho de tabelas hash em Java utilizando diferentes estratégias de tratamento de colisões. A implementação inclui:

Hash Linear (Linear Probing): usa rehashing linear para tratar colisões.

Hash Duplo (Double Hashing): utiliza duas funções hash combinadas para reduzir colisões.

Encadeamento Separado (Chaining): cada posição do vetor armazena uma lista encadeada para elementos que colidem.

O projeto mede o tempo de inserção, número de colisões, tempo de busca, gaps entre elementos e maiores listas encadeadas para cada abordagem, permitindo comparar a eficiência de cada técnica para diferentes volumes de dados.

Estrutura do Projeto

hash/Main.java: Classe principal que gera os registros, executa inserções, buscas, calcula métricas e exporta resultados.

hash/Registro.java: Representa um registro com código numérico de 9 dígitos.

hash/HashTable.java: Interface que define os métodos essenciais (inserção, busca e contagem de colisões) para qualquer tabela hash.

hash/HashTableLinear.java: Implementação de tabela hash usando rehashing linear.

hash/HashTableDuplo.java: Implementação de tabela hash usando hash duplo.

hash/HashTableEncadeamento.java: Implementação de tabela hash usando encadeamento separado.

hash/ListaEncadeada.java: Estrutura auxiliar para listas encadeadas, usada na implementação de encadeamento.

Conjuntos de Dados

Foram gerados três conjuntos de registros usando seed fixa para garantir que todas as tabelas recebam exatamente os mesmos elementos, evitando invalidação da análise.

Conjunto	Número de registros
Conjunto 1	100.000
Conjunto 2	1.000.000
Conjunto 3	10.000.000

Todos os registros possuem 9 dígitos, seguindo o padrão do enunciado. Exemplo de registros gerados:

100000365
000001240
123456789


A seed fixa permite reprodutibilidade dos resultados, garantindo comparações válidas entre as funções hash.

Tamanhos das Tabelas

Para cada abordagem, foram utilizadas três tabelas de tamanhos diferentes, com variação mínima de x10 entre cada tamanho:

Tabela	Tamanho do vetor
Pequena	150.000
Média	1.500.000
Grande	15.000.000

Essa variação permite analisar o impacto do tamanho da tabela na eficiência das operações e na ocorrência de colisões.

Funções Hash Implementadas

Foram escolhidas três estratégias de hash:

Hash Linear

Função hash: h(x) = x % tamanhoVetor

Rehashing linear: desloca a posição até encontrar espaço livre.

Vantagem: simples e fácil de implementar.

Desvantagem: clustering de colisões consecutivas em tabelas grandes.

Hash Duplo

Função principal: h1(x) = x % tamanhoVetor

Função secundária: h2(x) = 1 + (x / tamanhoVetor) % (tamanhoVetor-1)

Rehashing: pos = h1 + i * h2

Vantagem: reduz colisões comparado ao hash linear.

Desvantagem: implementação mais complexa e sensível a escolha das funções hash.

Encadeamento Separado

Função hash: h(x) = x % tamanhoVetor

Cada posição do vetor mantém uma lista encadeada para colisões.

Vantagem: não há necessidade de rehashing e tolera alto número de colisões.

Desvantagem: maior overhead de memória e busca pode ser mais lenta se listas crescerem muito.

Métricas Avaliadas

Para cada combinação de tabela e conjunto de dados, foram coletadas as seguintes métricas:

Tempo de Inserção (ms): tempo total necessário para inserir todos os registros na tabela hash.

Número de Colisões: contagem total de colisões durante a inserção, incluindo tentativas de rehash.

Tempo de Busca (ms): tempo total necessário para buscar todos os registros inseridos.

Gaps: para tabelas lineares e duplas, calcula o menor, maior e média de espaço entre elementos consecutivos.

Maiores Listas Encadeadas: para tabela com encadeamento, mostra o tamanho das três listas mais longas.

Todas as métricas foram exportadas para metricas.csv para análise gráfica.

Resultados Obtidos
Inserção e Busca

Exemplo de resultados para tabela de 15.000.000 posições com 10 milhões de registros:

Tabela	Inserção (ms)	Colisões	Busca (ms)	Gaps / Maiores Listas
Linear	635	10.231.319	659	1 / 23 / 1
Duplo	427	6.535.216	414	1 / 17 / 1
Encadeamento	4.358	2.700.700	923	9 / 8 / 8

Observações:

Hash Duplo apresentou melhor desempenho geral em termos de inserção e busca, devido à redução significativa de colisões.

Encadeamento exige mais tempo de inserção, mas mantém listas relativamente curtas, facilitando buscas subsequentes.

Hash Linear sofre com clustering, aumentando colisões e degradando desempenho em grandes volumes.

Gaps e Maiores Listas

Gaps: indicam o espaçamento entre elementos no vetor. Valores menores indicam distribuição mais uniforme.

Maiores Listas: apontam possíveis gargalos no encadeamento, mostrando onde mais registros colidiram na mesma posição.

Exportação e Análise

As métricas foram exportadas para metricas.csv, permitindo análise gráfica em ferramentas como Excel, Google Sheets ou Python (matplotlib/pandas).

Gráficos sugeridos:

Comparativo de tempo de inserção por tabela.

Comparativo de tempo de busca por tabela.

Distribuição de colisões.

Gaps médios e maiores listas.

Conclusão

O hash duplo demonstrou a melhor relação entre tempo e número de colisões, sendo ideal para grandes volumes.

O encadeamento separado é robusto contra colisões excessivas, mas consome mais memória e tempo de inserção.

O hash linear é simples, mas menos eficiente em cenários com muitos dados, sofrendo com clusters.

A escolha da função hash e do tamanho da tabela impacta diretamente na performance.

O uso de seed fixa garante reprodutibilidade e validação da atividade.

Como Rodar

Clone o repositório:

git clone <link-do-repo>


Compile todos os arquivos:

javac hash/*.java


Execute a aplicação:

java hash.Main


Os resultados serão exibidos no console e exportados para metricas.csv.
