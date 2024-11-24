package puppy.code;

public class Contexto {
	private Strategy estrategia;
	
	public void setStrategy(Strategy str) {
		this.estrategia = str;
	}
	public void ejecutarStr(Tarro tarro) {
		estrategia.tocar(tarro);
	}
}
