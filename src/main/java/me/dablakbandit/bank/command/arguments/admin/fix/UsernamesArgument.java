package me.dablakbandit.bank.command.arguments.admin.fix;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.dablakbandit.bank.command.base.BankEndArgument;
import me.dablakbandit.bank.database.BankDatabaseManager;
import me.dablakbandit.core.command.config.CommandConfiguration;

public class UsernamesArgument extends BankEndArgument{
	
	public UsernamesArgument(CommandConfiguration.Command command){
		super(command);
	}
	
	private Thread	threadFixUsernames;
	private boolean	confirm;
	
	@Override
	protected void onArgument(CommandSender s, Command cmd, String label, String[] args, String[] original){
		if(threadFixUsernames != null){
			s.sendMessage(ChatColor.RED + "Process already running");
			return;
		}
		if(!confirm){
			s.sendMessage(ChatColor.RED + "This command is to fix missing usernames in the database.");
			s.sendMessage(ChatColor.RED + "It may take some time, and you might have to repeat it several times.");
			s.sendMessage(ChatColor.RED + "Please keep an eye on console for progress info.");
			s.sendMessage(ChatColor.GREEN + "Repeat the command to confirm");
			confirm = true;
			return;
		}
		s.sendMessage(ChatColor.GREEN + "Fixing missing usernames started.");
		threadFixUsernames = new Thread(() -> {
			BankDatabaseManager.getInstance().getInfoDatabase().fixMissingUsernames();
			threadFixUsernames = null;
		});
		threadFixUsernames.start();
	}
	
}
