/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.panryba.mc.gifts.commands;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.panryba.mc.gifts.GiftInfo;
import pl.panryba.mc.gifts.PluginApi;
import pl.panryba.mc.gifts.parts.ColouredLeatherArmorSetPart;
import pl.panryba.mc.sets.api.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author PanRyba.pl
 */
public class GiftCommand implements CommandExecutor {

    private final static String VIP_GROUP = "vip";
    private final static String YT_GROUP = "yt";
    private final PluginApi api;
    private final HashMap<String, GiftInfo> gifts;

    public GiftCommand(PluginApi api) {
        this.api = api;

        this.gifts = new HashMap<>();

        this.gifts.put("enderchest", new GiftInfo("skrzynia kresu", "skrzynie kresu", 14, null, null,
                new ItemSetPart[]{new ItemSetPart(Material.ENDER_CHEST, 1)}));

        /*
        this.gifts.put("gabka", new GiftInfo("gabka", "gabke", 7, null, 0,
                new ItemSetPart[]{new ItemSetPart(Material.SPONGE, 1)}));
        */
        
        this.gifts.put("start", new GiftInfo("zestaw startowy", "zestaw startowy", 4, null, null,
                new ItemSetPart[]{
            new ItemSetPart(Material.STONE_SWORD, 1),
            new ItemSetPart(Material.IRON_PICKAXE, 1),
            new ItemSetPart(Material.STONE_SPADE, 1),
            new ItemSetPart(Material.STONE_AXE, 1),
            new ItemSetPart(Material.STONE_HOE, 1),
            new ColouredLeatherArmorSetPart(Material.LEATHER_HELMET, 1, Color.fromRGB(255, 255, 0)),
            new ColouredLeatherArmorSetPart(Material.LEATHER_CHESTPLATE, 1, Color.fromRGB(255, 255, 0)),
            new ColouredLeatherArmorSetPart(Material.LEATHER_LEGGINGS, 1, Color.fromRGB(255, 255, 0)),
            new ColouredLeatherArmorSetPart(Material.LEATHER_BOOTS, 1, Color.fromRGB(255, 255, 0))
        }));

        this.gifts.put("yt", new GiftInfo("zestaw YouTubera", "zestaw YouTubera", 7, "gifts.youtube", YT_GROUP,
                new ItemSetPart[]{
            new ItemSetPart(Material.DIAMOND_BOOTS, 1),
            new ItemSetPart(Material.DIAMOND_LEGGINGS, 1),
            new ItemSetPart(Material.DIAMOND_CHESTPLATE, 1),
            new ItemSetPart(Material.DIAMOND_HELMET, 1),
            new ItemSetPart(Material.DIAMOND_SWORD, 1),
            new ItemSetPart(Material.DIAMOND_PICKAXE, 1)
        }));

        /*
        this.gifts.put("vip", new GiftInfo("zestaw VIPa", "zestaw VIPa", 4, VIP_GROUP, 1,
                new ItemSetPart[]{
            new ItemSetPart(Material.DIAMOND_BOOTS, 1),
            new ItemSetPart(Material.DIAMOND_LEGGINGS, 1),
            new ItemSetPart(Material.DIAMOND_CHESTPLATE, 1),
            new ItemSetPart(Material.DIAMOND_HELMET, 1),
            new ItemSetPart(Material.DIAMOND_SWORD, 1),
            new ItemSetPart(Material.DIAMOND_PICKAXE, 1),
            new ItemSetPart(Material.BOW, 1),
            new ItemSetPart(Material.ARROW, 64),
            new ItemSetPart(Material.EXP_BOTTLE, 64),
            new ItemSetPart(Material.EXP_BOTTLE, 64),
            new ItemSetPart(Material.GOLD_BLOCK, 16)
        }));

        this.gifts.put("svip", new GiftInfo("zestaw Super VIPa", "zestaw Super VIPa", 7, VIP_GROUP, 2,
                new ItemSetPart[]{
            new ItemSetPart(Material.DIAMOND_SWORD, 1, new ItemSetEnchantment[]{new ItemSetEnchantment(Enchantment.DAMAGE_ALL, 5)}),
            new ItemSetPart(Material.DIAMOND_PICKAXE, 1, new ItemSetEnchantment[]{
                new ItemSetEnchantment(Enchantment.DURABILITY, 3),
                new ItemSetEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3)
            }),
            new ItemSetPart(Material.DIAMOND_HELMET, 1, new ItemSetEnchantment[]{new ItemSetEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2)}),
            new ItemSetPart(Material.DIAMOND_CHESTPLATE, 1, new ItemSetEnchantment[]{new ItemSetEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)}),
            new ItemSetPart(Material.DIAMOND_LEGGINGS, 1, new ItemSetEnchantment[]{new ItemSetEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2)}),
            new ItemSetPart(Material.DIAMOND_BOOTS, 1, new ItemSetEnchantment[]{new ItemSetEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2)}),
            new ItemSetPart(Material.FISHING_ROD, 1, new ItemSetEnchantment[]{
                new ItemSetEnchantment(Enchantment.DURABILITY, 2),
                new ItemSetEnchantment(Enchantment.LUCK, 2)
            }),
            new EnchGoldenAppleItemSetPart(3),
            new ItemSetPart(Material.NETHER_WARTS, 3),
            new ItemSetPart(Material.BLAZE_ROD, 3),
            new ItemSetPart(Material.FERMENTED_SPIDER_EYE, 3),
            new ItemSetPart(Material.MAGMA_CREAM, 3),
            new ItemSetPart(Material.SPECKLED_MELON, 3)
        }));
        */
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (!cmnd.getName().equalsIgnoreCase("prezent")) {
            return true;
        }

        if (!(cs instanceof Player)) {
            return false;
        }

        final Player player = (Player) cs;

        if (strings.length == 0) {
            List<String> msgs = new ArrayList<>();
            msgs.add(ChatColor.YELLOW + "Lista dostepnych prezentow:");

            for (String key : this.gifts.keySet()) {
                GiftInfo giftInfo = this.gifts.get(key);
                msgs.add(ChatColor.YELLOW + "/prezent " + key + " - " + ChatColor.GRAY + giftInfo.getName());
            }

            String[] msgsArray = new String[msgs.size()];
            msgs.toArray(msgsArray);
            player.sendMessage(msgsArray);

            return true;
        }

        if (strings.length != 1) {
            return false;
        }

        String giftName = strings[0];

        GiftInfo giftInfo = this.gifts.get(giftName.toLowerCase());

        if (giftInfo == null) {
            player.sendMessage(ChatColor.YELLOW + "Takiego prezentu nie dajemy.");
            return true;
        }
        
        if(api.giftDisabled(strings[0])) {
            player.sendMessage(ChatColor.YELLOW + "Ten prezent jest tymczasowo wylaczony.");
            return true;            
        }

        if (giftInfo.requiresGroup()) {
            String requiredPermission = giftInfo.getRequiredPermission();
            String requiredGroup = giftInfo.getGroupName();

            if (!player.hasPermission(requiredPermission)) {
                player.sendMessage(ChatColor.YELLOW + "Tobie nie nalezy sie taki prezent - musisz posiadac status " + requiredGroup);
                return true;
            }
        }

        Date checkDate = new Date(new Date().getTime() - giftInfo.getPeriodDays() * 24 * 60 * 60 * 1000);

        if (api.hasReceivedGiftAfter(player, giftName, checkDate)) {
            player.sendMessage(ChatColor.YELLOW + "Juz niedawno otrzymales wybrany prezent. Mozesz go odebrac raz na " +
                    giftInfo.getPeriodDays() + " " + (giftInfo.getPeriodDays() == 1 ? "dzien" : "dni") + ".");
            return true;
        }

        ItemSet set = giftInfo.getSet();
        GiveSetResult result = SetsManager.give(set, player, new SetCreatedListener() {

            @Override
            public void onSetPrepared(ItemStack[] stacks) {
                appendGiftLore(player, stacks);
            }
        });
        
        if(!result.getResult()) {
            switch(result.getReason()) {
                case NOT_ENOUGH_INVENTORY:
                    sendNotEnoughSlots(player, result.getTotalSlots(), result.getMissingSlots());
                    break;
            }
            
            return true;
        }

        api.setGiftReceived(player, giftName, new Date());
        player.sendMessage(ChatColor.YELLOW + "Wybrany prezent zostal umieszczony w Twoim plecaku");
        api.sendToOthers(player, ChatColor.GREEN + player.getName() + ChatColor.YELLOW + " odebral prezent - " + ChatColor.GREEN + giftInfo.getWhat());

        return true;
    }

    private void sendNotEnoughSlots(Player player, int totalRequired, int slotsRequired) {
        player.sendMessage(ChatColor.YELLOW + "Nie masz miejsca w plecaku aby otrzymac wybrany prezent. Potrzebujesz jeszcze " + slotsRequired + " z " + totalRequired + " slotow.");
    }

    private void appendGiftLore(Player player, ItemStack[] stacks) {
        String playerName = player.getName();

        for (ItemStack stack : stacks) {
            ItemMeta meta = stack.getItemMeta();
            List<String> lore = new ArrayList<>();

            lore.add("Prezent dla " + playerName + " od Pana Ryby");

            meta.setLore(lore);
            stack.setItemMeta(meta);
        }
    }
}
