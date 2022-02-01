package io.github.lama06.bankplugin;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class BankAccount {
    public UUID owner;
    public ArrayList<ItemStack> items;

    public BankAccount(UUID owner) {
        this.owner = owner;
        items = new ArrayList<ItemStack>();
    }
}
