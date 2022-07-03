package tech.kappke.lila.bot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import tech.kappke.lila.bot.generics.AbstractCommand;

public class PingCommand extends AbstractCommand
{
	public PingCommand(SlashCommandInteractionEvent event)
	{
		super(event);
	}

	@Override
	public boolean handle()
	{
		event.reply("Pong!").queue();
		
		return true;
	}
}
