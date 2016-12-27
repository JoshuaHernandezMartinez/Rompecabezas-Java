
public class Cell {
	private final int x, y;
	private Figure figure;
	
	public Cell(int x, int y, Figure figure){
		this.x = x;
		this.y = y;
		this.figure = figure;
		
	}
	public Cell(int x, int y){
		this.x = x;
		this.y = y;
		figure = null;
	}
	public Figure getFigure(){
		return figure;
	}
	public void setFigure(Figure figure){
		this.figure = figure;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}