package com.remover.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.remover.DropRemover;
import com.remover.MainListener;
import com.remover.utils.command.Command;



public class Users implements Command {

	DropRemover drop;
	public Users(DropRemover drop)
	{
		this.drop = drop;
	}
	
	@Override
	public List<String> getNames() {
		// TODO Auto-generated method stub
		return Arrays.asList(new String[]{"users","u"});
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "dropremover.users";
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return drop.messages.getString("Help.Users");
	}

	@Override
	public void Execute(CommandSender sender, org.bukkit.command.Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if(args.length != 1)
			{
				sender.sendMessage(drop.messages.getString("Error.Argument"));
				return;
			}
		sender.sendMessage(drop.messages.getString("Messages.Users"));
		for(String str : MainListener.playerList)
		{
			if(isOnline(str))sender.sendMessage(ChatColor.GREEN + str);
			else sender.sendMessage(ChatColor.RED + str);
		}
	}
	private boolean isOnline(String name)
	{
		for(Player p : drop.getServer().getOnlinePlayers())
		{
			if(p.getName().equals(name))return true;
		}
		return false;
	}

}
