package timeDepart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * フォームを起動するクラス
 * @author cook1293
 */

public class TimeForm extends JFrame{

	//各インスタンスの宣言
	MyPanel pn = new MyPanel();
	TimeDTO tdto = new TimeDTO();
	TimeLogic tl = new TimeLogic();
	TimeValidator tv = new TimeValidator();
	Calendar now = Calendar.getInstance();

	//各部品の宣言
	JLabel lb1,lb2;
	JTextField txh,txm;
	JButton btn;

	//各変数の宣言
	String nhour,nmin;
	int ret,val = 0;

	//フォームを起動するメソッド
	public TimeForm(String title){
		setTitle(title);							//タイトル
		setDefaultCloseOperation(EXIT_ON_CLOSE);	//閉じる操作

		//レイアウトを絶対座標にする
		setLayout(null);
		pn.setBounds(0,0,250,100);
		add(pn);

		//現在時刻の取得
		nhour = Integer.toString(now.get(now.HOUR_OF_DAY));
		nmin = Integer.toString(now.get(now.MINUTE));

		//検索の実行と、検索件数の取得
		ret = tl.timesearch(nhour,nmin,tdto);

		//アクションリスナーの宣言
		MyActionListener als = new MyActionListener();

		//時刻ラベルの設定
		lb1 = new JLabel("時刻");
		lb1.setBounds(10,120,40,25);
		add(lb1);
		lb2 = new JLabel(":");
		lb2.setBounds(110,120,20,25);
		add(lb2);

		//時刻入力用テキストボックスの設定
		txh = new JTextField(nhour);
		txh.setBounds(60,120,40,25);
		add(txh);
		txm = new JTextField(nmin);
		txm.setBounds(120,120,40,25);
		add(txm);

		//表示ボタンの設定
		btn = new JButton("表示");
		btn.addActionListener(als);
		btn.setBounds(180,120,60,25);
		add(btn);

	}

	//パネルクラス
	class MyPanel extends JPanel{

		//描画メソッド
		public void paintComponent(Graphics g){
			super.paintComponent(g);

			//背景の電光掲示板
			g.setColor(Color.black);
			g.fillRect(5,10,210,50);
			//フォント設定
			g.setFont(new Font("SansSerif",Font.PLAIN,14));

			//各時刻データの表示
			if(val == 0){		//チェックに合格ならば
				for(int i=0;i<2;i++){
					if(ret > 0){	//検索結果が成功なら

						//列車種別の表示・色の分類
						if(tdto.getType(i).equals("普通")){
							g.setColor(Color.green);
						} else {
							g.setColor(Color.orange);
						}
						g.drawString(tdto.getType(i),10,30+i*20);
						g.setColor(Color.green);
						g.drawString(tdto.getTrain(i),50,30+i*20);

						//発車時刻の表示
						g.setColor(Color.red);
						if(Integer.parseInt(tdto.getDhour(i))/10 == 0){
							g.drawString("  "+tdto.getDhour(i),110,30+i*20);//一桁の場合、右詰にする
						} else {
							g.drawString(tdto.getDhour(i),110,30+i*20);
						}
						g.drawString(":",130,30+i*20);
						if(Integer.parseInt(tdto.getDmin(i))/10 == 0){
							g.drawString("0"+tdto.getDmin(i),135,30+i*20);	//一桁の場合、0を表示する
						} else {
							g.drawString(tdto.getDmin(i),135,30+i*20);
						}

						//行先の表示
						g.setColor(Color.green);
						g.drawString(tdto.getDest(i),165,30+i*20);

						//1件のみならば、ここで終了
						if(ret < 2){
							break;
						}
					} else {	//検索結果が失敗なら、運行終了を表示して終了
						g.setColor(Color.orange);
						g.drawString("本日の運行は終了しました。",10,30);
						break;
					}
				}
			} else {			//チェックが不合格ならば、注意書きを表示
				g.setColor(Color.red);
				g.drawString("正しい値を入力してください。",10,30);
			}

		}
	}

	//アクションリスナークラス
	public class MyActionListener implements ActionListener{

		//アクションイベントメソッド
		public void actionPerformed(ActionEvent ae){

			//ボタンが押されたイベントの動作
			if(ae.getSource() == btn){
				//時刻の取得
				nhour = txh.getText();
				nmin = txm.getText();
				//TimeValidatorによる入力値チェック
				val = tv.validate(nhour, nmin);

				//チェックが合格ならば、検索処理を実行
				if(val == 0){
					ret = tl.timesearch(nhour,nmin,tdto);
				}
				//再描画
				repaint();
			}

		}
	}
}

