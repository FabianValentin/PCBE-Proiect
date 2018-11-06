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
	
	private static boolean trade_ok = false;
	private static int start_tradeing = 0;
	
	private int cnt = 2;

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

    public synchronized void trade(String givenRes, String receivedRes) {
    	if(start_tradeing != 0)
    	{
    		if(trade_ok) {
    			trade_ok = false;
    		}
    		else {
    			trade_ok = true;
    		}
    	}
    	else {
    		start_tradeing++;
    	}
    	while(!trade_ok)
    	{
    		try { 
    			System.out.println("primul tred s-a blocat");
    			wait();
    		} catch (InterruptedException e)  {
    			Thread.currentThread().interrupt(); 
    		}
    	}
    	notifyAll();
    	System.out.println("A fost realizat schimbul");
    }
    
	public String getMinimum() {
		
		if (resources.get("wood") <= resources.get("food")) {
			if (resources.get("wood") <= resources.get("iron")) {
				return "wood";
			}
			else if (resources.get("food") <= resources.get("iron")){
				return "food";
			}
		} else if (resources.get("food") <= resources.get("iron")) {
				return "food";
		}
		return "iron";
	}
	
	//se va face un schimb o data; dupa schimb se va actualiza numarul de soldati si se va preda firul aleatoriu
	public String chooseIfNeedsChange () {
		String min = getMinimum();
		int wood = resources.get("wood") - resources.get(min); //a
		int food = resources.get("food") - resources.get(min); //b
		int iron = resources.get("iron") - resources.get(min); //c
		
		if (wood == 0) {
			if ((food > 1 && iron > 1) || food > 2 || iron>2) {
				if (food > iron) {
					return "Needs to change b: " + (food - iron - 1);
				} else if (food < iron) {
					return "Needs to change c: " + (iron - food - 1);
				} else {
					return "Can change b or c: " + (food - iron - 1);
				}
			}
		} else if (food == 0) {
			if ((wood > 1 && iron > 1) || wood > 2 || iron>2) {
				if (wood > iron) {
					return "Needs to change a: " + (wood - iron - 1);
				} else if (wood < iron) {
					return "Needs to change c: " + (iron - wood - 1);
				} else {
					return "Can change a or c: " + (wood - iron - 1);
				}
			}
		} else if (iron == 0) {
			if ((wood > 1 && food > 1) || wood > 2 || food>2) {
				if (wood > food) {
					return "Needs to change a: " + (wood - food - 1);
				} else if (wood < food) {
					return "Needs to change c: " + (food - wood - 1);
				} else {
					return "Can change a or b: " + (wood - food - 1);
				}
			}
		}
		
		return null;
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
		while(this.cnt != 0) {
			trade("wood", "iron");
			this.cnt--;
		}
	}
}
