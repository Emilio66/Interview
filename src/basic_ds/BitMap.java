package basic_ds;

public class BitMap {

	public static int N=128;						//number of data, not final, in case it's needed in other program
	public static final int UNIT=32;				//data's bits
	public static final int SHIFT=5;				// i/unit = i >> shift
	public static final int MASK=0x1F;				//unit - 1
	public static int[] MAP=new int[1+N>>SHIFT];	//container of all POSITIVE numbers
	public static int[] MAP2=new int[1+N>>SHIFT];	//container of NEGATIVE numbers
	/**
	 * set ith bit 1
	 * @param i
	 */
	public static void set(int i){
		if( i>=0 ){
			int pos = i>>SHIFT;	//equals i/UNIT
			MAP[pos] = MAP[pos] | (1 << (i & MASK)); //i/UNIT is index, i & MASK = i % UNIT, 'OR' operation set ith as 1
		}else{
			i = -i;
			int pos = i>>SHIFT;
			MAP2[pos] = MAP2[pos] | (1 << (i & MASK));
		}
	}
	
	/**
	 * get ith bit, check if it is 0 
	 * @param i
	 * @return
	 */
	public static boolean get(int i){
		if(i<0){
			i = -i;
			if( (MAP2[i>>SHIFT] & (1 << (i & MASK))) != 0)
				return true;
			else
				return false;
		}
		if( (MAP[i>>SHIFT] & (1 << (i & MASK))) != 0)
			return true;
		return false;
	}
	
	/**
	 * set ith bit 0
	 * @param i
	 */
	public static void clear(int i){
		int pos=i>>SHIFT;
		MAP[pos] = MAP[pos] & ~(i << (i & MASK));
	}
	
	public static void main(String[] args) {
		int [] data={-21,-4,31,-32,63,8,-10,127,-9};
		for (int i = 0; i < data.length; i++) {
			set(data[i]);
		}
		System.out.println("Is -32 in? "+get(-32));
		System.out.println("Is 31 in? "+get(-32));
		//after setting these bits, it's a simple sorting
		for (int i = 0; i < MAP2.length; i++) {
			for (int j = 0; j < UNIT; j++) {
				if ( ((1 << j) & MAP2[i]) >0) {
					System.out.println(-(i*32+j));
				}
			}
		}
		for (int i = 0; i < MAP.length; i++) {
			for (int j = 0; j < UNIT; j++) {
				if ( ((1 << j) & MAP[i]) != 0) {
					System.out.println((i*32+j));
				}
			}
		}
	}

}
