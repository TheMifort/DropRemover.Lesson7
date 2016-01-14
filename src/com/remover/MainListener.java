package com.remover;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.remover.utils.ItemType;

public class MainListener implements Listener {

	DropRemover plugin;
	public static FileConfiguration messages,config;
	
	public static List<String> playerList = new ArrayList<String>();
	public static List<ItemType> list = new ArrayList<ItemType>();
	
	List<Location> locationList = new ArrayList<Location>();
	
	int radius = 1;
	
	public MainListener(DropRemover remover,FileConfiguration config,FileConfiguration messages)
	{
		MainListener.config = config;
		MainListener.messages = messages;
		plugin = remover;
		
		radius = config.getInt("radius");

	}
	@EventHandler(ignoreCancelled = true)
	public void onInvClick(InventoryClickEvent event)
	{
		if(event.getInventory().getName().equals(messages.getString("Title.Info")))event.setCancelled(true);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event)
	{
		locationList.clear();
		if(playerList.contains(event.getPlayer().getName()))
			locationList.add(event.getBlock().getLocation());
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(ignoreCancelled = true)
	public void onDrop(ItemSpawnEvent event)
	{
		Item item = event.getEntity();
		
		if(item.getType() != EntityType.DROPPED_ITEM)return;
		if(locationList.isEmpty())return;
		
		
		for(Location loc : locationList)
		{
			if(loc.getWorld() != event.getLocation().getWorld())continue;	
			
			if(ItemType.containts(item.getItemStack().getTypeId(), item.getItemStack().getData().getData(), list))
			if(loc.distance(event.getLocation())< radius)item.remove();
		}
	}
	
}
