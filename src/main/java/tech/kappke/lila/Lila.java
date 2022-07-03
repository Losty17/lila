package tech.kappke.lila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import tech.kappke.lila.bot.CommandAdapter;
import tech.kappke.lila.bot.CommandBuilder;

@SpringBootApplication
public class Lila 
{
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Lila.class, args);
	}

	/**
     * Initialize the bot
     * 
	 * @throws Exception
	 */
    @Bean
	public static void start() throws Exception
    {
        var dotenvPath = ResourceUtils.getFile("classpath:env/").getAbsolutePath();
        var dotenv = Dotenv.configure()
                           .directory(dotenvPath)
                           .ignoreIfMalformed()
                           .ignoreIfMissing()
                           .load();

        var builder = JDABuilder.createDefault(dotenv.get("BOT_TOKEN"));
        builder.addEventListeners(new CommandAdapter());

        var api = builder.build();
        var commands = ResourceUtils.getFile("classpath:commands/");

        var guild = api.awaitReady().getGuildById(dotenv.get("DEV_GUILD"));
        CommandBuilder.loadCommands(guild, commands);
    }
}