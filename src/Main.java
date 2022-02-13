import zer.http.HTTPConfig;
import java.util.Scanner;
import zer.cmd.CMDHandler;
import actions.CMDAction_CreateAccount;
import actions.CMDAction_Auth;
import actions.CMDAction_Logout;
import actions.CMDAction_GetServices;
import actions.CMDAction_ChangePassword;
import actions.CMDAction_EditService;
import actions.CMDAction_AddService;
import actions.CMDAction_DeleteService;
import actions.CMDAction_Help;
import actions.CMDAction_Temp;

public class Main
{
	public static void main(String[] args)
	{
		HTTPConfig.setHost("80.249.146.66");
		HTTPConfig.setPort(80);

		CMDHandler handler = new CMDHandler();
		
		handler.addAction(new CMDAction_CreateAccount());
		handler.addAction(new CMDAction_Auth());
		handler.addAction(new CMDAction_Logout());
		handler.addAction(new CMDAction_GetServices());
		handler.addAction(new CMDAction_ChangePassword());
		handler.addAction(new CMDAction_Temp());
		handler.addAction(new CMDAction_EditService());
		handler.addAction(new CMDAction_AddService());
		handler.addAction(new CMDAction_DeleteService());
		handler.addAction(new CMDAction_Help());

		System.out.println();

		handler.handle(args);
	}
}