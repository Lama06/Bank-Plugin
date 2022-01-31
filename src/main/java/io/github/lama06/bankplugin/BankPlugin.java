package io.github.lama06.bankplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class BankPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getSLF4JLogger().info("Das Bank Plugin wurde geladen");
    }

    @Override
    public void onDisable() {
        this.getSLF4JLogger().info("Das Bank Plugin wurde entladen");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }
}
