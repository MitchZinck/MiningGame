package mining.game.com.player;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import mining.game.com.R;
import android.widget.TextView;

public class Player {

	private Map<String, Integer> vault = new HashMap<String, Integer>();
	private Pickaxe pickaxe = Pickaxe.WOOD;
	private BigInteger money = new BigInteger("0");
	private int vaultSize = 0;

	public Player(Map<String, Integer> map, Pickaxe pickaxe, BigInteger money, int vaultSize) {
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

	public void setMoney(BigInteger money) {
		this.money = money;
	}
	
	public Pickaxe getPickaxe() {
		return pickaxe;
	}	
	
	public BigInteger getMoney() {
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

	public boolean pickUpgrade() {
		if(getPickaxe().isUpgradeable() && getMoney().intValue() >= Pickaxe.values()[getPickaxe().ordinal() + 1].getPrice()) {
			return true;
		}
		return false;
	}
	
}
