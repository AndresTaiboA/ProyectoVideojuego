package puppy.code;

public class EstrategiaConcretaSumarPuntos implements Strategy{

	@Override
	public void tocar(Tarro tarro) {
		tarro.sumarPuntos(10);
	}
}
