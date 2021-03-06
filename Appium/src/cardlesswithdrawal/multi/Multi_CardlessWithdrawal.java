package cardlesswithdrawal.multi;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import cardlesswithdrawal.CardlessWithdrawal;
import framework.LoadTestCase;

public class Multi_CardlessWithdrawal {

	@Factory(dataProvider="cardlessWdr")
	private Object[] cardlessCreateInstances(String username,String fromAccountType,String amount,String desc) throws IOException {
		return new Object[] {new CardlessWithdrawal(username,fromAccountType,amount,desc)};
	}

	@DataProvider(name="cardlessWdr")
	private static Object[][] cardlessDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("CardlessWithdrawal/Multi/Multi_CardlessWithdrawal.txt");
		return dataArray;
	}
}
