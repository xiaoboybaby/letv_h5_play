import java.io.UnsupportedEncodingException;
import java.util.Date;

public class GetQQVTime {
	public static int getNum(String url) throws UnsupportedEncodingException {

		String result = Shuadan.sendGet(url, null);
		result = new String(result.getBytes(), "UTF-8");
		result = result
				.substring(
						result.indexOf("<em id=\"mod_cover_playnum\" class=\"num\">") + 39,
						result.indexOf("</em>´Î²¥"));
		return Integer.parseInt(result);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String url = "http://v.qq.com/x/page/w0197ov9ry9.html";
		System.out.println(new Date(1471162501));

		//System.out.println(new Date().getTime());
		int countOld = 0;
		int countNew = 0;
		while(true){
			int num = getNum(url);
			if(countOld != num){
				countOld = num;
				System.out.println();
				System.out.println("" + new Date()+ ":´ÎÊý"+ num);
			}
			System.out.print(".");
			try {
//				Random r = new Random();
//				r.nextInt(5000);
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
