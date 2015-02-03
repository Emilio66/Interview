package kmp;

import java.util.Scanner;

public class StringMatcher {

	/**
	 * 蛮力搜索法
	 * 每当匹配失败，从头再来
	 * @param target
	 * @param pattern
	 */
	public static void BruteForce(String target,String pattern){
		int tLen=target.length();
		int pLen=pattern.length();
		int i=0,j=0,count=0;
		
		while(i<tLen && j<pLen){
			count++;
			if(target.charAt(i) == pattern.charAt(j)){
				i++;
				j++;
			}else{
				i=i-j+1;
				j=0;
			}
		}
		
		System.out.println("总共比较："+count+" 次");
		if(j==pLen){
			System.out.println("匹配成功！\n 从目标串的第 "+(i-pLen)+"位开始");
		}else{
			System.out.println("匹配失败！");
		}
		
	}
	
	/**
	 * KMP算法，避免回溯，当匹配不成功时，根据模式串的特点向前移动到相应的位置
	 * 这个位置叫做特征数，是该位置的最长前缀串的长度，其实就是匹配不成功时下一个匹配的位置
	 * 特征数的求法：
	 * Next[] 数组保存着此位置的下一个匹配不成功时(即此为前半部分匹配成功的最后一个位置)，下一次比较应该开始的位置
	 * 名曰该位置最长前缀串的长度，因为前缀开始位置是0，因此这正好是下一个应该比较的位置
	 * 1. Next[0]=0
	 * 2. k=Next[j-1], if(p[j]==p[k]) Next[j]=Next[j-1] else if(k>0) 接着往前判断 else Next[j]=0
	 * @param target
	 * @param pattern
	 */ 
	public static void KMP(String target,String pattern){
		int tLen=target.length();
		int pLen=pattern.length();
		//计算Next数组
		int Next[]=new int[pLen];
		Next[0]=0;
		for(int j=1;j<pLen;j++){
			int k=Next[j-1];
			while(true){
				if(pattern.charAt(j)==pattern.charAt(k)){
					Next[j]=Next[j-1]+1;
					break;
				}
				else if(k>0){
					k=Next[k-1]; //往前推一位
				}else {
					Next[j]=0;
					break;
				}
			}
		}
		
		//开始字符串的比较
		int i=0,j=0,count=0;
		while(i<tLen && j<pLen){
			count++; //record the times of comparison
			if(target.charAt(i) == pattern.charAt(j)){
				i++;
				j++;//继续往后比较
			}else if(j==0){
				i++;				
			}else 
				j=Next[j-1]; //匹配不成功，找到滑动的正确位置
		}
		
		System.out.println("总共比较："+count+" 次");
		if(j==pLen){
			System.out.println("匹配成功！\n 从目标串的第 "+(i-pLen)+"位开始");
		}else{
			System.out.println("匹配失败！");
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String target,pattern;
		System.out.println("Target: ");
		target=scanner.next();
		System.out.println("Pattern: ");
		pattern=scanner.next();
		
		BruteForce(target, pattern);
		//KMP(target,pattern);
	}

}
