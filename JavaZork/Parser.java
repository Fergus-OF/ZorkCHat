import java.util.Scanner;
/*
public class Parser {
    private CommandWords commands;
    private Scanner reader;

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand() {
        System.out.print("> ");
        String inputLine = reader.nextLine();

        String word1 = null;
        String word2 = null;

        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        if (commands.isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return new Command(null, word2);
        }
    }

    public void showCommands() {
        commands.showAll();
    }
}
*/


public class Parser {
    private CommandWords commands = new CommandWords();

    public Command getCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        String input = sc.nextLine().toLowerCase();

        String[] words = input.split("\\s+");

        String word1 = words.length > 0 ? words[0] : null;
        String word2 = words.length > 1 ? words[1] : null;
        Commands type = commands.getCommands(word1);
        return new Command(type, word2);
    }

    public void showCommands() {
        commands.showAll();
    }
}

