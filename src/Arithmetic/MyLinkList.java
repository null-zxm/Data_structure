package Arithmetic;

public class MyLinkList {
	/*
	 * 
	 * 节点类
	 */
	static class Node{
		Object data;
		Node last;
		Node next;
		public Node(Object o,Node last,Node next){
			this.data=o;
			this.last=last;
			this.next=next;
		}
	}
	
	//创建 链头 链尾
	private Node start;
	private Node end;
	private int size=0;
	//大小
	public MyLinkList(){
		doClean();
	}
	public int size(){
		return size;
		
	}
	/*
	 * 
	 * 添加的方法
	 */
	public void add(Object o){
		addbefore(end, o);
	}
	public void add(int index , Object o){
		addbefore(getNode(index+1), o);
	}
	private  void addbefore(Node p, Object o){
		Node newNode=new Node(o, p.last, p);
		newNode.last.next=newNode;
		p.last=newNode;
		size++;
	}
	private void addNext(Node p, Object o){
		Node newNode=new Node(o, p, p.next);
		newNode.next.last=newNode;
		p.next=newNode;
		size++;
	}
	/*
	 * 获取 下标为index 的 Node
	 */
	public Object get(int index){
		return getNode(index).data;
	}
	public boolean contain(Object o){
		if(getIndex(o)==0){
			return false;
		}
		return true;
	}
	public int getIndex(Object o){
		int count=1;
		for(int i=1;i<=size;i++){
			if(get(i).equals(o)){
				return count;
			}
			count++;
		}
		return 0;
		
	}
	
	private Node getNode(int index){
		if(index>0&&index<=size){
			Node p=start;
			for(int i=0;i<index;i++){
				p=p.next;
			}
			return p;
		}
		return null;
	}
	/*
	 * 清除
	 */
	public void clean(){
		doClean();
	}
	private void doClean(){
		start=new Node(null, null, null);
		end=new Node(null, start, null);
		start.next=end;
		size=0;
	}
	/*
	 * 删除
	 */
	public void remove(int index){
		remove(getNode(index));
	}
	public void remove(Node p){
		p.next.last=p.last;
		p.last.next=p.next;
		size--;
	}
	public void remove(Object o){
		remove(getIndex(o));
	}
	public void removelast(){
		remove(size);
	}
	public void removeFirst(){
		remove(1);
	}
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		int herf=size/2;
		for(int i=1;i<=herf;i++){
			changeWjthOther(i, size-i+1);
		}
		
	}
	private   void changeWjthOther(int m,int n){
		Node m1=getNode(m);
		Node n1=getNode(n);
		Node m_last=getNode(m).last;
		Node n_next=getNode(n).next;
		remove(m);
		remove(n-1);
		addNext(m_last, n1.data);
		addbefore(n_next, m1.data);
	}
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	int  herf=size/2;
	public  void removeFirstHalf(){
		int  herf=size/2;
		
		for(int i=1;i<=herf;i++){
			
			remove(1);
			
		}
		
	}
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
			int  herf=length;
		
		for(int j=1;j<=herf;j++){
			
			remove(i);
			
		}
		
		
	}
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(MyLinkList list){
		int[] a= new int[list.size+1];
		for(int i=1;i<=list.size;i++){
			
			a[i]=(int) get((Integer)list.get(i));
		}
		
		return a;
	}
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 
	 * @param list
	 */
	
	public  void subtract(MyLinkList list){
		for(int i=1;i<=list.size;i++){
			if(contain(list.get(i))){
				remove(get(i));
			}
		}
	}
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		//方案一因为都是递增 所以 先算出 有几个 直接调用 remove(int i, int length)
		//利用二分法 找出 min 然后判断 算出 有几个 Node 在这中间 最好 调用 remove(int i, int length)
	
	
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  MyLinkList intersection( MyLinkList list){
		MyLinkList list2= new MyLinkList();
		if(size>list.size){
		for(int i=1;i<=size;i++){
			if(list.contain(get(i))){
				list2.add(get(i));
			}
		}
		}else{
			for(int i=1;i<=list.size;i++){
				if(contain(list.get(i))){
					list2.add(list.get(i));
				}
			}
		}
		return list2;
	}
	/*
	 * 是否有 环
	 * 
	 */
	public boolean isLoop(){
		return isLoop(start.next);
	}
	public boolean isLoop(Node n){
		Node a=n;//a走一步
		Node b=n;//b走2步
		if(n!=null&&n.next!=null&&n.next.next!=null){
			while(true){
				a=a.next;
				b=b.next.next;
				if(a.data==b.data){
					return true;
				}
				if(a==null||b==null){
					return false;
				}
				return false;
			}
		}
		return false;
	}
}
