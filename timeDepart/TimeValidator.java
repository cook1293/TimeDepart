package timeDepart;

/**
 * バリデーション（入力チェック）クラス
 * @author cook1293
 */

public class TimeValidator {
	public int validate(String shour,String smin){

		int nhour,nmin;

		try{
			nhour = Integer.parseInt(shour);
			nmin = Integer.parseInt(smin);
		}
		catch(Exception e){
			System.out.println("入力エラー:"+e);
			return 1;
		}
		if(nhour<0 || nhour>23){
			return 1;
		}
		if(nmin<0 || nmin>59){
			return 1;
		}
		return 0;
	}
}
