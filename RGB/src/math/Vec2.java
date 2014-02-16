package math;

public class Vec2 {
	private float x, y;
	
	public Vec2(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public void add(Vec2 operand){
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
	
	public void set(Vec2 setter){
		setter.setX(x);
		setter.setY(y);
	}
}
