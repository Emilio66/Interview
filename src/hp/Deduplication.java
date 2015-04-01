package hp;

import basic_ds.BitMap;

public class Deduplication {

	/**
	 * Easy Mode
	 * @param int [] data
	 * 
	 * 使用bool数组的下标作为元素的索引，加速判断元素是否已存在 
	 * 1. 如果元素不存在就将该元素字面值对应的 数组的位置 置 1
	 * 2. 如果存在就不用管了
	 * 3. 如果越界，就再开辟一块区域
	 * ps: 为了节省空间可以使用 1bit代表元素是否存在，只是java为了安全不支持对1bit进行操作
	 */
	
	public static int[] easyDedupl(int [] data){
		int cnt = 0;
		int MIN_SIZE = data.length*2;
		int CURRENT_SIZE = MIN_SIZE;
		boolean[] container=new boolean[MIN_SIZE]; 
		boolean hasMax=false;
		
		for (int n : data) {
			if(n < CURRENT_SIZE){
				if(!container[n]){
					container[n] = true;
					cnt++;
				}
			}else{
				//mark when the maximum occurs
				if(n == Integer.MAX_VALUE){
					hasMax = true;
					continue;
				}
				//overflow, double the size
				while(n >= CURRENT_SIZE){
					if(CURRENT_SIZE < Integer.MAX_VALUE/2 )
						CURRENT_SIZE *=2;
					else 
						CURRENT_SIZE = Integer.MAX_VALUE;
				}
				boolean [] temp = new boolean[CURRENT_SIZE];
				System.arraycopy(container, 0, temp, 0, container.length);	//copy previous data
				temp[n] = true;
				cnt ++;
				container = temp;	//resign
			}
		}
		
		//return unique data
		int[] result;
		if(hasMax){
			result= new int[cnt+1];
			result[cnt] = Integer.MAX_VALUE;
		}
		else 
			result = new int[cnt];
		
		int j=0;
		for(int i=0;i<CURRENT_SIZE;i++)
			if(container[i])
				result[j++]=i;
		
		return result;
	}
	
	/**
	 * Hard Mode 
	 * Save space, but a little bit complex
	 * @param int [] data
	 * 
	 */
	public static int[] hardDedupl(int[] data){
		int SIZE = data.length;
		int CURRENT_SIZE = SIZE*2;
		BitMap.N = CURRENT_SIZE;
		
		int cnt=0;
		for(int n : data){
			if(n < CURRENT_SIZE*32){
				//if we don't count number, no need to make sure the existence of n, "Get(i)" waste 1 step
				if(!BitMap.get(n)){
					BitMap.set(n);
					cnt++;
				}
			}else{
				//overflow, double the size
				while(n >= CURRENT_SIZE*32){
					CURRENT_SIZE *=2;						
				}
				int[] temp =new int[CURRENT_SIZE];
				System.arraycopy(BitMap.MAP, 0, temp, 0, BitMap.MAP.length);
				BitMap.set(n);
				cnt++;
				BitMap.MAP = temp;
			}
		}
		
		//this is the most time-consuming part
		int result[] = new int[cnt];
		int k=0;
		for (int i = 0; i < BitMap.N; i++) {
			for (int j = 0; j < BitMap.UNIT; j++) {
				if ( ((1 << j) & BitMap.MAP[i]) >0) {
					result[k++]=i*32+j;
				}
			}
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		int [] data = {1,5,3,1,2,5,7,4,4,3,8,9,7,16,21};
		//int [] data = {1,5,3,1,2,5,7,4,4,3,Integer.MAX_VALUE};
		for(int n: data)
			System.out.print(n+" ");
		
		System.out.println("");
		
		data = easyDedupl(data);
		//data = hardDedupl(data);
		for(int n: data)
			System.out.print(n+" ");

	}

}
