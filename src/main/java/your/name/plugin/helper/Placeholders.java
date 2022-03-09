package your.name.plugin.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Placeholders {

  public String replace(String string, Map<String, Object> placeholders){
    final AtomicReference<String> atomicString = new AtomicReference<>(string);
    placeholders.forEach((s, o) -> atomicString.set(atomicString.get().replace("{" + s + "}", String.valueOf(o))));
    return atomicString.get();
  }

  public List<String> replace(List<String> list, Map<String, Object> placeholders){
    final ArrayList<String> replacedList = new ArrayList<>();
    list.forEach(s -> replacedList.add(replace(s, placeholders)));
    return replacedList;
  }

}
