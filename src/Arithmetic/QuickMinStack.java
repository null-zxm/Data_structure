package Arithmetic;

import org.junit.Test;

//一个pop push findMin 都是O（1）的数据结构
public class QuickMinStack {
	private final int DEFAULT=20;
	private String[] stack ;
	private String[] flag;
	private int size;
	private int flagSize;
	public QuickMinStack() {
		stack=new String[DEFAULT];
		flag=new String[DEFAULT];
	}
	public QuickMinStack(int size) {
		stack=new String[size];
		flag=new String[size];
	}
	public String pop(){
		String data=stack[--size];
		if(data.equals(flag[flagSize-1])){
			flag[flagSize--]=null;
		}
		return data;
	}
	public void push(String data){
		if(flagSize==0){
			flag[flagSize++]=data;
			stack[size++]=data;
			return;
		}
		if(data.compareTo(flag[flagSize-1])<0){
			flag[flagSize++]=data;
		}
		stack[size++]=data;
	}
	public String findMin(){
		return flag[flagSize-1];
	}
	@Test
	public void T(){
		QuickMinStack qm= new QuickMinStack();
		qm.push("5");
		qm.push("1");
		qm.push("6");
		System.out.println(qm.pop());
		System.out.println(qm.findMin());
	}
}
