package basic_ds;

/**
 * 快速排序
 * 1.选取轴元素
 * 2.将比轴元素大的扔到右边，比它小的扔到左边，这叫partition
 * 3.这样轴元素放到了合适的位置，再分别将轴元素左边和右边的部分进行快排
 * 4.直到只剩一个元素，就完成了快排
 * @author Administrator
 *
 */
public class QuickSort {

	public static int partition(int data[], int left, int right){
		if(left < right){
			int pivot = data[left]; //取左边第一个元素为轴元素
			
			while(left < right){
				//找到右边第一个比轴元素小的，放到左边空闲的位置
				while (left < right && data[right] >= pivot)
					right--;
				data[left] = data[right];
				//找到左边第一个比轴元素大的，放到右边空闲的位置
				while(left < right && data[left] <= pivot)
					left++;
				data[right] = data[left];
			}
			
			data[left]=pivot;	//将轴元素放到合适的位置
		}
		return left;	//返回轴元素的位置
	}
	
	public static void quickSort(int data[],int left, int right){
		if(left < right){
			int mid = partition(data, left, right);
			quickSort(data, left, mid-1);
			quickSort(data, mid+1, right);
		}
	}
	
	public static void main(String[] args) {
		int data[]={7,43,89,5,19,0,80,3,91,84,90,8,312,0,9,48,31,2,57,89,1,34,7,53,87,2,3,48,7};
		for (int i : data) {
			System.out.print(i+" ");
		}
		System.out.println("");
		
		long startTime=System.currentTimeMillis();
		
		quickSort(data, 0, data.length-1);
		//BubbleSort.bubbleSort(data);
		for (int i : data) {
			System.out.print(i+" ");
		}
		
		long endTime=System.currentTimeMillis();
		System.out.println("\nIt takes: "+(endTime-startTime) +"ms");
	}
}

class BubbleSort{
	public static void bubbleSort(int data[]){
		for (int i = 0; i < data.length; i++) {
			boolean isChange=false;
			for (int j = i+1; j < data.length; j++) {
				if (data[j] < data[i]) {
					int tmp = data[i];
					data[i] = data[j];
					data[j] = tmp;
					isChange = true;
				}
			}
			
			if(!isChange)
				break;
		}		
	}
}
