package puppy.code;

public class Aplicacion {
	private Gota gota;
	
	public Aplicacion(FabricaGotas fabrica) {
		gota = fabrica.obtenerGota();
	}
	public Gota crear() {
		return gota;
	}
}
