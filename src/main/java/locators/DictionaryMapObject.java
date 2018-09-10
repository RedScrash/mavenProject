package locators;

import java.util.Hashtable;

import interfacePackage.IMapObject;

public class DictionaryMapObject implements IMapObject {
	private Hashtable<String,String> mapObject = new Hashtable<String,String>();
	{
		mapObject.put("TXT_LOGIN_USER","id~userName");
		mapObject.put("TXT_LOGIN_PASS","id~password");
		mapObject.put("BTN_LOGIN_LOGIN","id~ingresar");
		mapObject.put("LBL_LOGIN_ERROR","id~errorLogin");
		mapObject.put("LBL_WELCOME","id~bienvenido");
	}
	@Override
	public String[] GetProperties(String strKey) {
		String[] strProperties = null;
		if(mapObject.containsKey(strKey))
			strProperties = mapObject.get(strKey).split("~");
		return strProperties;
	}
}
