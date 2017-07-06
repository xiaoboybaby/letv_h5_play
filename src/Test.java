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
		
		
		for(int i = 0; i < 1;i ++){
			Test t = new Test();
			t.start();
		}
		
	}
	
	public void startRun(){
		GeneralParam gp = new GeneralParam("http://m.le.com/vplay_29982549.html");
		Ppl p = new Ppl(gp);
		HttpHelper.sendGet(p.init().getUrl(), null);
		HttpHelper.sendGet(p.gslb().getUrl(), null);
		HttpHelper.sendGet(p.cload().getUrl(), null);
//		HttpHelper.sendGet(p.gslb2().getUrl(), null);
//		HttpHelper.sendGet(p.cload().getUrl(), null);
		HttpHelper.sendGet(p.block().getUrl(), null);
		HttpHelper.sendGet(p.play().getUrl(), null);		
		for(int i = 0;i < 2;i++){
			try {
				Thread.sleep(1000 * 120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			HttpHelper.sendGet(p.time().getUrl(), null);	
		}
		HttpHelper.sendGet(p.end().getUrl(), null);
	}

	@Override
	public void run() {
		while(true){
			startRun();	
		}
		
	}
}
