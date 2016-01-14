package com.remover;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.remover.commands.Info;
import com.remover.commands.Reload;
import com.remover.commands.Toggle;
import com.remover.commands.Users;
import com.remover.utils.ItemType;
import com.remover.utils.command.CommandWorker;

import net.md_5.bungee.api.ChatColor;

public class DropRemover extends JavaPlugin {
	
	public FileConfiguration messages,config;
	
	public void onEnable()
	{
		loadConfig();
		
		MainListener main = new MainListener(this,config,messages);
		
		CommandWorker worker = new CommandWorker("dr",true,messages);
		worker.commands.add(new Toggle());
		worker.commands.add(new Reload(this));
		
		worker.commands.add(new Users(this));
		worker.commands.add(new Info(this));
		
		getCommand("dr").setExecutor(worker);
		
		getServer().getPluginManager().registerEvents(main, this);
	}
	
	public void onDisable()
	{
		
	}
	
	public void loadConfig()
	{
		File file = new File("plugins/DropRemover/config.yml");
		
		config = YamlConfiguration.loadConfiguration(file);
		
		List<String> temp = new ArrayList<String>();
		temp.add("STONE");
		temp.add("4");
		temp.add("3.0");
		
		config.addDefault("radius", 3);
		getConfig().addDefault("listForRemove", temp);
		
		config.options().copyDefaults(true);

		try {
			config.save(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		file = new File("plugins/DropRemover/messages.yml");
		
		messages = YamlConfiguration.loadConfiguration(file);
		
		messages.addDefault("Help.Toggle",ChatColor.YELLOW +  "��������/��������� �������� �����");
		messages.addDefault("Help.Reload",ChatColor.YELLOW +  "������������ ������������");

		messages.addDefault("Help.AllowedCommands",ChatColor.GREEN+  "��������� �������:");
		messages.addDefault("Messages.ToggleOn",ChatColor.GREEN + "�������� ����� ��������");
		messages.addDefault("Messages.ToggleOff",ChatColor.RED + "�������� ����� ���������");
		messages.addDefault("Messages.Reload",ChatColor.GREEN + "������������ �������������");

		
		messages.addDefault("Error.ConsoleExecute",ChatColor.RED + "���������� ������������ �� �������");
		messages.addDefault("Error.Argument",ChatColor.RED + "������ ����������");
		messages.addDefault("Error.WithoutPerms",ChatColor.RED + "������������ ����");
		messages.addDefault("Error.NotFound",ChatColor.RED + "������� �� �������");
		
		messages.addDefault("Help.Info",ChatColor.YELLOW +  "���������� � ��������� ���������");
		messages.addDefault("Help.Users",ChatColor.YELLOW +  "������������");
		messages.addDefault("Title.Info",ChatColor.YELLOW +  "��������� ��������");
		messages.addDefault("Lore.AllOfID",ChatColor.YELLOW +  "��� �������� �� ID");
		messages.addDefault("Messages.Users",ChatColor.GREEN + "������� ������������");
		
		messages.options().copyDefaults(true);
		
		try {
			messages.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		MainListener.list = ItemType.load(config, "listForRemove");
	}
}
