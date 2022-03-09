package your.name.plugin.listener;

import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.injector.annotation.PostConstruct;
import java.util.Map;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import your.name.plugin.config.Configuration;
import your.name.plugin.helper.ChatHelper;
import your.name.plugin.helper.Placeholders;

public class PlayerJoinListener implements Listener {

  @Inject private Configuration configuration;
  @Inject private Logger logger;

  /**
    Method annotated with {@link PostConstruct} will execute when an instace of the class
    is created by the {@link eu.okaeri.injector.OkaeriInjector}
   */
  @PostConstruct
  private void postConstruct(){
    this.logger.info("Registered " + this.getClass().getSimpleName());
  }

  @EventHandler
  public void onPlayerJoin(final PlayerJoinEvent event){
    final Player player = event.getPlayer();
    player.sendMessage(ChatHelper.parse(Placeholders.replace(configuration.getWelcomeMessage(), Map.of("player", player.getName()))));
  }

}
