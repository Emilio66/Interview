package google.t2008;

import java.util.Arrays;
import java.util.Scanner;

public class MinScalar {

	/**
	 * Given two vector, adjust their coordinate to get the minimal product 
	 */
	public static void main(String[] args) {
		Scanner in =new Scanner(MinScalar.class.getResourceAsStream("/google/t2008/A-small-practice.in"));
		in.useDelimiter("\\s");
		
		int caseNum = in.nextInt();
		for (int i = 1; i <= caseNum; i++) {
			int dim= in.nextInt();
			int [] vectorA=new int[dim];
			int [] vectorB=new int[dim];
			//construct structure to contain data
			for (int j = 0; j < dim; j++) {
				vectorA[j] =in.nextInt();
			}
			for (int j = 0; j < dim; j++) {
				vectorB[j] =in.nextInt();
			}
			Arrays.sort(vectorA);//sort ascending
			Arrays.sort(vectorB);
			
			//calculate minimal product
			int product=0;
			for (int j = 0; j < dim; j++) {
				product+= vectorA[j] * vectorB[dim-j-1];
			}
			System.out.println("Case #"+i+": "+product);
		}
		
	}

}
