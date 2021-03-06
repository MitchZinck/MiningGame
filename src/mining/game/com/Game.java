package mining.game.com;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import mining.game.com.player.Ore;
import mining.game.com.player.Pickaxe;
import mining.game.com.player.Player;
import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
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
	private boolean mining = false;
	private Pickaxe pickaxe = null;
	private Map<String, Integer> map;
	private ArrayList<Ore> oresToClear = new ArrayList<Ore>();
	private int totalVault = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();		
		
		loadResources();
		player = new Player(map, pickaxe, new BigInteger("0"), 250);
		setValues();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	
	public void mine(View v) {
		if(mining == true) {
			return;
		}
		
    	totalVault = player.getTotalVault();
    	
    	if(totalVault == player.getVaultSize()) {
    		TextView ztc = (TextView) findViewById(R.id.txtFullVault);
    		ztc.setVisibility(View.VISIBLE);
    		return;
    	}
    	
		mining = true;
		
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
		
		ImageView animationTarget = (ImageView) findViewById(R.id.Pickaxe);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_pickaxe);
        animation.setDuration(player.getPickaxe().getSpeed() / 2);
        animationTarget.startAnimation(animation);    
        
        animationTarget.postDelayed(new Runnable() {
            @Override
            public void run() {
        		int amount = player.getPickaxe().getMax();		
        		int mined = 0;
        		int totalWorth = 0;
        		boolean full = false;
        		
        		Random r = new Random();
        		
        		if(r.nextInt(100) <= player.getPickaxe().getDropChance()) {
        			int yyzzyy = (int) Math.floor(r.nextInt((int) (amount * 0.3)));       
        			amount = (yyzzyy > 50) ? 50 : amount - yyzzyy;      
        			TextView taz = (TextView) findViewById(R.id.txtLostOres);
        			taz.setText("Ores lost:" + Integer.toString(yyzzyy));
        		}
        	
        		for(Ore ore : Ore.values()) {
        			if(player.getPickaxe().getSharpness() >= ore.getHardNess()) {
        				mined = (int) Math.round(amount * ore.getProb());
        				
        				if(mined + totalVault >= player.getVaultSize()) {
        					mined = player.getVaultSize() - totalVault;
        					full = true;
        					TextView ztc = (TextView) findViewById(R.id.txtFullVault);
        		    		ztc.setVisibility(View.VISIBLE);
        				}
        				
        				int prev = player.getVault().get(ore.name()) + mined;
        				player.getVault().put(ore.name(), prev);
        				amount -= mined;
        				
        				oresToClear.add(ore);
        				
        				TextView tv = (TextView) findViewById(ore.getIdTxt());
        				tv.setText(mined + " x ");
        				
        				ImageView zz = (ImageView) findViewById(ore.getIdImg());
        				zz.setVisibility(View.VISIBLE); 
        				
        				TextView t = (TextView) findViewById(ore.getTxtVault());        				
        				t.setText(prev + "\n$" + prev * ore.getWorth());
        				
        				totalWorth += (prev * ore.getWorth());
        				
        				if(full == true) {
        					break;
        				}
        			}
        		}	
        		TextView ttz = (TextView) findViewById(R.id.txtTotalWorth);
        		ttz.setText("Total Worth\n$" + totalWorth);
        		mining = false;        		
        		
        		TextView vault = (TextView) findViewById(R.id.txtVault);
        		vault.setText(Integer.toString(player.getTotalVault()) + "/" + Integer.toString(player.getVaultSize()));
            }
        }, player.getPickaxe().getSpeed());
        		
	}
	
	public void buyUpgrade1(View v) {
		TextView tsd = (TextView) findViewById(R.id.upgTxtName1);
		String s = (String) tsd.getText();
		if(s.contains("axe") && player.pickUpgrade()) {
			player.setPickAxe(Pickaxe.values()[player.getPickaxe().ordinal() + 1]);
			player.setMoney(BigInteger.valueOf(player.getMoney().intValue() - player.getPickaxe().getPrice()));
			TextView txtMoney = (TextView) findViewById(R.id.txtMoney);
			txtMoney.setText("$" + Integer.toString(player.getMoney().intValue()));
			tsd.setText(player.getPickaxe().name());
			TextView upg = (TextView) findViewById(R.id.upgPrice1);
			upg.setText("$" + Long.toString(Pickaxe.values()[player.getPickaxe().ordinal() + 1].getPrice()));
			ImageView im = (ImageView) findViewById(R.id.upgImg1);
			im.setImageResource(Pickaxe.values()[player.getPickaxe().ordinal() + 1].getFilepath());
			ImageView imz = (ImageView) findViewById(R.id.Pickaxe);
			imz.setImageResource(player.getPickaxe().getFilepath());
			if(!player.pickUpgrade()) {
				im.setBackgroundColor(getResources().getColor(R.color.red));
			}
		}
	}
	
	public void changeView(View v) {
		//ViewStub stub = (ViewStub) findViewById(R.id.viewInteract);
		View b = (View) findViewById(R.id.includeMine);
			
		if(b.getVisibility() == View.VISIBLE &&  v.getId() != R.id.buttonMine) {
			b.setVisibility(View.INVISIBLE);
		}
		
        switch (v.getId()) {
        
        case R.id.buttonVault:
        	findViewById(R.id.includeVault).setVisibility(View.VISIBLE);
        	findViewById(R.id.includeUpgrades).setVisibility(View.INVISIBLE);
			break;
			
        case R.id.buttonMine:
        	findViewById(R.id.includeVault).setVisibility(View.INVISIBLE);
        	findViewById(R.id.includeUpgrades).setVisibility(View.INVISIBLE);
        	b.setVisibility(View.VISIBLE);
        	break;
        case R.id.buttonUpgrades:
        	findViewById(R.id.includeVault).setVisibility(View.INVISIBLE);
        	findViewById(R.id.includeUpgrades).setVisibility(View.VISIBLE);
        	if(player.pickUpgrade()) {
        		findViewById(R.id.upgImg1).setBackgroundColor(getResources().getColor(R.color.green));
        	}
        	break;
        }
	}
	
	public void setValues() {
		TextView tv = (TextView) findViewById(R.id.textViewMaxOres);
		tv.setText("MaxOres: " + Integer.toString(player.getPickaxe().getMax()));
		tv = (TextView) findViewById(R.id.textViewSharp);	
		tv.setText("Sharpness: " + Integer.toString(player.getPickaxe().getSharpness()));
		tv = (TextView) findViewById(R.id.textViewDrop);	
		tv.setText("Drop Chance: " + Integer.toString(player.getPickaxe().getDropChance()));
		tv = (TextView) findViewById(R.id.textViewSpeed);	
		tv.setText("Speed: " + Integer.toString(player.getPickaxe().getSpeed()));
	}
	
	public void sellOre(View v) {
		int amount = 0;
		
		for(Entry<String, Integer> i : player.getVault().entrySet()) {
			amount += Ore.valueOf(i.getKey()).getWorth() * i.getValue();
		}		
		
		player.getVault().clear();
		player.getVault().put("STONE", 0);
		player.getVault().put("COAL", 0);
		player.getVault().put("MOSSYCOBBLE", 0);
		player.getVault().put("IRON", 0);
		player.getVault().put("GOLD", 0);
		player.getVault().put("DIAMOND", 0);
		player.getVault().put("GLOWSTONE", 0);
		player.getVault().put("NETHERQUARTZ", 0);
		player.getVault().put("ENDORE", 0);
		
		for(Ore ore : Ore.values()) {
			TextView t = (TextView) findViewById(ore.getTxtVault());        				
			t.setText("0\n$0");
		}
		
		TextView total = (TextView) findViewById(R.id.txtTotalWorth);
		total.setText("Total Worth\n$0");
		
		player.setMoney(BigInteger.valueOf(amount + player.getMoney().intValue()));
		TextView tv = (TextView) findViewById(R.id.txtMoney);
		tv.setText("$" + Integer.toString(player.getMoney().intValue()));
		
		TextView ztc = (TextView) findViewById(R.id.txtFullVault);
		ztc.setVisibility(View.INVISIBLE);
		
		TextView vault = (TextView) findViewById(R.id.txtVault);
		vault.setText("0/" + Integer.toString(player.getVaultSize()));
	}
	

//		case R.id.imgCoal:
//			if(findViewById(R.id.vltCoal).getVisibility() == View.VISIBLE) {
//				AlphaAnimation alpha = new AlphaAnimation(0.1F, 1F); 
//				alpha.setDuration(500);
//				alpha.setFillAfter(true);
//			} else {
//				AlphaAnimation alpha = new AlphaAnimation(1F, 0.1F); 
//				alpha.setDuration(500);
//				alpha.setFillAfter(true);
//			}
//			break;

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
