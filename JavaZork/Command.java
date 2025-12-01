public class Command {
    private Commands commandWord;
    private String secondWord;

    public Command(Commands firstWord, String secondWord) {
        this.commandWord = firstWord;
        this.secondWord = secondWord;
    }

    public Commands getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public boolean isUnknown() {
        return commandWord == null;
    }

    public boolean hasSecondWord() {
        return secondWord != null;
    }
}
