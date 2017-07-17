package Arithmetic;
/*
 * 不用泛形了 用 你int代替 好比较
 * 
 */
public class BinaryHeap {
	private final int DEFAULT_CAPACITY=10; 
	private int currentSize;
	public int[] arr;
	public BinaryHeap(){
		arr=new int[DEFAULT_CAPACITY];
	}
	public BinaryHeap(int capacity){
		arr=new int[capacity];
	}
	public void insert(int data){
		if(currentSize==arr.length-1){
			addCapacity(currentSize*2+1);
		}
		int hole=++currentSize;
		while(data<arr[hole/2]&&hole!=1){
			arr[hole]=arr[hole/2];
			hole /=2;
		}
		arr[hole]=data;
	}
	private void addCapacity(int newSize){
		int[] newArr=new int[newSize];
		System.arraycopy(arr, 0, newArr, 0, arr.length);
		arr=newArr;
	}
	public int deleteMin(){
		if(currentSize==0){
			throw new RuntimeException();
		}
		int tmp=arr[currentSize];
		int hole=1;//从第一个开始往下整理
		currentSize--;
		//开始整理二叉堆
		while(hole*2<=currentSize){
			if(tmp<arr[hole*2]&&tmp<arr[hole*2+1]){
				break;
			}else if(arr[hole*2]<=arr[hole*2+1]){
					arr[hole]=arr[hole*2];
					hole *=2;
			}else if(arr[hole*2]>arr[hole*2+1]){
					arr[hole]=arr[hole*2+1];
					hole=hole*2+1;
			}
		}
		arr[hole]=tmp;
		arr[currentSize+1]=0;
		return arr[1];
	}
}
