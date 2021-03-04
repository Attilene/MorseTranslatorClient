package sample.models.to.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictPassword implements DictModel {
    @Override
    public Map<String, String> setParams(ArrayList<String> args) {
        Map<String, String> dictToSend = new HashMap<>();
        dictToSend.put("hash", args.get(0));
        dictToSend.put("salt", args.get(1));
        dictToSend.put("user_id", args.get(2));
        return dictToSend;
    }
}
