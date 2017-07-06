import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;

/**
 * 通用参数
 * 
 * @author liusq15
 *
 */
public class GeneralParam {
	
	public String cl;
	public String cid;
	public String vid;
	public String pid;
	public String lc;
	public String vlen;
	private Map<String,Object> infoMap;
	public Ppl ppl;
	public Pop pop;
	public String url;
	
	public GeneralParam(String url) {
		this.url = url;
		this.infoMap = getVInfo(url);
		this.vid = getVid(url);
		this.pid = infoMap.get("pid").toString() ;
		this.cid = infoMap.get("cid").toString();
		this.vlen = infoMap.get("duration").toString();
		
		
		ppl = new Ppl(this);
		pop = new Pop(this);
	}
	
	public String getCl() {
		// MD5(lc + showmethemoney)
		if (this.cl == null) {
			this.cl = MD5.encry(getLc() + "showmethemoney");
		}
		return this.cl;
	}
	
	public Map<String, Object> getVInfo(String url) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = HttpHelper.sendGet(url, null);
		String infoJson = result.substring(result.indexOf("var info = ") + 11,
				result.indexOf("\"region\":\"CN\"};") + 14);
		map = (Map<String, Object>) JSON.parse(infoJson);
		System.out.println(infoJson);

		return map;
	}
	
	public String getVid(String url) {
		String vid = "";
		vid = url.substring(url.indexOf("vplay_") + 6,url.indexOf(".html"));
		return vid;
	}
	
	public String getLc() {
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
	
	public Map<String, Object> getInfoMap(){
		return this.infoMap;
	}
	
	

}
