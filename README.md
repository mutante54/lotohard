# lotohard

Solução java simples para geração de jogos da Lotofácil utilizando a técnica de espelhagem.

**Passo 1- O jogo original (primeiro a ser gerado) considera também as seguintes estatísticas conforme abaixo.**

*Estatísticas (12/11/2019):*

Linhas com mais dezenas sorteadas: 

Linha 3
Dezenas: 11, 12, 13, 14, 15	- 5731

Linha 1
Dezenas: 01, 02, 03, 04, 05	- 5718

Linha 5
Dezenas: 21, 22, 23, 24, 25	- 5704

Linha 4
Dezenas: 16, 17, 18, 19, 20	- 5630

Linha 2
Dezenas: 06, 07, 08, 09, 10	- 5552

15 dezenas mais sorteadas:

13 1172
24 1167
11 1162
01 1153
10 1152
20 1151
14 1150
02 1144
25 1144
03 1143
04 1143
18 1137
05 1135
19 1133
23 1133

Destas,

Linha 1: 01,02,03,04,05 
Linha 2: 10
Linha 3: 11,13,14
Linha 4: 18,19,20
Linha 5: 23,24,25

Conclusão: Linhas que tem mais números sorteados: 3, 1 e 5 (todas linhas ímpares).
-----------------------------------------------------------------------------------------------

Outras conclusões:

-----------------------------------------------------------------------------------------------
Em média, 60% (9) das 15 dezenas mais sorteadas compõem as 3 primeiras linhas (dezenas de 1 ao 15).
-----------------------------------------------------------------------------------------------
Das 10 dezenas que menos ocorrem, 4 (40%) compõem a linha 2 (dezenas de 6 ao 10)
-----------------------------------------------------------------------------------------------
Das 5 dezenas que menos ocorrem, 3 (60%) compõem a linha 2 (dezenas de 6 ao 10)
-----------------------------------------------------------------------------------------------
Das 10 dezenas que menos ocorrem, NENHUMA compõem a linha 1 (dezenas 1 ao 5)
-----------------------------------------------------------------------------------------------
Relação Pares x Impares: em 61% dos concursos ocorrem mais dezenas ÍMPARES
-----------------------------------------------------------------------------------------------

LotoHardApp

Estruturar em linhas (1 a 5); 

Definir a qtd de distribuição de dezenas para cada linha, além da prioridade:

Linha 1: 3 a 4 dezenas - Prioridade: 0
Linha 2: 1 a 2 dezenas - Prioridade: 1
Linha 3: 3 a 3 dezenas - Prioridade: 0
Linha 4: 2 a 3 dezenas - Prioridade: 1
Linha 5: 3 a 3 dezenas - Prioridade: 0

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

1- Serão gerados 6 novos jogos, utilizando o método de ESPELHO, derivados do jogo original:

1.1- No JOGO 1, iremos modificar apenas o BLOCO 1

1.2- No JOGO 2, modificamos apenas o BLOCO 2

1.3- No JOGO 3, modificamos apenas o BLOCO 3

1.4- No JOGO 4, modificamos os BLOCOS 2 e 3	

1.5- No JOGO 5, modificamos os BLOCOS 1 e 2	

1.6- No JOGO 6, modificamos 5 dezenas aleatórias

				
*REGRAS DE MOVIMENTO DE DEZENAS*

Quanto ao sentido: aleatório - para PRÓXIMA dezena ou ANTERIOR.

1- O ponteiro irá se mover para o respectivo sentido, até encontrar uma dezena QUE NÃO TENHA OCORRIDO NO JOGO ORIGINAL, E QUE AINDA NÃO TENHA SIDO ESCOLHIDA ENTRE AS NOVAS DEZENAS.

1.1- O ponteiro irá se mover até a dezena limite (25 - no caso do fluxo crescente; ou 01 no fluxo decrescente): Caso não encontre, irá tentar o fluxo OPOSTO.

1.1.1- Caso o ponteiro não encontre uma nova dezena, a mesma será REPETIDA (ou seja, será utilizada a mesma dezena usada no jogo original).				

