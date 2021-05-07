package me.dablakbandit.bank.inventory.pin;

import java.util.function.Consumer;

import me.dablakbandit.bank.config.BankItemConfiguration;
import me.dablakbandit.bank.config.BankSoundConfiguration;
import me.dablakbandit.bank.inventory.BankInventories;
import me.dablakbandit.bank.inventory.BankInventoriesManager;
import me.dablakbandit.bank.inventory.BankInventoryHandler;
import me.dablakbandit.bank.player.info.BankInfo;
import me.dablakbandit.core.players.CorePlayers;

public class BankPinMenuInventory extends BankInventoryHandler<BankInfo>{
	
	@Override
	public void init(){
		int size = descriptor.getSize();
		setAll(size, BankItemConfiguration.BANK_ITEM_BLANK);
		setItem(0, BankItemConfiguration.BANK_BACK, consumeSound(this::returnToMainMenu, BankSoundConfiguration.INVENTORY_GLOBAL_BACK));
		setItem(BankItemConfiguration.BANK_PIN_SET, consumeSound(getPinSet(), BankSoundConfiguration.INVENTORY_PIN_SET));
		setItem(BankItemConfiguration.BANK_PIN_REMOVE, (item, bi) -> bi.getPinInfo().hasPin() ? item.get() : BankItemConfiguration.BANK_ITEM_BLANK.get(), this::removePin);
	}
	
	public void removePin(CorePlayers pl, BankInfo bankInfo){
		BankSoundConfiguration.INVENTORY_PIN_REMOVE.play(pl);
		bankInfo.getPinInfo().setPin(null);
		pl.refreshInventory();
	}
	
	protected Consumer<CorePlayers> getPinSet(){
		return BankInventories.BANK_PIN_SET;
	}
	
	protected void returnToMainMenu(CorePlayers pl){
		BankInventoriesManager.getInstance().open(pl, BankInventories.BANK_MAIN_MENU);
	}
	
	@Override
	public BankInfo getInvoker(CorePlayers pl){
		return pl.getInfo(BankInfo.class);
	}
	
}
