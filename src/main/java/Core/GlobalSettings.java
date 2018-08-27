package Core;

public class GlobalSettings {
	protected GlobalSettings() {
		
	}
	private static String strURL = "http://localhost:4200/login";
	private static String strPathHtml = "//calidad-08/Compartida/UXUI/Reportes/html";
    private static String strPathImages = "//calidad-08/Compartida/UXUI/Reportes/images";
    
    //Funciones de encapsulado
    public static String StrURL(){ return strURL;}
    public static String StrPathHtml(){ return strPathHtml;}
    public static String StrPathImages(){ return strPathImages;}
}
