package basic_ds;
/**
 * 基数排序
 * LSDF (low significant digit first) 低位优先
 * 按照关键的位数，从低到高分别进行 distribute 分配, collect 收集操作， 当所有关键字都扫描完毕，排序也完成
 * @author zhaozy
 */
public class RadixSort {
	private myLinkList[] Lists; //用于存放分布好的数列
	private int [] source; //数据源
	private int radix; //基数
	private int keyNum; //关键码个数
	
	public RadixSort(int [] data,int r,int k){
		source=data;
		keyNum=k;
		radix=r;
	}
	
	//分发  以个位、十位、百位从低到高进行三次分布与收集
	public void distribute(int index){
		Lists=new myLinkList[radix]; //每次分布之前先清空一下里面的数据
		int pos=0;
		for(int i=0;i<source.length;i++){
			pos=index;
			int num=source[i];
			while(pos>0){
				num=num/radix;
				pos--;
			}
			num=num%radix; //获得该数字的组号
			myNode myNode=new myNode(source[i]);
			if(Lists[num]!=null){
				Lists[num].addmyNode(myNode);
			}else{
				Lists[num]=new myLinkList(myNode);
			}
		}
	}
	//收集
	public void collect(){
		int j=0;
		for(int i=0;i<radix;i++){
			if(Lists[i]!=null){
				myNode node=Lists[i].getHeadmyNode();
				while(node!=null){
					source[j]=node.getData();
					j++;
					node=node.getNextmyNode(); //指针向后挪动
				}
			}			
		}	
	}
	
	//基数排序,按照低位优先(LSDF)的方法来分散和收集
	public void radixSort(){
		for(int i=0;i<keyNum;i++){
			distribute(i);
			collect();
		}
	}
	
	//主函数
	public static void main (String[]args){
		int data[]={7,43,89,5,19,0,80,3,91,84,90,8,312,0,9,48,31,2,57,89,1,34,7,53,87,2,3,48,7};
		for (int i : data) {
			System.out.print(i+" ");
		}
		System.out.println("");
		
		long startTime=System.currentTimeMillis();
		new RadixSort(data, 10, 3).radixSort();
		for (int i : data) {
			System.out.print(i+" ");
		}
		
		long endTime=System.currentTimeMillis();
		System.out.println("\nIt takes: "+(endTime-startTime) +"ms");
	}
}

 class myNode{
	private int data;
	private myNode nextmyNode;
	
	public myNode(int num){
		data=num;
		nextmyNode=null;
	}
	public myNode(int data, myNode nextNode) {
		super();
		this.data = data;
		this.nextmyNode = nextNode;
	}
	public myNode(){}
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public myNode getNextmyNode() {
		return nextmyNode;
	}
	public void setNextmyNode(myNode nextmyNode) {
		this.nextmyNode = nextmyNode;
	}
}

 class myLinkList{
	private myNode headmyNode;
	private myNode rearmyNode;
	
	public void addmyNode(myNode myNode){
		if(headmyNode==null){
			headmyNode=myNode;
			rearmyNode=myNode;
		}else{
			rearmyNode.setNextmyNode(myNode);
			rearmyNode=myNode;
		}		
	}
	
	public myLinkList(){}
	public myLinkList(myNode myNode){
		headmyNode=myNode;
		rearmyNode=myNode;
	}
	
	public myNode getHeadmyNode() {
		return headmyNode;
	}

	public void setHeadmyNode(myNode headmyNode) {
		this.headmyNode = headmyNode;
	}

	public myNode getRearmyNode() {
		return rearmyNode;
	}

	public void setRearmyNode(myNode rearmyNode) {
		this.rearmyNode = rearmyNode;
	}
	
}
