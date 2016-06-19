public class Game {

    private Jar mJar;
    private Prompter mPrompter;
    private String mHighScoreName;
    private int mHighScoreScore;

    public static void main(String[] args) {

        // score could be loaded here from a file if needed
        int highScoreScore = 1000000000;
        String highScoreName  = "";

        Game game = new Game(highScoreScore, highScoreName);
        game.startJarGame();

    }

    public Game(int highScoreScore, String highScoreName) {
        mPrompter = new Prompter();
        mHighScoreScore = highScoreScore;
        mHighScoreName  = highScoreName;

    }

    public void startJarGame(){

        mPrompter.printWelcome();

        adminGameSetup();
        mPrompter.printLetsStartNow();

        boolean continuePlaying = true;
        while (continuePlaying) {
            playOneGame();
            // continuePlaying = mPrompter.continuePlayingOrNot(); // todo-importance:Unimportant: quit the game with "q"
            mPrompter.printStringNTimesAndNewLine("-", 80);
        }
        //System.out.println("\n\nThanks for having played the Game! We hope you had fun!"); // activate only if continuePlayingOrNot() is implemented
    }


    public void playOneGame(){

        mJar.setCurrentAmountInJar();

        mPrompter.printDebugInfo_currentNumberOfItemsInJar(mJar);
        mPrompter.printMaxNumberForItem(mJar);

        boolean isGuessed = false;
        int amountOfTries = 0;
        while(!isGuessed) {
            amountOfTries++;
            isGuessed = doOneGuess(amountOfTries);
        }
        ////mPrompter.printNumberOfTriesThatWereNeededThisRound(amountOfTries, mJar);
    }

    public boolean doOneGuess(int amountOfTries) {

        String strGuess = mPrompter.guessAmount(mJar);
        int numGuessed = checkAndConvertGuessToInt(strGuess);
        while (numGuessed > mJar.getMaxAmountPerJar()) {
            mPrompter.printGuessedOverMax(mJar, numGuessed);
            strGuess = mPrompter.guessAmount(mJar);
            numGuessed = checkAndConvertGuessToInt(strGuess);
        }
        if (numGuessed == mJar.getCurrentAmountInJar()) {
            mPrompter.printCorrectGuess(numGuessed, amountOfTries, mJar);
            if (amountOfTries < mHighScoreScore){
                // User made a new High Score!
                mPrompter.printExtraCredit(mHighScoreName, mHighScoreScore, amountOfTries);
                mHighScoreScore = amountOfTries;
                mHighScoreName  = mPrompter.promptForString("Enter your name to save your new High Score: ");
            }
            return true;
        } else if (numGuessed < mJar.getCurrentAmountInJar()) {
            mPrompter.printWrongGuessTooSmall(numGuessed);
            return false;
        } else {
            mPrompter.printWrongGuessTooBig(numGuessed);
            return false;
        }
    }

    public int checkAndConvertGuessToInt(String strGuess) {
        while (!isInteger(strGuess)) {
            mPrompter.inputIsNotNumber(strGuess);
            strGuess = mPrompter.guessAmount(mJar);
        }
        return Integer.parseInt(strGuess);
    }

    public void adminGameSetup(){

        String itemType     = mPrompter.promptForString("Please specify what type of item will be stored in the jar: ");
        int numAmountPerJar = mPrompter.promptForMaxNumberPerJar(itemType);

        mJar  = new Jar(itemType, numAmountPerJar);
    }

    public static boolean isInteger(String str)
    {
        try
        {
            Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
