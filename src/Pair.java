
public class Pair<X,Y> {
	
	private X x;
	private Y y;
	
	public Pair(X x,Y y){
		this.x = x;
		this.y = y;
	}
	
	public X getX(){
		return this.x;
	}
	
	public Y getY(){
		return this.y;
	}
	
	public void setX(X x){
		this.x = x;
	}
	
	public void setY(Y y){
		this.y = y;
	}
	
	@Override
	public String toString(){
	 return "<" + x + "," + y + ">";
		
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Pair)){
			return false;
		}
		Pair<X,Y> that = (Pair<X, Y>)obj;
		return that.x == this.x && that.y == this.y;
	}
	
	@Override
	public int hashCode(){
		return 0;
	}

}
