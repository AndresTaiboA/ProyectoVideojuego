package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Gota {
	private Texture sprite;
	protected Rectangle dimensiones;
	public Gota(int posX, int posY, int ancho, int alto, Texture textura) {
		dimensiones = new Rectangle();
		dimensiones.x = posX;
		dimensiones.y = posY;
		dimensiones.width = ancho;
		dimensiones.height = alto;
		sprite = textura;
	}
	public abstract boolean colisiona(Rectangle rec);
	public abstract int getY();
	public abstract int getX();
	public abstract void setY(int y);
	public abstract void dibujar(SpriteBatch batch);
}
