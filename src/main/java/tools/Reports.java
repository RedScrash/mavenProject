package tools;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Reports {
	ExtentReports extentReports;
	ExtentTest extentTest;
	String strPathHtml = "./src/test/resources/html";
	String strPathImage = "./src/test/resources/image";
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	String strTestName;
	
	public Reports() {
	}
	/**
	 * Permite iniciar el reporte de evidencias HTML
	 * @param strScenario
	 */
	public void StartReport(String strScenario) {
		String strFilePath;
		String strHtmlName;
		
		strHtmlName = strScenario.toUpperCase().concat("_").concat(dateFormat.format(new Date()));
		strFilePath = strPathHtml.concat("/").concat(strHtmlName).concat(".html");
		extentReports = new ExtentReports(strFilePath,false);
	}
	/**
	 * Permite iniciar el caso de prueba en el reporte HTML
	 * @param strTestName
	 */
	public void StartTest(String strTestName) {
		this.strTestName = strTestName;
		extentTest = extentReports.startTest(strTestName);
	}
	/**
	 * Permite guardar el pantallaso de un paso ejecutado, en el reporte HTML 
	 * @param strStepDetail
	 * @param bResult
	 */
	public void SaveStep(String strStepDetail, LogStatus logResult) {
		String strFilePath = ScreenShot();
		extentTest.log(logResult, strStepDetail+extentTest.addScreenCapture(strFilePath));
	}
	/**
	 * Finaliza el caso de prueba en el reporte 
	 */
	public void EndTest() {
		extentReports.endTest(extentTest);
	}
	/**
	 * Finaliza el reporte HTML
	 */
	public void EndReport() {
		extentReports.flush();
	}
	/**
	 * Permirte tomar un screenshot de la pantalla, para almacenar cómo evidencia
	 * @param strStepDetail
	 * @return
	 */
	private String ScreenShot() {
		String strFilePath =null;
		String strFileName = strTestName.replaceAll(" ", "_").concat("_").concat(dateFormat.format(new Date()));
		try {
			File scrFile = ((TakesScreenshot) DriverBrowser.Driver()).getScreenshotAs(OutputType.FILE);
			strFilePath = "./image/".concat(strFileName).concat(".png");
			FileUtils.copyFile(scrFile, new File(strPathHtml.concat(strFilePath)));
			
		}catch(Exception e) {
			System.out.println("Error en la función ScreenShot: ".concat(e.getMessage()));
		}
		return strFilePath;
	}
}
