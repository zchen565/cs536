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

    // The part for struct cases (definition and usage)
    private SymTable symTable;
    private StructDeclNode struct;

    public void setStruct(StructDeclNode struct) {
        this.struct = struct;
    }

    public StructDeclNode getStruct() {
        return this.struct;
    }

    public void setSymTable(SymTable symTable) {
        this.symTable = symTable;
    }

    public SymTable getSymTable(SymTable symTable) {
        return this.symTable;
    }
}

class StructSym extends Sym {
    private SymTable symTable;
    private StructDeclNode struct;

    public StructSym(StructDeclNode struct) {
        super("struct");
        this.struct = struct;
    }

    public StructDeclNode getStruct() {
        return this.struct;
    }

    public void setSymTable(SymTable symTable) {
        this.symTable = symTable;
    }

    public SymTable getSymTable(SymTable symTable) {
        return this.symTable;
    }
}

class FnSym extends Sym {
    private LinkedList<String> paraType;
    private String reType;

    public FnSym(String reType, LinkedList<String> paraType) {
        super("function");
        this.paraType = paraType;
        this.reType = reType;
    }

    public int getParamNum() {
        return paraType.size();
    }

    public String toString() {
        String params = String.join(", ", paraType);
        if (params.equals("")) {
            params = "void";
        }
        return params + " -> " + reType;
    }
}
