package System;

public class MiraMatrix {
    private static MiraResource[][] gameTable =  new MiraResource[6][6];;


    public static MiraResource[][] createMatrix() {
        for (int i = 0; i < 6; i++) {
                for(int j = 0; j < 6; j++){
                    if (i == 0 || i == 5 || i == 3)
                        gameTable[i][j] = new MiraResource("",0);
                    else
                        gameTable[i][j] = new MiraResource("wood", (int)(Math.random()*5));
                }
        }
        return gameTable;
    }

}
