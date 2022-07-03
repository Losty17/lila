package tech.kappke.lila.bot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.utils.data.DataObject;

public class CommandBuilder
{
	/**
	 * Load the commands from the specified directory into the guild
	 * 
	 * @param guild to add the commands to
	 * @param commands folder to find the json files
	 * @return the number of commands added
	 */
	public static long loadCommands(Guild guild, File commands)
	{
		return new CommandBuilder(guild, commands).loadCommands();
	}

	private Guild guild;
	private List<DataObject> commandsData;

	public CommandBuilder(Guild guild, File commands)
	{
		this.guild = guild;
		this.commandsData = getCommands(commands);
	}

	/**
	 * Load the commands JSON from the resource/commands folder 
	 * 
	 * @return the quantity of commands added
	 */
	private long loadCommands()
	{
		var oldCommands = guild.retrieveCommands().complete().stream()
						.map(c -> c.getName()).collect(Collectors.toList());

		var added = commandsData.stream()
					.filter(data -> !oldCommands.contains(data.getString("name")))
					.map(data -> CommandData.fromData(data))
					.map(command -> { 
						guild.upsertCommand(command).queue();
						return 1;
					}).count();
		
		return added;
	}

	/**
	 * Get a list of commands from a specified folder with its data represented in
	 * JSON
	 * 
	 * @param file File
	 * @return a list of DataObject representing the commands
	 */
	private List<DataObject> getCommands(File folder)
	{
		List<File> files = Arrays.asList(folder.listFiles());
		var commands = files.stream()
			.filter(file -> file.getName().endsWith(".json"))
			.map(file -> {
				try
				{
					return DataObject.fromJson(Files.readString(file.toPath()));
				}
				catch (IOException e)
				{
					return DataObject.empty();
				}
			})
			.collect(Collectors.toList());

		return commands;
	}
}