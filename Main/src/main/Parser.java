package main;

// Parser.java -- the implementation of class Parser
import java.io.IOException;

//
// Defines
//
//   class Parser;
//
// Parses the language
//
//   exp  ->  ( rest
//         |  #f
//         |  #t
//         |  ' exp
//         |  integer_constant
//         |  string_constant
//         |  identifier
//    rest -> )
//         |  exp+ [. exp] )
//
// and builds a parse tree.  Lists of the form (rest) are further
// `parsed' into regular lists and special forms in the constructor
// for the parse tree node class Cons.  See Cons.parseList() for
// more information.
//
// The parser is implemented as an LL(0) recursive descent parser.
// I.e., parseExp() expects that the first token of an exp has not
// been read yet.  If parseRest() reads the first token of an exp
// before calling parseExp(), that token must be put back so that
// it can be reread by parseExp() or an alternative version of
// parseExp() must be called.
//
// If EOF is reached (i.e., if the scanner returns a NULL) token,
// the parser returns a NULL tree.  In case of a parse error, the
// parser discards the offending token (which probably was a DOT
// or an RPAREN) and attempts to continue parsing with the next token.
class Parser {

    private final Scanner scanner;
    private final Nil nil = new Nil();
    private final BooleanLit boolTrue = new BooleanLit(true);
    private final BooleanLit boolFalse = new BooleanLit(false);

    public Parser(Scanner s) {
        scanner = s;
    }

    public Node parseExp() throws IOException {
        Token tkn = scanner.getNextToken();
        int tknType = tkn.getType();
        switch (tknType) {
            case Token.LPAREN:
                return parseRest();
            case Token.FALSE:
                return boolFalse;
            case Token.TRUE:
                return boolTrue;
            case Token.QUOTE:
                Node exp = parseExp();
                return new Cons(new Ident("'"), exp);
            case Token.INT:
                return new IntLit(tkn.getIntVal());
            case Token.STRING:
                return new StrLit(tkn.getStrVal());
            default:
                return new Ident(tkn.getName());
        }
    }

    public Node parseExp(Token tkn) throws IOException {
        int tknType = tkn.getType();
        switch (tknType) {
            case Token.LPAREN:
                return parseRest();
            case Token.FALSE:
                return boolFalse;
            case Token.TRUE:
                return boolTrue;
            case Token.QUOTE:
                Node exp = parseExp();
                return new Cons(new Ident("'"), exp);
            case Token.INT:
                return new IntLit(tkn.getIntVal());
            case Token.STRING:
                return new StrLit(tkn.getStrVal());
            default:
                return new Ident(tkn.getName());
        }
    }

    protected Node parseRest() throws IOException {
        Token tkn = scanner.getNextToken();
        int tknType = tkn.getType();

        if (tknType == Token.RPAREN) {
            return nil;
        } else {
            Node exp = parseExp(tkn);
            Token nextTkn = scanner.getNextToken();
            Node nextExp = parseExp(nextTkn);
            if (nextTkn.getType() == Token.RPAREN) {
                return new Cons(exp, nil);
            } else {
                Node rest = parseRest();
                Node cons = new Cons(nextExp, rest);
                return new Cons(exp, cons);
            }
        }
    }
}
