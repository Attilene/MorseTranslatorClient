package sample.models.to.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictUser implements DictModel {
    @Override
    public Map<String, String> setParams(ArrayList<String> args) {
        Map<String, String> dictToSend = new HashMap<>();
        dictToSend.put("first_name", args.get(0));
        dictToSend.put("last_name", args.get(1));
        dictToSend.put("login", args.get(2));
        dictToSend.put("email", args.get(3));
        dictToSend.put("phone_number", args.get(4));
        dictToSend.put("birthday", args.get(5));
        return dictToSend;
    }
}
