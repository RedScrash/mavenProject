package unitTest;
import static org.junit.Assert.*;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Core.*;
import tools.EnumClass.*;
import tools.Reports;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class testng {
	ActionObject actionObject = new ActionObject();
	Reports report;
	
	@BeforeClass
	public void setUpReport() {
		report = actionObject.reports;
		//report.StartReport("Pruebas_UXUI");
	}
	
	@BeforeMethod
	public void setUpTest() {
		actionObject.StartDriver(Browser.chrome);
		actionObject.GotoURL("http://localhost:4200/login");
		actionObject.WaitElement("TXT_LOGIN_USER");
	}
	@Test
	public void LoginCorrecto(){
		boolean bResult;
		report.StartTest("Login Correcto");
		///
		actionObject.ExecuteAction("TXT_LOGIN_USER","user@user.com" , ActionType.SetText);
		///
		actionObject.ExecuteAction("TXT_LOGIN_PASS","user123456" , ActionType.SetText);
		///
		report.SaveStep("Página de inicio", LogStatus.PASS);
		///
		actionObject.ExecuteAction("BTN_LOGIN_LOGIN",null , ActionType.Click);
		///
		bResult = actionObject.WaitElement("LBL_USER_INFO");
		report.SaveStep("Login Correcto", bResult?LogStatus.PASS:LogStatus.FAIL);
		assertTrue(bResult);
	}
	@Test
	public void LoginSinContrasena(){
		boolean bResult;
		report.StartTest("Login Sin Password");
		///
		actionObject.ExecuteAction("TXT_LOGIN_USER","user@user.com" , ActionType.SetText);
		///
		report.SaveStep("Página de inicio", LogStatus.PASS);
		///
		actionObject.ExecuteAction("BTN_LOGIN_LOGIN",null , ActionType.Click);
		///
		bResult = actionObject.WaitElement("LBL_LOGIN_NOT_PASS");
		report.SaveStep("Login Sin Contraseña", bResult?LogStatus.PASS:LogStatus.FAIL);
		assertTrue(bResult);
	}
	@Test
	public void LoginSinUsuario(){
		boolean bResult;
		report.StartTest("Login Sin Usuario");
		///
		actionObject.ExecuteAction("TXT_LOGIN_PASS","user123456" , ActionType.SetText);
		///
		report.SaveStep("Página de inicio", LogStatus.PASS);
		///
		actionObject.ExecuteAction("BTN_LOGIN_LOGIN",null , ActionType.Click);
		///
		bResult = actionObject.WaitElement("LBL_LOGIN_NOT_USER");
		report.SaveStep("Login Sin Usuario", bResult?LogStatus.PASS:LogStatus.FAIL);
		assertTrue(bResult);
	}
	@AfterMethod
	public void endTest() {
		report.EndTest();
		actionObject.DriverQuit();
	}
	@AfterClass
	public void endReport() {
		report.EndReport();
	}
}
