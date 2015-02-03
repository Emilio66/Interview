package google.codejam.africa2010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class T9Spelling {

	/**
	 * output the number sequence that stands for the string in T9 spelling style phone keyboard 
	 * @param args
	 */
	public static void main(String[] args) {
		InputStreamReader isReader=new InputStreamReader(
								 T9Spelling.class.getResourceAsStream("/google/codejam/africa2010/C-large-practice.in"));
		BufferedReader bufferedReader= new BufferedReader(isReader);
		// construct a hash map to contain irregular data
		HashMap<Character, Integer> map=new HashMap<>();
		map.put('s', 74); //the decade digit represents Key Number, while the units' digit stands for Press Times
		map.put('t', 81);
		map.put('u', 82);
		map.put('v', 83);
		map.put('w', 91);
		map.put('x', 92);
		map.put('y', 93);
		map.put('z', 94);
		
		try {
			int caseNum = Integer.parseInt(bufferedReader.readLine());
			for (int i = 1; i <= caseNum; i++) {
				String string = bufferedReader.readLine();
				char curr;
				int prevNum = -1, num=0;
				System.out.print("Case #"+i+": ");
				for (int j = 0; j < string.length(); j++) {
					curr = string.charAt(j);
			
					if(curr == ' '){	//process space specially
						if(prevNum == 0)
							System.out.print(" 0");
						else{
							System.out.print("0"); 
							prevNum = num=0;
						}
					}
					else{
						int time=0;
						if(curr < 's'){					
							num		=	(curr - 97)/3 +2;  //number in keypad
							time=	(curr - 97)%3 +1;
						}else{
							num=map.get(curr);	// search the hash map
							time=	num%10;
							num =	num/10;
						}	
						
						if(j>0 && num == prevNum)
							System.out.print(" "); //space separate the repeated number
							
						for (int k = 0; k < time; k++) { //output number corresponding times
							System.out.print(""+num);
						}
						
						prevNum = num;	//save last pressed number for later use
					}
				}
				System.out.println("");
			}			
		} catch (NumberFormatException | IOException e) {
			System.out.println("can not find file");
			e.printStackTrace();
		}
	}
}
