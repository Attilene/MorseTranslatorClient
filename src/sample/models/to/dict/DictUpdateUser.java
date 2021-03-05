package sample.models.to.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictUpdateUser implements DictModel{
    @Override
    public Map<String, String> setParams(ArrayList<String> args) {
        Map<String, String> dictToSend = new HashMap<>();
        dictToSend.put("id", args.get(0));
        dictToSend.put("first_name", args.get(1));
        dictToSend.put("last_name", args.get(2));
        dictToSend.put("login", args.get(3));
        dictToSend.put("email", args.get(4));
        dictToSend.put("phone_number", args.get(5));
        dictToSend.put("birthday", args.get(6));
        dictToSend.put("password_hash", args.get(7));
        dictToSend.put("salt", args.get(8));
        return dictToSend;
    }
}
