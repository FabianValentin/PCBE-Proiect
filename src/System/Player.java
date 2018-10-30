package System;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class Player {
	
	// Number of players.
	private static int ord = 1;
	
	// Number of the player.
	private int ind;
	
    private int soldiersNumber;
    
    // Mapping resource type to quantity. 
    private Map<String, Integer> resources = new HashMap<>();
    
    // Set of cells that were visited by the player. 
    private HashSet<Integer> pastPositions = new HashSet<>();
    
    // Current position of the player.
    private int currentPosition;
    //status poate fi blocked sau active
    //o sa il convertesc in enum maine sa fie mai type safe
    private String status;

	private Matrix matrix;

    public Player(int currentPosition, Matrix matrix){
        this.currentPosition = currentPosition;
        this.pastPositions.add(currentPosition);
        this.matrix = matrix;
        this.status = "free";
        this.ind = ord;
        this.ord++;
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

    public HashSet<Integer> getPastPositions() {
        return pastPositions;
    }
    
    public int getcurrentPosition() {
        return currentPosition;
    }
    
    public int getIndex() {
    	return ind;
    }

    // Method that moves the player from one position to another. 
    public void move(){
        if (this.status.equals("blocked"))
            return;

        HashSet<Integer> neighboursList = getNeighbourList();

        if (neighboursList.isEmpty()){
            this.status = "blocked";
            return;
        }
       // System.out.println(this.currentPosition);
        this.pastPositions.add(this.currentPosition);
        this.currentPosition = determineMax(neighboursList);
    }
    
    public String getStatus() {
    	return this.status;
    }

    //lista cu vecinii valizi - si ca pozitie si ca disponibilitate
    public HashSet<Integer> getNeighbourList(){
        HashSet<Integer> neighbourList = new HashSet<>();
        int j = currentPosition%10;
        int i = currentPosition/10;
        // nu suntem pe ultima linie
        if (i < 5){
            if (isAvailable((i+1)*10+j))
                neighbourList.add((i+1)*10+j);
        }
        
        //nu suntem pe ultima coloana
        if (j < 5){
            if (isAvailable(i*10+j+1))
                neighbourList.add(i*10+j+1);
        }
        
        //nu suntem pe prima linie
        if (i > 0){
            if (isAvailable((i-1)*10+j))
                neighbourList.add((i-1)*10+j);
        }
        
        //nu suntem pe prima coloana
        if (j > 0){
            if (isAvailable(i*10+j-1))
                neighbourList.add(i*10+j-1);
        }
        
        return neighbourList;
    }

    //daca pozitia e libera => true=available => move, else false
    public boolean isAvailable(int position){
        return !pastPositions.contains(position);
    }

    //determina pozitia cu nr cel mai mare de resurse dintre vecini
    //daca toate sunt egale merge pe ultima - sper ca un fel de random pt ca Set nu are ordine
    public int determineMax(HashSet<Integer> neighboursList){
        int max = 0, pos = 0;
        int i, j, noRes;
        for (Integer n : neighboursList) {
            j = n%10;
            i = n/10;
            noRes = matrix.getMatrix()[i][j].getNumber();
            if(noRes > max) {
                max = noRes;
                pos = n;
            }
        }
        return pos;
    }

	public int getMinimum() {
		Entry<String, Integer> min = null;
		for (Entry<String, Integer> entry : resources.entrySet()) {
		    if (min == null || min.getValue() > entry.getValue()) {
		        min = entry;
		    }
		}
		return min.getValue();
	}
}
