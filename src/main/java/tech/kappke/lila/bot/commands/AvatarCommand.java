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
		var member = option != null ? option.getAsMember() : event.getMember();
		var avatar = member.getAvatar();
		var name = member.getNickname();
		
		if (avatar == null)
		{
			avatar = member.getUser().getAvatar();
			name = member.getUser().getName();
		}

		var embed = new AvatarEmbed(avatar.getUrl(512), name + "'s avatar").get();
		
		event.replyEmbeds(embed).queue();

		return true;
	}
}
