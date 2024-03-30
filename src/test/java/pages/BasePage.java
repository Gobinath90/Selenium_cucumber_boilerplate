package pages;

import static constants.FrameworkConstants.EXPLICIT_WAIT;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
	static final Logger logger = Logger.getLogger(BasePage.class.getName());
	protected static WebDriver driver;
	protected WebDriverWait wait;

	@SuppressWarnings("static-access")
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
	}

	protected void locator(By by, String elementName) {
		waitUntilPresenceOfElementLocated(by).isDisplayed();
		logger.info("-- clicking on the element " + by);
	}

	protected void click(By by, String elementName) {
		waitUntilElementToBeClickable(by).click();
		logger.info("-- clicking on the element " + by);
	}

	protected void clearAndSendKeys(By by, String value, String elementName) {
		WebElement element = waitUntilElementToBeClickable(by);
		element.clear();
		element.sendKeys(value);
		logger.info("-- writing to the element " + by);
	}

	protected WebElement waitUntilElementToBeClickable(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	protected WebElement waitUntilPresenceOfElementLocated(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		return wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	protected String getElementText(By by) {
		WebElement element = waitUntilPresenceOfElementLocated(by);
		logger.info("-- getting the element's text " + by);
		return element.getText();
	}

	public static void holdScript(int seconds) {
		try {
			Thread.sleep(1000 * seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	public static String getFilePath(String folderName, String fileName) {
		String currentDir = System.getProperty("user.dir");
		String modifiedDir = currentDir.substring(0, currentDir.lastIndexOf(File.separator));
		return modifiedDir + File.separator + folderName + File.separator + fileName;
	}

	public static void waitForFileDownload(String fileExtension) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until((WebDriver wd) -> {
			File[] files = new File(System.getProperty("user.home") + "/Downloads").listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.getName().endsWith(fileExtension)) {
						return true;
					}
				}
			}
			return false;
		});
	}

	@SuppressWarnings("unused")
	private void verifyTextIsDisplayed(String expectedText) throws Exception {
		String fileName = getDownloadedFileName();
		String text = extractTextFromPDF(fileName);
		Assert.assertTrue(text.contains(expectedText));
	}

	@SuppressWarnings("unused")
	private void verifyStatement(String expectedText) throws Exception {
		String fileName = getDownloadedFileName();
		String text = extractTextFromPDF(fileName);
		Assert.assertTrue(text.contains(expectedText));
		SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy");
		String statementDate = formatter.format(new Date());
		System.out.println("Statement Date: " + statementDate);
		Assert.assertTrue(text.contains(statementDate));
	}

	private String getDownloadedFileName() throws Exception {
		File downloadPath = new File(System.getProperty("user.dir"), "Downloads");
		File dir = new File(downloadPath.getPath());
		File[] dirContents = dir.listFiles();
		String fileName = dirContents[0].getName();
		System.out.println("Downloaded file: " + fileName);
		// Assert.assertTrue(fileName.contains((driver).getFileNameUI()));
		return fileName;
	}

	private String extractTextFromPDF(String fileName) throws Exception {
		File PDFfile = new File(
				System.getProperty("user.dir") + File.separator + "Downloads" + File.separator + fileName);
		PDDocument document = PDDocument.load(PDFfile);
		PDFTextStripper stripper = new PDFTextStripper();
		String text = stripper.getText(document);
		System.out.println(text);
		document.close();
		return text;
	}

	@SuppressWarnings("unused")
	private String getFormattedStatementDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date currentDate = new Date();
		return formatter.format(currentDate);
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();
		if (dirContents != null) {
			for (File file : dirContents) {
				if (file.getName().contains(fileName)) {
					return true;
				}
			}
		}
		return false;
	}

	public void isFileDeleted(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles(file -> file.getName().contains(fileName));

		if (dirContents != null && dirContents.length > 0) {
			for (File file : dirContents) {
				if (file.delete()) {
					System.out.println("File deleted: " + file.getName());
				} else {
					System.err.println("Failed to delete file: " + file.getName());
				}
			}
		} else {
			System.err.println("File not found: " + fileName);
		}
	}

	public static void moveToEelement(WebElement element, WebDriver driver) {
		Actions act = new Actions(driver);

		act.moveToElement(element).build().perform();

	}

	public static void compareTextWithoutCaseCheck(String actual, String expected) {
		actual = actual.toLowerCase().trim();
		expected = expected.toLowerCase().trim();
		Assert.assertEquals(actual, expected);
	}

	public static boolean verifyElementDisplay(WebElement eleElement) {
		return eleElement.isDisplayed();

	}

	public static boolean verifyElementEnabled(WebElement eleElement) {
		return eleElement.isEnabled();

	}

	public static void selectSearchDropdown(WebDriver driver, By locator, String value) {
		driver.findElement(locator).click();
		driver.findElement(locator).sendKeys(value);
		driver.findElement(locator).sendKeys(Keys.TAB);
	}

	public static void selectDropdown(WebDriver driver, By locator, String value) {
		new Select(driver.findElement(locator)).selectByVisibleText(value);
	}

	public static void selectRadioButton(WebDriver driver, By locator, String value) {
		List<WebElement> select = driver.findElements(locator);

		for (WebElement radio : select) {
			if (radio.getAttribute("value").equalsIgnoreCase(value)) {
				radio.click();

			}
		}
	}

	public static void selectCheckboxes(WebDriver driver, By locator, String value) {

		List<WebElement> abc = driver.findElements(locator);
		List<String> list = new ArrayList<String>(Arrays.asList(value.split(",")));

		for (String check : list) {
			for (WebElement chk : abc) {
				if (chk.getAttribute("value").equalsIgnoreCase(check)) {
					chk.click();
				}
			}
		}
	}

	public static String checkHoverMessage(WebDriver driver, By locator) {
		String tooltip = driver.findElement(locator).getAttribute("title");
		return tooltip;
	}

	public ArrayList<String> getImageStatus() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> allImages = driver.findElements(By.tagName("img"));
		ArrayList<String> errList = new ArrayList<String>();
		for (WebElement image : allImages) {
			boolean loaded = (Boolean) ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					image);
			if (!loaded) {
				String s = " src = " + image.getAttribute("src");
				s = s + " :alt  = " + image.getAttribute("alt");
				s = s + " :title = " + image.getAttribute("title");
				s = s + " :Location = " + image.getLocation();
				errList.add("Image not Found: " + s);
				// System.out.println("Image not Found: " + s);
			}
		}
		return errList;
	}

	public static String getPopupMessage(WebDriver driver) {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();
			message = alert.getText();
			alert.accept();
		} catch (Exception e) {
			message = null;
		}
		return message;
	}

	public static String cancelPopupMessageBox(WebDriver driver) {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();

			message = alert.getText();
			alert.dismiss();
		} catch (Exception e) {
			message = null;
		}

		return message;
	}
}
