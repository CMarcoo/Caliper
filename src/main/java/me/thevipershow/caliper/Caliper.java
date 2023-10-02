package me.thevipershow.caliper;

import me.thevipershow.caliper.commands.CaliperExistingCommand;
import me.thevipershow.caliper.commands.implementations.CaliperCommand;
import me.thevipershow.caliper.commands.implementations.MeasuresCommand;
import me.thevipershow.caliper.listeners.CaliperUseListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Caliper extends JavaPlugin {

    public static final String CALIPER_PREFIX = "§7[§6Caliper§7]§f: ";
    private CaliperCommand caliperCommand;
    private MeasuresCommand measuresCommand;
    private CaliperUseListener caliperUseListener;

    /**
     * Registers all the available command for this server.
     */
    private void registerCommands() {
        caliperCommand = CaliperExistingCommand.MAIN_COMMAND_CALIPER.newCommand(this);
        measuresCommand = CaliperExistingCommand.COMMAND_MEASURES.newCommand(this);
        caliperCommand.register();
        measuresCommand.register();
    }

    /**
     * Registers Listeners necessary for this plugin.
     */
    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(caliperUseListener = new CaliperUseListener(caliperCommand), this);
    }

    @Override
    public void onEnable() { // Plugin startup logic
        registerCommands();
        registerListeners();
    }
}
