package me.dablakbandit.bank.player.info.admin;

import java.util.List;

import me.dablakbandit.bank.implementations.blacklist.BlacklistedItem;
import me.dablakbandit.bank.player.info.IBankInfo;
import me.dablakbandit.bank.player.permission.PermissionCheck;
import me.dablakbandit.core.players.CorePlayers;

public class BankAdminInfo extends IBankInfo{
	
	private BlacklistedItem			item;
	private CorePlayers				opened;
	private List<PermissionCheck>	permissionsCheck;
	
	public BankAdminInfo(CorePlayers pl){
		super(pl);
		init();
	}
	
	private void init(){
	}
	
	public BlacklistedItem getItem(){
		return item;
	}
	
	public void setItem(BlacklistedItem item){
		this.item = item;
	}
	
	public CorePlayers getOpened(){
		return opened;
	}
	
	public void setOpened(CorePlayers opened){
		this.opened = opened;
	}
	
	public List<PermissionCheck> getPermissionsCheck(){
		return permissionsCheck;
	}
	
	public void setPermissionsCheck(List<PermissionCheck> permissionsCheck){
		this.permissionsCheck = permissionsCheck;
	}
}
