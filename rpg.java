// Game Name: Wood Trek
// Programmer Name: Erin Hu
// Version Number: 1

import arc.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;

public class rpg{
	
	// Main method
	
	public static void main(String[] args){
		// Variables
		Console con = new Console("Blastoid", 800, 400);
		String[][] strMap = new String[20][20];
		boolean blnAlive = true;
		char chrPress = 'o'; // initialized to random number
		char chrMenu = 'o';
		int intPage = 1;
		String strFile = "";
		Font fntJoystix = con.loadFont("joystixmonospace.ttf", 25);
		
		
		while(intPage > 0){
	
			// Menu screen
			while(intPage == 1){
				con.clear();
				con.setDrawColor(Color.BLACK);
				con.fillRect(0, 0, 960, 540);
				BufferedImage imgTitleScreen = con.loadImage("title-screen.png");
				con.drawImage(imgTitleScreen, 0, 0);
				con.repaint();
				chrMenu = con.getChar();
				if(chrMenu == 'p'){
					con.clear();
					intPage = 4;
				}else if(chrMenu == 'h'){
					con.clear();
					intPage = 3;
				}else if(chrMenu == 'q'){
					con.closeConsole();
				}
			}
			
			// Select map
			while(intPage == 4){
				con.setDrawColor(Color.BLACK);
				con.fillRect(0, 0, 960, 540);
				con.println("Select map./n (a) or (b)");
				while(chrMenu == 'p'){
					chrMenu = con.getChar();
					if(chrMenu == 'a'){
						con.clear();
						strFile = "first.csv";
						System.out.println("map chosen");
					}else if(chrMenu == 'b'){
						con.clear();
						strFile = "second.csv";
					}
				}
			
				// Load map into array
				System.out.println("map chosen");
				strMap = loadMap(strFile);
				// Go to gameplay while loop
				intPage = 2;
				System.out.println("map chosen");
			}
			
			// Gameplay
			while(intPage == 2){
				// Starting values for up display variables
				int intLife = 50;
				int intEnemies = 0;
				int intAttackDamage = 0;
				int intDefense = 0;
				int intEnemyAttackDamage = 10 - intDefense;
				int intEnemyHealth = 100;
				int intAttackItems = 0;
				int intHealingItems = 0;
				// Display map on screen
				displayMap(con, strMap);
				// Randomly draw items on the screen
				items(strMap, con);
				// Drawing hero on initial position
				BufferedImage imgHero = con.loadImage("hero.png");
				BufferedImage imgThrowBomb = con.loadImage("hero-bomb-1.png");
				BufferedImage imgThrowBomb2 = con.loadImage("hero-bomb-2.png ");
				BufferedImage imgExplosion = con.loadImage("explosion.png");
				BufferedImage imgBomb = con.loadImage("bomb.png");
				BufferedImage imgKit = con.loadImage("healing.png");
				int intY = 40;
				int intX = 180;
				con.drawImage(imgHero, intX, intY);
				con.repaint();
				// Hero is alive
				while(blnAlive == true){
					// Starting count of enemies
					intEnemies = enemy(strMap);
					// Heads up display
					con.clear();
					con.println("					HERO STATS\n");
					con.println("					Health: "+intLife);
					con.println("					Attack Damage: "+intAttackDamage);
					con.println("					Defense: "+intDefense);
					con.println("\n					ENEMY STATS\n");
					con.println("					Health: "+intEnemyHealth);
					con.println("					Attack Damage: "+intEnemyAttackDamage);
					con.println("\n					ITEMS\n");
					con.drawImage(imgBomb, 560, 320);
					con.println("						 x"+intAttackItems);
					con.drawImage(imgKit, 560, 340);
					con.println("						 x"+intHealingItems);
					
					//System.out.println("Before moving. X: "+intX+". Y: "+intY);
					// Keyboard input
					chrPress = con.getChar();
					// Change position based on keyboard input
					if(chrPress == 'w'){
						if(intY > 0){
							if((!strMap[intY/20 - 1][intX/20].equals("t"))){
							intY = intY - 20;
							displayMap(con, strMap);
							con.drawImage(imgHero, intX, intY);
							}
						}
					}else if(chrPress == 'a'){
						if(intX > 0){
							if(!strMap[intY/20][intX/20 - 1].equals("t")){
							intX = intX - 20;
							displayMap(con, strMap);
							con.drawImage(imgHero, intX, intY);
							}
						}
					}else if(chrPress == 's'){				
						if(intY < 380){
							if(!strMap[intY/20 + 1][intX/20].equals("t")){
							intY = intY + 20;
							displayMap(con, strMap);
							con.drawImage(imgHero, intX, intY);
							}
						}						
					}else if(chrPress == 'd'){
						if(intX < 380){
							if(!strMap[intY/20][intX/20 + 1].equals("t")){
							intX = intX + 20;
							displayMap(con, strMap);
							con.drawImage(imgHero, intX, intY);
							}
						}

					}else if(chrPress == ' '){
						if(intAttackItems > 0){
							if((strMap[intY/20][intX/20]).equals("e")){ 
								con.drawImage(imgThrowBomb, intX, intY);
								con.repaint();
								con.sleep(600);
								con.drawImage(imgThrowBomb2, intX, intY);
								con.repaint();
								con.sleep(600);
								con.drawImage(imgExplosion, intX, intY);
								con.repaint();
								con.sleep(600);
								con.drawImage(imgHero, intX, intY);
								con.repaint();
								intEnemyHealth = intEnemyHealth - intAttackDamage;
								intAttackItems = intAttackItems - 1;
							}
						}else{
							con.println("					Collect bombs to attack!");
							con.sleep(1000);
						}						
					}
					con.repaint();
					// Test printing      
					System.out.println("After moving. X: "+intX+". Y: "+intY);
					System.out.println(strMap[intY/20][intX/20]);
					// If statements for different types of tiles
					if((strMap[intY/20][intX/20]).equals("w")){          // Water
						System.out.println("die");
						blnAlive = false;
					}else if((strMap[intY/20][intX/20]).equals("b")){    // Building
						intLife = intLife + 10;
					}else if((strMap[intY/20][intX/20]).equals("e")){    // Enemy
						intLife = intLife - 2;
						if(intLife <= 0){
							blnAlive = false;
						}
					}else if((strMap[intY/20][intX/20]).equals("h")){    // Healing Item
						strMap[intY/20][intX/20] = "g"; // Set it to grass after hero picks it up
						intDefense = intDefense + 2;
						if((intEnemyAttackDamage - intDefense) >= 2){
							intEnemyAttackDamage = intEnemyAttackDamage - intDefense;
						}
						intHealingItems = intHealingItems + 1;
					}else if((strMap[intY/20][intX/20]).equals("a")){    // Attack Item
						strMap[intY/20][intX/20] = "g"; // Set it to grass after hero picks it up
						intAttackDamage = intAttackDamage + 3;
						intAttackItems = intAttackItems + 1;
					}
				}
				// Hero dies
				con.setDrawColor(Color.BLACK);
				con.fillRect(0, 0, 960, 540);
				con.clear();
				con.println("You died. Go back to (m)enu");
				blnAlive = true;
				while(chrMenu != 'm'){
					chrMenu = con.getChar();
					if(chrMenu == 'm'){
						intPage = 1;
					}
				}
				
			}
			
			// Help screen
			while(intPage == 3){
				con.setDrawColor(Color.BLACK);
				con.fillRect(0, 0, 960, 540);
				BufferedImage imgHelpScreen = con.loadImage("help-screen.png");
				con.drawImage(imgHelpScreen, 0, 0);
				con.repaint();
				while(chrMenu != 'm'){
					chrMenu = con.getChar();
					if(chrMenu == 'm'){
						intPage = 1;
					}
				}
			}
			
			
			
		}
			
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
		con.repaint();
	}
	
