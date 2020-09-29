///////////////////////////////////////////////////////////////////////////////
// Title:            CS536 P1 Part2
// Files:            P1.java
// Semester:         CS536 Fall 2020
//
// Author:           Zihao Chen
// Email:            zchen565@wisc.edu
// CS Login:         zihaoc
// Lecturer's Name:  Aws Albarghouthi
////////////////////////////////////////////////////////////////////////////////
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * symbol table class store all the symbol into a a List<HashMap<String,Sym>>
 */
public class SymTable {
    
    // store all the information is this list
    private List<HashMap<String, Sym>> list = new LinkedList<>();

    /**
     * constructor
     * initialize with an empty HashMap
     */
    public SymTable() {
        list.add(new HashMap<>());
    }

    /**
     * @param name a name for deceleration
     * @param sym a symbol for deceleration
     * @throws DuplicateSymException  If the first HashMap in the list
     *                                contains a key with same name
     * @throws EmptySymTableException If SymTable's list is empty
     * @throws NullPointerException If either name or sym is null
     */
    public void addDecl(String name, Sym sym)
            throws DuplicateSymException, EmptySymTableException, NullPointerException{
        if (list.isEmpty())
            throw new EmptySymTableException();

        if (name == null || sym == null)
            throw new NullPointerException();

        if (list.get(0).containsKey(name))
            throw new DuplicateSymException();

        list.get(0).put(name, sym);
    }


    /**
     * Add a new, empty HashMap in the front of the list.
     */
    public void addScope() {
        list.add(0, new HashMap<>());
    }

    /**
     * @param name name for the symbol
     * @return the symbol in the first HashMap
     * @throws EmptySymTableException SymTable's list is empty
     */
    public Sym lookupLocal(String name) throws EmptySymTableException {
        if (list.isEmpty())
            throw new EmptySymTableException();

        return list.get(0).getOrDefault(name, null);
    }

    /**
     * @param name name for a symbol
     * @return the symbol in the whole HashMap
     * @throws EmptySymTableException SymTable's list is empty
     */
    public Sym lookupGlobal(String name) throws EmptySymTableException {
        if (list.isEmpty())
            throw new EmptySymTableException();

        // find the symbol in the whole list
        for (HashMap<String, Sym> hm : list)
            if (hm.containsKey(name))
                return hm.get(name);

        //otherwise
        return null;
    }

    /**
     * remove the front of the list.
     *
     * @throws EmptySymTableException SymTable's list is empty
     */
    public void removeScope() throws EmptySymTableException {
        if (list.isEmpty())
            throw new EmptySymTableException();

        list.remove(0);
    }


    /**
     * for debugging
     * It prints the data store in the symbol table
     */
    public void print() {
        System.out.print("\nSym Table\n");
        for (HashMap<String, Sym> hm : list)
            System.out.println(hm);
        System.out.println();
    }
}
