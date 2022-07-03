package tech.kappke.lila.bot;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tech.kappke.lila.bot.generics.AbstractCommand;

public class CommandAdapter extends ListenerAdapter
{
	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
	{
		boolean isError = false;
		boolean isHandled = false;
		var command = event.getName().toLowerCase();
		command = Character.toUpperCase(command.charAt(0)) + command.substring(1);
		var handlerClassName = "tech.kappke.lila.bot.commands." + command + "Command";
		String msg = "Ops! ";

		try
		{
			var handlerClass = Class.forName(handlerClassName);

			var handler = (AbstractCommand) handlerClass.getDeclaredConstructor(event.getClass())
					.newInstance(event);

			isHandled = handler.handle();
		}
		catch (ClassNotFoundException e)
		{
			isError = true;
			msg += "O comando não foi encontrado...";
		}
		catch (Exception e)
		{
			isError = true;
			msg += e.getLocalizedMessage();
		}

		if (isError)
		{
			System.out.println(msg);
		}
		if (!event.isAcknowledged() || !isHandled)
		{
			event.reply(msg).setEphemeral(true).queue();
		}
	}
}
