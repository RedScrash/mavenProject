package stepDefinition;

import cucumber.api.java.es.Cuando;

import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import tools.Reports;
import tools.EnumClass.ActionType;
import tools.EnumClass.Browser;
import cucumber.api.Scenario;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.LogStatus;

import Core.ActionObject;
import Core.GlobalSettings;

public class StepDefinition {
	private String strURL = GlobalSettings.StrURL(); 
	ActionObject actionObject;
	Reports report;
	String strScenarioName;
	String strFuncionalidadName;
	
	@BeforeClass
	public void Before(Scenario scenario) {
		strScenarioName = scenario.getName();
		//strFuncionalidadName = scenario.getSourceTagNames();
		report = actionObject.reports;
		report.StartReport("Pruebas_UXUI");
		report.StartTest(strScenarioName);
	}
	public StepDefinition() {
		actionObject = new ActionObject();
		report = actionObject.reports;
		report.StartReport("Pruebas_UXUI");
		report.StartTest("Login Correcto");
	}

	@Dado("^Usuario ingresa config$")
	public void usuarioIngresaConfig() throws Throwable {
		actionObject.StartDriver(Browser.chrome);
		actionObject.GotoURL(strURL);
		actionObject.WaitElement("TXT_LOGIN_USER");
		report.SaveStep("Ingreso correcto a la URL: ".concat(strURL), LogStatus.PASS);
	}

	@Cuando("^Usuario ingresa en el campo \"([^\"]*)\" el texto \"([^\"]*)\"$")
	public void usuarioIngresaEnElCampoElTexto(String strObject, String strValue) throws Throwable {
		if(strObject==null || strObject.equals(""))
			report.SaveStep("El objeto parece estar vacío o nulo! función: usuarioIngresaEnElCampoElTexto", LogStatus.FATAL);
		else
			actionObject.ExecuteAction(strObject,strValue , ActionType.SetText);
	}

	@Cuando("^Usuario hace clic sobre el objeto \"([^\"]*)\"$")
	public void usuarioHaceClicSobreElObjeto(String strObject) throws Throwable {
		if(strObject==null || strObject.equals(""))
			report.SaveStep("El objeto parece estar vacío o nulo! función: usuarioHaceClicSobreElObjeto", LogStatus.FATAL);
		else
			actionObject.ExecuteAction(strObject,null , ActionType.Click);
	}

	@Entonces("^Usuario valida que el objeto \"([^\"]*)\" este presente en pantalla$")
	public void usuarioValidaQueElObjetoEstePresenteEnPantalla(String strObject) throws Throwable {
		boolean bResult = false;
		if(strObject==null || strObject.equals("")) {
			report.SaveStep("El objeto parece estar vacío o nulo! función: usuarioValidaQueElObjetoEstePresenteEnPantalla", LogStatus.FATAL);
			bResult = false;
		}else
			bResult = actionObject.WaitElement(strObject);
		report.SaveStep("Login Correcto", bResult?LogStatus.PASS:LogStatus.FAIL);
		assertTrue(bResult);
	}

	@Entonces("^Usuario finaliza escenario$")
	public void usuarioFinalizaEscenario() throws Throwable {
		report.EndTest();
		actionObject.DriverQuit();
		report.EndReport();
	}

}
