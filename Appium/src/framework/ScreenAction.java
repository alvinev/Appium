package framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class ScreenAction   {

	private AndroidDriver<WebElement> driver;
	private Dimension screenSize;
	private int posX,posY;

	public ScreenAction(AndroidDriver<WebElement> driver)  {
		this.driver=driver;

		screenSize = driver.manage().window().getSize();
		posX=screenSize.getWidth()/2;
		posY=screenSize.getHeight()/4;
	}

	public void vScroll() {
		driver.swipe(posX, posY*2, posX, posY, 3000);
	}

	public void verticalScroll() {
		if(screenSize.getHeight()<1000)
			vScroll();
	}

	public WebElement scrollUntilElementByXpath(String xpathExpression) {

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> we = driver.findElements(By.xpath(xpathExpression));
		while (!(we.size()>0)){
			driver.swipe(posX, posY*2, posX, posY, 3000);
			we  = driver.findElements(By.xpath(xpathExpression));
		}
		return we.get(0);
	}

	public void scrollUntilElementInvisibleByXpath(String xpathExpression) {

		List<WebElement> we = driver.findElements(By.xpath(xpathExpression));
		if(we.size()>0) {
			while (we.size()>0){
				driver.swipe(posX, posY*2, posX, posY, 3000);
				we  = driver.findElements(By.xpath(xpathExpression));
			}
		}

	}
	


}
