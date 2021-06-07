package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleirodojogo.Peca;
import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecasNoTabuleiro;
	private List<Peca> pecasCapturadas;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.AZUL;
		pecasNoTabuleiro = new ArrayList<>();
		pecasCapturadas  = new ArrayList<>();
		iniciarPartida();
	}
	
	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
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
	
	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.novaPosicao();
		validarPosicaoDeOrigem(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
	}
	
	public PecaXadrez acaoMoverXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.novaPosicao();
		Posicao destino = posicaoDestino.novaPosicao();
		validarPosicaoDeOrigem(origem);
		validarPosicaoDeDestino(origem, destino);
		Peca capturarPeca = fazerMovimento(origem, destino);
		proximoTurno();
		return (PecaXadrez) capturarPeca;
	}

	private Peca fazerMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicaoDaPeca(p, destino);
		
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}

	private void validarPosicaoDeOrigem(Posicao posicao) {
		if (!tabuleiro.existeUmaPeca(posicao)) {
			throw new XadrezException("Não há peça na posição selecionada.");
		}
		if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peça escolhida não e sua.");
		}
		if (!tabuleiro.peca(posicao).ePossivelRealizarMovimento()) {
			throw new XadrezException("Não existe movimentos possíveis para peça escolhida.");
		}
	}
	
	private void validarPosicaoDeDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).possivelMovimento(destino)) {
			throw new XadrezException("A peça escolhida não pode se mover para posição de destino.");
		}
	}

	private void localDaNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.posicaoDaPeca(peca, new PosicaoXadrez(coluna, linha).novaPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.AZUL) ? Cor.VERMELHO : Cor.AZUL;
	}
	
	private void iniciarPartida() {
		localDaNovaPeca('c', 1, new Torre(tabuleiro, Cor.AZUL));
		localDaNovaPeca('c', 2, new Torre(tabuleiro, Cor.AZUL));
		localDaNovaPeca('d', 2, new Torre(tabuleiro, Cor.AZUL));
		localDaNovaPeca('e', 2, new Torre(tabuleiro, Cor.AZUL));
		localDaNovaPeca('e', 1, new Torre(tabuleiro, Cor.AZUL));
		localDaNovaPeca('d', 1, new Rei(tabuleiro, Cor.AZUL));

		localDaNovaPeca('c', 7, new Torre(tabuleiro, Cor.VERMELHO));
		localDaNovaPeca('c', 8, new Torre(tabuleiro, Cor.VERMELHO));
		localDaNovaPeca('d', 7, new Torre(tabuleiro, Cor.VERMELHO));
		localDaNovaPeca('e', 7, new Torre(tabuleiro, Cor.VERMELHO));
		localDaNovaPeca('e', 8, new Torre(tabuleiro, Cor.VERMELHO));
		localDaNovaPeca('d', 8, new Rei(tabuleiro, Cor.VERMELHO));
	}
}
