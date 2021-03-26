package xyz.vaskel.kotlinbot

import com.jagrosh.jdautilities.command.CommandClientBuilder
import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDABuilder
import xyz.vaskel.kotlinbot.modules.InfoModule

fun main(){
    val builder: CommandClientBuilder = CommandClientBuilder()
        .setPrefix("$")
        .setOwnerId("447422100798570496")
        .addAnnotatedModules(InfoModule())

    JDABuilder(AccountType.BOT)
        .addEventListeners(builder.build())
        .setToken(System.getenv("token"))
        .build()
}

