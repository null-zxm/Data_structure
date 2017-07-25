package Arithmetic;
/*一次性哈希的实现(带有虚拟节点的实现)
 * 这里使用5台服务器 每一个服务器有5个虚拟节点
 * 服务器的Hash 使用 FNV1_32_HASH算法 将ip地址转换
 * 就用Linklist来当作服务器，
 * 用TreeMap来模拟 哈希环
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class ConsistentHash {
	static String[] servers={"192.168.1.102:111","192.168.2.102:111","192.168.3.102:111"
							,"192.168.4.102:111","192.168.5.102:111"};
	
	static TreeMap<Integer, LinkedList<String>> store;// TreeMap中的String
										// 放的是Hash值，另一个放的是“服务器”
	static int VIRTUAL_NUM=1;//虚拟节点个数
	//初始化
	static{
		store=new TreeMap<>();
		//将5个服务器以及虚拟节点放到store中
		for(int i=0;i<servers.length;i++){
			LinkedList<String> server=new LinkedList<>();
			server.add("第"+i+"台服务器");//放上自己的IP，好测试
			store.put(getHash(servers[i]), server); 
			for(int j=0;j<VIRTUAL_NUM;j++){
			store.put(getHash(servers[i]+"virtualNode"+j), server);
			}
		}
	}
	/*
	 * 缓存节点数据的时候要找到比数据大的的那个服务器就可以了
	 *（只大一点点。。语文不好不知道怎么说）这里用String 表示了
	 */
	public static void addNode(String node){
		Integer hash=getHash(node);//因为TreeMap 默认是按照key升序，
								//的所以拿到hash值 然后一个个比较过去
		for (Integer key : store.keySet()) {
			if(hash<key){
				store.get(key).add(node); //找到后把node放进去
				return;
			}
		}
		//如果没有比它大的就加到第一个里面
		store.get(store.firstKey()).add(node);
	}
	//如果添加服务器的话
	public static void addServer(String serverIp){
		LinkedList<String> server=new LinkedList<>();
		server.add("第"+serverIp+"台");//放上自己的IP，好测试
		Integer hash=getHash(serverIp);//找到hash 影响到的是比这个小的那一个服务器。把里面的内容取出来
		store.put(hash, server);
		settleStore(hash,server);
		//其他的虚拟节点也是一样的操作
		for(int j=0;j<VIRTUAL_NUM;j++){
			Integer virtualHash=getHash(serverIp+"virtualNode"+j);
			settleStore(virtualHash,server);
			store.put(virtualHash, server);
		}
	}
	private static void settleStore(Integer hash,LinkedList<String> server){
		Integer lastKey = store.lastKey();
		
		for (Integer key : store.keySet()) {
			//找到hash比添加的这个大的服务器
			if(key>hash){
				//找到受影响的之后遍历 让比它小的从原来的移除加到它里面&&getHash(node)>lastKey)
				List<String> list=store.get(key);
				 Iterator<String>it=list.iterator();
				 while(it.hasNext()){
					String node =it.next();
					if(getHash(node)<hash&&getHash(node)>lastKey){
						System.out.println("@@@@@@@@");
						it.remove();
						if(list!=server){
						server.add(node);
						}
					}
				 }
				return;
			}
			lastKey=key;//记录上一个服务器的
		}
		//如果没有那么就是第一台
		//找到受影响的之后遍历 让比它小的从原来的移除加到它里面
		List<String> list=store.get(store.firstKey());
		for (String node : list) {
			if(getHash(node)<hash&&getHash(node)>store.lastKey()){
				list.remove(node);
				server.add(node);
			}
		}
	
	}
//节点名称的Hash值（其分布为[0, 2^32-1]）
	public static int getHash(String str) {
		final int p = 16777619;
		int hash = (int) 2166136261L;
		for (int i = 0; i < str.length(); i++)
			hash = (hash ^ str.charAt(i)) * p;
		hash += hash << 13;
		hash ^= hash >> 7;
		hash += hash << 3;
		hash ^= hash >> 17;
		hash += hash << 5;

		// 如果算出来的值为负数则取其绝对值
		if (hash < 0)
			hash = Math.abs(hash);
		return hash;
	}
	public static void main(String[] args) {
	 for(int i=0;i<100;i++){
		 ConsistentHash.addNode("第"+i+"个dsfsafvasvf");
	 }
		ConsistentHash.addServer("192.168.111.102:111");
		for(LinkedList<String> list:ConsistentHash.store.values()){
			System.out.println(list);
		}
	}
}