	// Method to count enemies
	
	public static int enemy(String[][] strMap){
		int intRow;
		int intColumn;
		int intEnemy = 0;
		for(intRow = 0; intRow < 20; intRow++){
			for(intColumn = 0; intColumn < 20; intColumn++){
				if(strMap[intRow][intColumn].equals("e")){
					intEnemy = intEnemy + 1;
				}
			}
		}
		return intEnemy;
	}	
	
	// Method to randomly generate items
	
	public static void items(String[][] strMap, Console con){
		int intRow;
		int intColumn;
		int intEnemy = 0;
		int intRandom;
		int intItemCount = 0;
		BufferedImage imgDefense = con.loadImage("healing.png");
		BufferedImage imgBomb = con.loadImage("bomb.png");
		for(intRow = 0; intRow < 20; intRow++){
			for(intColumn = 0; intColumn < 20; intColumn++){
				if(strMap[intRow][intColumn].equals("g")){
					intRandom = (int)(Math.random() * 20 + 1);
					if(intRandom == 2){
						con.drawImage(imgDefense, intColumn * 20, intRow * 20);
						intItemCount = intItemCount + 1;
						strMap[intRow][intColumn] = "h";  // healing item
					}if(intRandom == 11){
						con.drawImage(imgBomb, intColumn * 20, intRow * 20);
						intItemCount = intItemCount + 1;
						strMap[intRow][intColumn] = "a";  // attack item
					}
				}
			}
		}
		con.repaint();
	}
	
}
