// Game Name: Wood Trek
// Programmer Name: Erin Hu
// Version Number: 1

import arc.*;
import java.awt.image.BufferedImage;


public class rpg{
	
	// Main method
	
	public static void main(String[] args){
		// Variables
		Console con = new Console();
		String[][] strMap = new String[20][20];
		boolean blnAlive = true;
		char chrPress = 'o'; // initialized to random number
		// Load map into array
		strMap = loadMap("map.csv");
		// Display map on screen
		displayMap(con, strMap);
		// Drawing hero on initial position
		BufferedImage imgHero = con.loadImage("hero.png");
		con.drawImage(imgHero, (10 * 20), (3 * 20));
		int intY = 60;
		int intX = 200;
		// Hero moves
		while(blnAlive == true){
			// Keyboard input
			System.out.println("a: "+chrPress);
			chrPress = con.getChar();
			System.out.println("b: "+chrPress);
			// Change position based on keyboard input
			if(chrPress == 'w'){
				intY = intY - 20;
				con.repaint();
				displayMap(con, strMap);
				con.drawImage(imgHero, intX, intY);
			}else if(chrPress == 'a'){
				intX = intX - 20;
				con.repaint();
				displayMap(con, strMap);
				con.drawImage(imgHero, intX, intY);
			}else if(chrPress == 's'){
				intY = intY + 20;
				con.repaint();
				displayMap(con, strMap);
				con.drawImage(imgHero, intX, intY);
			}else if(chrPress == 'd'){
				intX = intX + 20;
				con.repaint();
				displayMap(con, strMap);
				con.drawImage(imgHero, intX, intY);
			}
			System.out.println("X: "+intX+". Y: "+intY);
			// Clear
			System.out.println("c: "+chrPress);
			chrPress = 'o';
			System.out.println("d: "+chrPress);
			con.clear();
		}
		
		
		// TO DO NEXT:
			// 
		
		
		//while(blnAlive == true){
			//heroMove(con, intRow, intColumn);
		//}
	}
	
	// Method to load CSV into array
	
	public static String[][] loadMap(String strFileName){
		// Variables
		String strMap[][] = new String[20][20];
		TextInputFile mapFile = new TextInputFile(strFileName);
		String strLine;
		String[] strSplit = new String[20];
		int intRow;
		int intColumn;
		// Read file
		for(intRow = 0; intRow < 20; intRow++){
			// Read entire line
			strLine = mapFile.readLine();
			//System.out.println(strLine);
			// Split to put values not including commas into array
			strSplit = strLine.split(",");
			for(intColumn = 0; intColumn < 20; intColumn++){
				strMap[intRow][intColumn] = strSplit[intColumn];
				//System.out.print(strMap[intRow][intColumn]);
			}
			//System.out.println();
		}
		// Close file
		mapFile.close();
		// Return
		return strMap;
	}
	
	// Method to put map to screen
	
	public static void displayMap(Console con, String[][] strMap){
		// Variables
		int intRow;
		int intColumn;
		// Image variables
		BufferedImage imgGrass = con.loadImage("grass.png");
		BufferedImage imgTree = con.loadImage("tree.png");
		BufferedImage imgWater = con.loadImage("water.png");
		BufferedImage imgBuilding = con.loadImage("building.png");
		BufferedImage imgEnemy = con.loadImage("enemy.png");
		// Draw image
		for(intRow = 0; intRow < 20; intRow++){
			for(intColumn = 0; intColumn < 20; intColumn++){
				if(strMap[intRow][intColumn].equals("g")){
					con.drawImage(imgGrass, (intColumn * 20), (intRow * 20));
				}else if(strMap[intRow][intColumn].equals("t")){
					con.drawImage(imgTree, (intColumn * 20), (intRow * 20));
				}else if(strMap[intRow][intColumn].equals("w")){
					con.drawImage(imgWater, (intColumn * 20), (intRow * 20));
				}else if(strMap[intRow][intColumn].equals("b")){
					con.drawImage(imgBuilding, (intColumn * 20), (intRow * 20));
				}else if(strMap[intRow][intColumn].equals("e")){
					con.drawImage(imgEnemy, (intColumn * 20), (intRow * 20));
				}
			}
		}
	}
	
	// Method to draw hero while using w,a,s,d keys to move
	
	/*public static void heroMove(Console con, int intRow, int intColumn){
		// Variables
		BufferedImage imgHero = con.loadImage("hero.png");
		char chrPress;
		// Draw image
		con.drawImage(imgHero, (intColumn * 20), (intRow * 20));
		// Char input to move hero
		chrPress = con.getChar();
		System.out.println(chrPress);
		if(chrPress == 'w'){
			intRow = intRow - 20;
			con.drawImage(imgHero, (intColumn * 20), (intRow * 20));
		}else if(chrPress == 'a'){
			intColumn = intColumn - 20;
			con.drawImage(imgHero, (intColumn * 20), (intRow * 20));
		}else if(chrPress == 's'){
			intRow = intRow + 20;
			con.drawImage(imgHero, (intColumn * 20), (intRow * 20));
		}else if(chrPress == 'd'){
			intColumn = intColumn + 20;
			con.drawImage(imgHero, (intColumn * 20), (intRow * 20));
		}
	} */
}
