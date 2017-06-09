package czw.util;

public enum RoomType {
	Standard("Standard", 0), Double("Double", 1), Suite("Suite", 2);
	
	private String name;
	private int seq;
	
	private RoomType(String name, int seq){
		this.name = name;
		this.seq = seq;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getSeq(){
		return this.seq;
	}
}