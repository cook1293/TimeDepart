package timeDepart;

/**
 * 列車時刻の検索・計算処理をするクラス
 * @author proglight
 */

public class TimeLogic {
	int max;			//読み込みデータの全行数
	int nhour,nmin;		//検索元時刻

	//列車の時刻検索をするメソッド
	public int timesearch(String shour,String smin,TimeDTO tdto){
		//各インスタンスの宣言
		TimeTable tt = new TimeTable();
		TimeData td = new TimeData();

		//ファイルからデータを読み込み、全行数を取得する
		max = tt.timeload(td);
		//検索の基となる時刻を取得する
		nhour = Integer.parseInt(shour);
		nmin = Integer.parseInt(smin);

		int flg = 0;	//検索処理のフラグ
		int num = 0;	//データの添え字

		while(flg == 0){
			if(td.getDhour(num)==nhour && td.getDmin(num)>=nmin){	//時が同じで、最も近い分であれば
				flg = 1;											//検索終了
			} else if(td.getDhour(num) > nhour){					//上記でなく、時が大きくて、最も近い分であれば
				flg = 1;											//検索終了
			} else {
				if(num < max){		//上記でなく、全行数まで達していなければ
					num++;			//添え字更新
				} else {			//全行数達していれば
					flg = 1;		//検索終了
				}
			}
		}

		if(num < max){		//添え字が全行数以下ならば
			//TimeDTOに各データを代入
			tdto.setType(td.getType(num),0);
			tdto.setTrain(td.getTrain(num),0);
			tdto.setDhour(Integer.toString(td.getDhour(num)),0);
			tdto.setDmin(Integer.toString(td.getDmin(num)),0);
			tdto.setDest(td.getDest(num),0);
		} else {
			return 0;		//添え字が全行数超えなら、検索失敗
		}
		num++;				//2番目に近い時刻データも、1番目と同様に保存
		if(num < max){
			tdto.setType(td.getType(num),1);
			tdto.setTrain(td.getTrain(num),1);
			tdto.setDhour(Integer.toString(td.getDhour(num)),1);
			tdto.setDmin(Integer.toString(td.getDmin(num)),1);
			tdto.setDest(td.getDest(num),1);
		} else {
			return 1;		//添え字が全行数超えなら、検索は1件のみ成功
		}
		return 2;			//最後まで行けば、検索は2件とも成功
	}

}
