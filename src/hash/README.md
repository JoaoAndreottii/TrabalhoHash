Tabela Hash em Java – Análise Comparativa de Desempenho

📋 Descrição do Projeto

Este projeto implementa e compara o desempenho de três estratégias de resolução de colisões em tabelas hash, desenvolvido como parte da avaliação da disciplina. As implementações incluem:

Hash Linear (Linear Probing) – Resolução de colisões através de sondagem linear
Hash Duplo (Double Hashing) – Uso de função de hash secundária para cálculo do incremento
Hash com Encadeamento (Chaining) – Tratamento de colisões mediante listas encadeadas

O objetivo central é avaliar quantitativamente cada abordagem considerando: tempo de inserção, tempo de busca, número de colisões e qualidade da distribuição dos elementos.

🗂️ Estrutura do Projeto
📁 hash/
├── Main.java                    # Ponto de entrada e orquestração dos testes
├── Registro.java                # Modelo de dados (objeto de registro)
├── HashTableLinear.java         # Implementação: Linear Probing
├── HashTableDuplo.java          # Implementação: Double Hashing
├── HashTableEncadeamento.java   # Implementação: Chaining
├── ListaEncadeada.java          # Estrutura auxiliar para encadeamento
└── HashTable.java               # Interface comum (contrato para todas as tabelas)

📄 metricas.csv                  # Arquivo de saída com todas as métricas coletadas

🚀 Configuração e Execução
Pré-requisitos

Java JDK 8 ou superior instalado
Terminal ou prompt de comando configurado com javac e java no PATH

Passo a passo

Compilar o projeto:

bashjavac hash/*.java

Executar os testes:

bashjava hash.Main
```

### O que o programa faz?

Ao executar, o programa realiza automaticamente:

1. Geração de três conjuntos de dados com seeds fixas:
   - 100.000 registros
   - 1.000.000 registros
   - 10.000.000 registros

2. Inserção dos dados nas três implementações de tabela hash

3. Execução de buscas e coleta de métricas de desempenho

4. Exportação dos resultados para o arquivo `metricas.csv`

---

## ⚙️ Decisões de Implementação

### Dimensionamento das Tabelas

| Registros     | Tamanho da Tabela | Fator de Carga (aprox.) |
|---------------|-------------------|-------------------------|
| 100.000       | 150.000          | 66%                     |
| 1.000.000     | 1.500.000        | 66%                     |
| 10.000.000    | 15.000.000       | 66%                     |

Os tamanhos foram escolhidos para manter um fator de carga consistente e permitir análise comparativa justa entre os métodos.

### Funções Hash Implementadas

#### Linear Probing
```
hash(k) = k mod tamanho
rehash(i) = (hash + i) mod tamanho
```

#### Double Hashing
```
hash1(k) = k mod tamanho
hash2(k) = 1 + (k / tamanho) mod (tamanho - 1)
rehash(i) = (hash1 + i × hash2) mod tamanho
```

#### Chaining
```
hash(k) = k mod tamanho
→ Armazena em lista encadeada na posição hash(k)
Geração de Dados

Códigos gerados: Inteiros aleatórios de 9 dígitos (intervalo: 100.000.000 a 999.999.999)
Seeds fixas: Garantem reprodutibilidade e que todas as três implementações processem exatamente os mesmos dados
Distribuição: Uniforme dentro do intervalo especificado


📊 Resultados Obtidos
Exemplo de Métricas Coletadas
TamanhoMétodoInserção (ms)ColisõesBusca (ms)Gaps/Maiores Listas150.000Linear6100.00961/16/1150.000Duplo964.63941/11/1150.000Encadeamento1027.08396/6/6

Nota: Resultados completos disponíveis em metricas.csv

Visualização dos Dados
Os dados exportados podem ser analisados graficamente utilizando:

Microsoft Excel ou LibreOffice Calc
Google Sheets
Python (pandas + matplotlib/seaborn)
R (ggplot2)


🔍 Análise Comparativa
Linear Probing
Vantagens:

Excelente localidade de cache
Rápido para fatores de carga baixos

Desvantagens:

Clustering primário severo
Degradação significativa de desempenho com alta ocupação
Maior número absoluto de colisões

Double Hashing
Vantagens:

Melhor distribuição dos elementos
Redução dramática no número de colisões comparado ao linear
Desempenho consistente mesmo com fatores de carga elevados

Desvantagens:

Ligeiramente mais complexo computacionalmente
Requer cálculo de hash secundário

Chaining
Vantagens:

Desempenho previsível e estável
Não sofre com clustering
Listas encadeadas geralmente curtas com boa função hash

Desvantagens:

Overhead de ponteiros e alocação dinâmica
Menor aproveitamento de cache
Tempo de inserção pode ser maior em grandes volumes


🎯 Conclusões
Com base nos experimentos realizados, Double Hashing apresentou o melhor equilíbrio entre:

Tempo de inserção
Tempo de busca
Número de colisões
Escalabilidade

É particularmente eficaz em cenários com fatores de carga médios a altos, onde as limitações do Linear Probing se tornam evidentes.
O Encadeamento permanece uma escolha sólida quando a previsibilidade de desempenho é crítica e o overhead de memória é aceitável.

📝 Observações Importantes

✅ Código extensively comentado para facilitar compreensão e validação
✅ Versão sem comentários incluída em pasta separada (prova de autoria)
✅ Seeds fixas garantem reprodutibilidade dos experimentos
✅ Todas as métricas exportadas para análise posterior
✅ Interface comum permite fácil extensão para novos métodos

