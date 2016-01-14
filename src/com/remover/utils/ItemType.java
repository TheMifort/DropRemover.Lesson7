package com.remover.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

public class ItemType {

	public int id,data;
	
	public ItemType(int id,int data)
	{
		this.id = id;
		this.data = data;
	}
	
	public static boolean containts(int id,int data,List<ItemType> list)
	{
		
		for(ItemType it : list)
		{
			if(id == it.id)
			{
				if(data == -1)return true;
				if(it.data == -1)return true;
				if(data == it.data)return true;
			}
		}
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static List<ItemType> load(FileConfiguration config,String path)
	{
		List<ItemType> list = new ArrayList<ItemType>();
		
		for(String str : config.getStringList(path))
		{
			String[] array = str.split("\\.");
			
			if(array.length == 2)
			{
				if(tryParseInt(array[0]))
				{
					list.add(new ItemType(Integer.parseInt(array[0]),Integer.parseInt(array[1])));
				}
				else
				{
					list.add(new ItemType(Material.getMaterial(array[0]).getId(),Integer.parseInt(array[1])));
				}
			}
			else
			{
				if(tryParseInt(array[0]))
				{
					list.add(new ItemType(Integer.parseInt(array[0]),-1));
				}
				else
				{
					list.add(new ItemType(Material.getMaterial(array[0]).getId(),-1));
				}
			}
			
		}
		
		
		return list;
	}
	static boolean tryParseInt(String value)
	{
		try{
			Integer.parseInt(value);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
