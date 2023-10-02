package me.thevipershow.caliper.commands;

import lombok.Getter;
import me.thevipershow.caliper.Caliper;
import me.thevipershow.caliper.commands.implementations.CaliperCommand;
import me.thevipershow.caliper.commands.implementations.MeasuresCommand;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * This Enum contains the class representative for the command.
 * That class will be later used to register the command by this plugin.
 */
@Getter
public enum CaliperExistingCommand implements AbstractCaliperCommandConvert {

    MAIN_COMMAND_CALIPER(CaliperCommand.class),
    COMMAND_MEASURES(MeasuresCommand.class);

    private final Class<? extends AbstractCaliperNameCommand> commandNameClass; // enum stored class field.

    /**
     * Main constructor for Enum entries of this class.
     * @param caliperCommandClass The class representative for that command.
     * @param <T> The command class type, which must implement CommandName.
     */
    <T extends AbstractCaliperNameCommand> CaliperExistingCommand(@NotNull Class<T> caliperCommandClass) {
        this.commandNameClass = caliperCommandClass;
    }

    /**
     * Create a new instance of the child abstract caliper command convert
     *
     * @param caliper The initialised plugin.
     * @return The caliper command class object instance.
     */
    @Override
    public @NotNull <T extends AbstractCaliperNameCommand> T newCommand(@NotNull Caliper caliper) {
        try {
            Constructor<T> constructor = (Constructor<T>) this.commandNameClass.getDeclaredConstructors()[0];
            return constructor.newInstance(caliper);
        } catch (ClassCastException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null; // code will never reach.
    }
}
