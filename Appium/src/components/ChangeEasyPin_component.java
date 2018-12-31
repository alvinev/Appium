package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class ChangeEasyPin_component {
	
	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait60;
	private ScreenAction screenAction;
	
	public ChangeEasyPin_component(AndroidDriver<WebElement> driver) {
		this.driver=driver;
		
		wait10=new WebDriverWait(driver, 10);
		wait30=new WebDriverWait(driver, 30);
		wait60=new WebDriverWait(driver, 60);
		
		screenAction = new ScreenAction(driver);	
	}

	public void userProfileMenu() {
		WebElement userProfileMenu=wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PROFIL'] | //*[@text='PROFILE']")));
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		userProfileMenu.click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElements(By.className("android.widget.TextView")).get(2).click();
		
	}
	
	
	public void changeEasyPinMenu() {

		userProfileMenu();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).click();
	}
	
	public void validatePassword(String currentPassword) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Perbarui EasyPIN'] | //*[@text='Update EasyPIN'] "))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='android.widget.EditText']"))).sendKeys(currentPassword);
		
		screenAction.scrollUntilElementByXpath("//*[@text='Konfirmasi'] | //*[@text='Confirm']").click();
		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle"))).isDisplayed();
		driver.findElement(By.id("android:id/button1")).click();			
	}
	
	public void createNewEasyPin(String newEasyPin) throws Exception
	{
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Buat EasyPIN baru'] | //*[@text='Enter new EasyPIN']"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys(newEasyPin);
	
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Konfirmasi EasyPIN'] | //*[@text='Confirm new EasyPIN']"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys(newEasyPin);
		
		screenAction.scrollUntilElementByXpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']").click();

	}
}
