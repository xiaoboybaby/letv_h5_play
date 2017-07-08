import java.util.Date;
import java.util.Map;
import java.util.Random;

public class Ppl extends SuperP {
	// 通用参数
	public GeneralParam gp = null;
	private static final String SECRET_KEY = "tYt2bxik";
	private static final int INIT = 1;
	private static final int GSLB = 2;
	private static final int GSLB2 = 3;
	private static final int CLOAD = 4;
	private static final int BLOCK = 5;
	private static final int PLAY = 6;
	private static final int TIME = 7;
	private static final int END = 8;
	String[] paramsInit = { "ac", "py", "ver", "p1", "p2", "p3", "ty", "uid", "lc", "uuid", "cid", "pid", "vid", "lid",
			"st", "sid", "vlen", "ch", "url", "weid", "ref", "pv", "ilu", "ctime", "r", "key", "app_name", "nt", "ipt",
			"pt", "ut", "ry", "vt", "cdev", "caid" };
	String[] paramsGslb = { "ac", "py", "ver", "p1", "p2", "p3", "ty", "uid", "lc", "uuid", "cid", "pid", "vid", "lid",
			"st", "sid", "vlen", "ch", "url", "weid", "ref", "pv", "ilu", "ctime", "r", "key", "app_name", "nt", "ipt",
			"pt", "ut", "ry", "vt" };
	String[] paramsCload = { "ac", "py", "ver", "p1", "p2", "p3", "ty", "uid", "lc", "uuid", "cid", "pid", "vid", "lid",
			"st", "sid", "vlen", "ch", "url", "weid", "ref", "pv", "ilu", "ctime", "r", "key", "app_name", "nt", "ipt",
			"pt", "ut", "ry", "vt" };
	String[] paramsBlock = { "ac", "py", "ver", "p1", "p2", "p3", "ty", "uid", "lc", "uuid", "cid", "pid", "vid", "lid",
			"st", "sid", "vlen", "ch", "url", "weid", "ref", "pv", "ilu", "ctime", "r", "key", "app_name", "nt", "ipt",
			"pt", "ut", "ry", "vt" };
	String[] paramsPlay = { "ac", "py", "ver", "p1", "p2", "p3", "ty", "uid", "lc", "uuid", "cid", "pid", "vid", "lid",
			"st", "sid", "vlen", "ch", "url", "weid", "ref", "pv", "ilu", "ctime", "r", "key", "app_name", "nt", "ipt",
			"pt", "ut", "ry", "vt", "prl", "pay", "joint" };
	String[] paramsTime = { "ac", "py", "ver", "p1", "p2", "p3", "ty", "uid", "lc", "uuid", "cid", "pid", "vid", "lid",
			"st", "sid", "vlen", "ch", "url", "weid", "ref", "pv", "ilu", "ctime", "r", "key", "app_name", "nt", "ipt",
			"pt", "ut", "ry", "vt" };
	String[] paramsEnd = { "ac", "py", "ver", "p1", "p2", "p3", "ty", "uid", "lc", "uuid", "cid", "pid", "vid", "lid",
			"st", "sid", "vlen", "ch", "url", "weid", "ref", "pv", "ilu", "ctime", "r", "key", "app_name", "nt", "ipt",
			"pt", "ut", "ry", "vt" };
	/**
	 * action
	 */
	public String ac;
	public String app_name = "H5Player";
	public String ch = "safari";
	// public String cl;
	// public String cid;
	public String ctime;
	public String ilu;
	public String ipt;
	public String key;
	// public String lc;
	public String lid;
	public String nt;
	public String p1;
	public String p2;
	public String p3;
	// public String pid;
	public String pv = "3.7.0";
	public String py;
	public String r;
	public String ref;
	public String sid;
	public String st;
	public String ty;
	public String uid;
	// public String url;
	public String uuid;
	public String ver = "3.7.2";
	// public String vid;
	// public String vlen;
	public String weid;
	public String pt;
	public String ut;
	public String ry;
	public String vt;
	public String cdev;
	public String caid;
	public String prl;
	public String pay;
	public String joint;
	private Map<String, Object> infoMap;

	public Ppl(GeneralParam gp) {
		this.gp = gp;
		this.ref = gp.url;
		this.URL = "http://apple-www.le.com/pl/";
	}

	/**
	 * 状态 在请求的过程中会有几个不同的状态
	 */
	private String status;
	/**
	 * 每次变更状态都要先设置成true,变更完在设置成false
	 */
	private boolean changingStatus;

	private String getUuid() {
		if (this.uuid == null) {
			// 1 + time+4 + 十位十进制数的后6位;
			Random random = new Random();
			String uuid = "1";
			String t = new Date().getTime() + "";
			String r = "";
			for (int i = 0; i < 6; i++) {
				r += random.nextInt(10);
			}

			uuid += new String(t).substring(4);
			uuid += r;
			this.uuid = uuid;
		}
		return uuid;
	}

	private String getCtime() {
		if (this.ctime == null) {
			this.ctime = new Date().getTime() + "";
		}
		return this.ctime;
	}

