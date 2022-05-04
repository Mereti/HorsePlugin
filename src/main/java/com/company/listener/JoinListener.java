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
    private ItemStack saddle = new ItemStack(Material.SADDLE, 1);
    private ItemStack compass = createCompass();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.teleport(spawn);
        player.getInventory().clear();
        player.setHealthScale(20D);
        player.setInvulnerable(true);
        player.setCanPickupItems(true);
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
            player.getInventory().setItem(1,saddle);
        } else player.sendMessage("Otrzymałeś już jajko za 1 logowanie");
        return false;
    }

    private void createHelpBook(Player player){
       ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
       BookMeta bookMeta = (BookMeta) book.getItemMeta();
       bookMeta.setAuthor("Nuqta");
       bookMeta.setTitle(ChatColor.AQUA + "POMOC");
        ArrayList<String> pages = new ArrayList<>();
        pages.add(ChatColor.DARK_AQUA  + "Witaj na serwerze MC Stud Serwer." +
                ChatColor.BLACK + "\n\n\n W tej książce znajdziesz wszystkie potrzebne informacje dotyczące gry");
        pages.add(ChatColor.BOLD + "PODSTAWOWE INFORMACJE:" + ChatColor.RESET
                + "\n\n Skoro odwiedziłaś/eś nasz serwer, znaczy że założyleś już konto na naszej stronie internetowej. Teraz podamy Ci kilka podstawowych informacji");
        pages.add("Kompas umieszczony na 1 miejscu w twoim ekwipunku przeniesie Cię na twoją działkę");
        pages.add("Punkty które możesz wykorzystać na stronie, zbierać możesz poprzez budowę na swojej działce i podczas wyścigów.");
        pages.add(ChatColor.DARK_AQUA + ""+ ChatColor.BOLD + "KOMENDY: " + " \n\n /tplobby "+ChatColor.RESET+ "- pozwola wrócić do lobby " +
                ChatColor.DARK_AQUA + ""+ ChatColor.BOLD + "\n /horsename [imie_konia]" + ChatColor.RESET+ " - dzięki tej komendzie tworzysz " +
                "swojego konia, jeśli posiadasz w ekwipunku jego jajko");
        pages.add(ChatColor.DARK_AQUA + ""+ ChatColor.BOLD + "/racing [imie_konia] " + ChatColor.RESET+ " - komenda pozwala na przejście do wyścigu, aby wyścig się rozpoczął musi być conajmniej 2 graczy.\n" +
                ChatColor.DARK_AQUA + ""+ ChatColor.BOLD +"/stopracing [imie_konia]" + ChatColor.RESET+ " - pozwala na opuszczenie wyścigów ");
        pages.add(ChatColor.DARK_AQUA + ""+ ChatColor.BOLD +"/studname [nazwa_stadniny] " + ChatColor.RESET+ " - pozwala na zmianę nazwy stadniny");
        pages.add(ChatColor.DARK_AQUA + ""+ ChatColor.BOLD + "MINIGAMES: " +
                "\n\n /minigame menu " + ChatColor.RESET + "- dzięki tej komendzie otwierasz ekwipunek z minigrami , lub aby wziąć udział w którejś z nich możesz posłużyć się komendami:" +
                ChatColor.DARK_AQUA + ""+ ChatColor.BOLD + "/minigame join Spleef" + ChatColor.RESET + " - polega na robieniu dziur w podłodze tak aby 2 gracz spadł ");
        pages.add(ChatColor.DARK_AQUA + ""+ ChatColor.BOLD +"/minigame join Parkour" + ChatColor.RESET + "masz za zadanie przejść tor parkour w określonym czasie \n" +
                ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "/minigame join FitTool" + ChatColor.RESET + "musisz zburzyć jak najwięcej bloków odpowienimi narzędziami");
        pages.add(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "/minigame leave" + ChatColor.RESET + " - wyjście z gry - czasami gra jest określona czasowo i opuszczenie jej jest możiwe po upłynięciu określonego czasu.");
        pages.add(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "DZIĘKUJEMY ZA UWAGĘ! \n\n" + ChatColor.RESET + "Jeśli masz jakieś pytania skonsultuj się z administratorem serwera mailowo: kinga2233@onet.pl");

                bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);
        player.getInventory().addItem(book);
    }

}
