package xadrez;

import tabuleiroDoJogo.Peca;
import tabuleiroDoJogo.Tabuleiro;

public class PecaDeXadrez extends Peca {
	
	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	private Cor cor;

	public Cor getCor() {
		return cor;
	}
	
	
}
