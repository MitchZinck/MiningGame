package mining.game.com.player;

import java.util.HashMap;
import java.util.Map;

public class Player {

	private Map<String, Integer> vault = new HashMap<String, Integer>();
	private Pickaxe pickaxe = Pickaxe.WOOD;
	
	public Player(Map<String, Integer> map, Pickaxe pickaxe) {
		this.vault.putAll(map);
		this.pickaxe = pickaxe;
	}
	
	public Map<String, Integer> getVault() {
		return vault;
	}

	public void setVault(Map<String, Integer> vault) {
		this.vault = vault;
	}

	public Pickaxe getPickAxe() {
		return pickaxe;
	}

	public void setPickAxe(Pickaxe pickAxe) {
		this.pickaxe = pickAxe;
	}
	
}
