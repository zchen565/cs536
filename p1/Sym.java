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
/**
 * This class is a symbol in a symbol table
 */
public class Sym {
    
    // the type of the Sym, stored as a string
    private String type;

    /**
     * constructor
     *
     * @param type symbol type
     */
    public Sym(String type) {
        this.type = type;
    }

    /**
     * @return the type of the symbol
     */
    public String getType() {
        return type;
    }

    /**
     * @return string of type
     */
    @Override
    public String toString() {
        return type;
    }
}