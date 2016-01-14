package com.remover.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.remover.DropRemover;
import com.remover.utils.command.Command;

public class Reload implements Command {

	DropRemover drop;
	public Reload(DropRemover remover)
	{
		drop = remover;
	}
	
	@Override
	public List<String> getNames() {
		// TODO Auto-generated method stub
		return Arrays.asList(new String[]{"reload","r"});
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "dropremover.reload";
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return drop.messages.getString("Help.Reload");
	}

	@Override
	public void Execute(CommandSender sender, org.bukkit.command.Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 1)
		{
		drop.loadConfig();
		sender.sendMessage(drop.messages.getString("Messages.Reload"));
		}else sender.sendMessage(drop.messages.getString("Error.Argument"));
	}
	
}
