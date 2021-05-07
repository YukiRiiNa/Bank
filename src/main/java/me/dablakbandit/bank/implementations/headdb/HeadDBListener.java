package me.dablakbandit.bank.implementations.headdb;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.arcaniax.hdb.api.DatabaseLoadEvent;
import me.dablakbandit.bank.config.BankItemConfiguration;

public class HeadDBListener implements Listener{
	
	private static HeadDBListener headDBListener = new HeadDBListener();
	
	public static HeadDBListener getInstance(){
		return headDBListener;
	}
	
	private HeadDBListener(){
		
	}
	
	@EventHandler
	public void onDatabaseLoad(DatabaseLoadEvent e){
		BankItemConfiguration.getInstance().load();
	}
}
