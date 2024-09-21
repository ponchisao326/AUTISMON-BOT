package victorgponce.com;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import victorgponce.com.listeners.WelcomeListener;
import victorgponce.com.listeners.buttons;
import victorgponce.com.listeners.info;
import victorgponce.com.listeners.ping;

import java.awt.*;

public class Main {

    // Introduce tu Token
    static String token = "";

    public static void main(String[] args) throws InterruptedException {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();

        for (String fontName : fontNames) {
            System.out.println(fontName);
        }

        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .disableCache(CacheFlag.ACTIVITY).build();

        jda.getPresence().setActivity(Activity.customStatus("Atrapando Autistas - Powered by PonchisaoHosting"));

        // Añadir Listeners
        jda.addEventListener(new listener());
        jda.addEventListener(new ping());
        jda.addEventListener(new buttons());
        jda.addEventListener(new WelcomeListener());
    }
}
