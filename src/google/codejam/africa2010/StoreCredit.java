package google.codejam.africa2010;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * we have C credits to buy 2 items in the goods list L, add up the price of the two items equals the amount of credits
 * The input file provides: 1th line is the amount of credits, 2th line is the number of items, 3th line is a space 
 * separated string, one index, the other price
 * 
 * @ @ I got the wrong meaning of this problem,  I'm so stupid. No wonder I ever doubt that the input file is incomplete
 * After reading again, I realize that the first line is the number of case, second is amount of credits, third is number
 * of items, cause there is no index of item, the position of item is their indices.
 * @author zhaozy
 */
public class StoreCredit {

	/**
	 * 1. input file (use regular-expression to split string)
	 * 2. first line is the amount of credits
	 * 3. second line is the number of commodities
	 * 4. subsequent is data: <index, price>
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args)  {
		int amount,number;		
		
		try {
			//Note: user.dir (project name) is the root directory of relative path by default
			Scanner scanner=new Scanner(StoreCredit.class.getResourceAsStream("/google/codejam/A-small-practice.in"))
								.useDelimiter("\\s");		//this regular-expression stands for [\t\n\x0B\f\r]
			
			amount=scanner.nextInt();
			number=scanner.nextInt();
			
			HashMap<Integer, List<Integer>> items =new HashMap<Integer,List<Integer>>();
							
			//parse input and build data structure to hold all datum			
			while(scanner.hasNext()){
				
				int index=scanner.nextInt();//automatically omit space
				int price=-1;
				
				if(scanner.hasNextInt()){
					price=scanner.nextInt();
					if(price >= amount) //delete unsatisfied price in advance
						continue;
				}else{
					break; 				//no price associated with this index
				}
				System.out.println(index+" : "+price);
				
				//price is key, index list is value 
				List<Integer> list;
				if(items.containsKey(price)){
					list=items.get(price);	//get a list of price
					list.add(index); 		//append an index with the same price
				}else{
					list=new ArrayList<Integer>();
					list.add(index);
				}
				//put back
				items.put(price, list);
			}

			//traverse the hash map to find appropriate pairs
			int count=1;
			for (Map.Entry<Integer, List<Integer>> entry : items.entrySet()) {
				
				int price1=entry.getKey();				
				int price2=amount-price1;	//find target
				List<Integer> list1=items.get(price1);
				List<Integer> list2=items.get(price2);
				
				//display result, avoid output repeatedly
				if(price1 > price2)
					break;				
				else if(price1 == price2){
					for (int i = 0; i < list1.size(); i++) {
						for (int j = i+1; j < list1.size(); j++) {
							int index1=list1.get(i);
							int index2=list1.get(j);
							if(index1>index2){
								System.out.println("Case #"+count+": "+index2+" "+index1);
							}else{
								System.out.println("Case #"+count+": "+index1+" "+index2);
							}
							count++;
						}
					}					
				}else{
					if(list2 != null)
						for (Integer index1 : list1) { //minor first
							for (Integer index2 : list2) {
								if(index1>index2){
									System.out.println("Case #"+count+": "+index2+" "+index1);
								}else{
									System.out.println("Case #"+count+": "+index1+" "+index2);
								}
								count++;
							}						
						}
				}
			}			
			
		}catch (NumberFormatException e) {
			System.out.println("cannot be converted to integer");
			e.printStackTrace();
		}
	}
}
