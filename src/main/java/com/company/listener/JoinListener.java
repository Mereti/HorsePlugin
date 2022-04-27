package com.company.listener;

import com.company.model.Authme;
import com.company.model.Gamer;
import com.company.StaticConfig;
import com.company.service.AuthmeService;
import com.company.service.GamerService;
import com.company.service.HorseService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.print.Book;
import java.awt.print.Printable;
import java.util.ArrayList;

public class JoinListener implements Listener {

    private GamerService gamerService;
    private HorseService horseService;
    private AuthmeService authmeService;

    public JoinListener(GamerService gamerService, HorseService horseService, AuthmeService authmeService) {
        this.gamerService = gamerService;
        this.horseService = horseService;
        this.authmeService = authmeService;
    }

    private static Location spawn = Bukkit.getWorld("world").getSpawnLocation();
    private ItemStack item = new ItemStack(Material.HORSE_SPAWN_EGG, 1);
    private ItemStack compass = createCompass();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.teleport(spawn);
        player.getInventory().clear();
        player.setHealthScale(20D);
        player.setInvulnerable(true);
        player.setCanPickupItems(false);
        player.getInventory().setItem(0, compass);
        createHelpBook(player);
        Gamer gamer = gamerService.loadGamer(player.getName());
        horseService.loadHorses(gamer);
        checkFirstLogin(gamer.getAuthmeId(), player);

    }

    private static ItemStack createCompass() {
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = compass.getItemMeta();
        meta.setDisplayName(StaticConfig.COMPASS_NAME);
        compass.setItemMeta(meta);
        return compass;
    }

    private boolean checkFirstLogin(int id, Player player){
        Authme authme = authmeService.loaddAuthme(id);
        if(authme.getLastLogin()==(authme.getRegdate())){
            player.getInventory().setItem(1, item);
        } else player.sendMessage("Otrzymałeś już jajko za 1 logowanie");
        return false;
    }

    private void createHelpBook(Player player){
       ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
       BookMeta bookMeta = (BookMeta) book.getItemMeta();
       bookMeta.setAuthor("Nuqta");
       bookMeta.setTitle(ChatColor.AQUA + "POMOC");
        ArrayList<String> pages = new ArrayList<>();
        pages.add(ChatColor.DARK_AQUA + "Witaj na serwerze MC Stud Serwer." +
                ChatColor.BLACK + "\n\n\n W tej książce znajdziesz wszystkie potrzebne informacje dotyczące gry");
        pages.add(ChatColor.BLACK + "PODSTAWOWE INFORMACJE:"
                + "\n\n Skoro odwiedziłaś/eś nasz serwer, znaczy że założyleś już konto na naszej stronie internetowej. Teraz podamy Ci kilka podstawowych onformacji"
                + "\n ");
        bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);
        player.getInventory().addItem(book);
    }

}
