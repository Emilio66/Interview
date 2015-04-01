package basic_ds;

public class BitMap {

	public static int N=1000;						//number of data, not final, in case it's needed in other program
	public static final int UNIT=32;				//data's bits
	public static final int SHIFT=5;				// i/unit = i >> shift
	public static final int MASK=0x1F;				//unit - 1
	public static int[] MAP=new int[1+N>>SHIFT];	//container of all bits
	
	/**
	 * set ith bit 1
	 * @param i
	 */
	public static void set(int i){
		int pos=i>>SHIFT;	//equals i/UNIT
		MAP[pos] = MAP[pos] | (1 << (i & MASK)); //i/UNIT is index, i & MASK = i % UNIT, 'OR' operation set ith as 1
	}
	
	/**
	 * get ith bit, check if it is 0 
	 * @param i
	 * @return
	 */
	public static boolean get(int i){
		if( (MAP[i>>SHIFT] & (i << (i & MASK))) >0)
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
		int [] data={734,89,5,71,98,273,59,817,457,189,238,409,21,384};
		for (int i = 0; i < data.length; i++) {
			set(data[i]);
		}
		System.out.println("Is 189 in? "+get(189));
		//after setting these bits, it's a simple sorting
		for (int i = 0; i < MAP.length; i++) {
			for (int j = 0; j < UNIT; j++) {
				if ( ((1 << j) & MAP[i]) >0) {
					System.out.println((i*32+j));
				}
			}
		}
	}

}
