import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;

public class Pop {
	private static final String URL = "http://apple-www.le.com/op/";
	private static final String SECRET_KEY = "tYt2bxik";

	// 通用参数
	public GeneralParam gp = null;
	public String acode;
	public String ap;
	public String ar;
	public String cid;
	public String pid;
	public String vid;
	public String uid;
	public String lc;
	public String cur_url;
	public String auid;
	public String targeturl;
	public String p1;
	public String p2;
	public String app;
	public String app_name;
	public String apprunid;
	public String nt;
	public String android_id;
	public String idfa;
	public String mac;
	public String wmac;
	public String im;
	public String imsi;
	public String mid;
	public String pcode;
	public String p3;
	public String ch;
	public String plat;
	public String pn;
	public String tick;
	public String seriaino;
	public String stime;
	public String py;
	public String r;
	public String ctime;
	private String url;

	private Map<String, Object> infoMap;

	public Pop() {

	}

	public Pop(GeneralParam gp) {
		this.gp = gp;
		this.url = gp.url;
		// this.vid = getVid(url);
		this.infoMap = getVInfo(url);
		this.pid = infoMap.get("pid").toString();
		this.cid = infoMap.get("cid").toString();
		// this.vlen = infoMap.get("duration").toString();
	}

	private Map<String, Object> getVInfo(String url) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = HttpHelper.sendGet(url, null);
		String infoJson = result.substring(result.indexOf("var info = ") + 11,
				result.indexOf("\"region\":\"CN\"};") + 14);
		map = (Map<String, Object>) JSON.parse(infoJson);
		System.out.println(infoJson);

