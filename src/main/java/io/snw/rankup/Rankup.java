package io.snw.rankup;

import io.snw.rankup.bukkit.Commands;
import io.snw.rankup.bukkit.ListenerSign;
import io.snw.rankup.util.FileManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Rankup extends JavaPlugin {

    public static Permission perms = null;
    public static Economy econ = null;

    public static Plugin plugin;
    public static FileManager fileManager;

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }


    @Override
    public void onEnable() {

        fileManager = new FileManager();

        PluginDescriptionFile pdfFile = this.getDescription();

        getCommand("rankup").setExecutor(new Commands());

        getServer().getPluginManager().registerEvents(new ListenerSign(), this);

        plugin = this;

        setupPermissions();
        setupEconomy();

        fileManager.loadFiles();

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            getLogger().info("Unable to send Metrics data.");
        }

        getLogger().info(pdfFile.getName() + " " + pdfFile.getVersion() + " is now enabled.");
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdfFile = this.getDescription();

        getLogger().info(pdfFile.getName() + " " + pdfFile.getVersion() + " is now disabled.");
    }
}
