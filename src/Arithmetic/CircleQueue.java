package Arithmetic;
/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	private int size;
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	public boolean isEmpty() {
		return size==0;
        
    }

    public int size() {
        return size;
    }
    public void enQueue(E data) {
        front=(front+1)%elementData.length;
        elementData[front++]=data;
    	size++;
    }

    public E deQueue() {
        E data=(E) elementData[rear];
        rear=(rear+1)%elementData.length;
        size--;
        elementData[rear] = null;
    	return data;
    }
}