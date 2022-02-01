package io.github.lama06.bankplugin;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class BankPlugin extends JavaPlugin implements Listener {
    public ArrayList<BankAccount> accounts;
    public ArrayList<Bank> banken;

    public BankAccount getBankAccountForPlayer(Player player) {
        for (BankAccount account : accounts) {
            if (account.owner.equals(player.getUniqueId())) {
                return account;
            }
        }

        return null;
    }

    @EventHandler
    public void handlePlayerJoinEvent(PlayerJoinEvent event) {
        accounts.add(new BankAccount(event.getPlayer().getUniqueId()));
    }

    @Override
    public void onEnable() {
        this.getSLF4JLogger().info("Das Bank Plugin wurde geladen");

        Bukkit.getPluginManager().registerEvents(this, this);

        accounts = new ArrayList<>();
        banken = new ArrayList<>();

        Bank bank = new Bank(this, " Bank in Rom", new Area(0, 0, 0, 100, 100, 100));
        Bukkit.getPluginManager().registerEvents(bank, this);
        banken.add(bank);
    }

    @Override
    public void onDisable() {
        this.getSLF4JLogger().info("Das Bank Plugin wurde entladen");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equals("bank")) {
            if (args.length == 0) {
                sender.sendMessage(Component.text("Du hast ein Argument vergessen"));
                return false;
            } else {
                String argument = args[0];

                if (argument.equals("auflisten")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;

                        BankAccount bankAccount = getBankAccountForPlayer(player);

                        player.sendMessage(Component.text("Das hier sind deine eingezahlten Items:"));
                        for (ItemStack item : bankAccount.items) {
                            player.sendMessage(Component.text("- " + item));
                        }
                    } else {
                        sender.sendMessage(Component.text("Du bist kein Spieler! Aus dem Weg Geringverdiener!"));
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
