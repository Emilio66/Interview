package google.codejam.africa2010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StoreCredit2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int caseNum;
		Scanner in=new Scanner(StoreCredit2.class.getResourceAsStream("/google/codejam/africa2010/A-large-practice.in"));
		in=in.useDelimiter("\\s");
		caseNum=in.nextInt();
		
		//n case
		for (int i = 1; i < caseNum+1; i++) {
			int amount = in.nextInt();
			int number = in.nextInt();
			
			//small data set
			if(number < 50){
				int [] array =new int[number];
				for(int k=0;k < number; k++){
					array[k] = in.nextInt();
				}
				
				for (int j = 0; j < array.length -1; j++) {
					for (int k = j+1; k < array.length; k++) {
						if(array[j]< amount && array[k]< amount && (array[k] + array[j]) == amount ){
							System.out.println("Case #"+i+": "+(j+1)+" "+(k+1));
							break;
						}
					}
				}
			}else {//large data set
				HashMap<Integer, List<Integer>> priceMap=new HashMap<Integer, List<Integer>>();
				//parse data and save them
				for (int j = 0; j < number; j++) {
					int price = in.nextInt();
					if(price >= amount)
						continue;
					List<Integer> indexList;
					if(priceMap.containsKey(price)){
						indexList=priceMap.get(price);
						indexList.add(j); //add this index
					}else{
						indexList=new ArrayList<Integer>();
						indexList.add(j);
					}
					
					priceMap.put(price, indexList); //bind price with indices list
				}
				
				//find the only one solution
				for (Map.Entry<Integer, List<Integer>> entry : priceMap.entrySet()) {
					int price1 = entry.getKey();
					int price2 = amount - price1;
					
					List<Integer> list1= entry.getValue();
					List <Integer> list2;
					
					if(list1.size()>2) //cause each case has exactly one solution 
						continue;
					
					if(price1 == price2){ 
						///System.out.println("=== "+list1);
						int index1=list1.get(0);
						if(list1.size() <2 )	// may not exist two items with half credits
							continue;
						int index2=list1.get(1); //only two items definitely 
						if(index1<index2)
							System.out.println("Case #"+i+": "+(index1+1)+" "+(index2+1));
						else {
							System.out.println("Case #"+i+": "+(index2+1)+" "+(index1+1));
						}
						break;
						
					}else if((list2=priceMap.get(price2))!=null){
						int index1=list1.get(0);
						int index2=list2.get(0);
						if(index1<index2)
							System.out.println("Case #"+i+": "+(index1+1)+" "+(index2+1));
						else {
							System.out.println("Case #"+i+": "+(index2+1)+" "+(index1+1));
						}
						break;
						
					}
				}
			}
		}
	}

}
