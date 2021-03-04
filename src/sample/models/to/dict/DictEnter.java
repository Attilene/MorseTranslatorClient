package sample.models.to.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictEnter implements DictModel {
    @Override
    public Map<String, String> setParams(ArrayList<String> args) {
        Map<String, String> dictToSend = new HashMap<>();
        dictToSend.put("login_email", args.get(0));
        dictToSend.put("password_hash", args.get(1));
        dictToSend.put("salt", args.get(2));
        return dictToSend;
    }
}
