package google.codejam.africa2010;

import java.util.Scanner;

public class ReverseWords {

	/**
	 * Given a string separated by space, reverse these words
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in =new Scanner(ReverseWords.class.getResourceAsStream("/google/codejam/africa2010/B-large-practice.in"));
		int caseNum= Integer.parseInt(in.nextLine());
		
		for (int i = 1; i < caseNum+1; i++) {
			String [] strArray= in.nextLine().split(" ");
			System.out.print("Case #"+i+":");
			for (int j = strArray.length-1; j >= 0; j--) {
				System.out.print(" "+strArray[j]);
			}
			System.out.println("");
		}
	}

}
