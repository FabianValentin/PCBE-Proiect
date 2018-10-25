package System;

import Constants.Constants;

public class GameSystem {

	private static Resource[][] matrix = new Resource[6][6];
	
	public static void main(String[] args) {
		createMatrix();
		afis();
	}
	
	public static void createMatrix() {
		int wood_res = 0;
		int iron_res = 0;
		int food_res = 0;
		
		String resType = "";
		int noR;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				noR = (int)(Math.random()*4);//cream un numar random de unitati dintr-o resurse <=5
				int typeRes = (int)(Math.random()*3); //alegem random tipul de resursa 
				
				switch (typeRes) {
					case 0 :// 0 = wood
						while(wood_res+noR > Constants.nr_wood){
							noR = (int)(Math.random()*3);
						}
						resType = Constants.wood;
						wood_res+=noR;
						break;
					case 1://1 = iron
						while(iron_res+noR > Constants.nr_iron) {
							noR = (int)(Math.random()*3);
						}
						resType = Constants.iron;
						iron_res+=noR;
						break;
					case 2://2 = food
						while(food_res+noR > Constants.nr_food){
							noR = (int)(Math.random()*3);
						}
						resType = Constants.food;
						food_res+=noR;
						break;	
				}
				matrix[i][j] = new Resource(resType, noR);
			}
		}
		
	}

	public static void afis() {
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
				System.out.print(matrix[i][j].getType() + matrix[i][j].getNumber() + " ");
			}
			System.out.println();
		}
	}
}
