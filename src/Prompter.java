import java.util.Scanner;

public class Prompter {

    private Scanner mScanner;
    public static final boolean isDebugMode = false;

    public Prompter(){
        mScanner    = new Scanner(System.in);
    }

    public void printWelcome(){
        printHeader("**    Welcome to the wonderful game of \"Guess How Many Are In The Jar\"!\n", "*", 80);
    }

    public String promptForString(String txt) {
        String outString = "";
        while (outString.isEmpty()){
            System.out.print(txt);
            outString = mScanner.nextLine();
        }
        return outString;
    }

    public int promptForMaxNumberPerJar(String itemType){
        System.out.printf("Good! Please enter the maximum number of %s-items that can fit in the jar: ", itemType);
        String strAmountPerJar = mScanner.nextLine();
        while (!Game.isInteger(strAmountPerJar)) {
            System.out.printf("Your input '%s' is not a number, please enter a maximum number: ", strAmountPerJar);
            strAmountPerJar = mScanner.nextLine();
        }
        return Integer.parseInt(strAmountPerJar);
    }

    public String guessAmount(Jar mJar)
    {
        System.out.printf("How many %s do you think are in the Jar?: ", mJar.getItemType());
        return mScanner.nextLine();
    }

    public void inputIsNotNumber(String strGuess)
    {
        System.out.printf("The input must be a number, but your input '%s' is not. ", strGuess);
    }

    public void printCorrectGuess(int numGuessed, int amountOfTries, Jar mJar){
        System.out.printf("Yay, %d is the CORRECT guess!! You've needed just %d tries (%d wrong guesses).\n", numGuessed, amountOfTries, amountOfTries-1);
    }

    public void printWrongGuessTooSmall(int numGuessed) {
        System.out.printf("Sorry, your guess %d is too small. ", numGuessed);
    }

    public void printWrongGuessTooBig(int numGuessed) {
        System.out.printf("Sorry, your guess %d is too big. ", numGuessed);
    }

    public void printExtraCredit(String oldHighScoreName, int oldHighScoreScore, int newHighScore){
        String repString = "*!";
        int nTimes = 40;

        System.out.println("");
        printHeader(repString + "    NEW HIGH SCORE !!\n", repString, nTimes);
        System.out.println(repString);
        if (oldHighScoreScore != 1000000000) {
            System.out.printf(repString + "    Old High Score: %d  (by %s)\n", oldHighScoreScore, oldHighScoreName);
        }
        System.out.printf(repString + "    New High Score: %d !!\n", newHighScore);
        System.out.println(repString);
        printStringNTimesAndNewLine(repString, nTimes);
        System.out.println("");
    }

    public void printNumberOfTriesThatWereNeededThisRound(int amountOfTries, Jar mJar) {
        System.out.printf("Congrats, you've needed %d guesses (%d)", amountOfTries, amountOfTries/mJar.getMaxAmountPerJar());
    }

    public void printDebugInfo_currentNumberOfItemsInJar(Jar mJar){
        if (isDebugMode) {
            // Programmer/admin-info => print only if isDebugMode-Flag is set
            System.out.printf("Programmer Debug-Info: currentNumberOfItemsInJar: %d\n", mJar.getCurrentAmountInJar());
        }
    }

    public void printGuessedOverMax(Jar mJar, int numGuessed) {
        System.out.printf("%d is over the maximum of %d that can fit in the %s-Jar. You're lucky, this won't count as a guess! Be careful next time!\n", numGuessed, mJar.getMaxAmountPerJar(), mJar.getItemType());
    }

    public void printMaxNumberForItem(Jar mJar){
        System.out.printf("Here some help for you: %d is the maximum number of %s that fit in the jar!\n", mJar.getMaxAmountPerJar(), mJar.getItemType());
    }

    public void printLetsStartNow(){
        printStringNTimesAndNewLine("*", 80);
        System.out.println("Now let's start the real game!\n");
    }

    public void printHeader(String headerText, String headerStringDecoration, int nTimes){
        printStringNTimesAndNewLine(headerStringDecoration, nTimes);
        //printStringNTimes(headerStringDecoration, 2);
        System.out.printf(headerText);
        //printStringNTimes(headerStringDecoration, 2);
        printStringNTimesAndNewLine(headerStringDecoration, nTimes);
    }

    public void printStringNTimesAndNewLine(String stringToRepeat, int nTimes){
        printStringNTimes(stringToRepeat, nTimes);
        System.out.printf("\n");
    }

    public void printStringNTimes(String stringToRepeat, int nTimes){
        System.out.printf(new String(new char[nTimes]).replace("\0", stringToRepeat));
    }
}