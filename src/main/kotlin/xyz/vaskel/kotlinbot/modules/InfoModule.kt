package xyz.vaskel.kotlinbot.modules

import com.jagrosh.jdautilities.command.CommandEvent
import com.jagrosh.jdautilities.command.annotation.JDACommand
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Guild
import xyz.vaskel.kotlinbot.extensions.toMessage
import xyz.vaskel.kotlinbot.extensions.toNameDiscrim
import java.time.format.DateTimeFormatter

@JDACommand.Module(value = [ "ServerInfoCommand" ])

class InfoModule {

    val bytesRatio: Int = 1000000

    @JDACommand(
        name = ["serverinfo", "guildinfo"],
        help = "Sends info about the server"
    )
    fun ServerinfoCommand(event: CommandEvent){
        val guild: Guild = event.guild

        val builder = EmbedBuilder()
            .setTitle("Info about `${guild.name}`")
            .setDescription(StringBuilder()
                .append("Owner: ${guild.owner.toNameDiscrim()}").appendLine()
                .append("ID: ${guild.id}").appendLine()
                .append("Created At: ${guild.timeCreated.format(DateTimeFormatter.RFC_1123_DATE_TIME)}").appendLine()
                .append("Verification Level: ${guild.verificationLevel.name.capitalize()}").appendLine()
                .append("Region: ${guild.regionRaw.capitalize()}")
                .toString())
            .setThumbnail(guild.iconUrl)
            .addField("Stats", StringBuilder()
                .append("Member Count: ${guild.memberCount}").appendLine()
                .append("Max Member Count: ${guild.maxMembers}").appendLine()
                .append("Max Emotes: ${guild.maxEmotes}").appendLine()
                .append("Max Filesize: ${guild.maxFileSize/bytesRatio} MB").appendLine()
                .append("Emojis: ${guild.emotes.count()}")
                .toString(),
                false
                )

        event.reply(builder.toMessage())
    }


}

