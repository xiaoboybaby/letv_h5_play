import java.util.Random;

public class Test extends Thread{
	public static void main(String[] args) throws InterruptedException {
//		int max=20;
//        int min=10;
//        Random random = new Random();
//
//        String s = null;
//        while(true){
//        	int i = random.nextInt(16);
//        	
//        	//Integer i = new Integer(s);
//        	s = Integer.toHexString(i);
//            System.out.print(s);
//            Thread.sleep(100);
//        }
		
		
		//System.out.println(p.py);
		
		
		for(int i = 0; i < 20;i ++){
			Test t = new Test();
			t.start();
		}
		
	}
	
	public void startRun(){
		String surl = "http://apple-www.le.com/pl/";
		String url = "";
		Parameters p = new Parameters("http://m.le.com/vplay_29982549.html");
		p.init();
		url = surl + "?" + p.toHttpParam();
		HttpHelper.sendGet(url, null);
		
		p.gslb();
		url = surl + "?" + p.toHttpParam();
		HttpHelper.sendGet(url, null);
		p.cload();
		url = surl + "?" + p.toHttpParam();
		HttpHelper.sendGet(url, null);
		p.gslb2();
		url = surl + "?" + p.toHttpParam();
		HttpHelper.sendGet(url, null);
		p.cload();
		url = surl + "?" + p.toHttpParam();
		HttpHelper.sendGet(url, null);
		p.block();
		url = surl + "?" + p.toHttpParam();
		HttpHelper.sendGet(url, null);
		p.play();
		url = surl + "?" + p.toHttpParam();
		HttpHelper.sendGet(url, null);
		
		for(int i = 0;i < 2;i++){
			try {
				Thread.sleep(1000 * 120);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.time();
			url = surl + "?" + p.toHttpParam();
			HttpHelper.sendGet(url, null);	
		}
		
		
		p.end();
		url = surl + "?" + p.toHttpParam();
		HttpHelper.sendGet(url, null);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			startRun();	
		}
		
	}
}
