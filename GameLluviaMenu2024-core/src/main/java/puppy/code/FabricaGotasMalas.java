package puppy.code;

import com.badlogic.gdx.math.MathUtils;

public class FabricaGotasMalas implements FabricaGotas{

	@Override
	public Gota obtenerGota() {
		return new GotaMala(MathUtils.random(0, 800-64), 480, 64, 64);
	}
}
