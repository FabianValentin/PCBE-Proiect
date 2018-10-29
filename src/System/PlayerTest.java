package System;

import org.junit.Test;
import org.junit.Before;
import java.util.HashSet;
import org.junit.Assert;

public class PlayerTest {
	private Matrix m;
	private Player player;
	
	@Before
	public void setup () {
	    Resource[][] r = new Resource[6][6];
		
		r[0][0] = new Resource("wood", 1);
		r[0][1] = new Resource("food", 2);
		r[0][2] = new Resource("food", 3);
		r[0][3] = new Resource("wood", 4);
		r[0][4] = new Resource("iron", 1);
		r[0][5] = new Resource("wood", 2);
		
		r[1][0] = new Resource("iron", 2);
		r[1][1] = new Resource("iron", 1);
		r[1][2] = new Resource("food", 1);
		r[1][3] = new Resource("wood", 3);
		r[1][4] = new Resource("iron", 4);
		r[1][5] = new Resource("wood", 3);
		
		r[2][0] = new Resource("food", 1);
		r[2][1] = new Resource("iron", 3);
		r[2][2] = new Resource("wood", 4);
		r[2][3] = new Resource("iron", 4);
		r[2][4] = new Resource("food", 4);
		r[2][5] = new Resource("iron", 1);
		
		r[3][0] = new Resource("wood", 1);
		r[3][1] = new Resource("food", 1);
		r[3][2] = new Resource("food", 2);
		r[3][3] = new Resource("wood", 4);
		r[3][4] = new Resource("wood", 3);
		r[3][5] = new Resource("food", 3);
		
		r[4][0] = new Resource("iron", 4);
		r[4][1] = new Resource("wood", 4);
		r[4][2] = new Resource("iron", 1);
		r[4][3] = new Resource("food", 3);
		r[4][4] = new Resource("food", 1);
		r[4][5] = new Resource("wood", 2);
		
		r[5][0] = new Resource("iron", 1);
		r[5][1] = new Resource("iron", 1);
		r[5][2] = new Resource("iron", 3);
		r[5][3] = new Resource("food", 2);
		r[5][4] = new Resource("wood", 2);
		r[5][5] = new Resource("iron", 2);
		
		m = new Matrix();
		m.setMatrix(r);
	}
	
	// Testam colturile.
	@Test
	public void neighbourList0 () {
		player = new Player(0, m);
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(1);
		hs.add(10);
		HashSet<Integer> p = player.getNeighbourList();
		Assert.assertEquals(hs, p);
	}
	
	@Test
	public void neighbourList5 () {
		player = new Player(5, m);
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(4);
		hs.add(15);
		Assert.assertEquals(hs, player.getNeighbourList());
	}
	
	@Test
	public void neighbourList50 () {
		player = new Player(50, m);
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(51);
		hs.add(40);
		Assert.assertEquals(hs, player.getNeighbourList());
	}
	
	@Test
	public void neighbourList55 () {
		player = new Player(55, m);
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(54);
		hs.add(45);
		Assert.assertEquals(hs, player.getNeighbourList());
	}
	
	// Testam linia 0 (fara colturi).
	@Test
	public void neighbourList1 () {
		player = new Player(1, m);
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(0);
		hs.add(2);
		hs.add(11);
		Assert.assertEquals(hs, player.getNeighbourList());
	}
	
	// Testam linia 5 (fara colturi).
	@Test
	public void neighbourList51 () {
		player = new Player(51, m);
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(50);
		hs.add(52);
		hs.add(41);
		Assert.assertEquals(hs, player.getNeighbourList());
	}
	
	// Testam coloana 0 (fara colturi).
	@Test
	public void neighbourList10 () {
		player = new Player(10, m);
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(0);
		hs.add(11);
		hs.add(20);
		Assert.assertEquals(hs, player.getNeighbourList());
	}
	
	// Testam coloana 5 (fara colturi).
	@Test
	public void neighbourList15 () {
		player = new Player(15, m);
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(5);
		hs.add(25);
		hs.add(14);
		Assert.assertEquals(hs, player.getNeighbourList());
	}
	
	@Test 
	public void move1() {
		player = new Player(1, m);
		player.move();
		Assert.assertEquals(2, player.getcurrentPosition());
	}
}
