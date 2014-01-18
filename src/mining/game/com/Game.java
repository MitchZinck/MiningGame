package mining.game.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mining.game.com.player.Ore;
import mining.game.com.player.Pickaxe;
import mining.game.com.player.Player;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Game extends Activity {

	private RelativeLayout mine;
	private Player player;
	private Pickaxe pickaxe = null;
	private Map<String, Integer> map;
	private ArrayList<Ore> oresToClear = new ArrayList<Ore>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();		
		
		loadResources();
		player = new Player(map, pickaxe);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	
	public void mine(View v) {		
		for(Ore i : oresToClear) {
			TextView t = (TextView) findViewById(i.getIdTxt());
			t.setText("");
			ImageView im = (ImageView) findViewById(i.getIdImg());
			im.setVisibility(View.INVISIBLE);
		}
		
		oresToClear.clear();
		
//		for(Map.Entry<String, Integer> map : player.getVault().entrySet()) {
//			Log.i(map.getKey(), Integer.toString(map.getValue()));
//		}
		
		ImageView animationTarget = (ImageView) findViewById(R.id.PickAxe);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_pickaxe);
        animation.setDuration(player.getPickAxe().getSpeed() / 2);
        animationTarget.startAnimation(animation);    
        
        animationTarget.postDelayed(new Runnable() {
            @Override
            public void run() {
        		int amount = player.getPickAxe().getMax();		
        		int mined = 0;
        		
        		for(Ore ore : Ore.values()) {
        			if(player.getPickAxe().getSharpness() >= ore.getHardNess()) {
        				mined = (int) Math.round(amount * ore.getProb());
        				player.getVault().put(ore.name(), mined);
        				amount -= mined;
        				
        				oresToClear.add(ore);
        				
        				TextView tv = (TextView) findViewById(ore.getIdTxt());
        				tv.setText(mined + " x ");
        				
        				ImageView zz = (ImageView) findViewById(ore.getIdImg());
        				zz.setVisibility(View.VISIBLE);        				
        			}
        		}	
        		
        		
            }
        }, player.getPickAxe().getSpeed());
        		
	}
	
	public void changeView(View v) {
		//ViewStub stub = (ViewStub) findViewById(R.id.viewInteract);
		View b = (View) findViewById(R.id.includeMine);
			
		if(b.getVisibility() == View.VISIBLE &&  v.getId() != R.id.buttonMine) {
			b.setVisibility(View.INVISIBLE);
		}
		
        switch (v.getId()) {
        
        case R.id.buttonVault:
//        	if(stub.getLayoutResource() != R.layout.vault) {
//				stub.setLayoutResource(R.layout.vault);
//				View inflated = stub.inflate();
//        	}
        	findViewById(R.id.includeVault).setVisibility(View.VISIBLE);
			break;
			
        case R.id.buttonMine:
//        	findViewById(R.id.viewInteract).setVisibility(View.GONE);
        	findViewById(R.id.includeVault).setVisibility(View.INVISIBLE);
        	b.setVisibility(View.VISIBLE);
        	break;
        }
	}

	private void loadResources() {
		setMine((RelativeLayout) findViewById(R.id.includeMine));
		loadSave();
	}
	
	private void loadSave() {
		map = new HashMap<String, Integer>();
		map.put("STONE", 0);
		map.put("COAL", 0);
		map.put("MOSSYCOBBLE", 0);
		map.put("IRON", 0);
		map.put("GOLD", 0);
		map.put("DIAMOND", 0);
		map.put("GLOWSTONE", 0);
		map.put("NETHERQUARTZ", 0);
		map.put("ENDORE", 0);
		pickaxe = Pickaxe.WOOD;
	}

	private void setMine(RelativeLayout rl) {
		this.mine = rl;
	}
	
}
