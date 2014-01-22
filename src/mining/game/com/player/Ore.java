package mining.game.com.player;

import mining.game.com.*;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

public enum Ore {

	ENDORE 			(R.drawable.endore, 6, 7000, 0.01, R.id.txtEndore, R.id.imgEndore, R.id.vltEndore),
	NETHERQUARTZ 	(R.drawable.netherquartz, 5, 3250, 0.03, R.id.txtNether, R.id.Netherquartz, R.id.vltNether),
	GLOWSTONE 		(R.drawable.glowstone, 4, 2250, 0.1, R.id.txtGlowStone, R.id.Glowstone, R.id.vltGlow),
	DIAMOND 		(R.drawable.diamond, 3, 240, 0.05, R.id.txtDiamond, R.id.Diamond, R.id.vltDiamond),
	GOLD 			(R.drawable.gold, 3, 120, 0.08, R.id.txtGold, R.id.Gold, R.id.vltGold),
	IRON 			(R.drawable.iron, 2, 30, 0.12, R.id.txtIron, R.id.Iron, R.id.vltEndore),
	MOSSYCOBBLE 	(R.drawable.mossycobble, 1, 7, 0.23, R.id.txtMossyCobble, R.id.Mossycobble, R.id.vltMossy),
	COAL 			(R.drawable.coal, 1, 3, 0.30, R.id.txtCoal, R.id.Coal, R.id.vltCoal),
	STONE 			(R.drawable.stone, 1, 1, 1, R.id.txtStone, R.id.Stone, R.id.vltStone);
	
	private int filepath = 0;
	private int hardness = 0;
	private int worth = 0;
	private double prob = 0;
	private int idImg = 0;
	private int idTxt = 0;
	private int txtVault = 0;
	
	private Ore(int filepath, int hardness, int worth, double prob, int idTxt, int idImg, int txtVault) {
		this.filepath = filepath;
		this.hardness = hardness;
		this.worth = worth;
		this.prob = prob;
		this.idTxt = idTxt;
		this.idImg = idImg;
		this.txtVault = txtVault;
	}
	
	public int getFilePath() {
		return filepath;
	}

	public int getHardNess() {
		return hardness;
	}

	public int getWorth() {
		return worth;
	}

	public double getProb() {
		return prob;
	}

	public int getIdTxt() {
		return idTxt;
	}
	
	public int getIdImg() {
		return idImg;
	}	
	
	public int getTxtVault() {
		return txtVault;
	}

}
