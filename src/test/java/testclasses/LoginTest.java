package testclasses;

import org.testng.annotations.Test;

import baseclass.BaseClass;
import pagefactory.LoginPage;
import utilities.ExcelDataProvider;

public class LoginTest extends BaseClass{
	
	@Test(dataProvider = "loginTestData", dataProviderClass = ExcelDataProvider.class)
	public void loginValidations(String validation, String username, String password, String message) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.getStartedBtnClick();
		loginPage.clickSignInLink();
		loginPage.enterUserName(username);
		loginPage.enterPwd(password);
		loginPage.loginBtnClick();
	}

}
