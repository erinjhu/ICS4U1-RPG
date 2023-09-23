import arc.*;

public class rpgmethods{
	
	// Load world into 2D array
	
	public static String[][] loadWorld(String strFileName){
		// Variables
		int intCount;  // y-coordinate (increasing from top to bottom)
		int intCount2; // x-coordinate (increasing from left to right) 
		TextInputFile txtMap = new TextInputFile(strFileName);
		String strMap[][] = new String[20][20];
		// Load into array
		for(intCount = 0; intCount < 20; intCount++){
			for(intCount2 = 0; intCount2 < 20; intCount2++){
				strMap[intCount2][intCount] = txtMap.readLine();
			}
		}
		
		return strMap;
	}
	
	// 
	
}
