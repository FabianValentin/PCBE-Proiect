package System;

public class GameSystem {
	
	public static void main(String[] args) {
		Matrix matrix = new Matrix();
		Player p1 = new Player(0, matrix);
		Player p2 = new Player(5, matrix);
		print(matrix.getMatrix(), p1, p2);
		p1.move();
		p2.move();
		System.out.println();
		print(matrix.getMatrix(), p1, p2);
	}
	
	public static void print(Resource[][] matrix, Player p1, Player p2) {
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
				System.out.print("|" + matrix[i][j].getType() + " " + matrix[i][j].getNumber() + "  ");
				if(p1.getcurrentPosition() == i*10 + j)
					System.out.print(p1.getIndex());
				else if (p2.getcurrentPosition() == i*10 + j)
					System.out.print(p2.getIndex());
				else
					System.out.print(" ");
			}
			System.out.print("|");
			System.out.println();
			for(int j = 0; j < 60; j++)
				System.out.print("-");
			System.out.println();
		}
	}
}
