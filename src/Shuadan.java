import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Shuadan {

	private static final Exception Exception = null;

	/**
	 * ��ָ��URL����GET����������
	 * 
	 * @param url
	 *            ���������URL
	 * @param param
	 *            ����������������Ӧ����name1=value1&name2=value2����ʽ��
	 * @return URL������Զ����Դ����Ӧ
	 */

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url ;
			URL realUrl = new URL(urlName);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
//			conn.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
			conn.setRequestProperty("Accept-Language", "gzh-CN,zh;q=0.8");
//			conn.setRequestProperty("Cache-Control", "max-age?=0");
//			conn.setRequestProperty("connection", "Keep-Alive");
//			conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
//			conn.setRequestProperty("Cookie", "ark_uuid=8f58f0c890414436b046cc4d5e78d2be; sso_curr_country=CN; language=zh-cn; IPX_EX_0=1470496251417; ARK_IPX=221.218.64.183X221.218.64.183; IPX_EX_1=1470496251419");
//			conn.setRequestProperty("Host", "apple.www.letv.com");
			
			conn.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
			
			// ����ʵ�ʵ�����
			conn.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = conn.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ��ָ��URL����POST����������
	 * 
	 * @param url
	 *            ���������URL
	 * @param param
	 *            ����������������Ӧ����name1=value1&name2=value2����ʽ��
	 * @return URL������Զ����Դ����Ӧ
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = ""; 
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
			conn.setRequestProperty("Accept-Language", "gzh-CN,zh;q=0.8");
			conn.setRequestProperty("Cache-Control", "max-age=0");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			conn.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(param);
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "/n" + line;
			}
		} catch (Exception e) {
			System.out.println("����POST��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	// �ṩ�����������Է���GET�����POST����
	public static void main(String args[]) {
//		if (args.length != 2) {
//			System.out.println("��������,��������ʽΪ:Shuadan 50");
//			System.exit(0);
//		}
//		String url = "http://apple.www.letv.com/pl/?ver=3.0&ac=play&ut=1&ry=0&ap=1&p1=1&p2=10&p3=-&lc=1D14ABD40F796E2E8844655E676F9AFF55965A2A&uid=-&uuid=E857F06E2FC4EC56136E986C58880E62B4B779F7&auid=-&cid=30&pid=null&vid=24398149&vlen=6864&ch=letv&ty=0&url=http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F24398149.html&ref=-&pv=Letv5.6.6&st=-&ilu=1&pcode=-&ctime=1470476477049&vt=13&prg=0&py=ade%3D1470476477040%26ads%3D1470476429307&r=0.6080030435696244&key=14a6a2fa54edf54e2f4e2dadd64af22d";
		String url = ""; 
//		url = "http://videocenter-2039197532.cn-north-1.elb.amazonaws.com.cn/dynamicinfo?callback=jQuery18206831517284736037_1471710256124&vid=100003";
		Date date = new Date();
		url = "http://videocenter-2039197532.cn-north-1.elb.amazonaws.com.cn/dynamicinfo?callback=jQuery18207393017180729657_" + new Date().getTime() + "&cid=166388&_=" + new Date().getTime();
//		url = "http://videocenter-2039197532.cn-north-1.elb.amazonaws.com.cn/dynamicinfo?callback=jQuery18207393017180729657_1471713390042&vid=3340335";
		// ����GET����
		int count = 0;
		int threadCount = 0;
		try {
			threadCount = 1;
		} catch (java.lang.Exception e) {
			System.out.println("�ڶ�������ӦΪ��������!");
		}
		for (int i = 0; i < threadCount; i++) {
			new ClickThread(url, i + 1).start();
		}

		// ����POST����
		// String s1 = Shuadan.sendPost(http: //localhost:8888/abc/a.jsp ,
		// "user=���&pass=abc");
		// System.out.println(s1);
	}
}

class ClickThread extends Thread {
	String url;
	int threadId;
	long paste;
	long startTime;
	long endTime;
	int count;

	public ClickThread(String url, int threadId) {
		this.url = url;
		this.threadId = threadId;
	}

	@Override
	public void run() {
		super.run();
		long oldCount = 0;
		long currCount = 0;
		long increaseCount = 0;
		FileWriter fw= null;
		try {
			String home = System.getenv("HOME");
			 fw = new FileWriter(home + "/" + new Date().getTime() + ".log");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			url = "http://videocenter-2039197532.cn-north-1.elb.amazonaws.com.cn/dynamicinfo?callback=jQuery18207393017180729657_" + new Date().getTime() + "&cid=166388&_=" + new Date().getTime();
			count++;
			startTime = new Date().getTime();
			String s = Shuadan.sendGet(url, null);
//			System.out.println(s);
			String subString = s.substring(s.indexOf("\"all\":")+6,s.indexOf(",\"allStr\""));
			//System.out.println(subString);
			currCount = Long.parseLong(subString);
			
			System.out.print("��ǰҳ��:" + currCount);
			if(oldCount != 0){
				increaseCount = currCount - oldCount;
				System.out.print("��ǰ����:" + increaseCount);				
			}
			oldCount = currCount;
			endTime = new Date().getTime();
			paste = endTime - startTime;
			System.out.println("�̱߳��:" + threadId + "������ʱ:" + paste + "ms"
					+ "��ǰ�̵߳������:" + count);
			try {
				fw.write("" + new Date() + "\t" + currCount + "\t" + increaseCount + "\t" + paste + "ms\r\n");
				fw.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
