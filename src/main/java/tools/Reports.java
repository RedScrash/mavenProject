package tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Core.GlobalSettings;


public class Reports {
	ExtentReports extentReports;
	ExtentTest extentTest;
	String strPathHtml = GlobalSettings.StrPathHtml();
	String strPathImage = GlobalSettings.StrPathImages();
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH");
	DateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss");
	String strTestName;
	
	public Reports() {
		//Constructor vacío
	}
	/**
	 * Permite iniciar el reporte de evidencias HTML
	 * @param strScenario
	 */
	public void StartReport(String strScenario) {
		String strFilePath;
		String strHtmlName;
		
		strHtmlName = strScenario.toUpperCase().replaceAll(" ", "_").concat("_").concat(dateFormat.format(new Date()));
		strFilePath = strPathHtml.concat("/").concat(strHtmlName).concat(".html");
		extentReports = new ExtentReports(strFilePath,false);
	}
	/**
	 * Permite iniciar el caso de prueba en el reporte HTML
	 * @param strTestName
	 */
	public void StartTest(String strTestName) {
		if (strTestName==null || strTestName.isEmpty())
            System.out.println("Error: el nombre del test está vacío!");
        else if (extentReports == null)
        	System.out.println("Error: no se ha iniciado el reporte, debe invocar primero la función StartReport!");
        else
        {
			this.strTestName = strTestName;
			extentTest = extentReports.startTest(strTestName);
        }
	}
	/**
	 * Permite guardar el pantallaso de un paso ejecutado, en el reporte HTML 
	 * @param strStepDetail
	 * @param bResult
	 */
	public void SaveStep(String strStepDetail, LogStatus logResult) {
		String strFilePath = _screenShot();
		extentTest.log(logResult, strStepDetail+extentTest.addBase64ScreenShot(strFilePath));
	}
	/**
	 * Finaliza el caso de prueba en el reporte 
	 */
	public void EndTest() {
		if(extentTest!=null)
			extentReports.endTest(extentTest);
	}
	/**
	 * Finaliza el reporte HTML
	 */
	public void EndReport() {
		if(extentReports!= null)
			extentReports.flush();
	}
	/**
	 * Permirte tomar un screenshot de la pantalla, para almacenar cómo evidencia
	 * @param strStepDetail
	 * @return
	 */
	private String _screenShot() {
		TakesScreenshot newScreen = (TakesScreenshot) DriverBrowser.Driver();
	    String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
	    return "data:image/jpg;base64, " + scnShot ;
	}
}
