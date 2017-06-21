package timeDepart;

import java.io.File;
import java.util.Scanner;

/**
 * 時刻表ファイルとやり取りするクラス
 * @author proglight
 */

public class TimeTable {

	//時刻表ファイルを読み込むメソッド
	public int timeload(TimeData td){
		int i = 0;
		try{
			//ファイルを読み込む

			//jarで実行の場合
			//Scanner sc = new Scanner(new File("./TrainTableSample.txt"));
			//eclipseで実行の場合
			Scanner sc = new Scanner(new File("./src/timeDepart/TrainTableSample.txt"));

			//終わりまで各データを読み込む
			while(sc.hasNextLine()==true){
				//TimeDataの各フィールドにデータを代入する
				td.setType(sc.next(),i);
				td.setTrain(sc.next(),i);
				td.setDhour(sc.nextInt(),i);
				td.setDmin(sc.nextInt(),i);
				td.setDest(sc.next(),i);
				i++;
			}
			sc.close();
		}
		catch(Exception e){	//読み込み時のエラー
			System.out.println("読み込みエラー："+e);
			System.exit(1);
		}
		finally{
			return i;	//読み込みデータの行数を返す
		}
	}
}
