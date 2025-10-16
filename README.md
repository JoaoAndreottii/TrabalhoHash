💻 Trabalho de Tabelas Hash em Java
📄 Descrição do Projeto

Este projeto tem como objetivo implementar e analisar o desempenho de tabelas hash em Java usando diferentes estratégias de tratamento de colisões:

Linear Probing (Hash Linear) 🔹: rehashing linear para tratar colisões.

Double Hashing (Hash Duplo) 🔹: combina duas funções hash para reduzir colisões.

Separate Chaining (Encadeamento Separado) 🔹: cada posição do vetor armazena uma lista encadeada para elementos que colidem.

O projeto mede o tempo de inserção, número de colisões, tempo de busca, gaps entre elementos e maiores listas encadeadas, permitindo uma análise completa do desempenho de cada técnica.

🗂 Estrutura do Projeto

hash/Main.java ➡️ Classe principal, gera registros, realiza inserções, buscas, calcula métricas e exporta resultados.

hash/Registro.java ➡️ Representa um registro com código numérico de 9 dígitos.

hash/HashTable.java ➡️ Interface definindo métodos essenciais (inserção, busca, colisões).

hash/HashTableLinear.java ➡️ Implementação de hash linear.

hash/HashTableDuplo.java ➡️ Implementação de hash duplo.

hash/HashTableEncadeamento.java ➡️ Implementação de encadeamento separado.

hash/ListaEncadeada.java ➡️ Estrutura auxiliar de listas encadeadas usada no encadeamento.

🧮 Conjuntos de Dados

Três conjuntos foram gerados usando seed fixa para garantir consistência:

Conjunto	Número de registros
Conjunto 1	100.000
Conjunto 2	1.000.000
Conjunto 3	10.000.000

Todos os registros têm 9 dígitos, seguindo o padrão do enunciado, ex.:

100000365
000001240
123456789


💡 Seed fixa garante reprodutibilidade, permitindo comparações válidas entre funções hash.

📏 Tamanhos das Tabelas

Para cada abordagem, três tamanhos foram utilizados:

Tabela	Tamanho do vetor
Pequena	150.000
Média	1.500.000
Grande	15.000.000

✅ A variação mínima de x10 permite analisar o impacto do tamanho da tabela na eficiência e colisões.

🧩 Funções Hash Implementadas

Hash Linear 🔹

h(x) = x % tamanhoVetor

Rehashing linear: desloca até encontrar espaço livre.

Hash Duplo 🔹

h1(x) = x % tamanhoVetor

h2(x) = 1 + (x / tamanhoVetor) % (tamanhoVetor-1)

Rehashing: pos = h1 + i * h2

Encadeamento Separado 🔹

h(x) = x % tamanhoVetor

Cada posição mantém uma lista encadeada para colisões.

💡 Escolha das funções baseada em pesquisa, sem uso das funções do slide.

⏱ Métricas Avaliadas

Para cada tabela e conjunto de dados:

Tempo de Inserção (ms)

Número de Colisões

Tempo de Busca (ms)

Gaps entre elementos (menor, maior, média)

Maiores listas encadeadas (para encadeamento)

✅ Métricas exportadas para metricas.csv para análise gráfica.

📊 Resultados Obtidos

Exemplo: tabela de 15.000.000 posições com 10 milhões de registros

Tabela	Inserção (ms)	Colisões	Busca (ms)	Gaps / Maiores Listas
Linear	635	10.231.319	659	1 / 23 / 1
Duplo	427	6.535.216	414	1 / 17 / 1
Encadeamento	4.358	2.700.700	923	9 / 8 / 8

🔹 Hash Duplo: melhor desempenho geral.
🔹 Encadeamento: robusto contra colisões, mas mais lento na inserção.
🔹 Hash Linear: simples, mas sofre com clustering.

📈 Exportação e Análise

Métricas exportadas para metricas.csv

Ferramentas recomendadas para gráficos: Excel, Google Sheets, Python (matplotlib/pandas)

Gráficos sugeridos:

Comparativo de tempo de inserção

Comparativo de tempo de busca

Distribuição de colisões

Gaps médios e maiores listas

🏁 Conclusão

Hash Duplo: melhor relação tempo x colisões, ideal para grandes volumes.

Encadeamento Separado: seguro contra colisões, porém consome mais memória e tempo.

Hash Linear: simples, menos eficiente em grandes volumes.

✅ A escolha da função hash e do tamanho da tabela impacta diretamente no desempenho.
✅ Uso de seed garante reprodutibilidade e validação da atividade.

⚡ Como Rodar
git clone <link-do-repo>
javac hash/*.java
java hash.Main


Resultados aparecem no console e em metricas.csv
