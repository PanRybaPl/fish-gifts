/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.panryba.mc.gifts;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanRyba.pl
 */
public class GiftsCommand implements CommandExecutor {
    private final Plugin plugin;
    private final PluginApi api;

    public GiftsCommand(Plugin plugin, PluginApi api) {
        this.plugin = plugin;
        this.api = api;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if(strings.length != 1) {
            return false;
        }
        
        if(!strings[0].equalsIgnoreCase("reload")) {
            return false;
        }
        
        this.plugin.reloadGiftsConfig();
        cs.sendMessage(ChatColor.YELLOW + "Gifts config reloaded");
        return true;
    }
    
}
