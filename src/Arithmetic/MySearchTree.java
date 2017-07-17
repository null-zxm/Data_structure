package Arithmetic;

public class MySearchTree<T extends Comparable> {
	//静态内部类
	private static class BinaryTreeNode<T extends Comparable> {
		public T data;
		public BinaryTreeNode<T> left;
		public BinaryTreeNode<T> right;
		
		public BinaryTreeNode(T data,BinaryTreeNode left,BinaryTreeNode right){
			this.data=data;
			this.left=left;
			this.right=right;
		}
	}
	//构造函数
	public MySearchTree(){
	}
	//数根 root
	private BinaryTreeNode root;
	//添加数据
	public boolean add(T o){
		if(root==null){
			root=new BinaryTreeNode(o,null,null);
			return true;
		}
		return add(root,o);
	}
	private boolean add(BinaryTreeNode p,T o){
		int compareResult = o.compareTo(p.data);
		if(compareResult>0){
			if(p.right==null){
				p.right=new BinaryTreeNode(o,null,null);
				return true;
			}
			p=p.right;
			return add( p,o);
		}else if(compareResult<0){
			if(p.left==null){
				p.left=new BinaryTreeNode(o,null,null);
				return true;
			}
			p=p.left;
			return add( p,o);
		}else{
		return false;
		}
	}
	//找到最小值
	public T findMin(){
		return (T) findMin(root).data;
	}
	private BinaryTreeNode findMin(BinaryTreeNode p){
		if(p.left==null){
			return  p;
		}return findMin(p.left);
	}
	//找到最大值
	public T findMax(){
		return (T) findMax(root).data;
	}
	private BinaryTreeNode findMax(BinaryTreeNode p){
		if(p.right==null){
			return  p;
		}return findMax(p.right);
	}
	//移除
	public void remove(T o){
		root= remove(root, o);
	}
	private BinaryTreeNode remove(BinaryTreeNode p,T o){
		int compareResult = o.compareTo(p.data);
		if(p==null){
			return new BinaryTreeNode(o,null,null);
		}
		if(compareResult>0){
			p.right=remove(p.right, o);
		}else if(compareResult<0){
			p.left=remove(p.left, o);
		}else{
			if(p.left!=null&&p.right!=null){
				p.data=findMin(p.right).data;
				p.right=remove(p.right,(T)p.data);
			}else{
				p=(p.left==null)?p.right:p.left;
			}
		}
		return p;
	}
	//包含
	public boolean contain(T o){
		return contain(root,o);
	}
	private boolean contain(BinaryTreeNode p,T o){
		int compareResult = o.compareTo(p.data);
		if(compareResult>0){
			if(p.right==null){
				return false;
			}
			return contain(p.right,o);
		}else if(compareResult<0){
			if(p.left==null){
				return false;
			}
			return contain(p.left,o);
		}else{
		return true;
		}
		}
	}
	
	

