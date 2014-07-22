
public class Organism {

	
	int x = 0;
	int y = 0;
	boolean isAlive = false;
        int species = 0;
	
	public Organism(int x, int y, int fate, int species){
		this.x = x;
		this.y = y;
		this.species = species;
                
		if(fate == 1){
			this.isAlive = true;
		}
		else{
			this.isAlive = false;
		}
		
	}
	
	public void die() {
		isAlive = false;
	}
        
        
	public void bringToLife() {
		isAlive = true;
	}

}

