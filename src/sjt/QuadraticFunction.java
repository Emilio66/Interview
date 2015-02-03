package sjt;
import java.text.DecimalFormat;

//quadratic function solution, b^2-4a*c >=0 
public class QuadraticFunction{
	
	public static final int NUMBER	=10;
	public static final int EQUAL	=0;
	public static final int MINUS	=-1;
	public static final int PLUS	=1;
	public static final int X		=2;
	
	//judge the type of a character
	public static int charType(char ch){
		if(ch >='0' && ch <='9')
			return NUMBER;
		else if(ch =='x')
			return X;
		else if(ch =='=')
			return EQUAL;
		else if(ch =='-')	
			return MINUS;
		else
			return PLUS;
	}

	//judge and calculate the solution to quadratic function
	public static boolean getSolution(int[] factors,double[] solutions){
		int a=factors[0];
		int b=factors[1];
		int c=factors[2];
		
		int delta= b*b - 4*a*c;
		if(delta<0)
			return false;
		else{
			double d= Math.sqrt(delta);
			double x1=(-b-d)/(2*a);//calculate solutions 
			double x2=(-b+d)/(2*a);
			
			DecimalFormat df =new DecimalFormat("#.00");
			solutions[0] = Double.parseDouble(df.format(x1));//format number
			solutions[1] = Double.parseDouble(df.format(x2));
			
			return true;
		}
	
	}

	//parse facotrs of function based on input equation
	//1. process all operators i.e. +,-,=
	//2. save number in a string
	//3. set corresponding factor
	public static int[] parseEquation(String s){
		int pos=0,ret;
		int len=s.length();
		int[] factors=new int[3];
		StringBuilder number;
		char curr;
		boolean isLeft=true;
		boolean isNegative=false;
		
		while(pos <len){
			number=new StringBuilder();
			curr=s.charAt(pos);
			ret=charType(curr);	//return code
			
			if(ret == EQUAL){	//reach the right part of the equation
				isLeft =false;
				isNegative=false;	//initialize
			}
			else if(ret == MINUS)
				isNegative= true;
			else if(ret == PLUS)	
				isNegative = false;	
			else if(ret == NUMBER || ret == X){		//factors or no factors before x
				while((ret=charType(curr))== NUMBER){
					number.append(curr);		//save factor in string
					pos++;
					if(pos < len)
						curr=s.charAt(pos);
					else
						break;
				
				}
			
			//the very next letter decide factor's position
				if(ret== X){
					if(pos<(len-1) && s.charAt(pos+1)=='^'){  //factor of x^2
						int a = 1;	//default factor is 1 for x or x^2	
						if(number.length() >0)
							a=Integer.parseInt(number.toString());
						if(isNegative)
							a=-a;
						if(isLeft)
							factors[0] +=a;	//add original number
						else
							factors[0] -=a;	//minus original number
							
						pos+=2;	//ignore next two characters	^2
						
					}else{
						int b = 1;
						if(number.length() >0)
							b=Integer.parseInt(number.toString());  //factor of x
						if(isNegative)
							b=-b;
						if(isLeft)
							factors[1] +=b;	
						else
							factors[1] -=b;	
					}
					
				}else{	//factor of constant number				
					pos--;  //go back 1 step
					int c = Integer.parseInt(number.toString());//constant number	
					if(isNegative)
						c=-c;
					if(isLeft)
						factors[2] +=c;	
					else
						factors[2] -=c;	
					
				}
			}
			//System.out.println(curr+"\t"+ret);
			pos++;	//move forward
		}
		
		return factors;
	}
	
	public static void main(String[]args){
		//suppose args[0] is the equation
		//System.out.println(args[0]);
		int[] factors = parseEquation(args[0]);
		
		//System.out.println(factors[0]+"x^2+("+factors[1]+")x+("+factors[2]+")=0");
		
		double[] solutions = new double[2]; //save solutions
		if(getSolution(factors,solutions)){
			System.out.println(solutions[0]+" "+solutions[1]);
		}else{
			System.out.println("No Solution");
		}
		
	}
}