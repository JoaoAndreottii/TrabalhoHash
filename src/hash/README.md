Trabalho de Tabelas Hash em Java

Descrição do Projeto:

Este projeto implementa e analisa o desempenho de diferentes tabelas hash em Java, conforme os requisitos do trabalho da disciplina. Foram implementadas três abordagens principais:

Hash Linear (Linear Probing) – Função de rehashing linear.

Hash Duplo (Double Hashing) – Função de rehashing duplo.

Hash com Encadeamento (Chaining) – Uso de listas encadeadas para tratamento de colisões.

O objetivo é medir o desempenho de cada abordagem em termos de tempo de inserção, tempo de busca, número de colisões e distribuição de elementos.

Estrutura do Projeto

hash/ – Pacote contendo todas as classes Java:

Main.java – Classe principal que executa os testes.

Registro.java – Classe que representa o objeto de registro.

HashTableLinear.java – Implementação de tabela hash com rehashing linear.

HashTableDuplo.java – Implementação de tabela hash com rehashing duplo.

HashTableEncadeamento.java – Implementação de tabela hash com encadeamento.

ListaEncadeada.java – Estrutura auxiliar para o encadeamento.

HashTable.java – Interface comum para todas as tabelas hash.

metricas.csv – Arquivo gerado pelo programa contendo todas as métricas coletadas durante a execução dos testes.

Configuração e Execução

Compilar todas as classes Java:

javac hash/*.java


Executar o programa:

java hash.Main


O programa irá:

Gerar três conjuntos de dados usando seed fixa (100.000, 1.000.000 e 10.000.000 registros).

Inserir os registros em cada tabela hash (linear, duplo, encadeamento).

Medir tempo de inserção, busca, colisões, maiores listas e gaps.

Exportar métricas para metricas.csv para análise gráfica.

Escolhas do Projeto

Tamanhos dos Vetores Hash:

150.000, 1.500.000 e 15.000.000.

Cada tamanho cresce aproximadamente 10x, seguindo as orientações do trabalho.

Funções Hash:

Linear Probing: hash = codigo % tamanho com rehashing linear.

Double Hashing: hash1 = codigo % tamanho, hash2 = 1 + (codigo / tamanho) % (tamanho - 1).

Encadeamento: hash = codigo % tamanho.

Geração dos Dados:

Foram usados números aleatórios de 9 dígitos (100000000 a 999999999).

Seeds fixas garantem que os três métodos recebam exatamente os mesmos registros.

Resultados
Exemplo de saída (resumida):
Tamanho Vetor	Método	Inserção (ms)	Colisões	Busca (ms)	Gaps / Maiores Listas
150.000	Linear	6	100009	6	1/16/1
150.000	Duplo	9	64639	4	1/11/1
150.000	Encadeamento	10	27083	9	6/6/6

Os resultados completos estão no arquivo metricas.csv e podem ser visualizados em gráficos para comparação de desempenho.

Análise e Conclusão

Linear Probing: rápido em vetores pequenos, mas colisões aumentam muito com vetores maiores.

Double Hashing: melhor desempenho geral, colisões significativamente menores que linear.

Encadeamento: mais estável em termos de colisões, porém maior tempo de inserção para grandes vetores.

Gaps e maiores listas: Linear e Duplo têm gaps pequenos; Encadeamento mantém listas encadeadas curtas.

Conclusão: Double Hashing apresentou o melhor equilíbrio entre tempo de inserção, busca e número de colisões, especialmente em vetores grandes.

Observações!!!

O código está comentado detalhadamente para facilitar a compreensão e validação do trabalho.

Uma pasta separada com código sem comentários está incluída para prova de autoria.

Métricas podem ser usadas para gerar gráficos em Excel, Google Sheets ou Python.