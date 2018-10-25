package System;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PlayerTest {
	private Resource[][] r;
	@Before
	private void setup () {
		r = new Resource[6][6];
		
		// food - 9; wood - 21; iron - 12
		r[0][0] = new Resource("wood", 1);
		r[0][1] = new Resource("food", 2);
		r[0][2] = new Resource("wood", 1);
		r[0][3] = new Resource("iron", 3);
		r[0][4] = new Resource("wood", 1);
		r[0][5] = new Resource("wood", 0);
		
		r[1][0] = new Resource("food", 0);
		r[1][1] = new Resource("wood", 2);
		r[1][2] = new Resource("wood", 1);
		r[1][3] = new Resource("wood", 0);
		r[1][4] = new Resource("wood", 3);
		r[1][5] = new Resource("iron", 2);
		
		r[2][0] = new Resource("food", 0);
		r[2][1] = new Resource("iron", 3);
		r[2][2] = new Resource("wood", 2);
		r[2][3] = new Resource("food", 2);
		r[2][4] = new Resource("food", 2);
		r[2][5] = new Resource("wood", 0);
		
		r[3][0] = new Resource("wood", 0);
		r[3][1] = new Resource("iron", 0);
		r[3][2] = new Resource("wood", 1);
		r[3][3] = new Resource("iron", 1);
		r[3][4] = new Resource("wood", 3);
		r[3][5] = new Resource("food", 0);
		
		r[4][0] = new Resource("wood", 1);
		r[4][1] = new Resource("iron", 0);
		r[4][2] = new Resource("food", 1);
		r[4][3] = new Resource("food", 2);
		r[4][4] = new Resource("iron", 0);
		r[4][5] = new Resource("wood", 2);
		
		r[1][0] = new Resource("wood", 0);
		r[1][1] = new Resource("wood", 3);
		r[1][2] = new Resource("food", 0);
		r[1][3] = new Resource("food", 0);
		r[1][4] = new Resource("wood", 0);
		r[1][5] = new Resource("iron", 3);
	}
	
}
