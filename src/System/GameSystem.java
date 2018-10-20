package System;

import Constants.Constants;

public class GameSystem {

	private Resource[][] matrix = new Resource[6][6];
	
	public static void main(String[] args) {
		GameSystem system = new GameSystem();
		system.createMatrix();
		system.afis();
	}
	
	public void createMatrix() {
		int cntr_wood = 0;
		int cntr_iron = 0;
		int cntr_food = 0;
		Resource res;
		String resType="";
		int noR;
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				noR = (int)(Math.random()*5);//cream un numar random de unitati dintr-o resurse <=5
				if(noR == 0)
					noR = 1;
				int typeRes = (int)(Math.random()*(5.9));//alegem random tipul de resursa 
				
				switch (typeRes) { //6 caseuri pentr a distribui pe harta de joc mai bine resursele
				case 0 :// 0 = wood
					while(cntr_wood+noR > Constants.nr_wood)
					{
						noR = (int)(Math.random()*3);
						if(noR == 0)
							noR = 1;
					}
					resType = Constants.wood;
					cntr_wood+=noR;
					break;
				case 1://1 = iron
					while(cntr_iron+noR > Constants.nr_iron)//daca depasim numarul maxim de unitati din resursa respectiva alegem alt numar mai mic
					{
						noR = (int)(Math.random()*3);
					}
					resType = Constants.iron;
					cntr_iron+=noR;
					break;
				case 2://2 = food
					while(cntr_food+noR > Constants.nr_food)
					{
						noR = (int)(Math.random()*3);
					}
					resType = Constants.food;
					cntr_food+=noR;
					break;	
				case 3 :// 3 = wood
					while(cntr_wood+noR > Constants.nr_wood)
					{
						noR = (int)(Math.random()*3);
						if(noR == 0)
							noR = 1;
					}
					resType = Constants.wood;
					cntr_wood+=noR;
					break;
				case 4://4 = iron
					while(cntr_iron+noR > Constants.nr_iron)
					{
						noR = (int)(Math.random()*3);
					}
					resType = Constants.iron;
					cntr_iron+=noR;
					break;
				case 5://5 = food
					while(cntr_food+noR > Constants.nr_food)
					{
						noR = (int)(Math.random()*3);
					}
					resType = Constants.food;
					cntr_food+=noR;
					break;	
				}
				res = new Resource(resType, noR);
				matrix[i][j] = res;//populam harta cu noua resursa
			}
		}
	}

	public void afis() {
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
				
				System.out.print(matrix[i][j].getType() + matrix[i][j].getNo() + " ");
			}
			System.out.println();
		}
	}
}
