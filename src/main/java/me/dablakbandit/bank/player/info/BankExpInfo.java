package me.dablakbandit.bank.player.info;

import org.bukkit.Bukkit;

import me.dablakbandit.bank.BankPlugin;
import me.dablakbandit.bank.config.BankLanguageConfiguration;
import me.dablakbandit.bank.config.BankPluginConfiguration;
import me.dablakbandit.bank.utils.Format;
import me.dablakbandit.core.players.CorePlayers;
import me.dablakbandit.core.players.info.JSONInfo;
import me.dablakbandit.core.utils.EXPUtils;

public class BankExpInfo extends IBankInfo implements JSONInfo{
	
	private double	exp;
	private double	offlineExp;
	
	public BankExpInfo(CorePlayers pl){
		super(pl);
	}
	
	public double getExp(){
		return exp;
	}
	
	public int getExpLevel(){
		int level = 0;
		while(exp >= EXPUtils.getTotalExperience(level + 1)){
			level++;
		}
		return level;
	}
	
	@Override
	public void jsonInit(){
		if(pl.getPlayer() == null){ return; }
		Bukkit.getScheduler().scheduleSyncDelayedTask(BankPlugin.getInstance(), this::finishLoad);
	}
	
	@Override
	public void jsonFinal(){
		if(pl.getPlayer() == null){ return; }
		
	}
	
	private void finishLoad(){
		if(offlineExp > 0){
			double newExp = this.exp + offlineExp;
			if(newExp < 0.0 || newExp > BankPluginConfiguration.BANK_EXP_MAX.get()){
				this.offlineExp = Math.max(0, Math.floor(BankPluginConfiguration.BANK_EXP_MAX.get() - this.exp));
			}
			addExp(offlineExp);
			this.offlineExp = 0;
		}
	}
	
	public void withdrawExp(CorePlayers pl, double withdraw){
		if(withdrawExp(withdraw)){
			int total = EXPUtils.getTotalExperience(pl.getPlayer());
			EXPUtils.setTotalExperience(pl.getPlayer(), (int)Math.min(Integer.MAX_VALUE, total + withdraw));
			BankLanguageConfiguration.sendMessage(pl, BankLanguageConfiguration.MESSAGE_EXP_WITHDRAW.get().replaceAll("<exp>", Format.formatExp(withdraw)));
		}
	}
	
	private boolean withdrawExp(double withdraw){
		if(withdraw <= this.exp){
			this.exp -= withdraw;
			// if(BankPluginConfiguration.LOGS_ENABLED.get() && BankPluginConfiguration.LOGS_EXP.get()){
			// log("Exp withdrew: " + i + ", new amount: " + exp);
			// }
			save(BankPluginConfiguration.BANK_SAVE_EXP_DEPOSIT);
			return true;
		}
		return false;
	}
	
	private void depositExp(double add){
		this.exp += add;
		save(BankPluginConfiguration.BANK_SAVE_EXP_DEPOSIT);
		// if(BankPluginConfiguration.LOGS_ENABLED.get() && BankPluginConfiguration.LOGS_EXP.get()){
		// log("Exp deposited: " + format(d) + ", new amount: " + exp);
		// }
	}
	
	public void depositExp(CorePlayers pl, double deposit){
		int total = EXPUtils.getTotalExperience(pl.getPlayer());
		deposit = Math.min(total, deposit);
		boolean full = false;
		if(this.exp >= BankPluginConfiguration.BANK_EXP_MAX.get()){
			deposit = 0;
		}else{
			double newExp = this.exp + deposit;
			if(newExp < 0.0 || newExp > BankPluginConfiguration.BANK_EXP_MAX.get()){
				full = true;
				deposit = Math.max(0, Math.floor(BankPluginConfiguration.BANK_EXP_MAX.get() - this.exp));
			}
		}
		depositExp(deposit);
		EXPUtils.setTotalExperience(pl.getPlayer(), (int)Math.min(Integer.MAX_VALUE, total - deposit));
		if(deposit != 0){
			BankLanguageConfiguration.sendMessage(pl, BankLanguageConfiguration.MESSAGE_EXP_DEPOSIT.get().replaceAll("<exp>", Format.formatExp(deposit)));
		}
		if(full){
			// player.sendMessage(LanguageConfiguration.MESSAGE_EXP_IS_FULL.getMessage());
		}
	}
	
	@Deprecated
	public void setExp(double exp){
		this.exp = exp;
	}
	
	@Deprecated
	public void addExp(double oexp){
		this.exp += oexp;
	}
	
	public void addOfflineExp(double exp){
		this.offlineExp += exp;
	}
	
	public double getOfflineExp(){
		return offlineExp;
	}
	
	public void setOfflineExp(double min){
		this.offlineExp = min;
	}
	
	@Deprecated
	public boolean subtractExp(int amount){
		if(exp <= amount){
			exp -= amount;
			save(BankPluginConfiguration.BANK_SAVE_EXP_WITHDRAW);
			return true;
		}
		return false;
	}
}
