package your.name.plugin;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.json.gson.JsonGsonConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import java.io.File;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import your.name.plugin.config.Configuration;
import your.name.plugin.listener.PlayerJoinListener;

public final class Plugin extends JavaPlugin {

  private Injector injector;

  @Override
  public void onLoad() {
    // This executes when the plugin is loading but isn't enabled yet.
  }

  @Override
  public void onEnable() {
    // Plugin enable logic

    final Logger logger = getLogger();
    this.injector = OkaeriInjector.create();
    this.injector.registerInjectable(logger);

    this.loadConfig();
    this.registerListeners();
  }

  @Override
  public void onDisable() {
    //Plugin disable logic
  }

  private void loadConfig(){
    Configuration configuration = ConfigManager.create(Configuration.class, it ->
        it.withConfigurer(new JsonGsonConfigurer(), new SerdesBukkit())
            .withBindFile(new File(this.getDataFolder(), "config.json"))
            .saveDefaults()
            .load(true)
    );
    this.injector.registerInjectable(configuration);
  }

  private void registerListeners(){
    final PluginManager pluginManager = Bukkit.getPluginManager();
    this.injector.registerInjectable(pluginManager);

    pluginManager.registerEvents(this.injector.createInstance(PlayerJoinListener.class), this);
  }
}
