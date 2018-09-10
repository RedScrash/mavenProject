package Core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import interfacePackage.IMapObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import locators.DictionaryMapObject;
import tools.DriverBrowser;
import tools.ObjectProperties;
import tools.EnumClass.*;
import tools.Reports;

public class ActionObject {
	IMapObject mapObject;
	public Reports reports;
	private WebDriverWait wait = null;
	
	String strDriverPath = "./src/test/resources/driver/IEDriverServer.exe";
	/**
	 * 
	 */
	public ActionObject() {
		reports = new Reports();
	}
	public void StartDriver(Browser browser) {
		switch(browser) {
		case edge:
			//System.setProperty("webdriver.ie.driver", strDriverPath);
			//driver = new InternetExplorerDriver();
			WebDriverManager.edgedriver().setup();
			DriverBrowser.setDriver(new EdgeDriver());
			break;
		case firefox:
			WebDriverManager.firefoxdriver().setup();
			DriverBrowser.setDriver(new FirefoxDriver());
			break;
		case chrome:
			WebDriverManager.chromedriver().setup();
			DriverBrowser.setDriver(new ChromeDriver());
			break;
		default:
			WebDriverManager.iedriver().setup();
			DriverBrowser.setDriver(new InternetExplorerDriver());
			break;
		}
		wait = new WebDriverWait(DriverBrowser.Driver(), 30);
	}
	/**
	 * 
	 * @param strURL
	 */
	public void GotoURL(String strURL) {
		DriverBrowser.Driver().get(strURL);
		DriverBrowser.Driver().manage().window().maximize();
	}
	/**
	 * 
	 * @param identy
	 * @param strProperty
	 * @return
	 */
	private WebElement _findElement(Identifier identy,String strProperty) {
		WebElement element=null;
		try {
			switch(identy) {
			case id:
				element = DriverBrowser.Driver().findElement(By.id(strProperty));
				break;
			case xpath:
				element = DriverBrowser.Driver().findElement(By.xpath(strProperty));
				break;
			case name:
				element = DriverBrowser.Driver().findElement(By.name(strProperty));
				break;
			default:
				element = DriverBrowser.Driver().findElement(By.className(strProperty));
				break;
			}
		}catch(Exception e) {
			System.out.println("Error en la función findElement: ".concat(e.getMessage()));
			reports.SaveStep("Error en la función findElement: ".concat(e.getMessage()), LogStatus.FATAL);
		}
		return element;		
	}
	/**
	 * 
	 * @param identy
	 * @param strProperty
	 * @return
	 */
	public boolean WaitElement(Identifier identy,String strProperty) {
		boolean bFind = false;
		try {
			switch(identy) {
			case id:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(strProperty)));
				bFind =true;
				break;
			case xpath:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(strProperty)));
				bFind =true;
				break;
			case name:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(strProperty)));
				bFind =true;
				break;
			default:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(strProperty)));
				bFind =true;
				break;
			}
		}catch(Exception e) {
			System.out.println("Elemento no encontrado: ".concat(strProperty));
			reports.SaveStep("Elemento no encontrado: ".concat(strProperty), LogStatus.FATAL);
		}
		return bFind;		
	}
	public boolean WaitElement(String strObjectName) {
		boolean bFind = false;
		ObjectProperties objectProperties = _getObjectProperties(strObjectName);
		
		if(objectProperties==null)
			return bFind;
		
		try {
			switch(objectProperties.getIdentifier()) {
			case id:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objectProperties.getProperty())));
				bFind =true;
				break;
			case xpath:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectProperties.getProperty())));
				bFind =true;
				break;
			case name:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(objectProperties.getProperty())));
				bFind =true;
				break;
			default:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(objectProperties.getProperty())));
				bFind =true;
				break;
			}
		}catch(Exception e) {
			System.out.println("Elemento no encontrado: ".concat(strObjectName));
			reports.SaveStep("Elemento no encontrado: ".concat(strObjectName), LogStatus.FATAL);
		}
		return bFind;		
	}
	/**
	 * 
	 * @param identy
	 * @param strProperty
	 * @param strParameter
	 * @param action
	 * @return
	 */
	public boolean ExecuteAction(Identifier identy,String strProperty,String strParameter,ActionType action) {
		boolean bResult = false;
		WebElement element;
		element = WaitElement(identy,strProperty) ? _findElement(identy,strProperty): null;
		
		bResult = _executeAction(element,action,strParameter);
		return bResult;
	}
	/**
	 * 
	 * @param strObjectName
	 * @param strParameter
	 * @param action
	 * @return
	 */
	public boolean ExecuteAction(String strObjectName,String strParameter,ActionType action) {
		boolean bResult = false;
		ObjectProperties objectProperties = _getObjectProperties(strObjectName);
		WebElement element;
		
		if(objectProperties==null)
			return bResult;
			
		element = WaitElement(objectProperties.getIdentifier(),objectProperties.getProperty()) ? _findElement(objectProperties.getIdentifier(),objectProperties.getProperty()): null;
		
		bResult = _executeAction(element,action,strParameter);
		return bResult;
	}
	/**
	 * 
	 * @param element
	 * @param action
	 * @param strParameter
	 * @return
	 */
	private boolean _executeAction(WebElement element, ActionType action,String strParameter) {
		boolean bResult = false;
		if(element!=null) {
			switch(action) {
			case Click:
				element.click();
				bResult = true;
				break;
			case SetText:
				element.sendKeys(strParameter);
				bResult = true;
				break;
			case EqualText:
				bResult = _validateText(element,strParameter,true);
				break;
			case ContainText:
				bResult = _validateText(element,strParameter,false);
				break;
			default:
				element.click();
				bResult =  true;
				break;
			}
		}
		return bResult;
	}
	/**
	 * 
	 * @param element
	 * @param strMessage
	 * @param bEquals
	 * @return
	 */
	private boolean _validateText(WebElement element,String strMessage,boolean bEquals) {
		String strProperty;
		if(!_getProperty(element,"text").isEmpty())
			strProperty = _getProperty(element,"text");
		else if(!_getProperty(element,"value").isEmpty())
			strProperty = _getProperty(element,"value");
		else
			strProperty = "";
		
		if(bEquals)
			return strMessage.trim().toLowerCase().equals(strProperty.trim().toLowerCase());
		else
			return strProperty.contains(strMessage);
		
	}
	/**
	 * 
	 * @param element
	 * @param strProperty
	 * @return
	 */
	private String _getProperty(WebElement element,String strProperty) {
		if(strProperty.isEmpty() || element==null)
			return null;
		switch(strProperty.toLowerCase().trim()) {
		case "text":
			return element.getText();
		case "value":
			return element.getAttribute("value");
		case "tagname":
			return element.getTagName();
		case "selected":
			return Boolean.toString(element.isSelected());
		default:
			return element.getAttribute(strProperty.toLowerCase().trim());
		}
	}
	/**
	 * 
	 * @param strObjectName
	 * @return
	 */
	private ObjectProperties _getObjectProperties(String strObjectName) {
		ObjectProperties objectProperties = null;
		String[] strProperties;
		
		mapObject = new DictionaryMapObject();
		strProperties = mapObject.GetProperties(strObjectName);
		if(strProperties==null) 
			reports.SaveStep("El objeto no se encontró en el mapeo de objetos: ".concat(strObjectName), LogStatus.ERROR);
		else if(strProperties[0].isEmpty()) 
			reports.SaveStep("El objeto tiene el identificador vacío: ".concat(strObjectName), LogStatus.ERROR);
		else if(strProperties[1].isEmpty()) 
			reports.SaveStep("El objeto tiene la propiedad vacía: ".concat(strObjectName), LogStatus.ERROR);
		else {
			switch(strProperties[0].toLowerCase().trim()) {
			case "id":
				objectProperties = new ObjectProperties(Identifier.id, strProperties[1]);
				break;
			case "name":
				objectProperties = new ObjectProperties(Identifier.name, strProperties[1]);
				break;
			case "xpath":
				objectProperties = new ObjectProperties(Identifier.xpath, strProperties[1]);
				break;
			default:
				objectProperties = new ObjectProperties(Identifier.className, strProperties[1]);
				break;
			}
		}
		return objectProperties;
	}
	/**
	 * 
	 */
	public void DriverQuit() {
		if (DriverBrowser.Driver() != null)
			DriverBrowser.Driver().quit();
	}
}
