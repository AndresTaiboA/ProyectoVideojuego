package puppy.code;

public class EstrategiaConcretaDañar implements Strategy{
	@Override
	public void tocar(Tarro tarro) {
		tarro.dañar();
	}
}
