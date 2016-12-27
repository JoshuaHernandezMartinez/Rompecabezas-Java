import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Figure extends JButton implements ActionListener{
	
	private final int xSolPos, ySolPos;
	private int xPos, yPos;
	private int dimension;
	
	public Figure(int xSolPos, int ySolPos, ImageIcon subimage, int dimension){
		this.xSolPos = xSolPos;
		this.ySolPos = ySolPos;
		this.dimension = dimension;
		
		xPos = xSolPos;
		yPos = ySolPos;
		
		this.setIcon(subimage);
		this.setPreferredSize(new Dimension(subimage.getIconWidth(), subimage.getIconHeight()));
		this.addActionListener(this);
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getxSolPos() {
		return xSolPos;
	}

	public int getySolPos() {
		return ySolPos;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Move();
	}
	private void Move(){
		Cell[][] board = Board.board;
		try{
		if(board[xPos][yPos-1].getFigure() == null){ // arriba
			Board.board[xPos][yPos-1].setFigure(this);
			Board.board[xPos][yPos].setFigure(null);
			yPos --;
			Puzzle.board.Remover();
			ComprobarRespuesta();
			return;
			
		}
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
		if(board[xPos][yPos +1].getFigure() == null){ // abajo
			Board.board[xPos][yPos +1].setFigure(this);
			Board.board[xPos][yPos].setFigure(null);
			yPos ++;
			Puzzle.board.Remover();
			ComprobarRespuesta();
			return;
		}
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
		if(board[xPos + 1][yPos].getFigure() == null){ // derecha
			Board.board[xPos + 1][yPos].setFigure(this);
			Board.board[xPos][yPos].setFigure(null);
			xPos ++;
			Puzzle.board.Remover();
			ComprobarRespuesta();
			return;
		}
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
		if(board[xPos - 1][yPos].getFigure() == null){ // izquierda
			Board.board[xPos - 1][yPos].setFigure(this);
			Board.board[xPos][yPos].setFigure(null);
			xPos --;
			Puzzle.board.Remover();
			ComprobarRespuesta();
			return;
		}
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
		
		
	}
	private void ComprobarRespuesta(){
		Figure figura = null;
		
		for(int i = 0; i< dimension; i++){
			for(int j = 0; j< dimension; j++){
				
				figura = Board.board[i][j].getFigure();
				if(figura == null){
					continue;
				}
				if(figura.getxPos() != figura.getxSolPos() || 
				   figura.getyPos() != figura.getySolPos()){
					return;
				}	
			}
		}
		
		// completado
		JOptionPane.showMessageDialog(new JPanel(), "Felicitaciones", "Rompecabezas Terminado", JOptionPane.INFORMATION_MESSAGE);
	}
}
