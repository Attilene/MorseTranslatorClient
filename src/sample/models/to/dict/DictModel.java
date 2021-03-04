package sample.models.to.dict;

import java.util.ArrayList;
import java.util.Map;

public interface DictModel {
    Map<String, String> setParams(ArrayList<String> args);
}
