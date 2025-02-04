package snakepackage;

/**
 * Classe Cell
 * 
 * contem informacao sobre o estado da celula, 
 * se contem elementos,
 *  quais, 
 *  e trata da Sincronizacao e coordenacao no acesso � mesma
 * 
 * @author Joao Andrade 28445
 * @author Diana Pereira 38074
 * @author Juan Pablo Daza Pereira
 */
public class Cell {

	/** X - Coordenada X da celula */
	private int x;
	
	/** Y - Coordenada Y da celula */
	private int y;
	
	/** full - Se Celula preenchida por uma cobra ou barreira */
	private boolean full;
	
	
	/** food - Se Celula preenchida por uma comida */
	private boolean food;
	
	/** jump_pad - Se Celula preenchida por um salto ao eixo*/
	private boolean jump_pad;
	
	/** turbo_boost - Se Celula preenchida por um turbo-boost */
	private boolean turbo_boost;
	
	private boolean barrier;
	
	/**
	 * Verifica se Celula tem turbo_boost.
	 *
	 * @return true, se tem turbo_boost
	 */
	public synchronized boolean isTurbo_boost() {
		return turbo_boost;
	}

	/**
	 * Poe turbo-boost na celula.
	 *
	 * @param turbo_boost � novo turbo-boost
	 */
	public synchronized void setTurbo_boost(boolean turbo_boost) {
		this.turbo_boost = turbo_boost;
	}

	/**
	 * Verifica se Celula tem comida.
	 *
	 * @return true, se tem comida
	 */
	public synchronized boolean isFood() {
		return food;
	}

	/**
	 * Poe comida na celula
	 *
	 * @param food � a nova comida
	 */
	public synchronized void setFood(boolean food) {
		this.food = food;
	}

	/**
	 * Verifica se Celula tem salto-ao-eixo.
	 *
	 * @return true, se tem salto-ao-eixo
	 */
	public synchronized boolean isJump_pad() {
		return jump_pad;
	}

	/**
	 * Poe salto-ao-eixo na celula
	 *
	 * @param jump_pad � o novo salto-ao-eixo
	 */
	public synchronized void setJump_pad(boolean jump_pad) {
		this.jump_pad = jump_pad;
	}

	/**
	 * Instancia a nova celula.
	 *
	 * @param x - x
	 * @param y - y
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Vai buscar o X da celula
	 *
	 * @return X
	 */
	public synchronized int getX() {
		return x;
	}

	/**
	 * Redefine o X
	 *
	 * @param x - x
	 */
	public synchronized void setX(int x) {
		this.x = x;
	}

	/**
	 * Vai buscar o Y da celula.
	 *
	 * @return y
	 */
	public synchronized int getY() {
		return y;
	}

	/**
	 * Redefine o Y.
	 *
	 * @param y - y
	 */
	public synchronized void setY(int y) {
		this.y = y;
	}

	/**
	 * Verifica se Celula est� cheia (com cobras ou barreiras)
	 *
	 * @return true, se estiver full
	 */
	public synchronized boolean isFull() {
		return full;
	}

	/**
	 * Poe a celula no estado Full
	 *
	 * @param full
	 */
	public synchronized void setFull(boolean full) {
		this.full = full;
	}

	/* 
	 * o toString da celula para imprimir na consola
	 */
	public String toString() {
		return "x: " + this.x + " y: " + this.y;

	}


	/**
	 * Liberta celula ( ultimo elemento da cobra liberta esta celula para poder ser ocupada por outras )
	 */
	public synchronized void freeCell() {
		full = false;
		notifyAll();
	}

	/**
	 * Verifica se Celula tem elements ( comida, salto-ao-eixo, cobras, barreiras ).
	 *
	 * @return true, 
	 */
	public synchronized boolean hasElements() {
		return full || food || jump_pad || barrier;
	}

	public synchronized boolean isBarrier() {
		return barrier;
	}

	public synchronized void setBarrier(boolean barrier) {
		this.barrier = barrier;
	}

	/**
	 * Compara esta celda con otra celda
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		Cell other = (Cell) obj;
		synchronized(this) {
			synchronized(other) {
				return x == other.x && y == other.y;
			}
		}
	}

	/**
	 * Genera el hashCode de la celda
	 */
	@Override
	public synchronized int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

}