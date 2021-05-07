package me.dablakbandit.bank.player.converter.old.sqlite;

import java.sql.Connection;

import me.dablakbandit.bank.player.converter.old.base.SQLBaseLoader;
import me.dablakbandit.core.players.CorePlayers;

public class OldSQLLiteLoader extends SQLBaseLoader{
	
	private static OldSQLLiteLoader loader = new OldSQLLiteLoader();
	
	public static OldSQLLiteLoader getInstance(){
		return loader;
	}
	
	private OldSQLLiteLoader(){
		
	}
	
	public boolean isConnected(){
		try{
			list_uuid.setString(1, "");
			list_uuid.execute();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void load(CorePlayers pl, boolean check){
		super.load(pl, check);
	}
	
	@Override
	public void close(Connection connection){
		super.close(connection);
		closeStatements();
	}
	
}
