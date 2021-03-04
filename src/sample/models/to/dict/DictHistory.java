package sample.models.to.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictHistory implements DictModel {
    @Override
    public Map<String, String> setParams(ArrayList<String> args) {
        Map<String, String> dictToSend = new HashMap<>();
        dictToSend.put("start_string", args.get(0));
        dictToSend.put("end_string", args.get(1));
        dictToSend.put("operation_time", args.get(2));
        dictToSend.put("user_id", args.get(3));
        return dictToSend;
    }
}