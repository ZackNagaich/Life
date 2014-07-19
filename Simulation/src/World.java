	 
import java.awt.*;

import java.util.Random;

import javax.swing.*;
	 

	 
	public class World extends JPanel {

		private static final long serialVersionUID = 1L;
		
		private static int width = 2;
		private static int height = 2;
		private static int arcWidth =2;
		private static int arcHeight = 2;
		private static Organism[][] worldContainer = new Organism[800][600];
		
		private void doDrawing(Graphics g) {

	        Graphics2D g2d = (Graphics2D) g;
	       
	        for (int i = 0; i < 800; i++) {
	    		 for(int j = 0; j < 600; j++){
	    			 
		        		Organism organism = worldContainer[i][j];
		        		if(organism != null){
		        			if(organism.isAlive){
		        					g2d.setColor(Color.blue);
		        					g2d.fillRoundRect(organism.x, organism.y, width, height, arcWidth, arcHeight);
		        			}
		        		}
	    		 }
	        }
	    }

	    @Override
	    public void paintComponent(Graphics g) {

	        super.paintComponent(g);
	        doDrawing(g);

	    }
		
	    public static void main(String s[]) {
	       
	    	World world = new World();
	    	JFrame frame = new JFrame("Simulation");
	    	frame.add(world);
	    	frame.setSize(800,600);
	    	frame.setVisible(true);
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	

	    	generateOrganisms();

	    	
	    	world.repaint();
	    	
	    	while(true){
	    		
	    		try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

	    		updatePopulation();
	    		
	    		
	    		world.repaint();
	    	}
	    }
	    
	    
	    public static void generateOrganisms(){
	    	
	    	 for (int i = 0; i < 800; i++) {
	    		 for(int j = 0; j < 600; j++){
	    			 
	    		  Random r = new Random();
	    		  
	    		  int fate = r.nextInt() % 19;
		          worldContainer[i][j] = new Organism(i,j,fate);

	    		 }
		     }

	    }
	    
	    public static void updatePopulation(){
	    	
	    	for(int i = 0; i < 800; i++){
	    		for(int j = 0; j < 600; j++){
	    			
		    		Organism organism = worldContainer[i][j];
		    		
		    		/*
		    		 * Any cell with fewer than two live neighbours dies, as if caused by under-population
		    		 */
		    		int organX = organism.x;
		    		int organY = organism.y;
		    		
		    		int adjacentSurvivors = 0;
		    		
		    		boolean left = isAlive(organX-1,organY);
		    		if(left){
		    			adjacentSurvivors++;
		    		}
		    		boolean right = isAlive(organX+1,organY);
		    		if(right){
		    			adjacentSurvivors++;
		    		}
		    		boolean upright = isAlive(organX+1,organY+1);
		    		if(upright){
		    			adjacentSurvivors++;
		    		}
		    		boolean upleft = isAlive(organX-1,organY+1);
		    		if(upleft){
		    			adjacentSurvivors++;
		    		}
		    		boolean up = isAlive(organX,organY+1);
		    		if(up){
		    			adjacentSurvivors++;
		    		}
		    		boolean down = isAlive(organX,organY-1);
		    		if(down){
		    			adjacentSurvivors++;
		    		}
		    		boolean downright = isAlive(organX+1,organY-1);
		    		if(downright){
		    			adjacentSurvivors++;
		    		}
		    	    boolean downleft = isAlive(organX-1,organY-1);
		    		if(downleft){
		    			adjacentSurvivors++;
		    		}
		    		
		    		if(isAlive(organX,organY)){

		    			if(adjacentSurvivors < 2 || adjacentSurvivors > 3){
		    				organism.die();
		    			}
		    			
		    		}
		    		else{
		    			if(adjacentSurvivors == 3){
		    				organism.bringToLife();
		    			}
		    			
		    		}
	    		}
	    	}
	    	
	    }
	    
	    
	    public static boolean isAlive(int x, int y){
	    	
	    	if(x == -1 || y == -1 || x == 800 || y == 600){
	    		return false;
	    	}
	    	
	    	else{
	    		return worldContainer[x][y].isAlive;
	    	}
	    	
	    }

	   
	   
	}
	
	
	