package me.dablakbandit.bank.implementations.vault;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

import me.dablakbandit.bank.BankPlugin;
import me.dablakbandit.bank.api.Economy_Bank;
import me.dablakbandit.bank.config.BankPluginConfiguration;
import me.dablakbandit.bank.implementations.BankImplementation;
import me.dablakbandit.bank.log.BankLog;
import net.milkbowl.vault.economy.Economy;

public class VaultImplementation extends BankImplementation{
	
	private static VaultImplementation vaultImplementation = new VaultImplementation();
	
	public static VaultImplementation getInstance(){
		return vaultImplementation;
	}
	
	private VaultImplementation(){
		
	}
	
	@Override
	public void load(){
		
	}
	
	@Override
	public void enable(){
		Bukkit.getServer().getServicesManager().register(Economy.class, Economy_Bank.getInstance(), BankPlugin.getInstance(), ServicePriority.Highest);
		Bukkit.getScheduler().runTaskLater(BankPlugin.getInstance(), () -> {
			BankLog.info(BankPluginConfiguration.BANK_LOG_PLUGIN_LEVEL, "Economy handler is " + Bukkit.getServer().getServicesManager().getRegistration(Economy.class).getProvider().getName());
		}, 1);
	}
	
	@Override
	public void disable(){
		Bukkit.getServer().getServicesManager().unregister(Economy.class, Economy_Bank.getInstance());
	}
	
}
