# lotohard

Solução java simples para geração de jogos da Lotofácil utilizando a técnica de espelhagem.

**Passo 1- O jogo original (primeiro a ser gerado) considera também as seguintes estatísticas conforme abaixo.**

*Estatísticas (24/06/2019):*

Linhas com mais dezenas sorteadas: 

Linha 3
Dezenas: 11, 12, 13, 14, 15	- 5551

Linha 1
Dezenas: 01, 02, 03, 04, 05	- 5542

Linha 5
Dezenas: 21, 22, 23, 24, 25	- 5516

Linha 4
Dezenas: 16, 17, 18, 19, 20	- 5454

Linha 2
Dezenas: 06, 07, 08, 09, 10	- 5387

Conclusão: Linhas que tem mais números sorteados: 3, 1 e 5 (todas linhas ímpares).
-----------------------------------------------------------------------------------------------

Colunas com as dezenas mais sorteadas:

Coluna 5
Dezenas: 05, 10, 15, 20, 25	- 5542

Coluna 4
Dezenas: 04, 09, 14, 19, 24	- 5523

Coluna 3
Dezenas: 03, 08, 13, 18, 23	- 5478

Coluna 2
Dezenas: 02, 07, 12, 17, 22	- 5469

Coluna 1
Dezenas: 01, 06, 11, 16, 21	- 5438

Conclusão: Colunas que tem mais números sorteados: 5, 4 e 3

-----------------------------------------------------------------------------------------------
Em média, 60% (9) das 15 dezenas mais sorteadas compõem as 3 primeiras linhas (dezenas de 1 ao 15).
-----------------------------------------------------------------------------------------------
Das 10 dezenas que menos ocorrem, 4 (40%) compõem a linha 2 (dezenas de 6 ao 10)
-----------------------------------------------------------------------------------------------
Das 5 dezenas que menos ocorrem, 3 (60%) compõem a linha 2 (dezenas de 6 ao 10)
-----------------------------------------------------------------------------------------------
Das 10 dezenas que menos ocorrem, NENHUMA compõem a linha 1 (dezenas 1 ao 5)
-----------------------------------------------------------------------------------------------
Relação Pares x Impares: em 60% dos concursos ocorrem mais dezenas ÍMPARES
-----------------------------------------------------------------------------------------------

LotoHardApp

Estruturar em linhas (1 a 5); 

Definir a qtd de distribuição de dezenas para cada linha, além da prioridade:

Linha 1: 3 a 4 dezenas - Prioridade: 0
Linha 2: 1 a 2 dezenas - Prioridade: 1
Linha 3: 3 a 4 dezenas - Prioridade: 0
Linha 4: 2 a 2 dezenas - Prioridade: 1
Linha 5: 2 a 3 dezenas - Prioridade: 0

Total de dezenas: 15
% Mínimo de Impares: 60

Parâmetros: 
Qtd total de dezenas:	x
% Mínimo de Impares:    y%

-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
**Passo 2 - Técnica de espelhagem:**

*DESCRIÇÃO DO MÉTODO*

Este método é aplicável, neste momento, somente para jogos de 15 dezenas. Sempre iremos partir de um primeiro jogo de 15 números, gerado aleatoriamente. Vamos definir como o jogo ORIGINAL.				
No geral, o jogo ORIGINAL é dividido em 3 partes iguais - com 5 dezenas cada. O método consiste em gerar NOVOS jogos a partir do jogo ORIGINAL, modificando as dezenas de acordo com um ou mais BLOCOS definidos para cada tipo de jogo.				
Desta forma, dependendo do tipo de jogo, algumas dezenas são consideradas FIXAS e outras VOLÁTEIS. As dezenas voláteis são modificadas para a próxima dezena MAIOR ou MENOR.	

1- Serão gerados 4 novos jogos, utilizando o método de ESPELHO, derivados do jogo original:

1.1- No JOGO 1, iremos modificar apenas o BLOCO 1

1.2- No JOGO 2, modificamos apenas o BLOCO 2

1.3- No JOGO 3, modificamos apenas o BLOCO 3

1.4- No JOGO 4, modificamos os BLOCOS 2 e 3	

				
*REGRAS DE MOVIMENTO DE DEZENAS*

Quanto ao sentido: aleatório - para PRÓXIMA dezena ou ANTERIOR.

1- O ponteiro irá se mover para o respectivo sentido, até encontrar uma dezena QUE NÃO TENHA OCORRIDO NO JOGO ORIGINAL, E QUE AINDA NÃO TENHA SIDO ESCOLHIDA ENTRE AS NOVAS DEZENAS.

1.1- O ponteiro irá se mover até a dezena limite (25 - no caso do fluxo crescente; ou 01 no fluxo decrescente): Caso não encontre, irá tentar o fluxo OPOSTO.

1.1.1- Caso o ponteiro não encontre uma nova dezena, a mesma será REPETIDA (ou seja, será utilizada a mesma dezena usada no jogo original).				

