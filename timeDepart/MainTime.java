
package timeDepart;

/**
 * 列車発車時刻表示アプリメインクラス
 * @author cook1293
 */

public class MainTime {

	//メインメソッド
	public static void main(String args[]){
		//列車時刻検索のフォームを起動する
		TimeForm frm = new TimeForm("広島駅 山陽本線 宮島口・岩国方面 (休日)");
		frm.setSize(300,200);
		frm.setVisible(true);
	}
}
