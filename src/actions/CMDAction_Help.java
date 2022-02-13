package actions;

import zer.cmd.CMDPattern;
import zer.cmd.CMDAction;

@CMDPattern()
public class CMDAction_Help extends CMDAction
{
	@Override
	public void exec(String[] args)
	{
		System.out.println("### KPASS help note ###\n");
		System.out.println("* Log into account:");
		System.out.println("\tkpass auth <email> <password>\n");
		System.out.println("* Create an account:");
		System.out.println("\tkpass auth --new <email> <password>\n");
		System.out.println("* Log out:");
		System.out.println("\tkpass logout\n");
		System.out.println("* Find services:");
		System.out.println("\tkpass f=<pattern | \"*\" (get all)>\n");
		System.out.println("* Add service:");
		System.out.println("\tkpass a <name> <login> <password | \"gen\" (generate password)>\n");
		System.out.println("* Delete service:");
		System.out.println("\tkpass d <index>\n");
		System.out.println("* Edit service:");
		System.out.println("\tkpass e <index> <name | \"_\" (do not change)> <login | \"_\" (do not change)> <password | \"_\" (do not change) | \"gen\" (generate password)>\n");
		System.out.println("* Change password:");
		System.out.println("\tkpass pwd <new_password>\n");
	}
}