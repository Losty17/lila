package tech.kappke.lila.bot.generics;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public abstract class AbstractCommand
{
	protected SlashCommandInteractionEvent event;

	public AbstractCommand(SlashCommandInteractionEvent event)
	{
		this.event = event;
	}

	public boolean handle()
	{
		return false;
	}
}
