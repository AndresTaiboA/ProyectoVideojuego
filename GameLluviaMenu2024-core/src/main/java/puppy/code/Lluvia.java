package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
	//private Array<Rectangle> rainDropsPos;
	//private Array<Integer> rainDropsType;
	private Array<Gota> gotas;
    private long lastDropTime;
    private Sound dropSound;
    private Music rainMusic;
    private FabricaGotasBuenas fabricaBuena = new FabricaGotasBuenas();
    private FabricaGotasMalas fabricaMala = new FabricaGotasMalas();
    private Aplicacion app;
    private Contexto context = new Contexto();
	
    public Lluvia(Sound ss, Music mm) {
		rainMusic = mm;
		dropSound = ss;
	}
	public void crear() {
		//rainDropsPos = new Array<Rectangle>();
		gotas = new Array<Gota>();
		//rainDropsType = new Array<Integer>();
		crearGotaDeLluvia();
	      // start the playback of the background music immediately
	      rainMusic.setLooping(true);
	      rainMusic.play();
	}

	private void crearGotaDeLluvia() {
	    //Rectangle raindrop = new Rectangle();
	    //rainDropsPos.add(raindrop);
	    // ver el tipo de gota
		//gotas se crean con su respectiva fábrica
	    if (MathUtils.random(1,10)<3) {
	    	//utilizar app 
	    	app = new Aplicacion(fabricaMala);
		    gotas.add(app.crear());
		    //utilizar la fabrica directamente
    		//gotas.add(fabricaMala.obtenerGota());
	    }
	    else {
	    	//utilizar la fabrica directamente
    		//gotas.add(fabricaBuena.obtenerGota());
	    	//utilizar app 
	    	app = new Aplicacion(fabricaBuena);
		    gotas.add(app.crear());
    	}
	    lastDropTime = TimeUtils.nanoTime();
	   }

   public boolean actualizarMovimiento(Tarro tarro) { 
	   // generar gotas de lluvia 
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();
	  

	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i=0; i < gotas.size; i++ ) {
		  Gota actual = gotas.get(i);
		  int y = actual.getY();

	      actual.actualizar(Gdx.graphics.getDeltaTime(), tarro.getArea());
	      //cae al suelo y se elimina
	      if(actual.getY() + 64 < 0) {
	    	  gotas.removeIndex(i); 
	      }
	      if(actual.getChoco()) { //la gota choca con el tarro
	    	if(gotas.get(i) instanceof GotaMala) { // gota dañina
	    	  context.setStrategy(new EstrategiaConcretaDañar());
	      	}else { // gota a recolectar
	    	  context.setStrategy(new EstrategiaConcretaSumarPuntos());
	          dropSound.play();
	      	}
	    	gotas.removeIndex(i);
	    	context.ejecutarStr(tarro);
	    	if (tarro.getVidas()<=0) {
	    		return false;
	    	}
	      }
	   }   
	   return true;
   }
   public void actualizarDibujoLluvia(SpriteBatch batch) { 
	   
	  for (int i=0; i < gotas.size; i++ ) {
		  (gotas.get(i)).dibujar(batch); 
	   }
   }
   public void destruir() {
	      dropSound.dispose();
	      rainMusic.dispose();
   }
   public void pausar() {
		  rainMusic.stop();
	   }
   public void continuar() {
		  rainMusic.play();
	   }

}
