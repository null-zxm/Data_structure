package Arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static void main(String[] args) {
		System.out.println(Arrays.toString(fibonacci2(100)));
//		System.out.println(Arrays.toString(getPrimes(100)));
//		System.out.println(isPrimes(4));
//		int[] a={1,4,8,3,0,8,4,0,4};
//		System.out.println(join(a,"-"));
//		System.out.println(Arrays.toString(getPerfectNumbers(100)));
//		System.out.println(numCount(6));
//		int[] a={1,4,8,3,0,8,4,0,4};
//		int[]a1 = {3, 5, 7,8} ; 
//		int[]a2 = {4, 5, 6,7};
//		System.out.println(Arrays.toString(grow(a1,2)));
//		System.out.println(Arrays.toString(merge(a1,a2)));
//		reverseArray(a);
//		System.out.println(Arrays.toString(a));
//		System.out.println(Arrays.toString(removeZero(a)));
	}
	public static void reverseArray(int[] origin){
	
//方法一		int[] b=new int[origin.length];
//		for(int i=0;i<origin.length;i++){
//			b[i]=origin[origin.length-i-1];
//		}
//		for(int j=0;j<origin.length;j++){
//			origin[j]=b[j];
//		}
		// 方法二
		for(int i=0;i<origin.length/2;i++){
			int temp=origin[i];
			origin[i]=origin[origin.length-1-i];
			origin[origin.length-1-i]=temp;
		}
	
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		//获得不为0的个数
		int count=0;
		int point=0;
		for (int i : oldArray) {
			if(i!=0)count++;
		}
		int[] newArray=new int[count];
		for (int i : oldArray) {
			if(i!=0){
				newArray[point]=i;
				point++;
			}
		}
		return newArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		int[] arrayy3=new int[array1.length+array2.length];
		int i=0;
		int j=0;
		int point=0;
		while(point<arrayy3.length){
			if(i==array1.length&&j==array2.length){
				break;
			}
			if(i==array1.length){
				arrayy3[point]=array2[j];
				j++;
				point++;
				continue;
			}
			if(j==array2.length){
				arrayy3[point]=array1[i];
				point++;
				i++;
				continue;
			}
			if(array1[i]>array2[j]){
				arrayy3[point]=array2[j];
				j++;
			}else if(array1[i]<array2[j]){
				arrayy3[point]=array1[i];
				i++;
			}else{
				arrayy3[point]=array1[i];
				i++;
				j++;
			}
			
			point++;
		}
		
		
		return  removeZero(arrayy3);
		
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public static int[] grow(int [] oldArray,  int size){
		int[] newArray=new int[oldArray.length+size];
		for(int i=0;i<oldArray.length;i++){
			newArray[i]=oldArray[i];
		}
		return newArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int fibonacci(int n){
		if(n==1){
			return 1;
		}
		if(n==2){
			return 1;
		}
		return fibonacci(n-1)+fibonacci(n-2);
	}
	/*
	 * 不要去想以前是否做过 一切从头构思！！！！！
	 * 
	 */
	public static int[] fibonacci2(int max){
		int[] a=new int[max];
		a[0]=1;
		a[1]=1;
		int i=2;
		while( true){
			a[i]=a[i-1]+a[i-2];
			if(a[i]>max){
				a[i]=0;
				break;
			}
			i++;
		}
		 a=removeZero(a);
		
		return a;
		
	}
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		int[] a= new int[max];
		int point=0;
		for(int i=2;i<max;i++){
			if(isPrimes(i)){
				a[point]=i;
				point++;
			}
		}
		return removeZero(a);
	}
	private static boolean isPrimes(int a){
		if(a==2)return true;
		for(int i=2;i<=a/2;i++){
			if(a%i==0)return false;
		}
		return true;
		
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		int point=0;
		int[] a=new int[max];
		for(int i=0;i<=max;i++){
			if(numCount(i)==i){
				a[point]=i;
				point++;
			}
		}
		return removeZero(a);
	}
	public static int numCount(int a){
		int count=1;		
		for(int i=2;i<a/2;i++){
			if(a%i==0)count +=i;
		}
		return count;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator){
		StringBuffer sb=new StringBuffer();
		for (int i : array) {
			sb.append(i+seperator);
		}
		return sb.substring(0, sb.length()-1).toString();
	}
	

}