package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GotaBuena extends Gota{
	private static final Texture textura = new Texture(Gdx.files.internal("drop.png"));
	public GotaBuena(int x, int y, int ancho, int alto){
		super(x,y,ancho,alto, textura);
	}
	@Override
	public boolean colisiona(Rectangle rec) {
		return dimensiones.overlaps(rec);
	}
	@Override
	public int getY() {
		return (int) dimensiones.y;
	}
	public int getX() {
		return (int) dimensiones.x;
	}
	@Override
	public void setY(int y) {
		dimensiones.y = y;
	}
	@Override
	public void dibujar(SpriteBatch batch) {
		batch.draw(textura, dimensiones.x, dimensiones.y);
	}
}
