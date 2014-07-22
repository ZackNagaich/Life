import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;
	 

	 
	public class World extends JPanel {

		private static final long serialVersionUID = 1L;
		
                private static boolean runSimulation = true;
                private static int worldWidth = 800;
                private static int worldHeight = 600;
		private static int width = 2;
		private static int height = 2;
		private static int arcWidth = 1;
		private static int arcHeight = 1;
		private static Organism[][] worldContainer = new Organism[worldWidth][worldHeight];
                private static World world = new World();
                
                public World(){
                        addKeyListener(new KeyListener() {
                            
                            @Override
                            public void keyTyped(KeyEvent e) {
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {
                                
                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_SPACE){

                                    if(runSimulation == true){
                                        
                                         runSimulation = false;
                                    }
                                    
                                    else{
                                        
                                        runSimulation = true;
                                    }
                                }
                                
                                if (e.getKeyCode() == KeyEvent.VK_R){

                                    generateRandomOrganisms();
                                }
                                
                                if (e.getKeyCode() == KeyEvent.VK_C){

                                    generateCenterOrganisms();
                                }
                                
                                if (e.getKeyCode() == KeyEvent.VK_S){

                                    generateSidedOrganisms();
                                }
                                                                   
                            }
                    });
                    setFocusable(true);
                }
                
		private void doDrawing(Graphics g) {

                    Graphics2D g2d = (Graphics2D) g;
	       
                    for (int i = 0; i < worldWidth; i++) {
                    	 for(int j = 0; j < worldHeight; j++){
                            Organism organism = worldContainer[i][j];
                            if(organism != null){
                            	if(organism.isAlive){
                                    if(organism.species == 1){
                                        g2d.setColor(Color.green);
                                    }
                                    else if(organism.species == 0){
                                        g2d.setColor(Color.green);
                                    }
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
	       
                world.setBackground(Color.black);
	    	JFrame frame = new JFrame("Simulation");
	    	frame.add(world);
	    	frame.setSize(worldWidth,worldHeight);
	    	frame.setVisible(true);
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	

	    	generateRandomOrganisms();

	    	
	    	world.repaint();
	    	
	    	while(true){
	    		
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(runSimulation){
     
	    		updatePopulation();
	    	
	    		world.repaint();
                    }
	    	}
	    }
	    
	    
	    public static void generateRandomOrganisms(){
	    	
                Random r = new Random();
                
	    	 for (int i = 0; i < worldWidth; i++) {
	    		 for(int j = 0; j < worldHeight; j++){
	    		  
                          int species = r.nextInt() % 2;	  
	    		  int fate = r.nextInt() % 10;
		          worldContainer[i][j] = new Organism(i,j,fate,species);

	    		 }
		     }

	    }
            
            public static void generateCenterOrganisms(){
	    	
                Random r = new Random();
                
                for(int i = 0; i < worldWidth; i++){
	    		for(int j = 0; j < worldHeight; j++){
                            
                            int species = r.nextInt() % 2;
                            worldContainer[i][j] = new Organism(i,j,0,species);
                        }
                }     
                
                for(int i = 400; i < 410; i++){
	    		for(int j = 300; j < 310; j++){
                            
                            int species = r.nextInt() % 2;
                            worldContainer[i][j] = new Organism(i,j,1,species);
                        }
                }

	    }
	    
            
             public static void generateSidedOrganisms(){

                
                for(int i = 0; i < worldWidth; i++){
	    		for(int j = 0; j < worldHeight; j++){
                            
                            
                            worldContainer[i][j] = new Organism(i,j,0,0);
                        }
                }     
                
                 for (int i = 398; i < 400; i++) {
                     for (int j = 0; j < 410; j++) {
                         worldContainer[i][j] = new Organism(i,j,1,1);
                     }
                 }

	    }
             
             
	    public static void updatePopulation(){
	    	
	    	for(int i = 0; i < worldWidth; i++){
	    		for(int j = 0; j < worldHeight; j++){
	    			
		    		Organism organism = worldContainer[i][j];
		    		
		    		/*
		    		 * Any cell with fewer than two live neighbours dies, as if caused by under-population
		    		 */
		    		int organX = organism.x;
		    		int organY = organism.y;
                                int organSpecies = organism.species;
                                boolean oppositeSpecies = false;
		    		
		    		int adjacentSurvivors = 0;
		    		
		    		boolean left = isAlive(organX-1,organY);
                                
                                if(speciesAtPosition(organX-1,organY) != organSpecies){
                                    oppositeSpecies = true;
                                }
		    		if(left){
		    			adjacentSurvivors++;
		    		}
                                
                                
		    		boolean right = isAlive(organX+1,organY);
                                if(speciesAtPosition(organX+1,organY) != organSpecies){
                                    oppositeSpecies = true;
                                }
		    		if(right){
		    			adjacentSurvivors++;
		    		}
                                
                              
		    		boolean upright = isAlive(organX+1,organY+1);
                                if(speciesAtPosition(organX+1,organY+1) != organSpecies){
                                    oppositeSpecies = true;
                                }
		    		if(upright){
		    			adjacentSurvivors++;
		    		}
                                
                                
		    		boolean upleft = isAlive(organX-1,organY+1);
                                if(speciesAtPosition(organX-1,organY+1) != organSpecies){
                                    oppositeSpecies = true;
                                }
		    		if(upleft){
		    			adjacentSurvivors++;
		    		}
                                
                                
		    		boolean up = isAlive(organX,organY+1);
                                if(speciesAtPosition(organX,organY+1) != organSpecies){
                                    oppositeSpecies = true;
                                }
		    		if(up){
		    			adjacentSurvivors++;
		    		}
                                
                                
		    		boolean down = isAlive(organX,organY-1);
                                if(speciesAtPosition(organX,organY-1) != organSpecies){
                                    oppositeSpecies = true;
                                }
		    		if(down){
		    			adjacentSurvivors++;
		    		}
                                
                                
		    		boolean downright = isAlive(organX+1,organY-1);
                                if(speciesAtPosition(organX+1,organY-1) != organSpecies){
                                    oppositeSpecies = true;
                                }
		    		if(downright){
		    			adjacentSurvivors++;
		    		}
                                
                                
                                boolean downleft = isAlive(organX-1,organY-1);
                                if(speciesAtPosition(organX-1,organY-1) != organSpecies){
                                    oppositeSpecies = true;
                                }
		    		if(downleft){
		    			adjacentSurvivors++;
		    		}
		    		
                                if(oppositeSpecies){
                                    if(organism.species == 1){
                                       // organism.species = 0;
                                    }
                                    else if(organism.species == 0){
                                       // organism.species = 1;
                                    }
                                }
		    		if(isAlive(organX,organY)){

		    			if(adjacentSurvivors < 2 || adjacentSurvivors > 3){
                                            
                                            organism.die();
		    			}
		    			
		    		}
		    		else{
		    			if(adjacentSurvivors == 3){
                                            Random r = new Random();
                                            if(r.nextInt() % 1 == 0){
                                                 organism.bringToLife();
                                            }
                                            
		    			}
		    			
		    		}
	    		}
	    	}
	    	
	    }
	    
            
            public static int speciesAtPosition(int x, int y){
            
                if(x == -1 || y == -1 || x == worldWidth || y == worldHeight){
	    		return -1;
	    	}
                
                return worldContainer[x][y].species;
            }
	
	    
	    public static boolean isAlive(int x, int y){
	    	
	    	if(x == -1 || y == -1 || x == worldWidth || y == worldHeight){
	    		return false;
	    	}
	    	
	    	else{
	    		return worldContainer[x][y].isAlive;
	    	}
	    	
	    }

	   
	   
	}
	
	
	
