package Arithmetic;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

//实现3-堆 
/*
 * 3-堆和2-堆一样 也可以利用数组来实现的
 */
public class Three_Heap {
	public int size;
	final int DEFAULT=200;
	public int[] a;
	public Three_Heap() {
		a=new int[DEFAULT];
	}
	public void insert(int data){
			int p=++size;
			a[p]=data;
			System.out.println(p);
		//使用上滤的方法
		while(true){
			if(p>1&&data<a[(p+1)/3]){
				a[p]=a[(p+1)/3];
				a[(p+1)/3]=data;
				p=(p+1)/3;
			}else{
				break;
			}
		}
	}
	//下滤
	public void removeMin(){
		int p=1;
		int n=0;
		while((p*3-1)<size){
			if(p*3>size){
				n=minOne(a,p*3-1,0,0);
			}else if(p*3+1>size){
				n=minOne(a,p*3-1,p*3,0);
			}else{
				n=minOne(a,p*3-1,p*3,p*3+1);
			}
			System.out.println(n+"nnnnnnnn");
			if(a[size]<a[n]){
				a[p]=a[size];
				a[size]=0;
				size--;
				return;
			}
			a[p]=a[n];
			p=n;
		}
		a[p]=a[size];
		a[size]=0;
		size--;
	}
	public int minOne(int[] n,int a,int b,int c ){
		if(b==0){
			return a;
		}
		if(c==0){
			if(n[a]>n[b]){
				return b;
			}else{
				return a;
			}
		}
		if(n[a]<=n[b]&&n[a]<=n[c]){
			return a;
		}else if(n[c]<=n[b]&&n[c]<=n[a]){
			return c;
		}else{
			return b;
		}
	}
	@Test
	public void test(){
	
		Three_Heap th=new Three_Heap();
		th.insert(5);
		th.insert(6);
		th.insert(1);
		th.insert(7);
		th.insert(10);
		th.insert(2);
		System.out.println(Arrays.toString(th.a));
		th.removeMin();
		th.removeMin();
		th.removeMin();
		th.removeMin();
//		th.removeMin();
		System.out.println(Arrays.toString(th.a));
	}
}
