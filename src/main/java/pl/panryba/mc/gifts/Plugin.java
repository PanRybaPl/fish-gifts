/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.panryba.mc.gifts;

import com.avaje.ebean.EbeanServer;
import pl.panryba.mc.db.FishDbPlugin;
import pl.panryba.mc.gifts.commands.GiftCommand;
import pl.panryba.mc.gifts.entities.ReceivedGift;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author PanRyba.pl
 */
public class Plugin extends FishDbPlugin {

    private PluginApi api;
    
    @Override
    public void onEnable() {
        
        EbeanServer database = getCustomDatabase();
        api = new PluginApi(database);
        
        readConfig();
        
        getCommand("prezent").setExecutor(new GiftCommand(api));
        getCommand("gifts").setExecutor(new GiftsCommand(this, api));
    }

    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = super.getDatabaseClasses();
        list.add(ReceivedGift.class);
        
        return list;
    }

    void reloadGiftsConfig() {
        reloadConfig();
        readConfig();
    }
    
    private void readConfig() {
        Collection<String> enabled = getConfig().getStringList("enabled");
        api.setEnabled(enabled);
    }
    
}
