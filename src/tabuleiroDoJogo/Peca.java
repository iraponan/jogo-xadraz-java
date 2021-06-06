package tabuleiroDoJogo;

public class Peca {
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null; // Por padrao o java poe o valor nullo ao declarar a veriavel sem valor.
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
}
