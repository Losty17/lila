package tech.kappke.lila.bot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import tech.kappke.lila.bot.embeds.AvatarEmbed;
import tech.kappke.lila.bot.generics.AbstractCommand;

public class AvatarCommand extends AbstractCommand
{
    public AvatarCommand(SlashCommandInteractionEvent event)
    {
        super(event);
    }

	@Override
	public boolean handle()
	{
		var option = event.getOption("member");
		var user = option != null ? option.getAsUser() : event.getUser();
		var avatar = user.getAvatar().getUrl(512);
		
		var embed = new AvatarEmbed(avatar, user.getName() + "'s avatar").get();
		
		event.replyEmbeds(embed).queue();

		return true;
	}
}
