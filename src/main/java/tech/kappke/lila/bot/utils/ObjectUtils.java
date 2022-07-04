package tech.kappke.lila.bot.utils;

public class ObjectUtils
{
	public static <T> T getIfNotNull(T src, T other)
	{
		return src != null ? src : other;
	}	
}
