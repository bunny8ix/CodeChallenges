/*
KFC Outlet..!
Problem Statement
KFC opened just a new outlet. The problem is that its very outer of the main city. A long highway leads to that KFC outlet. Due to very outer position of outlet almost no one stops in it, hence its now unprofitable to hold it now. One of the main reason is that they have not advertised well for their outlet. There are exactly N advertising boards on the highway from main city to this new KFC outlet. Now KFC want to advertise on some of these advertising boards ( Definitely advertising on every board cost some money) .
KFC strategy is very clear, they want that at least 'K' KFC advertisement should be there among M consecutive advertising boards. But at the same time KFC want to pay minimum for its advertisement because already it has spent a lot on its outlet.
Now your job is to help KFC and tell KFC the number of ways different ways in which they can place their advertisement on there boards ( definitely minimum number of advertisement) and still meeting its strategy.
Contest ends on Thu May 30, 2013 at 23:59:00
Input/Output Specifications
Input Specification:
Your input will be Three integers values.
N advertising boards on the highway from main city to this new KFC outlet
at least 'K' KFC advertisement should be there among M consecutive advertising boards.
N, M and K (1 <= K <= M <= 50, M <= N <= 10^9)
Output Specification:
Your output will be total number of possible ways modulo 1000000007.
if the input values are invalid then your output will be "-1".
Examples
Example 1. If N, M , K values are 3, 2, 1 respectively then there is only one way for minimum cost
Example 2.
If N M & k
values are 6, 3, 2 respectively then there is only 6 way for minimum cost.
*/
public class Test{
public static void main(String arg[]){
	min_boards(6,3,2);

}
private static String[] totalWays(int boards){
		int ways=1;
		for(int i=1;i<=boards;i++)
			{
				ways=ways*2;
			}
		String[] values = new String[ways];
		for(int i=0;i<values.length;i++){
			values[i]=convert_to_binary(i,boards);
		}
		return values;
}
private static String normalize(StringBuffer val,int boards){

		int diff_length=boards-val.length();
		while(diff_length>0){
			val=val.insert(0,"0");
			diff_length=boards-val.length();
		}
		return val.toString();
}

private static String convert_to_binary(int number,int boards){
	if(number==1){
	StringBuffer val =new StringBuffer ("1");
	return (boards>1)?normalize(val,boards):val.toString();
	}
	int quotent=0;
	StringBuffer val=new StringBuffer();
	for(int remainder=0;number>1;number=quotent){
		remainder=number%2;
		quotent=number/2;
		val=val.append(remainder);
	}
	val.append(quotent);
	val=val.reverse();
	return (val.length()!=boards)?normalize(val,boards):val.toString();
}

private static int eleminate_impossible(int boards,int m,int k){

	String[] totalWays=totalWays(boards);
	int no_of_parts=boards+(1-m);
	int[] need_to_null_result_index=new int[no_of_parts];
	String[] temp_result=new String[no_of_parts];
	String result_combine="";
	int index=-1;
	int count=0;
	for(int i=0;i<totalWays.length;i++){
		//System.out.println("src = "+totalWays[i]);
		for(int j=0;j<no_of_parts;j++){
			temp_result[j]=totalWays[i].substring(j,j+m);
				int occurence=0;
				for(int l=0;l<temp_result[j].length();l++){
					if(Character.getNumericValue(temp_result[j].charAt(l))==1){
						occurence++;
					}
				}
				if(occurence<k){	
					count++;
				}	
			}
		if(count==0){
			index++;
			String s=temp_result[0];
			for(int n=1;n<no_of_parts;n++){
				s= s+temp_result[n].substring(k);	
			}
			result_combine=result_combine+s+" ";
			//System.out.println("Final : "+result_combine);
		}
		count=0;
		}
	result_combine=result_combine.trim();
	String result[]=result_combine.split(" ");
	int occurence=0;
	int loop_index=0;
	int least_find=0;
	String least_indexs=" ";
			for(loop_index=0;loop_index<result.length;loop_index++){
				//System.out.println(loop_index+" : "+result[loop_index]);
				for(int j=0;j<result[loop_index].length();j++){
					if(Character.getNumericValue(result[loop_index].charAt(j))==1){
						occurence++;
					}
				}
				if(loop_index==0){	
						
						least_find=occurence;
						least_indexs=""+loop_index;
				}else{
					
					if(occurence<=least_find){
						if(loop_index-1==0){
							if(least_find>occurence){
								least_indexs=" ";
							}
						}
						least_find=occurence;
						least_indexs=least_indexs+" "+loop_index;
					}					
				}
				occurence=0;
			}
	String[] indexs= (least_indexs.trim()).split(" ");
	for(int i=0;i<indexs.length;i++){
		System.out.println("POSSIBLE WAY "+(i+1)+" : "+result[new Integer (indexs[i]).intValue()]);
	}
 return indexs.length;
}
public static int min_boards(int n,int m,int k)
{
	if((k>=1) && (m>=1) &&(n>=1) &&(k<=50) && (m<=50) &&( n<=1000000000)  && (m<=n) &&(k<=m)){
		System.out.println("Valid Input :  N="+n+" M="+m+" K="+k);
		int possibilities =  eleminate_impossible(n,m,k);
		System.out.println("NUMBER OF POSSIBLE WAYS : "+possibilities);
		return possibilities;
	}else{
		System.out.println("Invalid Input . . .");	
		return -1;
	}	
}

}
	
