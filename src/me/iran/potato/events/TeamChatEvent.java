package me.iran.potato.events;

import me.iran.potato.PotatoTeams;
import me.iran.potato.factions.PlayerFaction;
import me.iran.potato.factions.PlayerFactionManager;
import me.iran.potato.util.CollectionsUtil;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TeamChatEvent implements Listener {

	PotatoTeams plugin;
	
	public TeamChatEvent (PotatoTeams plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		
		PlayerFaction faction = PlayerFactionManager.getManager().getFactionByPlayer(player);
		
		if(faction != null) {
			
			if(CollectionsUtil.getTeamChat().contains(player.getName())) {
				
				event.setCancelled(true);
				
				String msg = ChatColor.GRAY + "[" + faction.getName() + "] " + ChatColor.DARK_AQUA + player.getName() + ": " + ChatColor.GRAY + event.getMessage();
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					
					if(faction.getMembers().contains(p.getUniqueId().toString())) {
						
						p.sendMessage(msg);
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
