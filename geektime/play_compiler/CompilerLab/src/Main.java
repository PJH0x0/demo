import com.pjh.lexicalanalysis.LexicalAnalyze;
import com.pjh.utils.Token;
import com.pjh.syntacticparsing.SimpleASTNode;
import com.pjh.syntacticparsing.SimpleCaculator;
import com.pjh.utils.TokenReader;

import java.util.*;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        int len = args.length;
        if (len < 1) {
            throw new IllegalAccessException("No expression" + len);
        } else {
            char[] expression = args[0].toCharArray();
            new LexicalAnalyze().startExpressionAnalyze("age >= 45");
            TokenReader tokens = new LexicalAnalyze().startExpressionAnalyze("int age = 2 + 3 + 4+5");
            tokens.dump();
            SimpleASTNode node = new SimpleCaculator().intDeclare(tokens);
            node.dumpAST("\t");

        }
    }

}
