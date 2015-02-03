package basic_ds;

/**
 * 堆排序
 * 1.建堆；
 * 2.将最大的元素与最后一个元素交换，堆大小减一；
 * 3.循环2 直至 堆中元素为1
 * @author zhaozy
 *
 *
 */
public class HeapSort {

	/**
	 * 从最后一个叶子节点开始，不断调整子堆
	 * @param data
	 */
	public static void buildHeap(int [] data){
		for(int i=data.length/2-1;i>=0;i--){
			siftDown(data, i);
		}
	}
	
	public static void buildHeap2(int [] data){
		for(int i=data.length/2-1;i>=0;i--){
			siftDown2(data, i);
		}
	}
	
	/**
	 * 递归形式的向下调堆方式
	 * @param data
	 * @param pos
	 */
	public static void siftDown(int [] data, int pos){
	
		if (pos <data.length/2) {
			int left =2*pos+1;
			int right=2*pos+2;
			int parent=data[pos];
			
			if (data[left] < data[right]) {	//找更大的那个
				left++;
			}
			
			if (data[left] > parent) {	//与父节点交换				
				data[pos] =data[left];
				data[left] =parent;
				pos = left;
				
				siftDown(data, pos); //继续调整子堆
			} 
		}
	}
	
	/**
	 * 非递归式的调堆，找到当前不满足要求节点的合适位置
	 * @param data
	 * @param pos
	 */
	public static void siftDown2(int data[],int pos){
		int parent =pos;
		int child = parent *2 +1;
		int num =data[parent];
		
		while(child < data.length){
			if(child < data.length-1 && data[child] < data[child+1])
				child ++;	//找到相对较大的子节点 跟父节点比较
			if (data[child] > num) {
				data[parent] = data[child]; //覆盖掉父节点
				parent = child;
				child =child * 2 +1;
			}else {
				break;
			}
		}
		data[parent] = num;
	}
	
	/**
	 * remove the largest element form data
	 * @param data
	 */
	public static void remove(int data[]){
		//交换第0个元素和最后一个元素，然后siftDown
		int len =data.length;
		int tmp=data[0];
		data[0]=data[len-1];
		data[len-1]=tmp;
		int[] newData=new int[len-1];
		System.arraycopy(data, 0, newData, 0, len-1);
		siftDown(data, 0);
	}
	
	public static void main(String[] args) {
		int data[]={22,1,3,4,21,3,42,4,654,2,68,9,438,609,83};
		for (int i : data) {
			System.out.print(i+" ");
		}
		System.out.println("\n");
		
		long start =System.currentTimeMillis();
		buildHeap2(data);
		System.out.println(System.currentTimeMillis()-start);
		for (int i : data) {
			System.out.print(i+" ");
		}
		System.out.println("\n");
		remove(data);
		for (int i : data) {
			System.out.print(i+" ");
		}
	}

}
