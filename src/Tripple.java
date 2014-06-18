
public class Tripple<X, Y, Z> {
	
	private X x;
	private Y y;
	private Z z;
	
	public Tripple(X x,Y y, Z z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public X getX() {
		return x; 
	}
	
	public Y getY(){
		return y;
	}
	
	public Z getZ(){
		return z;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Tripple)){
			return false;
		}
		Tripple<X,Y,Z> that = (Tripple<X, Y, Z>)obj;
		return that.x == this.x && that.y == this.y && that.z == this.z;
	}
	
	@Override
	public int hashCode(){
		return 0;
	}
	
	@Override
	public String toString(){
	 return "<" + x + "," + y + "," + z + ">";
		
	}
	
}
