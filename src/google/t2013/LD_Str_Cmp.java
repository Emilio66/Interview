package google.t2013;

import java.util.Scanner;

public class LD_Str_Cmp {

	/**
	 * @see 此类用来比较两个字符串的匹配程度，使用矩阵的形式呈现，寻找最少的操作使得两个字符串能够一致
	 * LD 算法的递推公式
	 * 1. 若 ai  = bj, LD(i,j) = LD(i-1,j-1); 若相等，则等于左上角的值，其实就是此次比较不用增加操作数
	 * 2. 若 ai != bj, LD(i,j) = MIN{LD(i-1,j-1), LD(i-1,j), LD(i,j-1)} + 1
	 *    这条说明当不匹配时，寻找最少的操作数使得俩串局部匹配 （左上角，上边，左边）+ 1， 
	 *    不匹配时，添加的操作左上角是替换，上边是删除，左边是插入，这在最后回溯的时候很有用
	 */
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Source String : ");
		String A,B;
		A=scanner.next();
		System.out.println("Target String : ");
		B=scanner.next();
		int row=A.length()+1;
		int column=B.length()+1;
		
		int[][] matrix=new int[row][column];
		//初始化下标
		for(int i=0; i<row; i++){
			matrix[i][0]=i;
		}
		for (int j = 1; j < column; j++) {
			matrix[0][j]=j;
		}		
		
		//逐个比较字符，填充矩阵
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < column; j++) {
				if(A.charAt(i-1)==B.charAt(j-1)){//字符跟矩阵的下标差一个 ，前面有编号占一位
					matrix[i][j]=matrix[i-1][j-1]; //ai = bi
				}else{
					matrix[i][j]=min(matrix[i-1][j-1], matrix[i-1][j], matrix[i][j-1])+1; //ai != bi
				}
			}			
		}
		
		//打印一下
		printMatrix(matrix, row, column);
		int steps=matrix[row-1][column-1];
		System.out.println("\n\n Minimal steps to consist with original string: "+steps);
		
		//回溯操作路径
		traceBackMatrix(matrix, row, column, A, B);
		printMatrix(matrix, row, column);
		
		//根据路径写出修改方案
		modification(matrix, row, column, A, B);
	}

	/**
	 * 回溯矩阵
	 * 从最后一个元素开始回溯，最后一个元素就是最小操作数
	 * 1. 若 ai = bi 往左上角前进一步
	 * 2. 若 ai != bi 选取左上角，上边，左边最小的数作为回溯的下一步，如果相等，按照前面的顺序取
	 * 3. 当回溯到 matrix[0][0] 时截止
	 * @param matrix
	 * @param row
	 * @param column
	 */
	public static void traceBackMatrix(int[][]matrix,int row,int column,String A,String B){
		int i = row-1; 
		int j = column-1;
		while(i != 0 || j != 0){
			matrix[i][j] = -1; //标记这个位置，便于将来回溯路径的确定
			if(i==0){ //当触到边界时，只能在一个方向移动
				j--;
			}else if(j==0){
				i--;
			}else if(A.charAt(i-1)==B.charAt(j-1)){			
				i--;
				j--;
			}else{				//不相等，取左上角，上边，左边最小值
				if(matrix[i-1][j-1] <= matrix[i-1][j] && matrix[i-1][j-1] <= matrix[i][j-1]){
					i--;  //向左上角移动
					j--;
				}
				else if(matrix[i-1][j] <= matrix[i][j-1]){
					i--; // 向上边移动
				}	
				else{					
					j--; // 向左边移动
				}
			}			
		}
	}
	
	/**
	 * 根据回溯路径，给出最少的修改方案
	 * 若 ai = bi ， 俩串都写上这个字母
	 * 若 ai != bi, 位于左上角，俩串都写上对应的字母，即用目标串的字母替换源串的字母
	 * 				位于左边，源串写下划线 _，目标串照常写，即给源串插入相应的字母
	 * 				位于上边，目标串写下划线 _,源串照常写，即将源串的该位置字母删除
	 * @param matrix
	 * @param row
	 * @param column
	 */
	public static void modification(int [][] matrix,int row, int column,String A,String B){
		int i=row-1,j=column-1;
		StringBuffer strA = new StringBuffer(),strB = new StringBuffer();
		//从最后一个位置开始回溯，到 [0][0] 截止
		while(i != 0 || j != 0){
			if(i==0){//靠边只能一个方向移动
				strA.append('_');
				strB.append(B.charAt(j-1));
				j--;
			}else if(j==0){
				strB.append('_');
				strA.append(A.charAt(i-1));
				i--;
			}else if(matrix[i-1][j-1] < 0 ){//左上角
				strA.append(A.charAt(i-1));
				strB.append(B.charAt(j-1));
				i--;
				j--;
			}else if(matrix[i][j-1] < 0){//左边				
				strA.append('_');
				strB.append(B.charAt(j-1));
				j--;			
			}else{				//上边			
				strA.append(A.charAt(i-1));
				strB.append('_');
				i--;
			}
		}
		
		System.out.println("\nTarget string: "+strB.reverse());//将原来的逆序字符串翻转一下
		System.out.println("Source string: "+strA.reverse());
	}
	/**
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return Minimum of a,b and c
	 */
	public static int min(int a,int b,int c) {
		if(a<b){
			if(a<c)
				return a;
			else 
				return c;		
		}else{
			if(b<c)
				return b;
			else 
				return c;
		}
	}
	
	/**
	 * 打印矩阵
	 * @param matrix
	 * @param row
	 * @param column
	 */
	public static void printMatrix(int[][] matrix,int row,int column){
		System.out.println("\n");
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println("");
		}
	}
}
