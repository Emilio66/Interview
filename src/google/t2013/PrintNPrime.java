package google.t2013;

public class PrintNPrime {

	public static void printPrime(int N){
		int cnt=0;
		for(int i=2;i<N*N;i++){
			if(isPrime(i)){
				System.out.print(i+" ");
				cnt++;
			}
			
			if(cnt==N)//只需要前 N 个素数
				break;
		}
	}
	
	/**
	 * 判断一个数是否是素数
	 * @param num
	 * @return
	 */
	public static boolean isPrime(int num){
		if(num<2)
			return false; //最小的素数是2
		
		for(int i=2; i<=Math.sqrt(num); i=i+2){//only dividing odd numbers is okay
			if(num % i ==0)
				return false;
		}
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintNPrime.printPrime(10);
	}

}
