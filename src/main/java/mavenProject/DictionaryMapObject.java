package mavenProject;

import java.util.Hashtable;

import interfacePackage.IMapObject;

public class DictionaryMapObject implements IMapObject {
	private Hashtable<String,String> mapObject = new Hashtable<String,String>();
	{
		mapObject.put("TXT_LOGIN_USER","id~mat-input-0");
		mapObject.put("TXT_LOGIN_PASS","id~mat-input-1");
		mapObject.put("BTN_LOGIN_LOGIN","xpath~/html/body/app-root/div/app-login/mat-sidenav-container/mat-sidenav-content/div/form/mat-card/mat-card-content/button");
		mapObject.put("LBL_USER_INFO","xpath~/html/body/app-root/div/app-pages/mat-sidenav-container/mat-sidenav[1]/app-sidenav/div[1]/div[1]/div/p[1]");
		mapObject.put("LBL_LOGIN_NOT_USER","id~mat-error-1");
		mapObject.put("LBL_LOGIN_NOT_PASS","id~mat-error-3");
	}
	@Override
	public String[] GetProperties(String strKey) {
		String[] strProperties = null;
		if(mapObject.containsKey(strKey))
			strProperties = mapObject.get(strKey).split("~");
		return strProperties;
	}
}
