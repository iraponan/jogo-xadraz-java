package xadrez;

import tabuleirodojogo.Peca;
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
	
	public PecaXadrez acaoMoverXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.novaPosicao();
		Posicao destino = posicaoDestino.novaPosicao();
		validarPosicaoDeOrigem(origem);
		Peca capturarPeca = fazerMovimento(origem, destino);
		return (PecaXadrez) capturarPeca;
	}
	
	private Peca fazerMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicaoDaPeca(p, destino);
		return pecaCapturada;
	}

	private void validarPosicaoDeOrigem(Posicao posicao) {
		if (!tabuleiro.existeUmaPeca(posicao)) {
			throw new XadrezException("Não há peça na posição selecionada.");
		}
	}

	private void localDaNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.posicaoDaPeca(peca, new PosicaoXadrez(coluna, linha).novaPosicao());
	}
	
	private void iniciarPartida() {
		localDaNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		localDaNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		localDaNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		localDaNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		localDaNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		localDaNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		localDaNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		localDaNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		localDaNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		localDaNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		localDaNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		localDaNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