		return map;
	}

	/**
	 * 状态 在请求的过程中会有几个不同的状态
	 */
	private String status;
	/**
	 * 每次变更状态都要先设置成true,变更完在设置成false
	 */
	private boolean changingStatus;

	private String getLc() {
		// 32位16进制随机数
		if (lc == null) {
			Random random = new Random();
			String s = "";
			for (int i = 32; i > 0; i--) {
				s += Integer.toHexString(random.nextInt(16));
			}
			this.lc = s;
		}
		return this.lc;
	}

	private String getCtime() {
		if (this.changingStatus) {
			this.ctime = new Date().getTime() + "";
		}
		return this.ctime;
	}

	private String getR() {
		// 12 位10进制随机数
		if (this.changingStatus) {
			Random random = new Random();
			String s = "";
			for (int i = 10; i > 0; i--) {
				s += random.nextInt(10);
			}
			this.r = s;
		}
		return this.r;
	}

	// private String getKey() {
	// // MD5(e+t+r+n+”tYt2bxik”)
	// // e->lc.toLowerCase
	// // t->uuid.toLowerCase
	// // r->ctime
	// // n->r
	// this.key = MD5.encry(this.getLc().toLowerCase() +
	// this.getUuid().toLowerCase() + this.getCtime() + this.getR()
	// + this.SECRET_KEY);
	// return this.key;
	// }
	//
	// private String getWEID() {
	// // 时间+七位随机十进制数
	// if (this.weid == null) {
	// Random random = new Random();
	// String t = new Date().getTime() + "";
	// for (int i = 0; i < 7; i++) {
	// t += random.nextInt(10);
	// }
	// this.weid = t;
	// }
	// return this.weid;
	// }
	//
	// private String getPy() {
	// this.py = "cl%3D" + this.getCl()
	// +
	// "%26br%3DMozilla%2F5.0%20(iPhone%3B%20CPU%20iPhone%20OS%209_1%20like%20Mac%20OS%20X)%20AppleWebKit%2F601.1.46%20(KHTML%2C%20like%20Gecko)%20Version%2F9.0%20Mobile%2F13B143%20Safari%2F601.1";
	// return this.py;
	// }
	//
	// private String getVid(String url) {
	// String vid = "";
	// vid = url.substring(url.indexOf("vplay_") + 6, url.indexOf(".html"));
	// return vid;
	// }
	//
	// /**
	// * 生成参数 @throws
	// */
	// private void genrateParam(int pharse) {
	// // 等待500毫秒时间
	// Random random = new Random();
	// int time = 300 + random.nextInt(200);
	// try {
	// Thread.sleep(time);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// this.changingStatus = true;
	// this.prl = null;
	// this.pay = null;
	// this.joint = null;
	// this.cdev = null;
	// this.caid = null;
	// switch (pharse) {
	// case INIT:
	// this.ac = "init";
	// this.p1 = "0";
	// this.p2 = "04";
	// p3 = "";
	// ty = "0";
	// ilu = "1";
	// nt = "none";
	// ipt = "0";
	// pt = "-";
	// ut = "";
	// ry = "";
	// vt = "13";
	// this.cdev = "-";
	// this.caid = "-";
	// break;
	// case GSLB:
	// this.ac = "gslb";
	// nt = "none";
	// ipt = "";
	// pt = "-";
	// ut = "197";
	// ry = "0";
	// vt = "13";
	// break;
	// case GSLB2:
	// this.ac = "gslb";
	// nt = "none";
	// ipt = "";
	// pt = "-";
	// ut = "80";
	// ry = "0";
	// vt = "13";
	// break;
	// case CLOAD:
	// this.ac = "cload";
	// nt = "none";
	// ipt = "0";
	// pt = "-";
	// ut = "-";
	// ry = "0";
	// vt = "13";
	// break;
	// case BLOCK:
	// this.ac = "block";
	// nt = "none";
	// ipt = "0";
	// pt = "-";
	// ut = "2262";
	// ry = "0";
	// vt = "13";
	// break;
	// case PLAY:
	// this.ac = "play";
	// nt = "none";
	// ipt = "0";
	// pt = "-";
	// ut = "-";
	// ry = "0";
	// vt = "13";
	// prl = "1";
	// pay = "0";
	// joint = "1";
	// this.prl = "1";
	// this.pay = "0";
	// this.joint = "1";
	// break;
	// case TIME:
	//
	// this.ac = "time";
	// nt = "none";
	// ipt = "0";
	// pt = "120";
	// ut = "-";
	// ry = "0";
	// vt = "13";
	// break;
	// case END:
	// this.ac = "time";
	// nt = "none";
	// ipt = "0";
	// pt = "-";
	// ut = "-";
	// ry = "0";
	// vt = "13";
	// break;
	// }
	//
	// this.getUuid();
	// this.getCl();
	// this.getLc();
	// this.getCl();
	// this.getR();
	// this.getKey();
	// this.getWEID();
	// this.getPy();
	// this.changingStatus = false;
	//
	// }
	//
	// /**
	// * 初始化
	// */
	// public Pop init() {
	// this.genrateParam(INIT);
	// return this;
	// }
	//
	// public Pop gslb() {
	// this.genrateParam(GSLB);
	// return this;
	// }
	//
	// public Pop gslb2() {
	// this.genrateParam(GSLB2);
	// return this;
	// }
	//
	// public Pop cload() {
	// this.genrateParam(CLOAD);
	// return this;
	// }
	//
	// public Pop block() {
	// this.genrateParam(BLOCK);
	// return this;
	// }
	//
	// public Pop play() {
	// this.genrateParam(PLAY);
	// return this;
	// }
	//
	// public Pop time() {
	// this.genrateParam(TIME);
	// return this;
	// }
	//
	// public Pop end() {
	// this.genrateParam(END);
	// return this;
	// }
	//
	// /**
	// *
	// */
	// public Pop start() {
	// return this;
	// }
	//
	// public String toHttpParam() {
	// String param = "";
	// Object[] fieldsValues = ParameterUtil.getFiledValues(this);
	// Map fieldsInfo = ParameterUtil.getFiledsInfo(this);
	// for (Object key : fieldsInfo.keySet()) {
	// Map ff = (HashMap) fieldsInfo.get(key);
	// String type = (String) ff.get("type");
	// if (ff.get("value") == null) {
	// continue;
	// }
	// if ("String".equals("String")) {
	// if ("".equals(param)) {
	// param = ff.get("name") + "=" + ff.get("value").toString();
	// } else {
	// param += "&" + ff.get("name") + "=" + ff.get("value").toString();
	// }
	// }
	// }
	// return param;
	// }
	//
	// public String getUrl() {
	// return URL + "?" + this.toHttpParam();
	// }
	//
	// public static void main(String[] args) {
	// Pop p = new Pop();
	//
	// p.getVInfo("http://m.le.com/vplay_29982549.html");
	// }
}
