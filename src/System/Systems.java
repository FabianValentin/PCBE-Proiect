package System;

import java.util.HashMap;

import Constants.Constants;

public class Systems {

	//Mira: camp static pentru a nu crea obiect Systems ca sa o folosesc in Player
	//sau clasa Matrix/ GameTable
	private Resource[][] matrix = new Resource[6][6];
	
	public static void main(String[] args) {
		Systems system = new Systems();
		system.createMatrix();
		system.afis();
	}
	
	public void createMatrix() {
		int cntr_wood = 0;
		int cntr_iron = 0;
		int cntr_food = 0;
		HashMap<String, Integer> res;
		Integer nrRes;
		String resType="";
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				int noR = (int)(Math.random()*5);
				if(noR == 0)
					noR = 1;
				int typeRes = (int)(Math.random()*(5.9));
				
				switch (typeRes) {
				case 0 :// 0 = wood
					while(cntr_wood+noR > Constants.nr_wood)
					{
						noR = (int)Math.random()*3;
						if(noR == 0)
							noR = 1;
					}
					resType = Constants.wood;
					cntr_wood+=noR;
					break;
				case 1://1 = iron
					while(cntr_iron+noR > Constants.nr_iron)
					{
						noR = (int)Math.random()*3;
					}
					resType = Constants.iron;
					cntr_iron+=noR;
					break;
				case 2://2 = food
					while(cntr_food+noR > Constants.nr_food)
					{
						noR = (int)Math.random()*3;
					}
					resType = Constants.food;
					cntr_food+=noR;
					break;	
				case 3 :// 3 = wood
					while(cntr_wood+noR > Constants.nr_wood)
					{
						noR = (int)Math.random()*3;
						if(noR == 0)
							noR = 1;
					}
					resType = Constants.wood;
					cntr_wood+=noR;
					break;
				case 4://4 = iron
					while(cntr_iron+noR > Constants.nr_iron)
					{
						noR = (int)Math.random()*3;
					}
					resType = Constants.iron;
					cntr_iron+=noR;
					break;
				case 5://5 = food
					while(cntr_food+noR > Constants.nr_food)
					{
						noR = (int)Math.random()*3;
					}
					resType = Constants.food;
					cntr_food+=noR;
					break;	
				}
				nrRes = new Integer(noR);
				res = new HashMap<>();
				res.put(resType, nrRes);
				matrix[i][j] = new Resource(res);
			}
		}
	}

	public void afis() {
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
				
				System.out.print(matrix[i][j].getResource().keySet().toString() + " " + matrix[i][j].getResource().values());
			}
			System.out.println();
		}
	}
}
