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
	private static int minPercOddSortedNumbers = 60;

	// numeros sorteados (jogo original)
	private static List<Integer> sortedNumbers = new ArrayList<>();

	// Bloco 1
	private static List<Integer> sortedNumbersBlock1 = new ArrayList<>();
	private static List<Integer> sortedNumbersBlock2 = new ArrayList<>();
	private static List<Integer> sortedNumbersBlock3 = new ArrayList<>();

	private static final int minDez = 1;
	private static final int maxDez = 25;

	// quantidade de numeros impares ja sorteados
	private static int countSortedOddNumbers = 0;

	public static void main(String[] args) {

		List<Line> lines = new ArrayList<>();
		lines.add(new Line(1, 0, 3, 4, Arrays.asList(1, 2, 3, 4, 5)));
		lines.add(new Line(2, 1, 1, 2, Arrays.asList(6, 7, 8, 9, 10)));
		lines.add(new Line(3, 0, 3, 4, Arrays.asList(11, 12, 13, 14, 15)));
		lines.add(new Line(4, 1, 2, 2, Arrays.asList(16, 17, 18, 19, 20)));
		lines.add(new Line(5, 0, 2, 3, Arrays.asList(21, 22, 23, 24, 25)));

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
					}
				}
			}
		});

		/*
		 * Parte 2 - Preenchendo as demais dezenas, atentendo aos requisitos minimos
		 */

		lines.sort(Comparator.comparing(Line::getPriority));
		lines.forEach(line -> {
			while (line.getCountSortedNumbers() < line.getMaxSortedNumbers()) {
				int sortedNumber = line.getNumbers().get(new Random().nextInt(line.getNumbers().size()));
				boolean isOddNumber = false;
				if (sortedNumber % 2 != 0) {
					isOddNumber = true;
				}
				int percOddNumbers = countSortedOddNumbers / qtdSortedNumbers;

				// validacao quanto a quantidade minima de numeros impares
				if (isOddNumber == false && percOddNumbers < minPercOddSortedNumbers) {
					continue;
				} else {
					if (isOddNumber) {
						countSortedOddNumbers++;
					}
				}

				if (!sortedNumbers.contains(sortedNumber)) {
					line.setCountSortedNumbers(line.getCountSortedNumbers() + 1);
					sortedNumbers.add(sortedNumber);
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

		System.out.println("\nBloco 1: ");
		printNumbers(sortedNumbersBlock1);
		System.out.println("Bloco 2: ");
		printNumbers(sortedNumbersBlock2);
		System.out.println("Bloco 3: ");
		printNumbers(sortedNumbersBlock3);

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

}
