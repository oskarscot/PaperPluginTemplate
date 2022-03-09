package your.name.plugin.helper;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

@UtilityClass
public class ChatHelper {

  /*
    We only want to create our instance of the LegacyComponentSerializer once.
   */
  private final LegacyComponentSerializer legacyComponentSerializer = LegacyComponentSerializer.legacyAmpersand()
      .toBuilder()
      .hexColors()
      .character('&')
      .hexCharacter('#')
      .useUnusualXRepeatedCharacterHexFormat()
      .build();


  public Component parse(String string) {
    return legacyComponentSerializer.deserialize(string);
  }

  public String serialize(Component component) {
    return legacyComponentSerializer.serialize(component);
  }

}
