package fundtransfer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import framework.LoadProperties;
import onboarding.Login_LockdownDevice;

public class Schedule_TransferInbank extends Login_LockdownDevice{
	
	private String sourceAccount,toAccount,amount,desc,recurrence,frequency;	
	private final String FOLDER = "FundTransfer/Schedule/Inbank/"+deviceID;
	
	public Schedule_TransferInbank() throws IOException {	
		super();
		Properties prop=LoadProperties.getProperties("fundtransfer.properties");
		this.sourceAccount=prop.getProperty("schInbankSourceAccount");
		this.toAccount=prop.getProperty("schInbankToAccount");
		this.amount=prop.getProperty("schInbankAmount");
		this.desc=prop.getProperty("schInbankDesc");
		this.recurrence=prop.getProperty("schInbankRecurrence");
		this.frequency=prop.getProperty("schInbankFrequency");	
	}
	
	public Schedule_TransferInbank(String deviceID,int port,int systemPort,String sourceAccount,String toAccount,String amount,String desc,String recurrence,String frequency) throws IOException {
		super(deviceID,port,systemPort);
		this.sourceAccount=sourceAccount;
		this.toAccount=toAccount;
		this.amount=amount;
		this.desc=desc;	
		this.recurrence=recurrence;
		this.frequency=frequency;	
	}
	
	@Test
	private void Login(Method method) throws Exception
	{	
		super.login(method);
	}
	
	@Test(dependsOnMethods="Login")
	private void After_Login_Page(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.fundTransferMenu();
	}	

	@Test(dependsOnMethods="After_Login_Page")
	public void Test01_Select_Payee_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectPayee(FOLDER,method.getName(),toAccount);
	}

	@Test(dependsOnMethods="Test01_Select_Payee_Page")
	public void Test02_Select_Account_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectAccountSchedule(FOLDER,method.getName(),sourceAccount,amount,desc);
	}
	
	@Test(dependsOnMethods="Test02_Select_Account_Page")
	public void Test03_Schedule_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectSchedule(FOLDER,method.getName(),recurrence,frequency);
	}
	
	@Test(dependsOnMethods="Test03_Schedule_Page")
	public void Test04_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.summarySchedule(FOLDER,method.getName());
	}

	@Test(dependsOnMethods="Test04_Summary_Page")
	public void Test05_Schedule_Transfer_Inbank_EasyPin_Page(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		if(Long.parseLong(amount)>5000000)
			otp_comp.input(FOLDER,method.getName());
		else
			easyPin_comp.input(FOLDER,method.getName(),easyPin);

	}
	@Test(dependsOnMethods="Test05_Schedule_Transfer_Inbank_EasyPin_Page")
	public void Test06_Schedule_Transfer_Inbank_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.result(FOLDER,method.getName());
	}
	
}
