package Arithmetic;

public class BinaryTreeNode<T > {
	//二叉搜索树
	public T data;
	public BinaryTreeNode<T> left;
	public BinaryTreeNode<T> right;
	
	public BinaryTreeNode(T data,BinaryTreeNode left,BinaryTreeNode right){
		this.data=data;
		this.left=left;
		this.right=right;
	}
}