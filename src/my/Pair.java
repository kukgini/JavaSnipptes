package my;

public class Pair<K,V> {
	private K k;
	private V v;
	
	public Pair() {
		
	}
	public Pair(K k, V v) {
		this.k = k;
		this.v = v;
	}
	public void setKey(K k) {
		this.k = k;
	}
	public void setVal(V v) {
		this.v = v;
	}
	public K getKey() {
		return this.k;
	}
	public V getVal() {
		return this.v;
	}
}
