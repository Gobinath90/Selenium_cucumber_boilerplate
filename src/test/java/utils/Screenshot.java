package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.plugin.event.PickleStepTestStep;
import support.Driver;

public class Screenshot {
	static Log Logger = (Log) LogFactory.getLog(Screenshot.class);
	private static String path;
	private static SimpleDateFormat timestampEvidence;
    public static SimpleDateFormat timeStampPasta;
    private static PickleStepTestStep currentStep;
    
    
    
//    @SuppressWarnings("deprecation")
	public static void takeScreenShot(){
        String stepName = "";
        String namePasta = "photos";
        try{
            stepName = currentStep.getStep().getText();
        }
        catch (NullPointerException ignored){
            Logger.warn("nome do step nao encontrado");
        }
        String namePrint = "Evidencia";
        Date date = new Date();
        File scrFile = (File)((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(scrFile, new File(path + ""  + "/" + namePasta + "/"
                    + "_" + timeStampPasta.format(date) + "/" + namePrint + "_" + stepName + " "
                    + timestampEvidence.format(date)+ ".png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    static {
        path = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "reports"
                + File.separator + "screenshot" + File.separator;
        timestampEvidence = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
        timeStampPasta = new SimpleDateFormat("yyMMdd");
    }
}
