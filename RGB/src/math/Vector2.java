package math;

public class Vector2 {
	private float x, y;
	
	public Vector2(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public void add(Vector2 operand){
		this.x += operand.getX();
		this.y += operand.getY();
	}
	
	public void scale(float scale){
		this.x *= scale;
		this.y *= scale;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void set(Vector2 setter){
		setter.setX(x);
		setter.setY(y);
	}
}
