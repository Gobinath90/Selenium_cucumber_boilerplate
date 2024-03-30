package utils;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;

import pages.BasePage;

public class FileUploadUtils {

	public static void performKeyAction(Robot robot) throws Exception {
		int keyToPress = KeyEvent.VK_CONTROL;
		String os = System.getProperty("os.name").toLowerCase();
		if (os.startsWith("mac")) {
			keyToPress = KeyEvent.VK_META;
		}
		robot.keyPress(keyToPress);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(keyToPress);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void imageUpload(String folderName, String fileName) throws Exception {
		BasePage.setClipboardData(BasePage.getFilePath(folderName, fileName));
		performKeyAction(new Robot());
	}

	public void downloadCsvFile(WebElement downloadButton) {
		downloadFile(downloadButton, ".csv");
	}

	public void downloadPdfFile(WebElement downloadButton) {
		downloadFile(downloadButton, ".pdf");
	}

	public void downloadExcelFile(WebElement downloadButton) {
		downloadFile(downloadButton, ".xlsx");
	}

	private void downloadFile(WebElement downloadButton, String fileExtension) {
        downloadButton.click();
        BasePage.waitForFileDownload(fileExtension);
    }

}
