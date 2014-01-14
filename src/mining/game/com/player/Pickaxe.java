package mining.game.com.player;

import mining.game.com.R;

public enum Pickaxe {
	
	WOOD 			(R.drawable.pickaxewood, "Wood Pickaxe", 7000, 1, 15, 100, 0, true),
	STONE 			(R.drawable.pickaxestone, "Stone Pickaxe", 4000, 2, 45, 70, 250, true),
	IRON 			(R.drawable.pickaxeiron, "Iron Pickaxe", 3000, 3, 100, 5, 1250, true),
	GOLD 			(R.drawable.pickaxegold, "Gold Pickaxe", 2000, 3, 250, 30, 12500, true),
	DIAMOND			(R.drawable.pickaxediamond, "Diam Pickaxe", 1000, 3, 450, 5, 20000, true),
	HEAVENLY 		(R.drawable.pickaxeheavenly, "Heavenly Pickaxe", 1000, 4, 1000, 1, 5000000, true),
	UNDERWORLD 		(R.drawable.pickaxehell, "Underworld Pickaxe", 1000, 5, 4000, 0, 28000000, false),
	ENDER 			(R.drawable.pickaxeender, "Ender Pickaxe", 1000, 6, 10000, 0, 500000000, false),
	FINAL 			(R.drawable.pickaxewood, "Final Pickaxe", 1000, 6, 20000, 0, 500000000000L, false),
	HELL_DRILL 		(R.drawable.drillhell, "Hell Drill", 1000, 5, 9000, 0, 30000000, false),
	MUCH_SOCK 		(R.drawable.muchsock, "Much Sock", 60000, 0, 2, 110, 0, false);
	
	private int filepath = 0;
	private int speed = 0;
	private int sharpness = 0;
	private int max = 0;
	private int dropChance = 0;
	private long price = 0;
	private boolean upgradeable = false;
	private String name = null;
	
	private Pickaxe(int filepath, String name, int speed, int sharpness,
					int max, int dropChance, long price, boolean upgradeable) {
		this.filepath = filepath;
		this.name = name;
		this.speed = speed;
		this.sharpness = sharpness;
		this.max = max;
		this.dropChance = dropChance;
		this.price = price;
		this.upgradeable = upgradeable;
	}
	
	public int getFilepath() {
		return filepath;
	}

	public int getSpeed() {
		return speed;
	}

	public int getSharpness() {
		return sharpness;
	}

	public int getMax() {
		return max;
	}

	public int getDropChance() {
		return dropChance;
	}

	public long getPrice() {
		return price;
	}

	public boolean isUpgradeable() {
		return upgradeable;
	}

	public String getName() {
		return name;
	}
	
}

