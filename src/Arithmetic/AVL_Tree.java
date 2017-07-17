package Arithmetic;
/*
 * 先不使用泛型
 */
public class AVL_Tree {
	public  AvlNode root;
	/*
	 * 私有内部AvlNode类 
	 */
	static class AvlNode{
		Object data;		//存储的数据
		AvlNode left;		//左节点
		AvlNode right;		//右节点
		int hight;			//深度
		public AvlNode(String data, AvlNode left,AvlNode right){
			this.data=data;
			this.left=left;
			this.right=right;
			this.hight=0;
		}
		public AvlNode(String data){
			this(data,null,null);
		}
		
	}
	/*
	 * 获得节点深度的方法
	 */
	public int hight(AvlNode node){
		return (node==null)?-1:node.hight;
	}
	/*
	 * 左旋转 
	 * 当 node.lefe.hight-node.right.hight>1 的时候
	 */
	private AvlNode leftSpin(AvlNode node){
		AvlNode node2=node.left;
		node.left=node2.right;
		node2.right=node;
		node.hight=Math.max(hight(node.left), hight(node.right))+1;
		node2.hight=Math.max(hight(node2.left), hight(node2.right))+1;
		return node2;
		
	}
	/*
	 * 右旋转
	 *  当 node.right.hight-node.left.hight>1 的时候
	 */
	private AvlNode rightSpin(AvlNode node){
		AvlNode node2=node.right;
		node.right=node2.left;
		node2.left=node;
		node.hight=Math.max(hight(node.left), hight(node.right))+1;
		node2.hight=Math.max(hight(node2.left), hight(node2.right))+1;
		return node2;
		
	}
	/*
	 * 双左旋转 
	 * 当node.left.right.hight>node.left.left.hight
	 */
	private AvlNode doubleLeftSpin(AvlNode node){
	node.left=rightSpin(node.left);
	return leftSpin(node);
	}
	/*
	 * 双右旋转 
	 * 当node.left.right.hight<node.left.left.hight
	 */
	private AvlNode doubleRightSpin(AvlNode node){
		node.right=rightSpin(node.right);
		return leftSpin(node);
		}
	/*
	 * 添加数据
	 */
	public AvlNode add(String o){
		return root= add(o,root);
	}
	private AvlNode add(String o,AvlNode node){
		if(node==null){
			return new AvlNode(o);
		}
		int CompareResult=o.compareTo((String) node.data);
		if(CompareResult>0){
			node.right=add(o,node.right);
		}else if(CompareResult<0){
			node.left=add(o,node.left);
		}else{
			;
		}
		return balance(node);
	}
	/*
	 * 平衡树结构
	 * 
	 */
	private AvlNode balance(AvlNode node){
		if(hight(node.left)-hight(node.right)>1){
			if(hight(node.left.left)>=hight(node.left.right)){
				leftSpin(node);
			}else{
				doubleLeftSpin(node);
			}
			
		}else if(hight(node.right)-hight(node.left)>1){
			if(hight(node.right.right)>=hight(node.right.left)){
				rightSpin(node);
			}else{
				doubleRightSpin(node);
			}
		}
		node.hight=Math.max(hight(node.left), hight(node.right))+1;
		return node;
		
	}
	/*
	 * 删除数据
	 * 
	 */
	public AvlNode remove(String o){
		return remove(o, root);
	}
	private AvlNode remove(String o,AvlNode node){
		if(node ==null){
			return node;
		}
		int CompareResult=o.compareTo((String) node.data);
		if(CompareResult>0){
			node.right=remove(o, node.right);
		}else if(CompareResult<0){
			node.left=remove(o, node.left);
		}else{
			//two children
			if(node.left!=null&&node.right!=null){
				node.data=findMin(node.right).data;
				node.right=remove((String)node.data, node.right);
			}else{
				node=(node.left!=null)?node.left:node.right;
			}
		}
		return balance(node);
	}
	/*
	 * z找到最小值
	 */
	private AvlNode findMin(AvlNode p){
		if(p.left==null){
			return  p;
		}return findMin(p.left);
	}
	public boolean contain(String o){
		return contain(o,root);
	}
	private boolean contain(String o,AvlNode node){
		if(node==null){
			return false;
		}
		int compareResult=o.compareTo((String)node.data);
		if(compareResult>0){
			return contain(o,node.right);
		}else if(compareResult<0){
			return contain(o,node.left);
		}else{
			return true;
		}	
	}
}
