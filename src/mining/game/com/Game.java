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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Game extends Activity {

	private RelativeLayout mine;
	private Player player;
	private Pickaxe pickaxe = null;
	private Map<String, Integer> map;
	private ArrayList<Integer> oresToClear = new ArrayList<Integer>();

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
		for(int i : oresToClear) {
			TextView t = (TextView) findViewById(i);
			t.setText("");
		}
		
		oresToClear.clear();
		
//		for(Map.Entry<String, Integer> map : player.getVault().entrySet()) {
//			Log.i(map.getKey(), Integer.toString(map.getValue()));
//		}
		
		ImageView animationTarget = (ImageView) findViewById(R.id.PickAxe);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_pickaxe);
        animation.setDuration(player.getPickAxe().getSpeed());
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
        				
        				oresToClear.add(ore.getId());
        				
        				TextView tv = (TextView) findViewById(ore.getId());
        				tv.setText(mined + " x ");
        				
        			}
        		}	
            }
        }, player.getPickAxe().getSpeed() * 2);
        		
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
