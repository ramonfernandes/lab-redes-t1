package beans;

public class Chest extends Item {

	private boolean closed;
	
	public Chest(String name, String description, boolean closed) {
		super(name, description);
		this.closed = closed;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

}
