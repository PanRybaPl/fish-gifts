/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.panryba.mc.gifts;

import com.avaje.ebean.EbeanServer;
import org.bukkit.entity.Player;
import pl.panryba.mc.gifts.entities.ReceivedGift;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author PanRyba.pl
 */
public class PluginApi {
    private final EbeanServer database;
    private final Set<String> enabled;
    
    public PluginApi(EbeanServer database) {
        this.database = database;
        this.enabled = new HashSet<>();
    }
    
    public void setEnabled(Collection<String> items) {
        enabled.clear();
        enabled.addAll(items);
    }
    
    public boolean hasReceivedGiftAfter(Player player, String giftName, Date after) {
        ReceivedGift gift = ReceivedGift.get(this.database, player.getName(), giftName);
        
        if(gift.getWhen() == null)
            return false;
        
        return gift.getWhen().getTime() > after.getTime();
    }
    
    public void setGiftReceived(Player player, String giftName, Date when) {
        ReceivedGift gift = ReceivedGift.get(database, player.getName(), giftName);
        gift.setWhen(when);
        
        this.database.save(gift);
    }
    
    public void sendToOthers(Player player, String message) {
        String playerName = player.getName();
        
        for(Player other : player.getServer().getOnlinePlayers()) {
            if(!other.getName().equals(playerName))
                other.sendMessage(message);
        }
    }

    public boolean giftDisabled(String string) {
        return !this.enabled.contains(string);
    }
}
