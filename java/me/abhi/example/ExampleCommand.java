package me.abhi.example;

import me.abhi.gameapi.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ExampleCommand {

    @Command(name = "example", playersOnly = true)
    public void exampleCommand(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "You ran the example command!");
    }
}
