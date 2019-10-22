# lotohard

Solução java simples para geração de jogos da Lotofácil utilizando a técnica de espelhagem.

O jogo original (primeiro a ser gerado, considera também as seguintes estatísticas conforme abaixo.

Estatísticas (24/06/2019):

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
