package sample.models.to.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictTranslator implements DictModel {
    @Override
    public Map<String, String> setParams(ArrayList<String> args) {
        Map<String, String> dictToSend = new HashMap<>();
        dictToSend.put("start_string", args.get(0));
        dictToSend.put("operation_time", args.get(1));
        dictToSend.put("user_id", args.get(2));
        dictToSend.put("morse", args.get(3));
        dictToSend.put("language", args.get(4));
        return dictToSend;
    }
}
