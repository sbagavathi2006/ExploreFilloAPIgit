package testclasses;

import org.testng.annotations.Test;

import baseclass.BaseClass;
import pagefactory.LoginPage;

public class LoginTest extends BaseClass{
//	WebDriver driver;
	@Test
	public void loginValidations() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.getStartedBtnClick();
		loginPage.clickSignInLink();
		loginPage.enterUserName("username");
		loginPage.enterPwd("password");
		loginPage.loginBtnClick();
		Thread.sleep(5000);
	}

}
