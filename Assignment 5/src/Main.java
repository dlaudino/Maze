import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

// Derek Laudino

public class Main {

	public static void main(String[] args) {
	
		Maze gettingMaze5 = new Maze(5,5, true);
		System.out.print(" Maze 5 X 5: " + "\n");
		gettingMaze5.showMaze();

		Maze gettingMaze10 = new Maze(10,15, false);
		System.out.print(" Maze 10 X 15: " + "\n");
		gettingMaze10.showMaze();

	//	 int[] intArray = new int[]{ 1,2,3,4,5,6,7,8,9,10 }; 
	//		System.out.print(intArray.length);
		
		double val = -0.7;
		int roundedVal = (int) (val + 0.5);
		System.out.println(roundedVal);

			
	}
}
