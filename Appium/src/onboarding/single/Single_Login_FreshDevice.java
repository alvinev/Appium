package onboarding.single;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import onboarding.Login_FreshDevice;

public class Single_Login_FreshDevice {

	@Factory(dataProvider="login_freshDevice")
	private Object[] loginFreshDeviceCreateInstances(String username) throws IOException {
		return new Object[] {new Login_FreshDevice(username)};
	}

	@DataProvider(name="login_freshDevice")
	private static Object[][] loginFreshDeviceDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("Onboarding/Single/Single_Login_FreshDevice.txt");
		return dataArray;
	}
}
