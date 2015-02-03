package basic_ds;

/**
 * 二路归并排序
 * 主体是将两个排好序的数组进行合并
 * 递归地进行归并，先排左边，再排右边，然后合并二者，递归终止在 元素个数为1时
 * 也可以将主体分成多路进行排序
 * @author zhaozy
 *
 */
public class MergeSort {

	/**
	 * 归并排序的关键
	 * 合并两个已经排好序的数组
	 * @param data
	 * @param start
	 * @param mid
	 * @param end
	 */
	public static void merge(int [] data,int start,int mid,int end){
		if(start < end){
			
			int[] left=new int[mid-start+1];
			int[] right=new int[end-mid];
			
			//copy array
			for (int i = 0; i < left.length; i++) {
				left[i] =data[start+i];
			}
			for (int i = 0; i < right.length; i++) {
				right[i] =data[mid+i+1];
			}
			
			//start merging
			int i=0,j=0,k=start;
			while (i<left.length && j<right.length) {
				if (left[i] <= right[j]) {
					data[k] =left[i];
					i++;
				} else {
					data[k] =right[j];
					j++;
				}
				k++;
			}
			
			//deal with the remaining data
			while (i < left.length) {
				data[k++] =left[i++];
			}
			while (j < right.length) {
				data[k++] =right[j++];
			}
		}
	}
	
	public static void mergeSort(int data[], int left, int right){
		if(left < right){
			int mid= (right - left)/2 +left;
			mergeSort(data, left, mid);		//sort the left part
			mergeSort(data, mid+1, right); 	//sort the right part
			merge(data, left, mid, right);	//merge two parts
		}		
	}
	
	public static void main(String[] args) {
		int [] data ={7,43,89,5,19,0,80,3,91,84,90,8,312,0,9,48,31,2,57,89,1,34,7,53,87,2,3,48,7};
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i]+" ");
		}
		System.out.println("");
		
		long startTime=System.currentTimeMillis();
		mergeSort(data, 0, data.length-1);
		
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i]+" ");
		}
		long endTime=System.currentTimeMillis();
		System.out.println("\nIt takes: "+(endTime-startTime) +"ms");
	}

}
