package br.com.lotohard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Main {

	// total de numeros a serem sorteados
	private static int qtdSortedNumbers = 15;

	// percentual minimo de números impares a serem sorteados
	private static int minPercOddSortedNumbers = 53;

	// percentual minimo de números pares a serem sorteados
	private static int minPercEvenSortedNumbers = 40;

	// numeros sorteados (jogo original)
	private static List<Integer> sortedNumbers = new ArrayList<>();

	// Bloco 1
	private static List<Integer> sortedNumbersBlock1 = new ArrayList<>();
	// Bloco 2
	private static List<Integer> sortedNumbersBlock2 = new ArrayList<>();
	// Bloco 3
	private static List<Integer> sortedNumbersBlock3 = new ArrayList<>();

	// Bloco Aleatório (5 dezenas, equivalente à 1/3 das dezenas)
	private static List<Integer> sortedNumbersBlockRandom = new ArrayList<>();

	private static final int minDez = 1;
	private static final int maxDez = 25;

	// quantidade de numeros impares ja sorteados
	private static double countSortedOddNumbers = 0;

	// quantidade de numeros pares ja sorteados
	private static double countSortedEvenNumbers = 0;

	public static void main(String[] args) {

		List<Line> lines = new ArrayList<>();
		lines.add(new Line(1, 0, 3, 4, Arrays.asList(1, 2, 3, 4, 5)));
		lines.add(new Line(2, 1, 1, 2, Arrays.asList(6, 7, 8, 9, 10)));
		lines.add(new Line(3, 0, 3, 3, Arrays.asList(11, 12, 13, 14, 15)));
		lines.add(new Line(4, 1, 2, 3, Arrays.asList(16, 17, 18, 19, 20)));
		lines.add(new Line(5, 0, 3, 3, Arrays.asList(21, 22, 23, 24, 25)));

		/*
		 * Parte 1 - Preenchendo o minimo de dezenas por linha
		 */
		lines.forEach(line -> {
			while (line.getCountSortedNumbers() < line.getMinSortedNumbers()) {
				int sortedNumber = line.getNumbers().get(new Random().nextInt(line.getNumbers().size()));
				if (!sortedNumbers.contains(sortedNumber)) {
					line.setCountSortedNumbers(line.getCountSortedNumbers() + 1);
					sortedNumbers.add(sortedNumber);
					if (sortedNumber % 2 != 0) {
						countSortedOddNumbers++;
					} else {
						countSortedEvenNumbers++;
					}
				}
			}
		});

		/*
		 * Parte 2 - Preenchendo as demais dezenas, atentendo aos requisitos minimos
		 */

		lines.sort(Comparator.comparing(Line::getPriority));

		lines.forEach(line -> {
			int maxAttempts = 50;
			int countAttempts = 0;
			// temos a necessidade de preencher a linha em no máximo "x" tentativas..
			// pode ocorrer de não ser possível preencher a linha porque não existem mais
			// dezenas disponíveis para atender à regra de pares/ ímpares
			while (line.getCountSortedNumbers() < line.getMaxSortedNumbers() && countAttempts < maxAttempts) {

				// sorteando próxima dezena
				int sortedNumber = line.getNumbers().get(new Random().nextInt(line.getNumbers().size()));

				boolean isOddNumber = false;

				if (sortedNumber % 2 != 0) {
					isOddNumber = true;
				}

				// percentual de dezenas ímpares
				double percOddNumbers = Double.valueOf(countSortedOddNumbers / qtdSortedNumbers) * 100;

				// percentual de dezenas pares
				double percEvenNumbers = Double.valueOf(countSortedEvenNumbers / qtdSortedNumbers) * 100;

				// validacao quanto a quantidade minima de numeros pares/ impares
				if (isOddNumber == true && (percOddNumbers >= minPercOddSortedNumbers && percEvenNumbers < minPercEvenSortedNumbers)) {
					// dezena é ÍMPAR e o percentual de ímpares já foi satisfeito (e o percentual de
					// pares não foi satisfeito)
					continue;
				} else if (isOddNumber == false && (percEvenNumbers >= minPercEvenSortedNumbers || percOddNumbers < minPercOddSortedNumbers)) {
					// dezena é PAR e o percentual de pares já foi satisfeito (e o percentual de
					// ímpares não foi satisfeito)
					continue;
				}

				// validando se a dezena já não foi sorteada
				if (!sortedNumbers.contains(sortedNumber)) {

					if (isOddNumber) {
						countSortedOddNumbers++;
					} else {
						countSortedEvenNumbers++;
					}

					line.setCountSortedNumbers(line.getCountSortedNumbers() + 1);
					sortedNumbers.add(sortedNumber);
				} else {
					// mais uma tentativa de preencher essa linha..
					countAttempts++;
				}
			}
		});

		sortedNumbers.sort(Comparator.naturalOrder());

		System.out.println("Data de geração: " + new Date().toString());

		/*
		 * Imprimir as dezenas
		 */

		System.out.println("\nDezenas sorteadas (Jogo original): \n");

		printNumbers(sortedNumbers);

		/*
		 * Parte 3 - Isolando blocos e construindo demais jogos
		 */

		sortedNumbersBlock1 = sortedNumbers.subList(0, 5);
		sortedNumbersBlock2 = sortedNumbers.subList(5, 10);
		sortedNumbersBlock3 = sortedNumbers.subList(10, sortedNumbers.size());

		Random r = new Random();
		Integer sortedIndex;
		Integer sortedNumber;
		while (sortedNumbersBlockRandom.size() < 5) {
			sortedIndex = r.nextInt(sortedNumbers.size() - 1);
			sortedNumber = sortedNumbers.get(sortedIndex);

			if (!sortedNumbersBlockRandom.contains(sortedNumber)) {
				sortedNumbersBlockRandom.add(sortedNumber);
			}
		}

		System.out.println("\nBloco 1: ");
		printNumbers(sortedNumbersBlock1);
		System.out.println("Bloco 2: ");
		printNumbers(sortedNumbersBlock2);
		System.out.println("Bloco 3: ");
		printNumbers(sortedNumbersBlock3);

		System.out.println("\nQtd Pares: " + (int) countSortedEvenNumbers);
		System.out.println("\nQtd Ímpares: " + (int) countSortedOddNumbers);

		System.out.println("\nJogo #2 (Movendo Bloco 1): ");
		List<Integer> game2Numbers = new ArrayList<>();
		game2Numbers.addAll(moveNumbersTo(sortedNumbersBlock1));
		game2Numbers.addAll(sortedNumbersBlock2);
		game2Numbers.addAll(sortedNumbersBlock3);
		game2Numbers.sort(Comparator.naturalOrder());
		printNumbers(game2Numbers);
		System.out.println("Qtd de dezenas que não constam no jogo #1: " + getQtdNumbersDiffWithOriginalGame(game2Numbers));

		System.out.println("\nJogo #3 (Movendo Bloco 2): ");
		List<Integer> game3Numbers = new ArrayList<>();
		game3Numbers.addAll(sortedNumbersBlock1);
		game3Numbers.addAll(moveNumbersTo(sortedNumbersBlock2));
		game3Numbers.addAll(sortedNumbersBlock3);
		game3Numbers.sort(Comparator.naturalOrder());
		printNumbers(game3Numbers);
		System.out.println("Qtd de dezenas que não constam no jogo #1: " + getQtdNumbersDiffWithOriginalGame(game3Numbers));

		System.out.println("\nJogo #4 (Movendo Bloco 3): ");
		List<Integer> game4Numbers = new ArrayList<>();
		game4Numbers.addAll(sortedNumbersBlock1);
		game4Numbers.addAll(sortedNumbersBlock2);
		game4Numbers.addAll(moveNumbersTo(sortedNumbersBlock3));
		game4Numbers.sort(Comparator.naturalOrder());
		printNumbers(game4Numbers);
		System.out.println("Qtd de dezenas que não constam no jogo #1: " + getQtdNumbersDiffWithOriginalGame(game4Numbers));

		System.out.println("\nJogo #5 (Movendo Blocos 2 e 3): ");
		List<Integer> game5Numbers = new ArrayList<>();
		game5Numbers.addAll(sortedNumbersBlock1);
		List<Integer> game5SortedNumbersBlock2PlusBlock3 = new ArrayList<>();
		game5SortedNumbersBlock2PlusBlock3.addAll(sortedNumbersBlock2);
		game5SortedNumbersBlock2PlusBlock3.addAll(sortedNumbersBlock3);
		game5Numbers.addAll(moveNumbersTo(game5SortedNumbersBlock2PlusBlock3));
		game5Numbers.sort(Comparator.naturalOrder());
		printNumbers(game5Numbers);
		System.out.println("Qtd de dezenas que não constam no jogo #1: " + getQtdNumbersDiffWithOriginalGame(game5Numbers));

		System.out.println("\nJogo #6 (Movendo Blocos 1 e 2): ");
		List<Integer> game6Numbers = new ArrayList<>();
		List<Integer> game6SortedNumbersBlock1PlusBlock2 = new ArrayList<>();
		game6SortedNumbersBlock1PlusBlock2.addAll(sortedNumbersBlock1);
		game6SortedNumbersBlock1PlusBlock2.addAll(sortedNumbersBlock2);
		game6Numbers.addAll(moveNumbersTo(game6SortedNumbersBlock1PlusBlock2));
		game6Numbers.addAll(sortedNumbersBlock3);
		game6Numbers.sort(Comparator.naturalOrder());
		printNumbers(game6Numbers);
		System.out.println("Qtd de dezenas que não constam no jogo #1: " + getQtdNumbersDiffWithOriginalGame(game6Numbers));

		System.out.println("\nJogo #7 (Movendo 5 dezenas aleatórias): ");
		List<Integer> game7Numbers = new ArrayList<>();
		game7Numbers.addAll(moveNumbersTo(sortedNumbersBlockRandom));
		game7Numbers.addAll(getExceptionNumbersFromOriginalGame(sortedNumbersBlockRandom));
		game7Numbers.sort(Comparator.naturalOrder());
		printNumbers(game7Numbers);
		System.out.println("Qtd de dezenas que não constam no jogo #1: " + getQtdNumbersDiffWithOriginalGame(game7Numbers));

	}

	/**
	 * Imprime os números (como uma única string)
	 * 
	 * @param numbers
	 */
	private static void printNumbers(List<Integer> numbers) {
		String line = "";
		for (int i = 0; i < numbers.size(); i++) {
			if (!line.isEmpty()) {
				line += "-";
			}
			line += numbers.get(i);
		}

		System.out.println(line);
	}

	/**
	 * Move cada dezena para dois sentidos possíveis (LEFT ou RIGHT)
	 * 
	 * @param numbers
	 * @return Lista de números atualizados
	 */
	private static List<Integer> moveNumbersTo(List<Integer> numbers) {

		List<Integer> newNumbers = new ArrayList<>();
		Integer[] directions = { 1, 2 };

		numbers.forEach(number -> {

			Boolean found = null;
			int maxLoopToTry = 2;
			int loopCounter = 1;

			Integer nextNumber = null;

			while ((found == null || found == false) && loopCounter <= maxLoopToTry) {

				int direction = directions[new Random().nextInt(2)];

				if ((direction == 1 && found == null) || (found != null && found == false)) {
					// left
					nextNumber = number - 1;

					while ((newNumbers.contains(nextNumber) || sortedNumbers.contains(nextNumber)) && nextNumber > minDez) {
						nextNumber--;
					}

					if ((!newNumbers.contains(nextNumber) && !sortedNumbers.contains(nextNumber)) && nextNumber >= minDez) {
						found = true;
					} else {
						found = false;
					}
				}

				if ((direction == 2 && found == null) || (found != null && found == false)) {
					// right
					nextNumber = number + 1;

					while ((newNumbers.contains(nextNumber) || sortedNumbers.contains(nextNumber)) && nextNumber < maxDez) {
						nextNumber++;
					}

					if ((!newNumbers.contains(nextNumber) && !sortedNumbers.contains(nextNumber)) && nextNumber <= maxDez) {
						found = true;
					} else {
						found = false;
					}
				}

				loopCounter++;
			}

			if (found == true) {
				// encontrou a próxima dezena/ anterior
				newNumbers.add(nextNumber);
			} else {
				// repete a dezena
				newNumbers.add(number);
			}

		});

		return newNumbers;
	}

	/**
	 * Obtém a quantidade de dezenas do jogo informado que não constam no jogo
	 * original
	 * 
	 * @param numbers
	 * @return
	 */
	private static int getQtdNumbersDiffWithOriginalGame(List<Integer> numbers) {
		int qtd = 0;
		for (Integer integer : numbers) {
			if (!sortedNumbers.contains(integer)) {
				qtd++;
			}
		}
		return qtd;
	}

	/**
	 * Obtém as dezenas excedentes do jogo original, desconsiderando as dezenas
	 * informadas no parâmetro
	 * 
	 * @param numbers
	 * @return
	 */
	private static List<Integer> getExceptionNumbersFromOriginalGame(List<Integer> numbers) {
		List<Integer> exceptNumbers = new ArrayList<>();
		for (Integer integer : sortedNumbers) {
			if (!numbers.contains(integer)) {
				exceptNumbers.add(integer);
			}
		}
		return exceptNumbers;
	}

}
