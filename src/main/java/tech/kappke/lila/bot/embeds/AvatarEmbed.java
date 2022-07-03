package tech.kappke.lila.bot.embeds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import tech.kappke.lila.bot.generics.AbstractEmbed;

public class AvatarEmbed extends AbstractEmbed
{
	private String avatar;
	private String title;

	public AvatarEmbed(String avatarURL, String title)
	{
		this.avatar = avatarURL;
		this.title = title;
	}

	@Override
	public MessageEmbed get()
	{
		return new EmbedBuilder()
				.setTitle(title)
				.setImage(avatar)
				.build();
	}
}
