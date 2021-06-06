package xadrez;

import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		iniciarPartida();
	}
	
	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()]; 
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	private void localDaNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.posicaoDaPeca(peca, new PosicaoXadrez(coluna, linha).novaPosicao());
	}
	
	private void iniciarPartida() {
		localDaNovaPeca('b', 6, new Torre(tabuleiro, Cor.BRANCO));
		localDaNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
		localDaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
	}
}
