package alibaba.test3;

public class MaxDifference {

	/**
	 * 思路：
	 * 1. 复制该树一份
	 * 2. 利用该树构建一个最大堆，利用复制的一份建一个最小堆
	 * 3. 返回两个堆顶元素的绝对值
	 */
	public static void main(String[] args) {
		
	}
}

class Node {
	public int value;
	public Node nextNode;
	public Node(){}
	public Node(int v){
		this.value=v;
		this.nextNode=null;
	}
}