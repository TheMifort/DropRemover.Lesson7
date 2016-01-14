package com.remover.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.remover.DropRemover;
import com.remover.MainListener;
import com.remover.utils.ItemType;
import com.remover.utils.command.Command;

public class Info implements Command {

	DropRemover drop;
	
	public Info(DropRemover drop)
	{
		this.drop = drop;
	}
	
	@Override
	public List<String> getNames() {
		// TODO Auto-generated method stub
		return Arrays.asList(new String[]{"info","i"});
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "dropremover.info";
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return drop.messages.getString("Help.Info");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void Execute(CommandSender sender, org.bukkit.command.Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if(args.length != 1)
		{
			sender.sendMessage(drop.messages.getString("Error.Argument"));
			return;
		}
		Player player = (Player)sender;
		
		Inventory inv = drop.getServer().createInventory(player, 27, drop.messages.getString("Title.Info"));
		
		for(ItemType it : MainListener.list)
		{
			ItemStack is;
			if(it.data != -1)
			is = new ItemStack(it.id,1,(short)0,(byte)it.data);
			else
			{
				is = new ItemStack(it.id);
				ItemMeta im = is.getItemMeta();
				im.setLore(Arrays.asList(new String[]{drop.messages.getString("Lore.AllOfID") +": "+ it.id}));
				is.setItemMeta(im);
			}
			inv.addItem(is);
		}
		player.openInventory(inv);
	}
	@Override
	public void ExecuteConsole(CommandSender sender, org.bukkit.command.Command cmd, String[] args) {
		sender.sendMessage(MainListener.messages.getString("Error.ConsoleExecute"));
	}
}
