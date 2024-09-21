package victorgponce.com;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import victorgponce.com.listeners.ping;

public class listener extends ListenerAdapter {

    private String selectedType;

    @Override
    public void onReady(@NotNull ReadyEvent event) {

        for (Guild guild : event.getJDA().getGuilds()) {
            for (TextChannel channel : guild.getTextChannels()) {
                System.out.println(channel + " is Ready");
                guild.updateCommands().addCommands(
                        Commands.slash("ping", "Check the services status.")
                                // .addOptions(
                                //         new OptionData(OptionType.STRING, "selection", "The type of hosting (Minecraft or Web)")
                                //                 .addChoice("Minecraft", "minecraft")
                                //                 .addChoice("Web Hosting", "web")),
                        // Commands.slash("normas-button", "Only For Admins")
                ).queue();
            }
        }

    }

    // @Override
    // public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        // if (event.getName().equals("ping")) {
            // selectedType = event.getOption("selection").getAsString();
            // new ping().ping(selectedType, event);
        // }
    // }
}
