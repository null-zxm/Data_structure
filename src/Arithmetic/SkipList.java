package Arithmetic;
/*
 * 跳跃表（实现的是一个不重复的  读者有兴趣可以实现一个可以重复的）
 * www.zhouximin.com
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkipList {
	/*
	 * 有一个内部类SkipNode 
	 */
	private SkipNode begin;
	private SkipNode end;
	public int hight;
	private Random rd;
	private class SkipNode{
		/*
		 * 四个方向的指针
		 */
		String data;
		SkipNode up;
		SkipNode down;
		SkipNode left;
		SkipNode right;
		public SkipNode(SkipNode up,SkipNode down,SkipNode left,SkipNode right){
			this.up=up;
			this.down=down;
			this.left=left;
			this.right=right;
		}
	}
	//初始化
	public SkipList(){
		rd=new Random();
		hight=1;
		begin=new SkipNode(null,null,null,null);
		end=new SkipNode(null, null, begin, null);
		begin.right=end;
	}
	//查询
	
	public SkipNode find(String data){
		SkipNode p=begin;
		while(true){
			while((p.right.data!=null)&&p.right.data.compareTo(data)<=0){
				p=p.right;//向右移动
			}
			if(p.down!=null){
				p=p.down;
			}else{
				break;
			}
		}
		return p;
	}
	
	public void addHight(){
		System.out.println("高了");
		SkipNode newBegin=new SkipNode(null, begin, null, null);
		SkipNode newEnd=new SkipNode(null, end, null, null);
		newBegin.right=newEnd;
		newEnd.left=newBegin;
		begin.up=newBegin;
		begin=newBegin;
		end=newEnd;
		hight++;
	}
	public void paint(){
		List<SkipNode> list=new ArrayList<SkipList.SkipNode>();
		SkipNode p=begin;
		while(p.down!=null){
			list.add(p);
			p=p.down;
		}
		list.add(p);
		for (SkipNode skipNode : list) {
			paintLine(skipNode);
			System.out.println();
		}
	}
	public void paintLine(SkipNode p){
		while(p.right.data!=null){
			p=p.right;
			System.out.print(p.data+"————");
		}
	}
	//添加
	public void insert(String data){
		SkipNode p=find(data);//找到 在data 前一个 或者 相等的那个 SkipNode
		if(p.data!=null&&p.data.equals(data)){
			return;
		}
		SkipNode q=new SkipNode(null,null,p,p.right);
		q.data=data;
		p.right.left=q;
		p.right=q;
		int i=1;//表示当前的高度
		while(rd.nextInt()<0.5){
			System.out.println(i+"i");
			if(i==hight){
				addHight();
			}
			//利用up 来找 高一层的
			System.out.println("p.up==null"+"————"+p.data);
			System.out.println(p.up==null);
			while(p.up==null&&p.left!=null){
				System.out.println("!!!!");
				p=p.left;
			}
			p=p.up;
			System.out.println("p==nullaaa	:");
			System.out.println(p==null);
			SkipNode q2=new SkipNode(null, q, p, p.right);
			q.up=q2;
			q2.data=data;
			p.right.left=q2;
			p.right=q2;
			q=q2;
			i++;
		}
		
	}
	/*
	 * 删除
	 * 找到data 把那一条都删了就好了
	 * 这里没考虑不存在的情况 可以自行加上一个contain的函数
	 */
	public boolean remove(String data){
		SkipNode q=find(data);
		//把q 这一排都删除就好了
		while(q!=null){
			q.left.right=q.right;
			q.right.left=q.left;
			//这两步是为了方便 GC
			q.left=null;
			q.right=null;
			q=q.down;
		}
		//判断begin是否和end相链如果是就剪一层
		if(begin.right==end){
			removeHight();
		}
		return true;
	}
	public void removeHight(){
		SkipNode oldbegin=begin;
		SkipNode oldend=end;
		begin=begin.down;
		end=end.down;
		oldbegin.right=null;
		oldbegin.down=null;
		oldend.left=null;
		oldend.down=null;
		begin.up=null;
		end.up=null;
	}
	
}
