package fundtransfer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.OTP_component;
import framework.LoadProperties;
import framework.LockdownDevice;


public class TransferInbank extends LockdownDevice{

	private EasyPin_component easyPin_comp;
	private OTP_component otp_comp;
	private FundTransfer_component fundTransfer_comp;
	
	private String sourceAccount,toAccount,amount,desc;
	private final String FOLDER = "FundTransfer/Inbank/"+deviceID;

	public TransferInbank() throws IOException {
		super();
		Properties prop=LoadProperties.getProperties("fundtransfer.properties");
		this.sourceAccount=prop.getProperty("inbankSourceAccount");
		this.toAccount=prop.getProperty("inbankToAccount");
		this.amount=prop.getProperty("inbankAmount");
		this.desc=prop.getProperty("inbankDesc");	
	}

	public TransferInbank(String deviceID,int port,int systemPort,String sourceAccount,String toAccount,String amount,String desc) throws IOException {
		super(deviceID,port,systemPort);	
		this.sourceAccount=sourceAccount;
		this.toAccount=toAccount;
		this.amount=amount;
		this.desc=desc;	
	}

	@BeforeClass
	private void loadComponent(){
		
		easyPin_comp= new EasyPin_component(driver);
		otp_comp=new OTP_component(driver);
		fundTransfer_comp= new FundTransfer_component(driver);
		
	}
	
	@Test
	private void Login(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.loginEasyPin(easyPin);
	}
	
	@Test(dependsOnMethods="Login")
	private void After_Login_Page(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.fundTransferMenu();
	}
	@Test(dependsOnMethods="After_Login_Page")
	private void Test01_Select_Payee_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectPayee(FOLDER,method.getName(),toAccount);
	}
	@Test(dependsOnMethods="Test01_Select_Payee_Page")
	private void Test02_Select_Account_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectAccount(FOLDER,method.getName(),sourceAccount, amount, desc);
	}
	@Test(dependsOnMethods="Test02_Select_Account_Page")
	private void Test03_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.summary(FOLDER,method.getName());
	}
	@Test(dependsOnMethods="Test03_Summary_Page")
	private void Test04_Transfer_Inbank_EasyPin_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		if(Long.parseLong(amount)>5000000)
			otp_comp.input(FOLDER,method.getName());
		else
			easyPin_comp.input(FOLDER,method.getName(),easyPin);
	}
	@Test(dependsOnMethods="Test04_Transfer_Inbank_EasyPin_Page")
	private void Test05_Transfer_Inbank_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.result(FOLDER,method.getName());
	}
}
