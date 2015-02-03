package basic_ds;

public class LinkList {
	private Node head;
	
	public LinkList(Node node){
		this.head=node;
	}
	
	public LinkList(){
		this.head=null;
	}
	
	//return the length of this list
	public int length(){
		int cnt=0;
		Node temp=head;
		while(temp != null){
			cnt++;
			temp=temp.getNextNode();
		}
		return cnt;
	}
	
	public void display(){
		int cnt=0;
		Node temp=head;
		while(temp != null){
			cnt++;
			System.out.println("Node "+cnt+" : "+temp.getValue());
			temp=temp.getNextNode();
		}		
	}
	
	public boolean insert(Node node,int pos){
		if(pos>length())
			return false;
		if(head==null){
			LinkList list=new LinkList(node);
		}else if(pos ==0){
			node.setNextNode(head);
			this.head =node;
		}else{
			Node prevNode=head,currNode=head.getNextNode();
			for (int i = 0; i < pos-1; i++) {
				prevNode =currNode;
				currNode =currNode.getNextNode();
			}
			prevNode.setNextNode(node);
			node.setNextNode(currNode);
		}
		return true;
	}
	
	public boolean delete(int pos){
		if(pos> (length()-1) || head ==null)
			return false;
		if(pos ==0){
			Node node=head.getNextNode();
			head=node;			
		}else{
			Node prevNode=head,currNode=head.getNextNode();
			for (int i = 0; i < pos-1; i++) {
				prevNode =currNode;
				currNode =currNode.getNextNode();
			}
			prevNode.setNextNode(currNode.getNextNode());
		}
		
		return true;
	}
	
	public static void main(String[] args) {

	}

}

class Node{
	private int value;
	private Node nextNode;
	
	public Node(int v){this.value=v;}
	public Node () {}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Node getNextNode() {
		return nextNode;
	}
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
	
}
