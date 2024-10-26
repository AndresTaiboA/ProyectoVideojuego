package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
	//private Array<Rectangle> rainDropsPos;
	//private Array<Integer> rainDropsType;
	private Array<Gota> gotas;
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Sound dropSound;
    private Music rainMusic;
	   
	public Lluvia(Texture gotaBuena, Texture gotaMala, Sound ss, Music mm) {
		rainMusic = mm;
		dropSound = ss;
		this.gotaBuena = gotaBuena;
		this.gotaMala = gotaMala;
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
	    if (MathUtils.random(1,10)<3) {
	    	GotaMala aux = new GotaMala(MathUtils.random(0, 800-64), 480, 64, 64);
		    gotas.add(aux);
	    }
	    else {
	    	GotaBuena aux = new GotaBuena(MathUtils.random(0, 800-64), 480, 64, 64);
    		gotas.add(aux);
    	}
	    lastDropTime = TimeUtils.nanoTime();
	   }
	
   public void actualizarMovimiento(Tarro tarro) { 
	   // generar gotas de lluvia 
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();

	   
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i=0; i < gotas.size; i++ ) {
		  Gota actual = gotas.get(i);
		  int y = actual.getY();
		  y -= 300 * Gdx.graphics.getDeltaTime();
	      actual.setY(y);
	      //cae al suelo y se elimina
	      if(y + 64 < 0) {
	    	  gotas.removeIndex(i); 
	      }
	      if(actual.colisiona(tarro.getArea())) { //la gota choca con el tarro
	    	if(gotas.get(i) instanceof GotaMala) { // gota dañina
	    	  tarro.dañar();
	    	  gotas.removeIndex(i);
	      	}else { // gota a recolectar
	    	  tarro.sumarPuntos(10);
	          dropSound.play();
	          gotas.removeIndex(i);
	      	}
	      }
	   }   
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
   
}
