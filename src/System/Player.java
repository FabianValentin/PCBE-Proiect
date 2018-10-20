package System;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Player {
    private int soldiersNumber;
    private Map<String, Integer> resources = new HashMap<>();
    private HashSet<Integer> takenPositions = new HashSet<>();
    private int actualPosition;
    //status poate fi blocked sau active
    //o sa il convertesc in enum maine sa fie mai type safe
    private String status;

    private MiraResource[][] gameTable = MiraMatrix.createMatrix();

    public Player(int actualPosition, String status){
        this.actualPosition = actualPosition;
        this.status = status;
    }

    public int getSoldiersNumber() {
        return soldiersNumber;
    }

    public void setSoldiersNumber(int soldiersNumber) {
        this.soldiersNumber = soldiersNumber;
    }

    public Map<String, Integer> getResources() {
        return resources;
    }

    public void setResources(Map<String, Integer> resources) {
        this.resources = resources;
    }

    public HashSet<Integer> getTakenPositions() {
        return takenPositions;
    }

    public void setTakenPositions(HashSet<Integer> takenPositions) {
        this.takenPositions = takenPositions;
    }

    public int getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(int actualPosition) {
        this.actualPosition = actualPosition;
    }

    //Metoda care il face pe jucator sa se mute pe alta casuta
    //I se actualizeaza actualPosition
    public void move(){
        if (this.status.equals("blocked"))
            return;

        HashSet<Integer> neighboursList = createNeighboursList();

        if (neighboursList.isEmpty()){
            this.status = "blocked";
            return;
        }

        this.actualPosition = determineMax(neighboursList);
    }

    //lista cu vecinii valizi - si ca pozitie si ca disponibilitate
    public HashSet<Integer> createNeighboursList(){
        HashSet<Integer> neighboursList = new HashSet<>();
        int j = actualPosition%10;
        int i = (actualPosition-j)/10;

        if (i + 1 < 6){
            if (isAvailable((i+1)*10+j))
                neighboursList.add((i+1)*10+j);
        }
        if (j + 1 < 6){
            if (isAvailable(i*10+j+1))
                neighboursList.add(i*10+j+1);
        }
        if (i - 1 > 0){
            if (isAvailable((i-1)*10+j))
                neighboursList.add((i-1)*10+j);
        }
        if (j - 1 > 0){
            if (isAvailable(i*10+j-1))
                neighboursList.add(i*10+j-1);
        }
        return neighboursList;
    }

    //daca pozitia e libera => true=available => move, else false
    public boolean isAvailable(int position){
        return !takenPositions.contains(position);
    }

    //determina pozitia cu nr cel mai mare de resurse dintre vecini
    //daca toate sunt egale merge pe ultima - sper ca un fel de random pt ca Set nu are ordine
    public int determineMax(HashSet<Integer> neighboursList){
        int max = 0, pos = 0;
        int i, j, noRes;
        for (Integer n : neighboursList) {
            j = n%10;
            i = (n-j)/10;
            noRes = gameTable[i][j].getNumber();
            if(noRes > max)
                max = noRes;
            pos = n;
        }
        return pos;
    }

}
