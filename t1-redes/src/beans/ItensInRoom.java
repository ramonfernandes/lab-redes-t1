package beans;

public class ItensInRoom {

	private Item item;
	private int x;
	private int y;
	
	public ItensInRoom(Item item, int x, int y) {
		super();
		this.item = item;
		this.x = x;
		this.y = y;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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
	
}
