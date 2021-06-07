package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESETE = "\u001B[0m";
	public static final String ANSI_PRETO = "\u001B[30m";
	public static final String ANSI_VERMELHO = "\u001B[31m";
	public static final String ANSI_VERDE = "\u001B[32m";
	public static final String ANSI_AMARELO = "\u001B[33m";
	public static final String ANSI_AZUL = "\u001B[34m";
	public static final String ANSI_ROXO = "\u001B[35m";
	public static final String ANSI_CIANO = "\u001B[36m";
	public static final String ANSI_BRANCO = "\u001B[37m";

	public static final String ANSI_FUNDO_PRETO = "\u001B[40m";
	public static final String ANSI_FUNDO_VERMELHO = "\u001B[41m";
	public static final String ANSI_FUNDO_VERDE = "\u001B[42m";
	public static final String ANSI_FUNDO_AMARELO = "\u001B[43m";
	public static final String ANSI_FUNDO_AZUL = "\u001B[44m";
	public static final String ANSI_FUNDO_ROXO = "\u001B[45m";
	public static final String ANSI_FUNDO_CIANO = "\u001B[46m";
	public static final String ANSI_FUNDO_BRANCO = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(coluna, linha);
		} catch (RuntimeException e) {
			throw new InputMismatchException(
					"Erro na leitura da posição do Xadrez: Somente valores do a1 ao h8 são permitidos.");

		}
	}
	
	public static void imprimirPartida(PartidaXadrez partidaXadrez, List<PecaXadrez> capturadas) {
		imprimirTabuleiro(partidaXadrez.getPecas());
		System.out.println();
		imprimirPecasCapturadas(capturadas);
		System.out.println();
		System.out.println("Turno: " + partidaXadrez.getTurno());
		System.out.println("Aguardando o jogador: " + partidaXadrez.getJogadorAtual());
	}

	public static void imprimirTabuleiro(PecaXadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) { // Esta sendo utilizado length em consideracao de uma matriz quadrada
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimirPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void imprimirTabuleiro(PecaXadrez[][] pecas, boolean[][] possiveisMovimentos) {
		for (int i = 0; i < pecas.length; i++) { // Esta sendo utilizado length em consideracao de uma matriz quadrada
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimirPeca(pecas[i][j], possiveisMovimentos[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void imprimirPeca(PecaXadrez peca, boolean fundoTela) {
		if (fundoTela) {
			System.out.print(ANSI_FUNDO_VERDE);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESETE);
		} else {
			if (peca.getCor() == Cor.AZUL) {
				System.out.print(ANSI_AZUL + peca + ANSI_RESETE);
			} else {
				System.out.print(ANSI_VERMELHO + peca + ANSI_RESETE);
			}
		}
		System.out.print(" ");
	}
	
	private static void imprimirPecasCapturadas(List<PecaXadrez> capturadas) {
		List<PecaXadrez> azul = capturadas.stream().filter(x -> x.getCor() == Cor.AZUL).collect(Collectors.toList());
		List<PecaXadrez> vermelha = capturadas.stream().filter(x -> x.getCor() == Cor.VERMELHO).collect(Collectors.toList());
		
		System.out.println("Peças capturadas.");
		System.out.print("Azuis: ");
		System.out.println(ANSI_AZUL + Arrays.toString(azul.toArray()) + ANSI_RESETE);
		System.out.print("Vermelhas: ");
		System.out.println(ANSI_VERMELHO + Arrays.toString(vermelha.toArray()) + ANSI_RESETE);
	}
}
