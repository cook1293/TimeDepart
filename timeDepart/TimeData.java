package timeDepart;

/**
 * 時刻表データ（BI⇔DL）を保持するクラス
 * @author proglight
 */

public class TimeData {

	private String[] type = new String[1000];	//列車種別
	private String[] train = new String[1000];	//路線名
	private int[] dhour = new int[1000];		//発車時
	private int[] dmin = new int[1000];			//発車分
	private String[] dest = new String[1000];	//行先


	//各フィールドのゲッター及びセッター
	public String getType(int i) {
		return type[i];
	}
	public void setType(String type,int i) {
		this.type[i] = type;
	}

	public String getTrain(int i) {
		return train[i];
	}
	public void setTrain(String train,int i) {
		this.train[i] = train;
	}

	public int getDhour(int i) {
		return dhour[i];
	}
	public void setDhour(int dhour,int i) {
		this.dhour[i] = dhour;
	}

	public int getDmin(int i) {
		return dmin[i];
	}
	public void setDmin(int dmin,int i) {
		this.dmin[i] = dmin;
	}

	public String getDest(int i) {
		return dest[i];
	}
	public void setDest(String dest,int i) {
		this.dest[i] = dest;
	}

	//各フィールドの確認用メソッド
	public String toString(int i){
		return type[i] + train[i] + dhour[i] + dmin[i] + dest[i];
	}
}
