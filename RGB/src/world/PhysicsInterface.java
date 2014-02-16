package world;

import java.awt.Rectangle;

public interface PhysicsInterface {
	public boolean resolveCollisions(PhysicsInterface physicsInterface);
	public Rectangle getHitbox();
}
