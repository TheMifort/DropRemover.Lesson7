package com.remover.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.remover.MainListener;
import com.remover.utils.command.Command;

public class Toggle implements Command {

	@Override
	public List<String> getNames() {
		// TODO Auto-generated method stub
		return Arrays.asList(new String[]{"toggle","t"});
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "dropremover.toggle";
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return MainListener.messages.getString("Help.Toggle");
	}

	@Override
	public void Execute(CommandSender sender, org.bukkit.command.Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 1)
		{
			if(MainListener.playerList.contains(sender.getName()))
			{
				MainListener.playerList.remove(sender.getName());
				sender.sendMessage(MainListener.messages.getString("Messages.ToggleOff"));
			}
			else 
			{
				MainListener.playerList.add(sender.getName());
				sender.sendMessage(MainListener.messages.getString("Messages.ToggleOn"));
			}
		}
		else sender.sendMessage(MainListener.messages.getString("Error.Argument"));
	}
	@Override
	public void ExecuteConsole(CommandSender sender, org.bukkit.command.Command cmd, String[] args) {
		// TODO Auto-generated method stub
		sender.sendMessage(MainListener.messages.getString("Error.ConsoleExecute"));
	}

}
