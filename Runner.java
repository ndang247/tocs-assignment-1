public class Runner {
    public static void main(String[] args) {
        try {
            // System.out.println(LexicalAnalyser.analyse("1+2*3"));
            // System.out.println(LexicalAnalyser.analyse("311"));
            // System.out.println(LexicalAnalyser.analyse("0."));
            // System.out.println(LexicalAnalyser.analyse("0.51"));
            // System.out.println(LexicalAnalyser.analyse("22 /"));
            // System.out.println(LexicalAnalyser.analyse(" "));
            // System.out.println(LexicalAnalyser.analyse(" 1 + 2"));
            // System.out.println(LexicalAnalyser.analyse(" 1 + "));
            System.out.println(LexicalAnalyser.analyse(" 34"));
        } catch (NumberException e) {
            System.out.println(e.getMessage());
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
        }
    }
}