package components;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class BillPayment_component {
	
	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait60,wait70;
	private ScreenAction screenAction;
	
	public BillPayment_component(AndroidDriver<WebElement> driver) {
		
		this.driver=driver;
		
		wait10=new WebDriverWait(driver, 10);
		wait30=new WebDriverWait(driver, 30);
		wait60=new WebDriverWait(driver, 60);
		wait70=new WebDriverWait(driver, 70);
		
		screenAction = new ScreenAction(driver);
		
	}

	public void other_billerMenu(String billerName) throws InterruptedException {
				
		WebElement billElement = wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAY'] | //*[@text='BAYAR']")));
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		billElement.click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Others'] | //*[@text='Lainnya']"))).click();
		
		Thread.sleep(1500);
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(billerName);
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'"+billerName+"')]"))).click();	
		
	}
	
	
	public void waterPayment_billerMenu(String billerName) {
		
		WebElement billElement = wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAY'] | //*[@text='BAYAR']")));
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		billElement.click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Water'] | //*[@text='Air']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'"+billerName+"')]"))).click();	

	}
	
	public void inputSubscriberNo(String subscriberNo) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pay / Top up to'] | //*[@text='Bayar / Isi ulang']"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys(subscriberNo);

		driver.findElements(By.className("android.widget.TextView")).get(4).click();
	}
	
	public void block1_selectAccount(String sourceAccount) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] "))).isDisplayed();

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//select account
		driver.findElements(By.xpath("//*[@text='Select source account'] | //*[@text='Pilih rekening sumber']")).get(1).click();
		screenAction.scrollUntilElementByXpath("//*[contains(@text,'"+sourceAccount+"')]").click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] ")));
		
		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();
		
	}
	
	public void block2_selectAccount(String sourceAccount,String amount,String desc) {
		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] "))).isDisplayed();

		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));
		//input amount
		inputFields.get(0).sendKeys(amount);
		
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select account
		driver.findElements(By.xpath("//*[@text='Select source account'] | //*[@text='Pilih rekening sumber']")).get(1).click();
		screenAction.scrollUntilElementByXpath("//*[contains(@text,'"+sourceAccount+"')]").click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] ")));

		//input desc
		inputFields.get(1).sendKeys(desc);
		
		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();		
	}
	
	public void block3_selectAccount(String sourceAccount,String amount,String desc) {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] "))).isDisplayed();

		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));
		//input amount
		inputFields.get(0).sendKeys(amount);

		//select account
		driver.findElements(By.xpath("//*[@text='Select source account'] | //*[@text='Pilih rekening sumber']")).get(1).click();
		screenAction.scrollUntilElementByXpath("//*[contains(@text,'"+sourceAccount+"')]").click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] ")));
		
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select bill period
		driver.findElements(By.xpath("//*[@text='Bill Period'] | //*[@text='Periode tagihan']")).get(1).click();
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] ")));

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElements(By.className("android.widget.TextView")).get(new Random().nextInt(3)+1).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] ")));
	
		//input desc
		inputFields.get(1).sendKeys(desc);
		
		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();		
		
	}
	
	public void summary() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Konfirmasi')] | //*[contains(@text,'Confirmation')]"))).isDisplayed();
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
	
		screenAction.scrollUntilElementByXpath("//*[@text='CONFIRM PAYMENT'] | //*[@text='KONFIRMASI PEMBAYARAN']").click();		
	}
	
	public void result() {
		wait70.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'MB-BP')]"))).isDisplayed();
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
			
		driver.findElement(By.xpath("//*[contains(@text,'success')] | //*[contains(@text,'sukses')]")).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();

	}
}
