package System;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameSystem {
	
	public static void main(String[] args) {
		Matrix matrix = new Matrix();
		CopyOnWriteArrayList<Player> player_list = new CopyOnWriteArrayList<Player>();
		 
		Player p1 = new Player(0, matrix);
		player_list.add(p1);
		
		Player p2 = new Player(2, matrix);
		player_list.add(p2);
		
		print(matrix.getMatrix(), p1, p2);
		
		ArrayList<Thread> thread_list = new ArrayList<Thread>();
		
		for(Player p : player_list)
					thread_list.add(new Thread(p));
			
		for(Thread t : thread_list) {
			t.start();
		}
			
		try {
			for(Thread t : thread_list) {
				t.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		print(matrix.getMatrix(), p1, p2);
	}
	
	public static void print(Resource[][] matrix, Player p1, Player p2) {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
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
		System.out.println("Player 1 has " + p1.getResources());
		System.out.println("Player 2 has " + p2.getResources());
		System.out.println();
	}
}
