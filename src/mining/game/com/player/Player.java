package mining.game.com.player;

import java.util.HashMap;
import java.util.Map;

import mining.game.com.R;
import android.widget.TextView;

public class Player {

	private Map<String, Integer> vault = new HashMap<String, Integer>();
	private Pickaxe pickaxe = Pickaxe.WOOD;
	private int money = 0;
	private int vaultSize = 0;

	public Player(Map<String, Integer> map, Pickaxe pickaxe, int money, int vaultSize) {
		this.vault.putAll(map);
		this.pickaxe = pickaxe;
		this.money = money;
		this.vaultSize = vaultSize;
	}
	
	public Map<String, Integer> getVault() {
		return vault;
	}

	public void setVault(Map<String, Integer> vault) {
		this.vault = vault;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public Pickaxe getPickAxe() {
		return pickaxe;
	}	
	
	public int getMoney() {
		return money;
	}

	public void setPickAxe(Pickaxe pickAxe) {
		this.pickaxe = pickAxe;
	}	

	public int getVaultSize() {
		return vaultSize;
	}

	public void setVaultSize(int vaultSize) {
		this.vaultSize = vaultSize;
	}
	
	public int getTotalVault() {
		int totalVault = 0;
    	for(int j : getVault().values()) {
    		totalVault += j;
    	}
    	return totalVault;
	}
	
}
