package victorgponce.com.listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;

public class buttons extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("verify")) {
            Member memberVerify = event.getMember();
            Role role = event.getGuild().getRoleById("1286473974959706164");

            if (memberVerify != null && role != null && !memberVerify.getRoles().contains(role)) {
                event.getGuild().addRoleToMember(memberVerify, role).queue();
                event.reply("Te has verificado correctamente!").setEphemeral(true).queue();
            } else {
                event.reply("Ya estas verificado!").setEphemeral(true).queue();
            }
        }
    }
    // @Override
    // public void onModalInteraction(@NotNull ModalInteractionEvent event) {
    //     if (event.getModalId().equals("modmail")) {
    //         String subject = event.getValue("subject").getAsString();
    //         String body = event.getValue("body").getAsString();
//
    //         //createSupportTicket(subject, body);
//
    //         event.reply("Thanks for your request!").setEphemeral(true).queue();
    //     }
    // }
}
