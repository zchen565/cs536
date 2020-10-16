import java_cup.runtime.*; // defines the Symbol class
// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (literals and IDs) also include the value of the token.
class TokenVal {
  // fields
    int linenum;
    int charnum;
  // constructor
    TokenVal(int lnum, int cnum) {
        linenum = lnum;
        charnum = cnum;
    }
}
class IntLitTokenVal extends TokenVal {
  // new field: the value of the integer literal
    int intVal;
  // constructor
    IntLitTokenVal(int lnum, int cnum, int val) {
        super(lnum, cnum);
        intVal = val;
    }
}
class IdTokenVal extends TokenVal {
  // new field: the value of the identifier
    String idVal;
  // constructor
    IdTokenVal(int lnum, int cnum, String val) {
        super(lnum, cnum);
    idVal = val;
    }
}
class StrLitTokenVal extends TokenVal {
  // new field: the value of the string literal
    String strVal;
  // constructor
    StrLitTokenVal(int lnum, int cnum, String val) {
        super(lnum, cnum);
        strVal = val;
    }
}
// The following class is used to keep track of the character number at which
// the current token starts on its line.
class CharNum {
    static int num=1;
}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"44:9,43,25,44:2,24,44:18,43,33,21,45,44:2,30,23,36,37,41,28,39,29,40,42,20:" +
"10,44,38,26,32,27,23,44,18:26,44,22,44:2,19,44,13,1,15,8,11,12,18,17,4,18:2" +
",3,18,5,2,18:2,9,14,6,10,7,16,18:3,34,31,35,44:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,98,
"0,1,2,3,4,1:2,5,6,7,8,9,10,1:8,11,12,13,1,14,1:11,13:7,1,13:4,15,16,1,17,18" +
",19,20,21,22,23,24,25,26,27,1,28,29,30,31,32,33,34,35,36,37,38,13,39,40,41," +
"42,43,44,45,46,47,48,49,50,51,13,52,53,54,55,56,57,58,59")[0];

	private int yy_nxt[][] = unpackFromString(60,46,
"1,2,89:2,50,89,91,92,89,93,89,94,95,89,96,73,97,89:3,3,4,5:2,-1,6,7,8,9,10," +
"52,57,11,12,13,14,15,16,17,18,19,20,21,22,5,60,-1:47,89,74,89:16,75:2,-1:45" +
",3,-1:26,4:20,24,25,4:2,51,4:20,-1:26,26,-1:5,27,-1:40,28,-1:4,29,-1:41,30," +
"-1:46,31,-1:48,34,-1:45,35,-1:55,36,-1:46,22,-1:3,89:18,75:2,-1:26,53:4,56:" +
"2,53:14,56:3,53,58,53:5,56,53:4,56:2,53:8,-1:5,56:2,-1:14,56:3,-1:7,56,-1:4" +
",56:2,-1:9,89:4,55,89:6,23,89:6,75:2,-1:55,32,-1:16,53:20,44,61,53:2,63,53:" +
"20,-1,54:24,-1,54:20,-1,89:5,37,89:12,75:2,-1:26,56:20,24,49,56:2,51,56:20," +
"-1:31,33,-1:15,58:20,-1,65,58:2,63,58:20,-1,89:4,38,89:13,75:2,-1:70,54,-1," +
"53:4,-1:2,53:14,-1:3,53,58,53:5,-1,53:4,-1:2,53:8,-1,89:2,39,89:15,75:2,-1:" +
"26,89:10,40,89:7,75:2,-1:26,58:4,-1:2,58:14,-1:3,58:7,-1,58:4,-1:2,58:8,-1," +
"89:7,41,89:10,75:2,-1:26,89:10,42,89:7,75:2,-1:26,89:5,43,89:12,75:2,-1:26," +
"89:10,45,89:7,75:2,-1:26,89:10,46,89:7,75:2,-1:26,89:4,47,89:13,75:2,-1:26," +
"89:5,48,89:12,75:2,-1:26,89,82,89,59,89:14,75:2,-1:26,89,62,89:16,75:2,-1:2" +
"6,89:9,64,89:8,75:2,-1:26,89:3,66,89:14,75:2,-1:26,89:5,84,89:12,75:2,-1:26" +
",89:13,67,89:4,75:2,-1:26,89:2,85,89:15,75:2,-1:26,89:8,90,89:9,75:2,-1:26," +
"89:9,68,89:8,75:2,-1:26,89:3,86,89:14,75:2,-1:26,89:9,87,89:8,75:2,-1:26,89" +
":13,69,89:4,75:2,-1:26,89:2,70,89:15,75:2,-1:26,89:8,71,89:9,75:2,-1:26,89:" +
"14,72,89:3,75:2,-1:26,89:9,88,89:8,75:2,-1:26,89:8,76,89:9,75:2,-1:26,89,77" +
",89:16,75:2,-1:26,89:10,78,89:7,75:2,-1:26,89:2,79,89:15,75:2,-1:26,89:12,8" +
"0,89:5,75:2,-1:26,89:5,81,89:12,75:2,-1:26,89:16,83,89,75:2,-1:25");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -3:
						break;
					case 3:
						{ 
  // Any integer literal (a sequence of one or more digits).
  // NOTE: the following computation of the integer value does NOT
            //       check for overflow.  This must be changed.
            int val;
            try {
              val = (new Integer(yytext())).intValue();
            } catch (NumberFormatException ex) {
              ErrMsg.warn(yyline+1, CharNum.num, 
                              "integer literal too large; using max value");
              val = Integer.MAX_VALUE;
            }
            Symbol S = new Symbol(sym.INTLITERAL,
                              new IntLitTokenVal(yyline+1, CharNum.num, val));
            CharNum.num += yytext().length();
            return S;
          }
					case -4:
						break;
					case 4:
						{
  // Unterminated string literals
  ErrMsg.fatal(yyline+1, CharNum.num, "unterminated string literal ignored");
  // new line char num set to 1
  CharNum.num = 1;
}
					case -5:
						break;
					case 5:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -6:
						break;
					case 6:
						{ CharNum.num = 1; }
					case -7:
						break;
					case 7:
						{
    Symbol S = new Symbol(sym.LESS, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -8:
						break;
					case 8:
						{
    Symbol S = new Symbol(sym.GREATER, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -9:
						break;
					case 9:
						{
    Symbol S = new Symbol(sym.PLUS, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -10:
						break;
					case 10:
						{
    Symbol S = new Symbol(sym.MINUS, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -11:
						break;
					case 11:
						{
    Symbol S = new Symbol(sym.ASSIGN, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -12:
						break;
					case 12:
						{
    Symbol S = new Symbol(sym.NOT, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -13:
						break;
					case 13:
						{
    Symbol S = new Symbol(sym.LCURLY, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -14:
						break;
					case 14:
						{
    Symbol S = new Symbol(sym.RCURLY, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -15:
						break;
					case 15:
						{
    Symbol S = new Symbol(sym.LPAREN, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -16:
						break;
					case 16:
						{
    Symbol S = new Symbol(sym.RPAREN, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -17:
						break;
					case 17:
						{
    Symbol S = new Symbol(sym.SEMICOLON, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -18:
						break;
					case 18:
						{
    Symbol S = new Symbol(sym.COMMA, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -19:
						break;
					case 19:
						{
    Symbol S = new Symbol(sym.DOT, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -20:
						break;
					case 20:
						{
    Symbol S = new Symbol(sym.TIMES, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -21:
						break;
					case 21:
						{
    Symbol S = new Symbol(sym.DIVIDE, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -22:
						break;
					case 22:
						{ CharNum.num += yytext().length(); }
					case -23:
						break;
					case 23:
						{ Symbol S = new Symbol(sym.IF, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -24:
						break;
					case 24:
						{
  // Any string literal (a sequence of zero or more string characters
  // surrounded by double quotes). 
  Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
  CharNum.num += yytext().length();
  return S;
}
					case -25:
						break;
					case 25:
						{
  // unterminated string literal with bad escaped character ignored
  ErrMsg.fatal(yyline+1, CharNum.num, 
            "unterminated string literal with bad escaped character ignored");
  // new line char num set to 1
  CharNum.num = 1;
}
					case -26:
						break;
					case 26:
						{
    Symbol S = new Symbol(sym.WRITE, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -27:
						break;
					case 27:
						{
    Symbol S = new Symbol(sym.LESSEQ, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -28:
						break;
					case 28:
						{
    Symbol S = new Symbol(sym.READ, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -29:
						break;
					case 29:
						{
    Symbol S = new Symbol(sym.GREATEREQ, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -30:
						break;
					case 30:
						{
    Symbol S = new Symbol(sym.PLUSPLUS, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -31:
						break;
					case 31:
						{
    Symbol S = new Symbol(sym.MINUSMINUS, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -32:
						break;
					case 32:
						{
    Symbol S = new Symbol(sym.AND, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -33:
						break;
					case 33:
						{
    Symbol S = new Symbol(sym.OR, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -34:
						break;
					case 34:
						{
    Symbol S = new Symbol(sym.EQUALS, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -35:
						break;
					case 35:
						{
    Symbol S = new Symbol(sym.NOTEQUALS, new TokenVal(yyline + 1, CharNum.num));
    CharNum.num += yytext().length();
    return S;
}
					case -36:
						break;
					case 36:
						{
  // This is comment, ignor
  CharNum.num = 1;
}
					case -37:
						break;
					case 37:
						{ Symbol S = new Symbol(sym.INT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -38:
						break;
					case 38:
						{ Symbol S = new Symbol(sym.CIN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -39:
						break;
					case 39:
						{ Symbol S = new Symbol(sym.BOOL, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -40:
						break;
					case 40:
						{ Symbol S = new Symbol(sym.TRUE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -41:
						break;
					case 41:
						{ Symbol S = new Symbol(sym.VOID, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -42:
						break;
					case 42:
						{ Symbol S = new Symbol(sym.ELSE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -43:
						break;
					case 43:
						{ Symbol S = new Symbol(sym.COUT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -44:
						break;
					case 44:
						{
  // string literal with bad escaped character ignored
  ErrMsg.fatal(yyline+1, CharNum.num, 
                        "string literal with bad escaped character ignored");
  CharNum.num += yytext().length();
}
					case -45:
						break;
					case 45:
						{ Symbol S = new Symbol(sym.FALSE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -46:
						break;
					case 46:
						{ Symbol S = new Symbol(sym.WHILE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -47:
						break;
					case 47:
						{ Symbol S = new Symbol(sym.RETURN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -48:
						break;
					case 48:
						{ Symbol S = new Symbol(sym.STRUCT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -49:
						break;
					case 50:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -50:
						break;
					case 51:
						{
  // Unterminated string literals
  ErrMsg.fatal(yyline+1, CharNum.num, "unterminated string literal ignored");
  // new line char num set to 1
  CharNum.num = 1;
}
					case -51:
						break;
					case 52:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -52:
						break;
					case 53:
						{
  // unterminated string literal with bad escaped character ignored
  ErrMsg.fatal(yyline+1, CharNum.num, 
            "unterminated string literal with bad escaped character ignored");
  // new line char num set to 1
  CharNum.num = 1;
}
					case -53:
						break;
					case 54:
						{
  // This is comment, ignor
  CharNum.num = 1;
}
					case -54:
						break;
					case 55:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -55:
						break;
					case 56:
						{
  // Unterminated string literals
  ErrMsg.fatal(yyline+1, CharNum.num, "unterminated string literal ignored");
  // new line char num set to 1
  CharNum.num = 1;
}
					case -56:
						break;
					case 57:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -57:
						break;
					case 58:
						{
  // unterminated string literal with bad escaped character ignored
  ErrMsg.fatal(yyline+1, CharNum.num, 
            "unterminated string literal with bad escaped character ignored");
  // new line char num set to 1
  CharNum.num = 1;
}
					case -58:
						break;
					case 59:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -59:
						break;
					case 60:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -60:
						break;
					case 61:
						{
  // unterminated string literal with bad escaped character ignored
  ErrMsg.fatal(yyline+1, CharNum.num, 
            "unterminated string literal with bad escaped character ignored");
  // new line char num set to 1
  CharNum.num = 1;
}
					case -61:
						break;
					case 62:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -62:
						break;
					case 63:
						{
  // unterminated string literal with bad escaped character ignored
  ErrMsg.fatal(yyline+1, CharNum.num, 
            "unterminated string literal with bad escaped character ignored");
  // new line char num set to 1
  CharNum.num = 1;
}
					case -63:
						break;
					case 64:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -64:
						break;
					case 65:
						{
  // unterminated string literal with bad escaped character ignored
  ErrMsg.fatal(yyline+1, CharNum.num, 
            "unterminated string literal with bad escaped character ignored");
  // new line char num set to 1
  CharNum.num = 1;
}
					case -65:
						break;
					case 66:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -66:
						break;
					case 67:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -67:
						break;
					case 68:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -68:
						break;
					case 69:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -69:
						break;
					case 70:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -70:
						break;
					case 71:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -71:
						break;
					case 72:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -72:
						break;
					case 73:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -73:
						break;
					case 74:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -74:
						break;
					case 75:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -75:
						break;
					case 76:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -76:
						break;
					case 77:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -77:
						break;
					case 78:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -78:
						break;
					case 79:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -79:
						break;
					case 80:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -80:
						break;
					case 81:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -81:
						break;
					case 82:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -82:
						break;
					case 83:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -83:
						break;
					case 84:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -84:
						break;
					case 85:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -85:
						break;
					case 86:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -86:
						break;
					case 87:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -87:
						break;
					case 88:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -88:
						break;
					case 89:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -89:
						break;
					case 90:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -90:
						break;
					case 91:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -91:
						break;
					case 92:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -92:
						break;
					case 93:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -93:
						break;
					case 94:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -94:
						break;
					case 95:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -95:
						break;
					case 96:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -96:
						break;
					case 97:
						{
    Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline + 1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return S;
}
					case -97:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
