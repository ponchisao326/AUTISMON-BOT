package victorgponce.com.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;

public class ping extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ping")) {
            String serverAddress;

            serverAddress = "node-marb.ponchisaohosting.xyz";

            try {
                InetAddress server = InetAddress.getByName(serverAddress);

                if (server.isReachable(3000)) {
                    event.reply("El nodo esta ONLINE, si no puedes acceder al servidor puede estár en mantenimiento! Contacta con un administrador").queue();
                } else {
                    event.reply("El nodo esta OFFLINE, puede ser una interrupción momentanea, si deseas mas información, contacta con un administrador").queue();
                }
            } catch (IOException e) {
                event.reply("Error en la conexión: " + e.getMessage()).queue();
            }
        }
//         else if (event.getName().equals("normas-button")) {
//
//             TextChannel textChannel = Objects.requireNonNull(event.getGuild()).getTextChannelById("1286473974980673562");
//
//             // Create the EmbedBuilder instance
//             EmbedBuilder eb = new EmbedBuilder();
//
// /*
//     Set the title:
//     1. Arg: title as string
//     2. Arg: URL as string or could also be null
//  */
//             eb.setTitle("Normas del Servidor", null);
//
// /*
//     Set the color
//  */
//             eb.setColor(Color.GREEN);
//
// /*
//     Set the text of the Embed:
//     Arg: text as string
//  */
//             eb.addField("🛠️ **Normas del Servidor de Minecraft**",
//                     "• No **cheats** ⛔\n" +
//                             "• No **grifeos** 🚫\n" +
//                             "• No **peleas innecesarias** ⚔️",
//                     false);
//
//             eb.addField("💬 **Normas del Servidor de Discord**",
//                     "• Usa el **sentido común** 💡\n" +
//                             "• No seas mas autista de lo que ya eres 🙅‍♂️\n",
//                     false);
//
//
//
// /*
//     Add spacer like field
//     Arg: inline mode true / false
//  */
//             eb.addField("", "____", false); // Adds a horizontal line separator
//
//             eb.addField("✨ **Claridad y Sencillez**",
//                     "Os quejaréis, claras y concisas",
//                     false);
//
// /*
//     Add embed author:
//     1. Arg: name as string
//     2. Arg: url as string (can be null)
//     3. Arg: icon url as string (can be null)
//  */
//             eb.setAuthor("🤖 Autisbot - Sistema de Reglas",
//                     null,
//                     "https://ponchisaohosting.xyz/downloads/normas2.png");
//
// /*
//     Set image:
//     Arg: image url as string
//  */
// // eb.setImage("https://github.com/zekroTJA/DiscordBot/blob/master/.websrc/logo%20-%20title.png");
//
// /*
//     Set thumbnail image:
//     Arg: image url as string
//  */
// // eb.setThumbnail("https://github.com/zekroTJA/DiscordBot/blob/master/.websrc/logo%20-%20title.png");
//
//             textChannel.sendMessageEmbeds(eb.build())
//                     .addActionRow(
//                             Button.success("verify", "✅ VERIFICARME ✅")) // Button with only a label
//                     .queue();
//         }
    }
}
