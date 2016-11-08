package elastic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zsjanos on 2016.11.08..
 */
public class FlatElement {
    public String childWordForm;
    public String childLemma;
    public String childPos;

    public String depRel;

    public String parentWordForm;
    public String parentLemma;
    public String parentPos;


    @Override
    public String toString() {
        return "FlatElement{" +
                "childWordForm='" + childWordForm + '\'' +
                ", childLemma='" + childLemma + '\'' +
                ", childPos='" + childPos + '\'' +
                ", depRel='" + depRel + '\'' +
                ", parentWordForm='" + parentWordForm + '\'' +
                ", parentLemma='" + parentLemma + '\'' +
                ", parentPos='" + parentPos + '\'' +
                '}';
    }

    public FlatElement(String childWordForm, String childLemma, String childPos, String depRel, String parentWordForm, String parentLemma, String parentPos) {
        this.childWordForm = childWordForm;
        this.childLemma = childLemma;
        this.childPos = childPos;
        this.depRel = depRel;
        this.parentWordForm = parentWordForm;
        this.parentLemma = parentLemma;
        this.parentPos = parentPos;
    }

    public Map<String, Object> toJson() {
        Map<String, Object> jsonDocument = new HashMap<>();
        jsonDocument.put("childWordForm", this.childWordForm);
        jsonDocument.put("childLemma", this.childLemma);
        jsonDocument.put("childPos", this.childPos);
        jsonDocument.put("depRel", this.depRel);
        jsonDocument.put("parentWordForm", this.parentWordForm);
        jsonDocument.put("parentLemma", this.parentLemma);
        jsonDocument.put("parentPos", this.parentPos);
        return jsonDocument;
    }


}