import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

// Derek Laudino
// Assignment 5


public class Maze {
	private int width; // width - the width of the maze
	private int height; // height - the height of the maze
	public boolean doTheDebug;
	private Node maze[][];
	public boolean targetEnd;
	public Stack<int[]> shortest;
	public Stack<int[]> path;
	public int rowOfMaze;
	public int colOfMaze;
	public Random rand; 
	
	public Maze(int Wi, int Hei, boolean debug){ // constructor
		this.width = Wi;
		this.height = Hei;
		this.doTheDebug = debug;
		targetEnd = false;
		shortest = new Stack<int[]>();
		path = new Stack<int[]>();
		maze = new Node[(Hei*2)+1][(Wi*2)+1];
		rowOfMaze = 1;
		colOfMaze = 1;
		rand = new Random();

		buildGraph();
		buildMaze();
		solveMaze();
	}
	
	// This method builds the initial graphG(width,height).
	// The implementation details are up to you.
	private void buildGraph() { 
        int lenghtHeigh = height * 2 + 1;
        int lenghtWidth = width * 2 + 1;
        double z = 0;
        double g = 0;
        
		for(int i = 0; i < lenghtHeigh; i++) {
			for (int j = 0; j < lenghtWidth; j++) {
				z=Math.pow(-1, j) * j;
				g=Math.pow(-1, i) * i;
				
				if (j == z || i == g) { //j == z || i == g
					maze[i][j] = new Node(i, j, "X "); // X and space
				} else {
					maze[i][j] = new Node(i, j, "  "); // space, space
				}
			}
		}
		
		maze[0][1] = new Node(0, 1, "A ");// Start point
		maze[lenghtHeigh-1][lenghtWidth-2] = new Node(lenghtHeigh-1, lenghtWidth-2, "B ");//Ending point
	}
		
	
	
	
	// this method builds the maze from the graphG(width, height).
	// The choice of algorithm is up to you.	
	// My recommendation is depth-first search as it can solve the maze at the same
	//time as building it.
	// This method should have a debug setting to display each step of the maze
	//building process
	private void buildMaze() {
	    int lenghtHeigh = (height * 2) + 1;
        int lenghtWidth = (width * 2) + 1;
//		System.out.print(rowOfMaze+ "  ********  " + colOfMaze + "\n");
		
        maze[rowOfMaze][colOfMaze].stringNode = "V "; // making this node to carry V

		int [] newSpaceV =  {rowOfMaze, colOfMaze}; 
		
		if(doTheDebug) {
			showMaze();
		}
		
		if(!targetEnd) {
			shortest.push(newSpaceV);
			path.push(newSpaceV);
		}
		
		if(targetEnd) {
			path.push(newSpaceV);

		}

		ArrayList<Integer> directions = new ArrayList<Integer>();
		
		int RandomPick2 = rand.nextInt(4);// 0 , 1 , 2 , 3

		while (directions.size() < 4) {

			while (directions.contains(RandomPick2)) {				
				RandomPick2 = rand.nextInt(4);
			}
			
			directions.add(RandomPick2);
		//	System.out.print(tracker.size() + " tracker size ******" + "\n");

			
			if(rowOfMaze == lenghtHeigh-2 && colOfMaze == lenghtWidth-2) {
				targetEnd = true;
			}		
			
			if(RandomPick2 == 3 && rowOfMaze - 2 > 0 && maze[rowOfMaze - 2][colOfMaze].stringNode.equals("  ")) {
				maze[rowOfMaze-1][colOfMaze] = new Node(rowOfMaze-1, colOfMaze, "  ");
				rowOfMaze = rowOfMaze -2;
				buildMaze();
				
			}
			
			if(RandomPick2 == 2 && rowOfMaze + 2 < lenghtHeigh && maze[rowOfMaze + 2][colOfMaze].stringNode.equals("  ")) {
				maze[rowOfMaze+1][colOfMaze] = new Node(rowOfMaze+1, colOfMaze, "  ");
				rowOfMaze = rowOfMaze +2;
				buildMaze();
				
			}
			
			if(RandomPick2 == 0 && colOfMaze + 2 < lenghtWidth && maze[rowOfMaze][colOfMaze + 2].stringNode.equals("  ")){
				maze[rowOfMaze][colOfMaze+1] = new Node(rowOfMaze, colOfMaze+1, "  ");
				colOfMaze = colOfMaze +2;
				buildMaze();
				
			}
			
			if(RandomPick2 == 1 && colOfMaze - 2 > 0 && maze[rowOfMaze][colOfMaze - 2].stringNode.equals("  ")) {
				maze[rowOfMaze][colOfMaze - 1] = new Node(rowOfMaze,colOfMaze - 1, "  ");
				colOfMaze = colOfMaze -2;
				buildMaze();
			}			
			
		}
		

		//if did not find the end point   ********************************************
		while(!targetEnd){
			//		System.out.print("it is removing!" + "\n");
					shortest.pop();
					path.pop();
					directions.remove(0);
					directions.remove(0);
					directions.remove(0);
					directions.remove(0);
			
			while (directions.size()<4) {			
			
					int [] newSpaceV1 = shortest.get(shortest.size()-1);
					rowOfMaze = newSpaceV1[0];
					colOfMaze = newSpaceV1[1];
		//			System.out.print(rowOfMaze+ " " + colOfMaze + "\n");
		
		
					int RandomPick1 = rand.nextInt(4); // can not be 0
		
					while (directions.contains(RandomPick1)) {
						RandomPick1 = rand.nextInt(4);
					}
					directions.add(RandomPick1);
		
					
					if(rowOfMaze == lenghtHeigh-2 && colOfMaze == lenghtWidth-2) {
						targetEnd = true;
					}		
					
					if(RandomPick1 == 3 && rowOfMaze - 2 > 0 && maze[rowOfMaze - 2][colOfMaze].stringNode.equals("  ")) {
						maze[rowOfMaze-1][colOfMaze] = new Node(rowOfMaze-1, colOfMaze, "  ");
						rowOfMaze = rowOfMaze -2;
						buildMaze();
						
					}
					
					if(RandomPick1 == 2 && rowOfMaze + 2 < lenghtHeigh && maze[rowOfMaze + 2][colOfMaze].stringNode.equals("  ")) {
						maze[rowOfMaze+1][colOfMaze] = new Node(rowOfMaze+1, colOfMaze, "  ");
						rowOfMaze = rowOfMaze +2;
						buildMaze();
						
					}
					
					if(RandomPick1 == 0 && colOfMaze + 2 < lenghtWidth && maze[rowOfMaze][colOfMaze + 2].stringNode.equals("  ")){
						maze[rowOfMaze][colOfMaze+1] = new Node(rowOfMaze, colOfMaze+1, "  ");
						colOfMaze = colOfMaze +2;
						buildMaze();
						
					}
					
					if(RandomPick1 == 1 && colOfMaze - 2 > 0 && maze[rowOfMaze][colOfMaze - 2].stringNode.equals("  ")) {
						maze[rowOfMaze][colOfMaze - 1] = new Node(rowOfMaze,colOfMaze - 1, "  ");
						colOfMaze = colOfMaze -2;
						buildMaze();
					}
		      }
		}
		
		
		// if found the end point!    ********************************************
		while (targetEnd) {
			//			System.out.print("it is removing!" + "\n");
						//shortest.pop();
						if(path.size() !=1) {
						path.pop();
						}else {
			//				System.out.print(path.size() +" Path Size!!!!" + "\n");

							break;
						}
						directions.remove(0);
						directions.remove(0);
						directions.remove(0);
						directions.remove(0);
						
						int RandomPick = rand.nextInt(4); // can not be 0

				
				while (directions.size()<4) {
						int [] newSpaceV2 = path.get(path.size()-1);
						rowOfMaze = newSpaceV2[0];
						colOfMaze = newSpaceV2[1];
			//			System.out.print(rowOfMaze+ " " + colOfMaze + "\n");
						
						while (directions.contains(RandomPick)) {
							RandomPick = rand.nextInt(4);
						}
						directions.add(RandomPick);

						
						if(RandomPick == 3 && rowOfMaze - 2 > 0 && maze[rowOfMaze - 2][colOfMaze].stringNode.equals("  ")) {
							maze[rowOfMaze-1][colOfMaze] = new Node(rowOfMaze-1, colOfMaze, "  ");
							rowOfMaze = rowOfMaze -2;
							buildMaze();
							
						}
						
						if(RandomPick == 2 && rowOfMaze + 2 < lenghtHeigh && maze[rowOfMaze + 2][colOfMaze].stringNode.equals("  ")) {
							maze[rowOfMaze+1][colOfMaze] = new Node(rowOfMaze+1, colOfMaze, "  ");
							rowOfMaze = rowOfMaze +2;
							buildMaze();
							
						}
						
						if(RandomPick == 0 && colOfMaze + 2 < lenghtWidth && maze[rowOfMaze][colOfMaze + 2].stringNode.equals("  ")){
							maze[rowOfMaze][colOfMaze+1] = new Node(rowOfMaze, colOfMaze+1, "  ");
							colOfMaze = colOfMaze +2;
							buildMaze();
							
						}
						
						if(RandomPick == 1 && colOfMaze - 2 > 0 && maze[rowOfMaze][colOfMaze - 2].stringNode.equals("  ")) {
							maze[rowOfMaze][colOfMaze - 1] = new Node(rowOfMaze,colOfMaze - 1, "  ");
							colOfMaze = colOfMaze -2;
							buildMaze();
						}
			      }
		}
	
	}
	
	
	// this method solves the maze once built by finding the single path from the
	//entrance to the exit
	// Depth-first searching for the exit from the entrance is the best technique to solve
	//the maze.
	// If you solved the maze while you built the maze you can make this method empty
	//(we call this a stub usually) or trivial (make the solution appear).
	// I use Xs to represent walls and +s to represent the solution path.
	private void solveMaze() {		
		
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if(maze[i][j].stringNode.equals("V ")) { // for every V in the Map, put a space!
					maze[i][j].stringNode = "  ";
				}
			}
		}
	
	while(shortest.isEmpty()==false){	 // when it is not empty go inside 	
			int[] fast = shortest.pop();
			int index1 = fast[0]; // this is the row
			int index2 = fast[1]; // this is the col
			maze[index1][index2].stringNode = "* ";
		}
	
	}
	
	
	public void showMaze() { // use for the debug ! I prints the whole maze
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				System.out.print(maze[i][j].stringNode);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	
	public String toString() {
		return null;
		
	}
	
	
	class Node {	
		String stringNode;	
		int Row;
		int Col;
		public Node(int row, int col, String leChar) {
			Row = row;
			Col = col;
			stringNode = leChar;
		}
		
	}
	
	
}
