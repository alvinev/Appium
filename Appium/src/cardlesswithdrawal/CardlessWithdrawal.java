package cardlesswithdrawal;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.CardlessWithdrawal_component;
import components.EasyPin_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class CardlessWithdrawal  extends DeviceSetup{

	private String username,easyPin,fromAccountType,fromAccount,phoneNo,amount,desc;	
	private EasyPin_component easyPin_comp;
	private CardlessWithdrawal_component cardlessWithdrawal_comp;


	public CardlessWithdrawal() throws IOException {
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_CARDLESS_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_CARDLESS_DESC"));
	}

	public CardlessWithdrawal(String username,String fromAccountType,String amount,String desc) throws IOException
	{
		super(false,username);

		this.username=username;
		Properties prop = LoadProperties.getUserProperties(this.username);	
		this.easyPin=prop.getProperty("EASYPIN");
		this.fromAccountType=fromAccountType;
		this.fromAccount=prop.getProperty(fromAccountType);

		this.phoneNo=prop.getProperty("TO_PHONE_NO");
		this.amount=amount;
		this.desc=desc;

	}

	@BeforeClass
	private void loadComponent() {
		easyPin_comp= new EasyPin_component(driver);
		cardlessWithdrawal_comp=new CardlessWithdrawal_component(driver);

	}

	@Test
	private void Test01_Login(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.loginEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test01_Login")
	private void Test02_After_Login_Page(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		cardlessWithdrawal_comp.cardlessWdrMenu();
	}

	@Test(dependsOnMethods="Test02_After_Login_Page")
	public void Test03_Select_Payee_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		cardlessWithdrawal_comp.selectPhoneNo(phoneNo);
	}

	@Test(dependsOnMethods="Test03_Select_Payee_Page")
	public void Test04_Select_Account_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		cardlessWithdrawal_comp.selectAccount(fromAccount, amount, desc);
	}

	@Test(dependsOnMethods="Test04_Select_Account_Page")
	public void Test05_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		cardlessWithdrawal_comp.summary();
	}

	@Test(dependsOnMethods="Test05_Summary_Page")
	public void Test06_CardlessWdr_EasyPin_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.inputEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test06_CardlessWdr_EasyPin_Page")
	public void Test07_CardlessWdr_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		cardlessWithdrawal_comp.result(fromAccountType);
	}

}
