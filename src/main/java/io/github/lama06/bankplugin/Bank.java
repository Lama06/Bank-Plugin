package io.github.lama06.bankplugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.time.Duration;

public class Bank implements Listener {
    public BankPlugin plugin;
    public String name;
    public Area area;

    public Bank(BankPlugin plugin, String name, Area area) {
        this.plugin = plugin;
        this.name = name;
        this.area = area;
    }

    @EventHandler
    public void handlePlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (!area.isInside(from.getBlockX(), from.getBlockY(), from.getBlockZ()) && area.isInside(to.getBlockX(), to.getBlockY(), to.getBlockZ())) {

            player.showTitle(Title.title(
                    Component.text("Willkommen in der Bank: " + name),
                    Component.empty(),
                    Title.Times.of(Duration.ofSeconds(1), Duration.ofSeconds(5), Duration.ofSeconds(1))
            ));

        }
    }

    @EventHandler
    public void handlePlayerDropItemEvent(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        if (area.isInside(location.getBlockX(), location.getBlockY(), location.getBlockZ())) {
            BankAccount bankAccount = plugin.getBankAccountForPlayer(player);

            if (bankAccount != null) {
                bankAccount.items.add(event.getItemDrop().getItemStack());
                event.getItemDrop().remove();
            }
        }
    }
}