	private String getR() {
		// 12 位10进制随机数
		if (this.r == null) {
			Random random = new Random();
			String s = "";
			for (int i = 10; i > 0; i--) {
				s += random.nextInt(10);
			}
			this.r = s;
		}
		return this.r;
	}

	private String getKey() {
		// MD5(e+t+r+n+”tYt2bxik”)
		// e->lc.toLowerCase
		// t->uuid.toLowerCase
		// r->ctime
		// n->r
		this.key = MD5.encry(gp.getLc().toLowerCase() + this.getUuid().toLowerCase() + this.getCtime() + this.getR()
				+ this.SECRET_KEY);
		return this.key;
	}

	private String getWEID() {
		// 时间+七位随机十进制数
		if (this.weid == null) {
			Random random = new Random();
			String t = new Date().getTime() + "";
			for (int i = 0; i < 7; i++) {
				t += random.nextInt(10);
			}
			this.weid = t;
		}
		return this.weid;
	}

	private String getPy() {
		this.py = "cl%3D" + gp.getCl()
				+ "%26br%3DMozilla%2F5.0%20(iPhone%3B%20CPU%20iPhone%20OS%209_1%20like%20Mac%20OS%20X)%20AppleWebKit%2F601.1.46%20(KHTML%2C%20like%20Gecko)%20Version%2F9.0%20Mobile%2F13B143%20Safari%2F601.1";
		return this.py;
	}

	/**
	 * 生成参数 @throws
	 */
	private void genrateParam(int pharse) {
		// 等待500毫秒时间
		Random random = new Random();
		int time = 300 + random.nextInt(200);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//清空本次事件需要重新生成的数据
		this.ctime = null;
		this.r = null;
		
		
		
		
		this.prl = null;
		this.pay = null;
		this.joint = null;
		this.cdev = null;
		this.caid = null;
		switch (pharse) {
		case INIT:

			params = paramsInit;
			this.ac = "init";
			this.p1 = "0";
			this.p2 = "04";
			p3 = "";
			ty = "0";
			ilu = "1";
			nt = "none";
			ipt = "0";
			pt = "-";
			ut = "-";
			ry = "0";
			vt = "13";
			cdev = "-";
			caid = "-";
			break;
		case GSLB:

			params = paramsGslb;
			this.ac = "gslb";
			nt = "none";
			ipt = "";
			pt = "-";
			ut = "197";
			ry = "0";
			vt = "13";
			break;
		case GSLB2:
			// TODO 现在没有gslb2
			params = paramsGslb;
			this.ac = "gslb";
			nt = "none";
			ipt = "";
			pt = "-";
			// TODO 会变,现在是214
			ut = "80";
			ry = "0";
			vt = "13";
			break;
		case CLOAD:
			params = paramsCload;
			this.ac = "cload";
			nt = "none";
			ipt = "0";
			pt = "-";
			ut = "-";
			ry = "0";
			vt = "13";
			break;
		case BLOCK:
			params = paramsBlock;
			this.ac = "block";
			nt = "none";
			ipt = "0";
			pt = "-";
			// TODO 会变,现在监测到的是986
			ut = "2262";
			ry = "0";
			vt = "13";
			break;
		case PLAY:
			params = paramsPlay;
			this.ac = "play";
			nt = "none";
			ipt = "0";
			pt = "-";
			ut = "-";
			ry = "0";
			vt = "13";
			prl = "1";
			pay = "0";
			joint = "1";
			break;
		case TIME:
			params = paramsTime;
			this.ac = "time";
			nt = "none";
			ipt = "0";
			// TODO 测试中的两条数据一个120 一个是20,是不是会跟随时间变化而变化?
			pt = "120";// 会变动
			ut = "-";
			ry = "0";
			vt = "13";
			break;
		case END:
			params = paramsEnd;
			this.ac = "end";
			nt = "none";
			ipt = "0";
			pt = "-";
			ut = "-";
			ry = "0";
			vt = "13";
			break;
		}

		this.getUuid();
		gp.getCl();
		gp.getLc();
		this.getR();
		this.getKey();
		this.getWEID();
		this.getPy();

	}

	/**
	 * 初始化
	 */
	public Ppl init() {
		this.genrateParam(INIT);
		return this;
	}

	public Ppl gslb() {
		this.genrateParam(GSLB);
		return this;
	}

	public Ppl gslb2() {
		this.genrateParam(GSLB2);
		return this;
	}

	public Ppl cload() {
		this.genrateParam(CLOAD);
		return this;
	}

	public Ppl block() {
		this.genrateParam(BLOCK);
		return this;
	}

	public Ppl play() {
		this.genrateParam(PLAY);
		return this;
	}

	public Ppl time() {
		this.genrateParam(TIME);
		return this;
	}

	public Ppl end() {
		this.genrateParam(END);
		return this;
	}

	/**
	 * 
	 */
	public Ppl start() {
		return this;
	}

	public String getUrl() {
		return super.getUrl();
	}

	public static void main(String[] args) {
		// Ppl p = new Ppl("http://m.le.com/vplay_29982549.html");

		// p.gp.getVInfo("http://m.le.com/vplay_29982549.html");
	}

}
