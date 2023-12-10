package filesprocessing.filter;

public abstract class Type implements Filter {
    private static final String YES = "YES";
    private static final String NO = "NO";
    protected String ans;

    Type(String ans) throws IllegalTypeArgumentException {
        if (!isLegalAnswer(ans))
            throw new IllegalTypeArgumentException();
        this.ans = ans;
    }

    private boolean isLegalAnswer(String ans){
        return ans.equals(YES) || ans.equals(NO);
    }

    protected boolean isYes(){
        return this.ans.equals(YES);
    }

    public abstract boolean isPass(java.io.File f);
}
