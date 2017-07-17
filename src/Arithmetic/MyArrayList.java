package Arithmetic;

import java.util.ArrayList;
import java.util.Iterator;

public class MyArrayList {
	//数组长度
	private int size=0;
	//底层维护一个数组
	private Object[] data=new Object[100];
	//数组长度
	public int size(){
		return size;
	}
	//判断是否越界
	private boolean isCross(int index){
		return index<0||index>size();
	}
	private void autoIncrease(){
		Object[] d=new Object[data.length*2-1];
		for(int i=0;i<data.length;i++){
			d[i]=data[i];
		}
		data=d;
	}
	//添加数据
	public void add(Object o){
		data[size]=o;
		size++;
	}
	//根据下标添加数据
	public void add(Object o,int index){
		if(isCross(index)){
			System.out.println("下标越界");
			return;
		}
		if(data[index]==null){
			data[index]=o;
			size++;
		}else{
			for(int i=data.length-1;i>index;i--){
				data[i]=data[i-1];
			}
			data[index]=o;
			size++;
		}
	}
	public Object get(int index){
		if(isCross(index)){
			System.out.println("下标越界");
			return null;
		}else{
			return data[index];
		}
	}	
	//移除数据
	public void remove(){
		data[size]=null;
		size--;
	}
	public void remove(int index){
		if(isCross(index)){
			System.out.println("下标越界");
			return;
		}else{
			for(int i=index;i<size-1;i++){
				data[i]=data[i+1];
			}
			remove();
		}
	}
	public class myIterator implements Iterator{
		private int count=0;
		@Override
		public boolean hasNext() {
			if(count==size){
				return false;
			}else{
				count++;
				return true;
			}
		}

		@Override
		public Object next() {

			return data[count-1];
		}
	}
	
}
