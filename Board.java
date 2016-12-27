import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel{
	
	public static Cell[][] board;
	private ArrayList<Cell> completeBoard = new ArrayList<Cell>();
	private int dimension;
	private int x, y;
	private int figureWidth, figureHeight;
	private JLabel empty;
	
	public Board(int dimension, BufferedImage rompecabezas){
		this.dimension = dimension;
		board = new Cell[dimension][dimension];
		this.setBackground(Color.BLACK);
		x = 0;
		y = 0;
		
		figureWidth = rompecabezas.getWidth() / dimension;
		figureHeight = rompecabezas.getHeight() / dimension;
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		for(int i = 0; i< dimension; i++){
			for(int j = 0; j< dimension; j++){
				if(i == dimension -1 && j == dimension -1){
					continue;
				}	
				completeBoard.add(new Cell(i, j, new Figure(i, j, new ImageIcon(rompecabezas.getSubimage(x, y, figureWidth, figureHeight)), dimension)));
				
				x += figureWidth;
			}
			x = 0;
			y += figureHeight;
		}
		Desordenar();
		
		Remover();
		
	}
	private void Desordenar(){
		
		Random generador = new Random();
		ArrayList<Cell> copia = new ArrayList<Cell>(completeBoard);
		for(int i = 0; i< dimension; i++){
			for(int j = 0; j< dimension; j++){
				if(i == dimension -1 && j == dimension -1){
					board[i][j] = new Cell(i, j);
					continue;
				}
				int aleatorio = generador.nextInt(completeBoard.size());
				completeBoard.get(aleatorio).getFigure().setxPos(i);
				completeBoard.get(aleatorio).getFigure().setyPos(j);
				board[i][j] = new Cell(i, j, completeBoard.get(aleatorio).getFigure());
				completeBoard.remove(aleatorio);
			}
		}
		completeBoard = copia;
		Remover();
	}
	private void Actualizar(){
		for(int i = 0; i< dimension; i++){
			for(int j = 0; j< dimension; j++){
				if(board[i][j].getFigure() == null){
					empty = new JLabel();
					empty.setPreferredSize(new Dimension(figureWidth, figureHeight));
					this.add(empty);
					continue;
				}
				this.add(board[i][j].getFigure());
			}
		}
		Puzzle.contenedor.validate();
		
	}
	public void Remover(){
		this.removeAll();
		Actualizar();
	}
}