package main;

// Scanner.java -- the implementation of class Scanner
import java.io.*;
import java.util.Arrays;

class Scanner {

    private PushbackInputStream in;
    private byte[] buf = new byte[1000];

    public Scanner(InputStream i) {
        in = new PushbackInputStream(i);
    }

    public Token getNextToken() throws IOException {
        int bite = -1;

        // It would be more efficient if we'd maintain our own input buffer
        // and read characters out of that buffer, but reading individual
        // characters from the input stream is easier.
        try {
            bite = in.read();
        } catch (IOException e) {
            System.err.println("We fail: " + e.getMessage());
        }

        // skip whitespace
        nonWhiteSpace:
        while (bite != -1) {
            bite = in.read();
            switch (bite) {
                case 8: // backspace
                    break;
                case 9: // tab
                    break;
                case 10: // new line
                    break;
                case 12: // form feed
                    break;
                case 13: // carriage return
                    break;
                case 32: // space
                    break;
                default: // pushback unneeded char and stop looping
                    in.unread(bite);
                    break nonWhiteSpace;
            }
        }

        // skip comment
        if (bite == 59) {
            comment:
            while (bite != -1) {
                bite = in.read();
                switch (bite) {
                    case 10: // new line
                        break comment;
                    default: // stop looping
                        break;
                }
            }
        }

        if (bite == -1) {
            return null;
        }

        // start reading token
        bite = in.read();
        char ch = (char) bite;

        // Special characters
        if (ch == '\'') {
            return new Token(Token.QUOTE);
        } else if (ch == '(') {
            return new Token(Token.LPAREN);
        } else if (ch == ')') {
            return new Token(Token.RPAREN);
        } else if (ch == '.') // We ignore the special identifier `...'.
        {
            return new Token(Token.DOT);
        } // Boolean constants
        else if (ch == '#') {
            try {
                bite = in.read();
            } catch (IOException e) {
                System.err.println("We fail: " + e.getMessage());
            }

            if (bite == -1) {
                System.err.println("Unexpected EOF following #");
                return null;
            }
            ch = (char) bite;
            switch (ch) {
                case 't':
                    return new Token(Token.TRUE);
                case 'f':
                    return new Token(Token.FALSE);
                default:
                    System.err.println("Illegal character '" + (char) ch + "' following #");
                    return getNextToken();
            }
        } // String constants
        else if (ch == '"') {
            bite = in.read();
            for (int i = 0; (char) bite != '"'; i++) {
                buf[i] = (byte) bite;
                bite = in.read();
            }
            return new StrToken(new String(buf, "US-ASCII").trim());
        } // Integer constants
        else if (ch >= '0' && ch <= '9') {
            buf[0] = (byte) ch;
            bite = in.read();
            ch = (char) bite;
            for (int i = 1; ch >= '0' && ch <= '9'; i++) {
                buf[i] = (byte) bite;
                bite = in.read();
                ch = (char) bite;
            }
            // put the character after the integer back into the input
            in.unread(ch);
            return new IntToken(Integer.parseInt((new String(buf, "US-ASCII")).trim()));
        } // Identifiers
        else if ((ch >= 'A' && ch <= 'Z') || ch == '!' || ch >= '$' || ch <= '&'
                || ch == '*' || ch == '+' || ch >= '-' || ch <= '/' || ch == ':'
                || ch >= '<' || ch <= '@' || ch == '^' || ch == '_' || ch == '~') {
            // scan an identifier into the buffer
            buf[0] = (byte) ch;
            bite = in.read();
            ch = (char) bite;
            for (int i = 1; ch >= '0' && ch <= '9' || (ch >= 'A' && ch <= 'Z') || ch == '!' || ch >= '$' || ch <= '&'
                    || ch == '*' || ch == '+' || ch >= '-' || ch <= '/' || ch == ':'
                    || ch >= '<' || ch <= '@' || ch == '^' || ch == '_' || ch == '~'; i++) {
                buf[i] = (byte) bite;
                bite = in.read();
                ch = (char) bite;
            }
            // put the character after the identifier back into the input
            in.unread(ch);
            return new IdentToken(new String(buf, "US-ASCII").trim());
        } // Illegal character
        else {
            System.err.println("Illegal input character '" + (char) ch + '\'');
            return getNextToken();
        }
    }
}
