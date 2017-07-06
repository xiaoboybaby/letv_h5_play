import java.util.HashMap;
import java.util.Map;

public class SuperP {
	public String[] params ;
	public String URL;
	public String toHttpParam() {
		String param = "";
		Object[] fieldsValues = ParameterUtil.getFiledValues(this);
		Map fieldsInfo = ParameterUtil.getFiledsInfo(this);
		
		for(String key : params){
			Map ff = (HashMap)fieldsInfo.get(key);
			if(ff == null ){
				System.out.println(key);
				continue;
			}
			String type = (String) ff.get("type");
			if(ff.get("value") == null){
				continue;
			}
			if("String".equals("String")){
				if("".equals(param)){
					param = ff.get("name") + "=" + ff.get("value").toString();	
				}else{
					param += "&" + ff.get("name") +  "=" +ff.get("value").toString();
				}	
			}	
		}
		
		return param;
	}
	
	public String getUrl(){
		return URL + "?" + this.toHttpParam();
	}
}
