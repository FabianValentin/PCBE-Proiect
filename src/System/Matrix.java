package System;

class Matrix {
	private static Resource[][] game_matrix = new Resource[6][6];
	private static final int NR_RES = 30;
	
	public Matrix () {
		int wood_res = 0;
		int iron_res = 0;
		int food_res = 0;
		int wood_cells = 0;
		int iron_cells = 0;
		int food_cells = 0;
		
		String resType = "";
		int noR;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				noR = (int)(Math.random()*4);//cream un numar random de unitati dintr-o resurse <=5
				int typeRes = (int)(Math.random()*3) ; //alegem random tipul de resursa 
				switch(typeRes) {
				case 0: if(iron_cells >= food_cells) {
							if((wood_cells - food_cells) > 1)
								typeRes = 2;
						}else {
							if((wood_cells - iron_cells) > 1)
								typeRes = 1;
						}
						break;
				
				case 1: if(wood_cells >= food_cells) {
					if((iron_cells - food_cells) > 1)
						typeRes = 2;
					}else {
						if((iron_cells - wood_cells) > 1)
					typeRes = 0;
					}
					break;
				case 2: if(wood_cells >= iron_cells) {
					if((food_cells - wood_cells) > 1)
						typeRes = 0;
					}else {
						if((food_cells - iron_cells) > 1)
							typeRes = 1;
					}
					break;
				}
				switch (typeRes) {
					case 0 :// 0 = wood
						while(wood_res+noR > NR_RES){
							noR = (int)(Math.random()*3);
						}
						resType = "wood";
						wood_res+=noR;
						wood_cells++;
						break;
					case 1://1 = iron
						while(iron_res+noR > NR_RES) {
							noR = (int)(Math.random()*3);
						}
						resType = "iron";
						iron_res+=noR;
						iron_cells++;
						break;
					case 2://2 = food
						while(food_res+noR > NR_RES){
							noR = (int)(Math.random()*3);
						}
						resType = "food";
						food_res+=noR;
						food_cells++;
						break;	
				}
				
				game_matrix[i][j] = new Resource(resType, noR + 1);
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
				sb.append(game_matrix[i][j].getType() + game_matrix[i][j].getNumber() + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
