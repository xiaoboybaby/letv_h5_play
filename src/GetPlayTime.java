import java.util.Date;

public class GetPlayTime {
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			String url = "http://v.stat.letv.com/vplay/getIdsInfo?type=vlist&ids=29982549&callback=jQuery17106766484423258297_"
					+ new Date().getTime() + "&_=" + new Date().getTime();
			System.out.println(HttpHelper.sendGet(url, null));
			Thread.sleep(1000);
		}
	}
}
