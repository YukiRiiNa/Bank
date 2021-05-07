package me.dablakbandit.bank.inventory.item;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

import me.dablakbandit.bank.BankPlugin;
import me.dablakbandit.core.inventory.PlayerInventoryHandler;
import me.dablakbandit.core.players.CorePlayers;

public class BankTrashcanPlayerHandler extends PlayerInventoryHandler{
	@Override
	public void parseClick(CorePlayers pl, Inventory clicked, Inventory top, InventoryClickEvent event){
		Bukkit.getScheduler().runTaskLater(BankPlugin.getInstance(), () -> pl.refreshInventory(BankTrashcanInventory.class), 1);
	}
	
	@Override
	public void parseInventoryDrag(CorePlayers pl, Inventory clicked, Inventory top, InventoryDragEvent event){
		Bukkit.getScheduler().runTaskLater(BankPlugin.getInstance(), () -> pl.refreshInventory(BankTrashcanInventory.class), 1);
	}
}
