package Arithmetic;
import java.util.Arrays;
import java.util.Stack;

import org.junit.Test;
public class StackUtil {
	
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack<Integer> s) {
		Stack<Integer> stack=new Stack<>();
		for(int i=0;i<s.size();i++){
			stack.push(s.pop());
		}
		for(int i=0;i<stack.size();i++){
			s.push(stack.pop());
		}
		
	}
	
	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		Stack newS=new Stack<>();
		Object data;
		while(s.peek()!=o&&!s.isEmpty()){
			data=s.pop();
			newS.push(data);
		}
		s.pop();
		while(!newS.isEmpty()){
			data=newS.pop();
			s.push(data);
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		Object[] o=new Object[len-1];
		int count=0;
		while(!s.isEmpty()&&count!=len){
			o[count]=s.pop();
			count++;
		}
		return o;
	}
	/**
	 * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
	 * 使用堆栈检查字符串s中的括号是不是成对出现的。
	 * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
	 * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s){
		byte[] by=s.getBytes();
		Stack st =new Stack<>();
		st.push(by[0]);
		int count=1;
		while(count<by.length){
		if(by[count]=='('||by[count]==')'||by[count]=='['||by[count]==']'||by[count]=='{'||by[count]=='}'){
			if(((byte)st.peek()=='('&&by[count]==')')||((byte)st.peek()=='['&&by[count]==']')
					||((byte)st.peek()=='{'&&by[count]=='}')){
				st.pop();
			}else{
				st.push(by[count]);
			}
		}
		count++;
		}
		return st.isEmpty();
	}
	@Test
	public void test(){
//		byte[] b="s".getBytes();
//		System.out.println(b[0]==444);
		String str="([b{x]y})";
		System.out.println(isValidPairs(str));
	}
	
	
}