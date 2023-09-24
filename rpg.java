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
		// Load map into array
		strMap = loadMap("map.csv");
		// Display map on screen
		displayMap(con, strMap);
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
		BufferedImage imgHero = con.loadImage("hero.png");
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
	
}
