package victorgponce.com.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class WelcomeListener extends ListenerAdapter {

    private final String WELCOME_CHANNEL_ID = "1286473974980673561"; // Cambia esto al ID de tu canal de bienvenida
    private final String WELCOME_BACKGROUND_IMAGE = "https://ponchisaohosting.xyz/downloads/bg.jpg"; // URL de la imagen de fondo
    private Font customFont;

    public WelcomeListener() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fawkeshalf.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Guild guild = event.getGuild();
        Member member = event.getMember();
        TextChannel welcomeChannel = guild.getTextChannelById(WELCOME_CHANNEL_ID);

        if (welcomeChannel != null) {
            // Crea la imagen de bienvenida como InputStream
            InputStream welcomeImageStream = createWelcomeImage(member);

            if (welcomeImageStream != null) {
                // Crea el embed de bienvenida
                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(Color.GREEN);
                eb.setAuthor(member.getEffectiveName());
                eb.setTitle("🎉 ¡Bienvenido al servidor!");
                eb.setDescription("Nos alegra tenerte aquí. ¡Esperamos que disfrutes tu estadía! \n\n" +
                        "-> No olvides pasarte por las normas para verificarte!:\n" +
                        "┏━━━━━━━━━━━━━━┓\n" +
                        "┃:arrow_right: <#1286473974980673562> \n" +
                        "┗━━━━━━━━━━━━━━┛");
                eb.setImage("attachment://welcome.png"); // La imagen se enviará adjunta con este nombre

                // Envía el mensaje con la imagen adjunta
                welcomeChannel.sendMessageEmbeds(eb.build())
                        .addFiles(FileUpload.fromData(welcomeImageStream, "welcome.png"))
                        .queue();
            }
        }
    }

    // Método para crear la imagen de bienvenida y devolverla como InputStream
    private InputStream createWelcomeImage(Member member) {
        try {
            // Descargar la imagen de fondo desde la URL
            URL backgroundUrl = new URL(WELCOME_BACKGROUND_IMAGE);
            InputStream backgroundStream = backgroundUrl.openStream();
            BufferedImage backgroundImage = ImageIO.read(backgroundStream);

            // Descargar la imagen del avatar del usuario
            URL avatarUrl = new URL(member.getUser().getEffectiveAvatarUrl() + "?size=256"); // Tamaño de avatar aumentado
            InputStream avatarStream = avatarUrl.openStream();
            BufferedImage avatarImage = ImageIO.read(avatarStream);

            // Crear una imagen circular del avatar
            int diameter = Math.min(avatarImage.getWidth() + 250, avatarImage.getHeight() + 250); // Increased diameter by 30%
            BufferedImage circularAvatar = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = circularAvatar.createGraphics();
            g2.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
            g2.drawImage(avatarImage, 0, 0, diameter, diameter, null);
            g2.dispose();

            // Combinar las imágenes
            BufferedImage combinedImage = new BufferedImage(backgroundImage.getWidth(), backgroundImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = combinedImage.createGraphics();

            // Dibujar el fondo
            g.drawImage(backgroundImage, 0, 0, null);

            // Calcular la posición del avatar
            int avatarX = (backgroundImage.getWidth() - diameter) / 2;
            int avatarY = (backgroundImage.getHeight() - diameter) / 2 - 50; // Ajustar la posición para dejar espacio para el texto

            // Dibujar el marco negro del avatar
            g.setColor(Color.black);
            g.setStroke(new BasicStroke(10)); // Set the border thickness to 10 pixels
            g.drawOval(avatarX - 5, avatarY - 5, diameter + 10, diameter + 10); // Draw a black circle border

            // Dibujar el avatar (centrado en la imagen de fondo)
            g.drawImage(circularAvatar, avatarX, avatarY, null);

            // Dibujar el texto debajo del avatar
            String welcomeText = "¡Bienvenido";
            g.setFont(customFont.deriveFont(Font.BOLD, 130)); // Increased font size by 30%
            g.setColor(Color.RED);
            FontMetrics fm = g.getFontMetrics();
            int textX = (backgroundImage.getWidth() - fm.stringWidth(welcomeText)) / 2;
            int textY = avatarY + diameter + fm.getHeight() + 30;
            g.drawString(welcomeText, textX, textY);

            String welcomeTextName = member.getEffectiveName() + "!";
            g.setFont(customFont.deriveFont(Font.BOLD, 110)); // Increased font size by 30%
            g.setColor(Color.WHITE);
            FontMetrics fmName = g.getFontMetrics();
            int textXName = (backgroundImage.getWidth() - fmName.stringWidth(welcomeTextName)) / 2 + 10;
            int textYName = avatarY + diameter + fmName.getHeight() + 170;
            g.drawString(welcomeTextName, textXName, textYName);

            // Liberar recursos
            g.dispose();

            // Convertir la imagen combinada a InputStream
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(combinedImage, "png", os);
            return new ByteArrayInputStream(os.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
