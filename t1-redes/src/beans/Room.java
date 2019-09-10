package beans;

import java.util.List;

public class Room {

	private int x;
	private int y;
	private List<ItensInRoom> itens;
	
	public Room(int x, int y, List<ItensInRoom> itens) {
		super();
		this.x = x;
		this.y = y;
		this.itens = itens;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public List<ItensInRoom> getItens() {
		return itens;
	}

	public void setItens(List<ItensInRoom> itens) {
		this.itens = itens;
	}
	
}
