package me.thevipershow.caliper;

import lombok.Getter;
import me.thevipershow.caliper.commands.CaliperExistingCommand;
import me.thevipershow.caliper.commands.implementations.CaliperCommand;
import me.thevipershow.caliper.commands.implementations.MeasuresCommand;
import me.thevipershow.caliper.commands.measures.PlayerCaliperData;
import me.thevipershow.caliper.listeners.CaliperUseListener;
import me.thevipershow.caliper.listeners.LogoutTaskRemoveListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Caliper extends JavaPlugin {

    public static final String CALIPER_PREFIX = "§7[§6Caliper§7]§f: ";
    private CaliperCommand caliperCommand = null;
    private MeasuresCommand measuresCommand = null;
    private CaliperUseListener caliperUseListener = null;
    private LogoutTaskRemoveListener logoutTaskRemoveListener = null;
    private final PlayerCaliperData playerCaliperData = new PlayerCaliperData();

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
        pluginManager.registerEvents(logoutTaskRemoveListener = new LogoutTaskRemoveListener(this), this);
    }

    @Override
    public void onEnable() { // Plugin startup logic
        registerCommands();
        registerListeners();
    }
}
