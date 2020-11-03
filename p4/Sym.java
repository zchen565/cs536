import java.util.*;

/**
 * We define FnSym, StructSym as subclass of Sym
 */

public class Sym {
    private String type;

    public Sym(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return type;
    }

}

/**
 * this is only for struct definition
 */
class StructDefSym extends Sym {
    // new fields
    private SymTable symTab;

    public StructDefSym(SymTable table) {
        super("struct");
        symTab = table;
    }

    public SymTable getSymTable() {
        return symTab;
    }
}

/**
 * this is only for struct variable
 */
class StructSym extends Sym {
    private IdNode structType; // name of the struct type

    public StructSym(IdNode id) {
        super(id.toString());
        structType = id;
    }

    public IdNode getStructType() {
        return structType;
    }
}

class FnSym extends Sym {
    private List<String> paraType;
    private String reType;

    public FnSym(String reType, List<String> paraType) {
        super("function");
        this.paraType = paraType;
        this.reType = reType;
    }

    public int getParamNum() {
        return paraType.size();
    }

    public void addFormals(List<String> formalList) {
        paraType = formalList;
    }

    public String toString() {
        String params = String.join(", ", paraType);
        if (params.equals("")) {
            params = "void";
        }
        return params + " -> " + reType;
    }
}
