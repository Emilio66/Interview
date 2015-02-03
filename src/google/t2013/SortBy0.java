package google.t2013;

import java.util.ArrayList;
import java.util.Collections;

public class SortBy0 {

	public static void main(String[] args) {
		ArrayList<Integer> a=new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			a.add(i);
		}
		Collections.shuffle(a);
		System.out.println(a);
		
		//先找到0的位置
		//跟A[0]对应的下标的数进行排序，直到下标为0
		Object[] A= a.toArray();
		int pos=0;
		for (int i = 0; i < A.length; i++) {
			if((Integer)(A[i])==0){
				pos=i;
				System.out.println("Position of 0: "+pos);
				break;
			}
		}
		
		//排序，只能通过与 0交换
		while(true){
			int tmp=pos;
			//查找它对应的数字的位置
			int cnt=0;
			for (int i=0; i < A.length; i++) {
				if((Integer)(A[i])==i){
					cnt++;
					continue;//最终的结果就是每个A[i]==i
				}
				if((Integer)(A[i])==tmp){
					pos=i;
					System.out.println("Position of "+tmp+" : "+pos);
					break;
				}
			}
			
			if(cnt==A.length)//没有任何交换,跳出循环
				break;
			//找到数字位置，与0交换位置
			A[tmp]=tmp;
			A[pos]=0;
			
			//pos=0,却未跳出，造成死循环，因此要移动一下
			if(pos ==0 && cnt< A.length-2){
				for (int i = 1; i < A.length; i++) {
					if((Integer)(A[i]) !=i){
						pos=i;
						A[0]=A[i];//暂时交换
						A[i]=0;
						break;
					}
				}
			}
		}
		
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i]+" ");
		}
	}
}
