package testclasses;

import org.testng.annotations.Test;

import baseclass.BaseClass;
import pagefactory.LoginPage;
import utilities.ExcelDataProvider;

public class LoginTest extends BaseClass{
	
	@Test(dataProvider = "loginTestData", dataProviderClass = ExcelDataProvider.class)
	public void loginValidations(String validation, String userName, String password, String message) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.getStartedBtnClick();
		loginPage.clickSignInLink();
		loginPage.enterUserName(userName);
		loginPage.enterPwd(password);
		loginPage.loginBtnClick();
	}

}
