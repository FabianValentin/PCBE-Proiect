package System;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class Player implements Runnable {
	
	// Number of players.
	private static int ord = 1;
	// Number of the player.
	private int ind;
	// Number of soldiers.
    private int soldiersNumber;   
    // Mapping resource type to quantity. 
    private Map<String, Integer> resources = new HashMap<>();
    // Set of cells that were visited by the player. 
    private HashSet<Integer> pastPositions = new HashSet<>();
    // Current position of the player.
    private int currentPosition;
    // Status can be either free or blocked. 
    private String status;
    // The game matrix.
	private Matrix matrix;

    public Player(int currentPosition, Matrix matrix){
    	this.matrix = matrix;
        this.currentPosition = currentPosition;
        this.pastPositions.add(currentPosition);
        takeResources(currentPosition);
        this.status = "free";
        this.ind = ord;
        increment();
    }
    
    private static void increment () {
    	ord++;
    }

    private void takeResources(int currentPosition) {
    	int i = currentPosition/10;
    	int j = currentPosition%10;
    	String type = matrix.getMatrix()[i][j].getType();
    	
    	// If the cell isn't empty, the player gets one resource. 
    	if (matrix.getMatrix()[i][j].getNumber() != 0) {
	    	if (!this.resources.containsKey(type)) {
	    		this.resources.put(type, 1);
	    	} else {
	    		this.resources.put(type, 1 + this.resources.get(type));
	    	}
	    	matrix.update(i, j);
    	}
	}

	public int getSoldiersNumber() {
        return soldiersNumber;
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
    
    public String getStatus() {
    	return this.status;
    }

    /* Method that moves the player from one position to another. */
    public void move(){
    	synchronized(matrix) {
	        if (this.status.equals("blocked"))
	            return;
	
	        HashSet<Integer> neighboursList = getNeighbourList();
	
	        if (neighboursList.isEmpty()){
	        	System.out.println("Player " + this.ind + " is blocked.");
	            this.status = "blocked";
	            return;
	        }
	        
	        System.out.print("Player " + this.ind + " moved from " + this.currentPosition + " to ");
	        this.pastPositions.add(this.currentPosition);
	        if (determineMax(neighboursList) == -1) {
	        	this.status = "blocked";
	        	return;
	        }
	        this.currentPosition = determineMax(neighboursList);
	        System.out.print(currentPosition + "\n");
	        takeResources(currentPosition);
    	}
    }

    /* Generates a list with valid neighbours. */
    public HashSet<Integer> getNeighbourList(){
        HashSet<Integer> neighbourList = new HashSet<>();
        int j = currentPosition%10;
        int i = currentPosition/10;
        
        // Not on the last row.
        if (i < 2){ //5
            if (isAvailable((i+1)*10+j))
                neighbourList.add((i+1)*10+j);
        }
        
        // Not on the last column. 
        if (j < 2){ //5
            if (isAvailable(i*10+j+1))
                neighbourList.add(i*10+j+1);
        }
        
        // Not on the first row. 
        if (i > 0){
            if (isAvailable((i-1)*10+j))
                neighbourList.add((i-1)*10+j);
        }
        
        // Not on the first column, 
        if (j > 0){
            if (isAvailable(i*10+j-1))
                neighbourList.add(i*10+j-1);
        }
        
        return neighbourList;
    }

    /* Check if a position is available (the player hasn't been there before). */
    public boolean isAvailable(int position){
        return !pastPositions.contains(position);
    }

    /* Taking the neighbour list, determines which one has the largest number of resources.
     * In case of equality, the last value in the set is returned (this is done randomly,
     * as sets have no specific order. 
     */
    public int determineMax(HashSet<Integer> neighboursList){
        int max = -1, pos = -1;
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

	@Override
	public void run() {
		while(this.status.equals("free")) {
			move();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
