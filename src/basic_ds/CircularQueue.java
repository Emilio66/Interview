package basic_ds;


/**
 * 循环队列
 * 防止假溢出，使得入队和出队时间复杂度都是 O (1)
 * @author ZhaoZy
 */
public class CircularQueue {

	private int SIZE = 100;
	private int front = 0;
	private int rear = 0;
	//private Object[] container;
	private int[] container;
	public CircularQueue(int size){
		this.SIZE =size;
		//container = new Object[size];
		container = new int[size];
	}
	
	public CircularQueue(){
		container =new int[100];
	}
	
	/**
	 * 只要对列出不空，就往外面取
	 * @return 队首元素
	 */
	public int poll(){
		int head = -2147483648;	//minimal int 
		
		if((front) % SIZE != rear){
			head = container[front] ;
			front = (front+1) % SIZE ;			
		}else{
			System.out.println("! Empty Queue !!");
		}
		return head;
	}
	
	/**
	 * 添加元素
	 * 只要队列不满，就往里面加
	 * @param one
	 * @return
	 */
	public boolean add(int one){
		if((rear + 1)% SIZE != front){
			container[rear++] = one;
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		CircularQueue q =new CircularQueue(4);
		System.out.println(q.add(10));
		System.out.println(q.add(3));
		System.out.println(q.add(101));
		System.out.println(q.add(233));
		
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
	}

}
